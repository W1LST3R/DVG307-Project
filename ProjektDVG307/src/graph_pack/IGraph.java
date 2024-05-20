package graph_pack;

import java.util.List;

import dijkstrasAlgorithm.Vertex;

public interface IGraph
  {
  public void addVertex(String id, double population, double longitude, double latitude);
  public void connectVertices(String id1, String id2, double weight);
  public List<Vertex> getVertices();
  public Vertex getStartVertex();
  public void setTargetVertex(Vertex target);
  public Vertex getTargetVertex();
  public void clear(); // resets startVertex and targetVertex
  public void findShortestPath(String start_id);
  public void findShortestPath(Vertex start_vertex);
  }