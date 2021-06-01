package data_structures.WeightedGraph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Owner;

class GraphTest {

	private Graph<Owner> miGrafo;
	private Owner a, b, c, d, e;
	
	public void setUp1() {
		
		miGrafo = new Graph<Owner>();
		
		a = new Owner("bryan", "1151", "TI", "3015079361", "bryangf0822@gmail.com");
		b = new Owner("paola", "1152", "CC", "3005071939", "paosorio05232@gmail.com");
		c = new Owner("jhon", "1153", "PT", "3158795543", "MrPresidentJFK@gmail.com");
		d = new Owner("sean", "1154", "CC", "3056934466", "seanGUI_CRACK@gmail.com");
		e = new Owner("aristi", "1155", "TI", "3218499985", "aristiTuPapa_Crack@gmail.com");
		

        miGrafo.addNode(a, "R");
        miGrafo.addNode(b, "P");
        miGrafo.addNode(c, "T");
        miGrafo.addNode(d, "T");
        miGrafo.addNode(e, "T");
        
        miGrafo.addConnection(a.hashCode(), d.hashCode(), 3);

        miGrafo.addConnection(a.hashCode(), c.hashCode(), 20);

        miGrafo.addConnection(d.hashCode(), c.hashCode(), 2);

        miGrafo.addConnection(c.hashCode(), e.hashCode(), 3);

        miGrafo.addConnection(a.hashCode(), b.hashCode(), 3);
	}
	
	 @Test
	    void dfsTest1() {
	        setUp1();
	        String expected = "bryanseanjhonaristipaola";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.dfs(a.hashCode());
	        for (Node<?> node: temp
	             ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }
	 
	 @Test
	    void dfsTest2() {
	        setUp1();
	        String expected = "paolabryanseanjhonaristi";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.dfs(b.hashCode());
	        for (Node<?> node: temp
	        ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }
	 
	 @Test
	    void dfsTest3() {
	        setUp1();
	        String expected = "jhonbryanseanpaolaaristi";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.dfs(c.hashCode());
	        for (Node<?> node: temp
	        ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }


	    @Test
	    void bfsTest1() {
	        setUp1();
	        String expected = "bryanpaolaseanjhonaristi";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.bfs(a.hashCode());
	        for (Node<?> node: temp
	        ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }
	    
	    @Test
	    void bfsTest2() {
	        setUp1();
	        String expected = "paolabryanseanjhonaristi";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.bfs(b.hashCode());
	        for (Node<?> node: temp
	        ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }

	    @Test
	    void bfsTest3() {
	        setUp1();
	        String expected = "aristijhonbryanpaolasean";
	        String actual = "";
	        List<Node<?>> temp = miGrafo.bfs(e.hashCode());
	        for (Node<?> node: temp
	        ) {
	            actual += node.getValue().toString();
	        }
	        assertEquals(expected, actual);
	    }
	    
	    @Test
	    void addNodeTest1() {
	    	setUp1();
	    	Owner aux = new Owner("bryan", "1151", "TI", "3015079361", "bryangf0822@gmail.com");
	    	miGrafo.addNode(aux, "T");
	    	assertEquals(aux.hashCode(), miGrafo.getNodes().get(aux.hashCode()).getValue().hashCode());
	    }

	    @Test
	    void addNodeTest2() {
	     	setUp1();
	     	Owner aux = new Owner("paola", "1152", "CC", "3005071939", "paosorio05232@gmail.com");
	    	miGrafo.addNode(aux, "T");
	    	assertEquals(aux.hashCode(), miGrafo.getNodes().get(aux.hashCode()).getValue().hashCode());
	    }

	    @Test
	    void deleteNodeTest1() {
	    	setUp1();
	        assertTrue(miGrafo.removeNode(a.hashCode()));
	    }

	    @Test
	    void deleteNodeTest2() {
	    	setUp1();
	        assertTrue(miGrafo.removeNode(b.hashCode()));
	    }

	    @Test
	    void searchNodeTest1() {
	    	setUp1();
	    	assertEquals(a, miGrafo.searchNode(a.hashCode()).getValue());
	    }

	    @Test
	    void searchNodeTest2() {
	    	setUp1();
	    	assertEquals(b, miGrafo.searchNode(b.hashCode()).getValue());
	    }

	    @Test
	    void searchNodeTest3() {
	    	setUp1();
	    	assertNull(miGrafo.searchNode(123));
	    }
}
