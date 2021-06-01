package data_structures.WeightedGraph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Node<V> implements Serializable {

    private V value;

    private List<Edge<?>> edges;

    private boolean visited;

    private int indexMatrix;
    
    private String type;

    public String getType() {
		return type;
	}
    //constructor
    public Node(V value, String type) {
        this.value = value;
        edges = new ArrayList<Edge<?>>();
       this.type = type;
    }

	public void setType(String type) {
		this.type = type;
	}

	public int getIndexMatrix() {
        return indexMatrix;
    }

    public void setIndexMatrix(int indexMatrix) {
        this.indexMatrix = indexMatrix;
    }

   

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public List<Edge<?>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<?>> edges) {
        this.edges = edges;
    }

    public boolean addEdge(Edge<V> edge) {
        return edges.add(edge);
    }

    public List<Node<?>> dfs() {

        List<Node<?>> aN = new ArrayList<Node<?>>();
        visited = true;

        aN.add(this);

        for (int i = 0; i < edges.size(); i++) {

            if (!edges.get(i).getDestination().visited) {
                aN.addAll(edges.get(i).getDestination().dfs());
            }

        }

        return aN;

    }

    public List<Node<?>> bfs() {

        List<Node<?>> aN = new ArrayList<Node<?>>();
        visited = true;

        for (int i = 0; i < edges.size(); i++) {

            if (!edges.get(i).getDestination().visited) {
                aN.addAll(edges.get(i).getDestination().bfs());
            }

        }
        aN.add(this);

        return aN;

    }

    public List<Node<?>> getAdjNodes() {

        List<Node<?>> aN = new ArrayList<Node<?>>();

        for (int i = 0; i < edges.size(); i++) {

            aN.add(edges.get(i).getDestination());

        }

        return aN;

    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}