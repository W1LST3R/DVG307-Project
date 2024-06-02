package dijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import graph_pack.IGraph;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph<T extends Comparable<T>> implements IGraph {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	private HashMap<String, Vertex> vertexMap;
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

	// reads from a file with vertexes and adds the data correctly
	public void readVertexFile(String filename) {

		Scanner scanLine, scanRow = null;

		try {
			scanLine = new Scanner(new File(filename));
			scanLine.useDelimiter("[\r\n]"); // set delimiter for new row
			scanLine.nextLine(); // skip first line

			while (scanLine.hasNextLine()) {
				String row = scanLine.next();
				scanRow = new Scanner(row);
				scanRow.useDelimiter(";");
				while (scanRow.hasNext()) {
					String name = scanRow.next();
					String bef = scanRow.next();
					String lon = scanRow.next();
					String lat = scanRow.next();
					if (lat.charAt(lat.length() - 1) == '"') {
						lat = lat.substring(0, lat.length() - 1);
					}
					addVertex(name, // name/id
							Double.parseDouble(bef), // population
							Double.parseDouble(lon.replace(",", ".")), // longitude
							Double.parseDouble(lat.replace(",", ".")) // latitude
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

	// reads from a file with edges and adds the data correctly
	public void readEdgeFile(String filename) {

		Scanner scanLine, scanRow = null;

		try {
			scanLine = new Scanner(new File(filename));
			scanLine.useDelimiter("[\r\n]"); // set delimiter for new row
			scanLine.nextLine(); // skip first line

			while (scanLine.hasNextLine()) {
				scanRow = new Scanner(scanLine.next());
				scanRow.useDelimiter(";");
				while (scanRow.hasNext()) {
					connectVertices(scanRow.next(), // fromVertex
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

	public ArrayList<Vertex> getVertices() {
		return vertexList;
	}

	public ArrayList<Edge> getEdges() {
		return edgeList;
	}

	// Makes a new vertex
	@Override
	public void addVertex(String id, double population, double longitude, double latitude) {
		Vertex tempVertex = new Vertex(id, population, longitude, latitude);
		vertexList.add(tempVertex);
		vertexMap.put(id, tempVertex);
	}

	// Makes new edges
	@Override
	public void connectVertices(String id1, String id2, double weight) {
		Edge tempEdge = new Edge(vertexMap.get(id1), vertexMap.get(id2), weight);
		Edge tempEdge2 = new Edge(vertexMap.get(id2), vertexMap.get(id1), weight);
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

	// clears data for Dijkstra
	@Override
	public void clear() {
		targetVertex = null;
		startVertex = null;
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			vertex.setDistance(0);
		}

	}

	// clears all data for Dijkstra
	public void superClear() {
		targetVertex = null;
		startVertex = null;
		vertexList = new ArrayList<>();
		edgeList = new ArrayList<>();
		vertexMap = new HashMap<>();
	}

	// Dijkstra's algorithm
	@Override
	public void findShortestPath(String start_id) {
		// System.out.println("Executing Dijkstra's Algorithm: from " + getStartVertex() + " to " + getTargetVertex());
		// makes a queue
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		// loops threw all vertex
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			// if not start vertex set weight too something high
			if (!vertex.getName().equals(start_id)) {
				vertex.setDistance(9999999);
				// else set start vertex weight to 0 and add it to the queue
			} else {
				vertex.setDistance(0);
				prioQ.insert(vertex, vertex.getDistance());
			}
		}
		// while the queue is not empty
		while (!prioQ.isEmpty()) {
			// takes out the first vertex
			Vertex currentVertex = (Vertex) prioQ.extract();
			// loops threw all edges to the current vertex
			for (Edge edge : currentVertex.getEdges()) {
				// gets the vertex to where the edge is leading
				Vertex proposedVertex = edge.getToVertex();
				// if the proposedVertex weight is more than current vertex weight + edge weight.
				// Set distance for proposedVertex to current vertex weight + edge weight and predecessor for proposedVertex to current vertex
				// update queue with proposedVertex
				if (proposedVertex.getDistance() > (currentVertex.getDistance() + edge.getWeight())) {
					proposedVertex.setDistance(currentVertex.getDistance() + edge.getWeight());
					proposedVertex.setPredecessor(currentVertex);
					prioQ.update(proposedVertex, proposedVertex.getDistance());
				}
			}
		}
	}

	// Dijkstra's algorithm
	@Override
	public void findShortestPath(Vertex start_vertex) {
		// System.out.println("Executing Dijkstra's Algorithm: from " + getStartVertex() + " to " + getTargetVertex());
		// makes a queue
		PriorityQ prioQ = new PriorityQ(vertexList.size());
		// loops threw all vertex
		for (Vertex vertex : getVertices()) {
			vertex.setPredecessor(null);
			// if not start vertex set weight too something high
			if (!vertex.getName().equals(start_vertex)) {
				vertex.setDistance(9999999);
				// else set start vertex weight to 0 and add it to the queue
			} else {
				vertex.setDistance(0);
				prioQ.insert(vertex, vertex.getDistance());
			}
		}
		// while the queue is not empty
		while (!prioQ.isEmpty()) {
			// takes out the first vertex
			Vertex currentVertex = (Vertex) prioQ.extract();
			// loops threw all edges to the current vertex
			for (Edge edge : currentVertex.getEdges()) {
				// gets the vertex to where the edge is leading
				Vertex proposedVertex = edge.getToVertex();
				// if the proposedVertex weight is more than current vertex weight + edge weight.
				// Set distance for proposedVertex to current vertex weight + edge weight and predecessor for proposedVertex to current vertex
				// update queue with proposedVertex
				if (proposedVertex.getDistance() > (currentVertex.getDistance() + edge.getWeight())) {
					proposedVertex.setDistance(currentVertex.getDistance() + edge.getWeight());
					proposedVertex.setPredecessor(currentVertex);
					prioQ.update(proposedVertex, proposedVertex.getDistance());
				}
			}
		}
	}
}
