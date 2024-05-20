package dijkstrasAlgorithm;

import java.lang.reflect.Array;

public class PriorityQ<DATA, WEIGHT extends Comparable<WEIGHT>>{
	private Heapish<Node> heap;

	public PriorityQ(int capacity) {
		@SuppressWarnings("unchecked")
		Node[] array = (Node[]) Array.newInstance(Node.class, capacity);
		heap = new MinHeap<Node>(array);
	}

	public int size() {
		return heap.size();
	}
	
	public boolean isEmpty() {
		return heap.empty();
	}

	public void insert(DATA data, WEIGHT weight) {
		heap.insert(new Node(data, weight));
	}

	public void update(Vertex vertic,int distance) {
		
	}
	
	public DATA extract() {
		return heap.extract().getData();
	}

	private class Node implements Comparable<Node> {
		private DATA data;
		private WEIGHT weight;

		public Node(DATA data, WEIGHT weight) {
			this.data = data;
			this.weight = weight;
		}

		public DATA getData() {
			return data;
		}

		public WEIGHT getWeight() {
			return weight;
		}

		public int compareTo(Node other) {
			int result = weight.compareTo(other.getWeight());
			return result;
		}
	}
}
