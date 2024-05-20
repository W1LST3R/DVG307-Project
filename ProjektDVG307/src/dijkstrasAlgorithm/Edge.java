package dijkstrasAlgorithm;
//kommentar

public class Edge{

	private double weight;
	private Vertex fromVertex;
	private Vertex toVertex;
	public Edge(Vertex fromVertex, Vertex toVertex, double weight) {
		// kanske borde kolla så att fromVertex och toVertex inte är samma
		setFromVertex(fromVertex);
		setToVertex(toVertex);
		setWeight(weight);
	}
	public double getWeight(){
		return weight;
	}
	
	public void setWeight(double newWeight){
		weight = newWeight;
	}
	
	public Vertex getFromVertex(){
		return fromVertex;
	}
	
	public void setFromVertex(Vertex newFromVertex){
		fromVertex = newFromVertex;
	}
	
	public Vertex getToVertex(){
		return toVertex;
	}
	
	public void setToVertex(Vertex newFromVertex){
		toVertex = newFromVertex;
	}
}
