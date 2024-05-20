package dijkstrasAlgorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		Graph test = new Graph();
		test.readVertexFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\760_tatorter.csv");
		ArrayList<Vertex> ar = test.getVertices();
		System.out.println("size: " + ar.size());
		for(Vertex elem : ar) System.out.println(elem.getName() + ", " + elem.getPopulation() + ", " + elem.getLongitude() + ", " + elem.getLatitude());
		
		test.readEdgeFile("H:\\git\\DVG307-Project\\ProjektDVG307\\src\\edges_760_tatorter.csv");
		ArrayList<Edge> ar2 = test.getEdges();
		System.out.println("size: " + ar2.size());
		for(Edge elem : ar2) System.out.println(elem.getFromVertex().getName() + ", " + elem.getToVertex().getName() + ", " + elem.getWeight());
	}

}
