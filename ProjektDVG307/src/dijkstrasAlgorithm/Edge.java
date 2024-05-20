package dijkstrasAlgorithm;

public class Edge <T extends Comparable<T>> {

	private int weight;
	private Vertex fromVertex;
	private Vertex toVertex;
	public Edge() {
		
	}
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int newWeight){
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
