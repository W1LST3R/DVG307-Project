package dijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import graph_pack.IGraph;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph <T extends Comparable<T>> implements IGraph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	private HashMap<String,Vertex> vertexMap;
	private Vertex startVertex;
	private Vertex targetVertex;
	public Graph() {		
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		vertexMap = new HashMap<>();
	}
	public Graph(Vertex start) {
		startVertex = start;
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		vertexMap = new HashMap<>();
	}
	public void readVertexFile(String filename) {

		Scanner scanLine, scanRow = null;

		try {
			scanLine = new Scanner(new File(filename));
			scanLine.useDelimiter("[\r\n]"); // sätt delimiter på ny rad
			scanLine.nextLine(); // hoppa över första raden
			
			while(scanLine.hasNextLine()) {
				String row = scanLine.next();
				scanRow = new Scanner(row);
				scanRow.useDelimiter(";");
				while(scanRow.hasNext()) {
					String str = scanRow.next();
					String str2 = scanRow.next();
					String str3 = scanRow.next();
					String str4 = scanRow.next();
					System.out.println(str4);
					addVertex(
							str,
							//string =scanRow.next(), // name/id
							Double.parseDouble(str2), // population
							Double.parseDouble(str3.replace(",", ".")), //longitude
							Double.parseDouble(str4.replace(",", ".")) // latitude
							);
					
				}
			}
			
			scanLine.close();
			scanRow.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readEdgeFile(String filename){
		
		Scanner scanLine, scanRow = null;

		try {
			scanLine = new Scanner(new File(filename));
			scanLine.useDelimiter("[\r\n]"); // sätt delimiter på ny rad
			scanLine.nextLine(); // hoppa över första raden
			
			while(scanLine.hasNextLine()) {
				scanRow = new Scanner(scanLine.next());
				scanRow.useDelimiter(";");
				while(scanRow.hasNext()) {
					connectVertices(
							scanRow.next(), // fromVertex
							scanRow.next(), // toVertex
							Double.parseDouble(scanRow.next().replace(",", ".")) // distance in meters
							);
				}
			}
			
			scanLine.close();
			scanRow.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Vertex> getVertices(){
		return vertexList;
	}
	public ArrayList<Edge> getEdges(){
		return edgeList;
	}
	
	@Override
	public void addVertex(String id, double population, double longitude, double latitude) {
		Vertex tempVertex = new Vertex(id, population, longitude, latitude);
		vertexList.add(tempVertex);
		vertexMap.put(id, tempVertex);
	}
	@Override
	public void connectVertices(String id1, String id2, double weight) {
		Edge tempEdge = new Edge(vertexMap.get(id1), vertexMap.get(id2), weight);
		Edge tempEdge2 = new Edge(vertexMap.get(id2), vertexMap.get(id1), weight);
		edgeList.add(tempEdge);
		vertexMap.get(id1).addEdge(tempEdge);
		vertexMap.get(id2).addEdge(tempEdge2);
		
	}
	@Override
	public void setStartVertex(Vertex start) {
		startVertex = start;
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
		startVertex = null;
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			vertex.setDistance(0);
		}
		
	}
	public void superClear() {
		targetVertex = null;
		startVertex = null;
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		vertexMap = new HashMap<>();
	}
	@Override
	public void findShortestPath(String start_id) {
		// TODO Auto-generated method stub
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			if (!vertex.getName().equals(start_id)) {
				vertex.setDistance(9999999);
			} else {
				vertex.setDistance(0);
				prioQ.insert(vertex, vertex.getDistance());
			}
		}
		while (!prioQ.isEmpty()) {
			Vertex currentVertex = (Vertex) prioQ.extract();
			for (Edge edge : currentVertex.getEdges()) {
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
		System.out.println("Executing Dijkstras Algoritm: from " + getStartVertex() + " to " + getTargetVertex());
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			if (!vertex.getName().equals(start_vertex.getName())) {
				vertex.setDistance(99999999);
			} else {
				vertex.setDistance(0);
				prioQ.insert(vertex, vertex.getDistance());
			}
		}

		while (!prioQ.isEmpty()) {
			String temp = prioQ.extract().toString();
			Vertex currentVertex = vertexMap.get(temp);
			for (Edge edge : currentVertex.getEdges()) {
				Vertex proposedVertex = edge.getToVertex();
				if (proposedVertex.getDistance() > (currentVertex.getDistance() + edge.getWeight())) {
					proposedVertex.setDistance(currentVertex.getDistance() + edge.getWeight());
					proposedVertex.setPredecessor(currentVertex);
					prioQ.update(proposedVertex, proposedVertex.getDistance());
				}
			}
		}
	}
}
