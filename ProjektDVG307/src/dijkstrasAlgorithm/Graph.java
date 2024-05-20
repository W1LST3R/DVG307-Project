package dijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import graph_pack.IGraph;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import graph_pack.IGraph;

public class Graph <T extends Comparable<T>> implements IGraph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	private HashMap<String,Vertex> vertexMap;
	private Vertex startVertex;
	private Vertex targetVertex;

	public Graph(Vertex start) {
		startVertex = start;
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		vertexMap = new HashMap<>();
	}
	public void readVertexFile(String filename) {

		Scanner scanner;
		try {
			scanner = new Scanner(new File(filename));
			scanner.nextLine(); // hoppa över första raden
			scanner.useDelimiter(";");
			while(scanner.hasNext()) {
				addVertex(
						scanner.next(), // name/id
						Double.parseDouble(scanner.next()), // population
						Double.parseDouble(scanner.next()), //longitude
						Double.parseDouble(scanner.next()) // latitude
						);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readEdgeFile(String filename){
		
	}
	public ArrayList<Vertex> getVertices(){
		return vertexList;
	}
	public ArrayList<Edge> getEdges(){
		return edgeList;
	}
	
	@Override
	public void addVertex(String id, double population, double longitude, double latitude) {
		Vertex tempVertex = new Vertex(id, longitude, latitude, population);
		vertexList.add(tempVertex);
		vertexMap.put(id, tempVertex);
	}
	@Override
	public void connectVertices(String id1, String id2, double weight) {
		Edge tempEdge = new Edge(vertexMap.get(id1), vertexMap.get(id1), weight);
		edgeList.add(tempEdge);
		vertexMap.get(id1).addEdge(tempEdge);
		vertexMap.get(id2).addEdge(tempEdge);
		
	}
	@Override
	public Vertex getStartVertex() {
		return startVertex;
	}
	@Override
	public void setTargetVertex(Vertex target) {
		targetVertex = target;
	}
	@Override
	public Vertex getTargetVertex() {
		return targetVertex;
	}
	@Override
	public void clear() {
		targetVertex = null;
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
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		for(Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
	        if(vertex != start_vertex) {
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
	public void addVertex(String id, double population, double longitude, double latitude) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void connectVertices(String id1, String id2, double weight) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vertex getStartVertex() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setTargetVertex(Vertex target) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Vertex getTargetVertex() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void findShortestPath(String start_id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void findShortestPath(Vertex start_vertex) {
		// TODO Auto-generated method stub
		
	}
}
