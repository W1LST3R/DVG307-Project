package dijkstrasAlgorithm;
//kommentar

public class Edge <T extends Comparable<T>> {

	private int weight;
	private Vertex nextVertex;
	public Edge() {
		
	}
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int newWeight){
		weight = newWeight;
	}
	
	public Vertex getVertex(){
		return nextVertex;
	}
	
	public void setVertex(Vertex newFromVertex){
		nextVertex = newFromVertex;
	}
}
