package dijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph <T extends Comparable<T>> {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private HashMap<String,Vertex>  vertexMap;
	
	public Graph(int startVertex) {
		// Tack förslaget Jonas H-H
	}
	public void readVertexFile(String filename) {
		
	}
	public void readEdgeFile(String filename){
		
	}
	public ArrayList<Vertex> getVertices(){
		return vertexList;
	}
	public ArrayList<Edge> getEdges(){
		return edgeList;
	}
	public void findShortestPath(int start, int destination){
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		for(Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
	        if(vertex != vertexList.get(start)) {
	        	vertex.setDistance(9999999);
	        }else {
	        	 vertex.setDistance(0);
		         prioQ.insert(vertex, vertex.getDistance());
	        }
		}
		while(!prioQ.isEmpty()) {
			  Vertex currentVertex = (Vertex) prioQ.extract();
			  for(Edge edge : currentVertex.getEdges()) {
				  Vertex proposedVertex = edge.getToVertex();
						  if (proposedVertex.getDistance() > (currentVertex.getDistance() + edge.getWeight())) {
							  proposedVertex.setDistance(currentVertex.getDistance() + edge.getWeight());
							  proposedVertex.setPredecessor(currentVertex);
				              prioQ.update(proposedVertex, proposedVertex.getDistance());
						  }
			            
			  }
		}
	    /*priorityQ prioQ = PriorityQ()
	    foreach vertex in G.getVertices()
	        vertex.setPredecessor(null)
	        if vertex != start_vertex
	            vertex.setDistance(största möjliga talvärde)
	        else
	            vertex.setDistance(0) # avståndet till start är ju 0
	            prioQ.insert(vertex, vertex.getDistance())

	    while ! prioQ.empty()
	        currentV = prioQ.extract()
	        foreach edge in currentV.getEdges()
	            proposedV = edge.getVertex()
	            if proposedV.getDistance() > currentV.getDistance() + edge.getWeight()
	                proposedV.setDistance(currentV.getDistance() + edge.getWeight())
	                proposedV.setPredecessor(currentV)
	                prioQ.update(proposedV, proposedV.getDistance())*/
	}
}
