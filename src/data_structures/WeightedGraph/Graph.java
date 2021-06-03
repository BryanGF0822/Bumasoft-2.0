package data_structures.WeightedGraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Graph<V> implements Serializable {

	private Map<Integer, Node<V>> adjList;
	private int nV;
	private ArrayList<Node<?>> nodesList;

	private int numEdges;
	public int[][] adjMatrix;

	public Graph() {

		adjList = new HashMap<Integer, Node<V>>();
		numEdges = 0;
		nV = 0;

		adjMatrix = new int[nV][nV];
		
		nodesList = new ArrayList<Node<?>>();
	}
	
	public ArrayList<Node<?>> getNodesList() {
		return nodesList;
	}
	
	public void setNodeList(ArrayList<Node<?>> nodesList) {
		this.nodesList = nodesList;
	}

	
	//Lista de aristas
	public Map<Integer, Node<V>> getNodes() {
		return adjList;
	}

	public void setNodes(Map<Integer, Node<V>> nodes) {
		this.adjList = nodes;
	}

	public void addNode(V value, String type) {

		int[][] newMatrix = new int[adjMatrix.length + 1][adjMatrix.length + 1];

		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				newMatrix[i][j] = adjMatrix[i][j];
			}

		}

		adjMatrix = newMatrix;

		adjList.put(value.hashCode(), new Node<V>(value, type));

		nV = nV + 1;

		adjList.get(value.hashCode()).setIndexMatrix(nV - 1);
	}

	public void addConnection(Integer hashCodeOrigin, Integer hashCodeDestination, int weight) {
		Node<V> origin = adjList.get(hashCodeOrigin), destination = adjList.get(hashCodeDestination);
		origin.addEdge(new Edge<V>(origin, destination, weight));
		destination.addEdge(new Edge<V>(destination, origin, weight));

		adjMatrix[origin.getIndexMatrix()][destination.getIndexMatrix()] = weight;
		adjMatrix[destination.getIndexMatrix()][origin.getIndexMatrix()] = weight;

	}

	public List<Node<?>> bfs(int hashCodeSource) {

		List<Node<?>> list = adjList.get(hashCodeSource).bfs();
		Collections.reverse(list);
		List<Node<?>> temp = new ArrayList<Node<?>>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}

		return temp;

	}

	public List<Node<?>> dfs(int source) {
		List<Node<?>> list = adjList.get(source).dfs();

		return list;

	}

	public List<Node<?>> getAdjNodes(int source) {

		List<Node<?>> list = adjList.get(source).getAdjNodes();

		List<Node<?>> temp = new ArrayList<Node<?>>();

		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		return temp;

	}

	public List<Node<?>> dijkstraForAdjaMatrix(Integer hashCodeOrigin, Integer hashCodeDestination) {
		DijkstraAlgorithmForAdjacencyMatrix myDijkstra = new DijkstraAlgorithmForAdjacencyMatrix();
		myDijkstra.dijkstra(adjMatrix, adjList.get(hashCodeOrigin).getIndexMatrix(),
				adjList.get(hashCodeDestination).getIndexMatrix(), nV);
		List<Integer> myIndex = myDijkstra.getShortesPath();
		List<Node<?>> temp = new ArrayList<>();
		temp.addAll(adjList.values());
		List<Node<?>> path = new ArrayList<>();
		for (int i = 0; i < myIndex.size(); i++) {
			for (int j = 0; j < temp.size(); j++) {
				if (temp.get(j).getIndexMatrix() == myIndex.get(i)) {
					path.add(temp.get(j));
					j = temp.size();
				}
			}
		}
		List<Node<?>> valuesPath = new ArrayList<Node<?>>();

		for (int i = 0; i < path.size(); i++) {
			valuesPath.add(path.get(i));
		}

		return valuesPath;
	}

	public List<V> dijkstraForAdjaList(Integer hashCodeOrigin, Integer hashCodeDestination) {

		List<Node<V>> nodes = new ArrayList<>(adjList.values());

		@SuppressWarnings("rawtypes")
		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < nodes.size(); i++) {
			edges.addAll(nodes.get(i).getEdges());
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		DijkstraAlgorithmForAdjacencyList<V> dijkstra = new DijkstraAlgorithmForAdjacencyList(edges);

		dijkstra.execute(adjList.get(hashCodeOrigin));

		LinkedList<Node<V>> path = dijkstra.getPath(adjList.get(hashCodeDestination));

		List<Node<V>> l = new ArrayList<>();
		l.addAll(path);

		List<V> valuesPath = new ArrayList<V>();

		for (int i = 0; i < path.size(); i++) {
			valuesPath.add(path.get(i).getValue());
		}

		return valuesPath;

	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

	public boolean removeNode(Integer hashCode) {
		boolean aux = false;
		Node<V> tmp = adjList.remove(hashCode);
		if (tmp == null) {
			return aux;
		} else {
			for (int i = 0; i < adjMatrix.length; i++) {
				adjMatrix[i][tmp.getIndexMatrix()] = 0;
				adjMatrix[tmp.getIndexMatrix()][i] = 0;
			}
			return !aux;
		}
	}

	public Node<V> searchNode(Integer hashCode) {
		return adjList.get(hashCode);
	}

}