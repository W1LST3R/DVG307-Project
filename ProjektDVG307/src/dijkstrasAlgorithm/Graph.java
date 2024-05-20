package dijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

import graph_pack.IGraph;

public class Graph <T extends Comparable<T>> implements IGraph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private HashMap<String,Vertex>  vertexMap;
	
	public Graph(Vertex startVertex) {
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
	@Override
	public void addVertex(String id, double population, double longitude, double latitude) {
		Vertex tempVertex = new Vertex(id, longitude, latitude, population);
		vertexList.add(tempVertex);
		vertexMap.put(id, tempVertex);
	}
	@Override
	public void connectVertices(String id1, String id2, double weight) {
		// TODO Auto-generated method stub
		Edge tempEdge = new Edge(vertexMap.get(id1), vertexMap.get(id1), weight);
		edgeList.add(tempEdge);
		vertexMap.get(id1).addEdge(tempEdge);
		
	}
	@Override
	public Vertex getStartVertex() {
		return null;
	}
	@Override
	public void setTargetVertex(Vertex target) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vertex getTargetVertex() {
		return null;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void findShortestPath(String start_id) {
		// TODO Auto-generated method stub
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		for(Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
	        if(vertex != vertexMap.get(start_id)) {
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
	}
	@Override
	public void findShortestPath(Vertex start_vertex) {
		// TODO Auto-generated method stub
		
	}
}
