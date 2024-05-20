package dijkstrasAlgorithm;

import java.util.ArrayList;

public class Vertex {

	private String name;
	private double longitude;
	private double latitude;
	private double population;
	private ArrayList<Edge> ajencyList; //edges - lista med de edges som leder till närliggande vertexar ("adjency list")
	private Vertex predecessor;//predecessor - den vertex av de närliggande som är nästa steg längs den kortaste vägen till startvertex
	private double distance; //distance - avståndet från denna vertex till startvertex
	
	public Vertex(String name, double population, double longitude, double latitude) {
		setName(name);
		setPopulation(population);
		setLongitude(longitude);
		setLatitude(latitude);
		ajencyList = new ArrayList<>();
	}
	
	public void addEdge(Edge newEdge){ //- lägg till edge till närliggande vertex
		ajencyList.add(newEdge);
	}
	public ArrayList<Edge> getEdges() { // - returnerar edges till alla närliggande vertex
		return ajencyList;
	}
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double newLongitude) {
		longitude = newLongitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double newLatitude) {
		latitude = newLatitude;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double newPopulation) {
		population = newPopulation;
	}
	public ArrayList<Edge> getAjencyList() {
		return ajencyList;
	}
	public void setAjencyList(ArrayList<Edge> newAjencyList) {
		ajencyList = newAjencyList;
	}
	public Vertex getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(Vertex newPredecessor) {
		predecessor = newPredecessor;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double newDistance) {
		distance = newDistance;
	}
}
