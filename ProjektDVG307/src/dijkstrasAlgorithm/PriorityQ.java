package dijkstrasAlgorithm;

import java.lang.reflect.Array;
import java.util.HashMap;

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

	public void update(DATA vertix,WEIGHT distance) {
		@SuppressWarnings("unchecked")
		Node[] tempArray = (Node[]) Array.newInstance(Node.class, size());
		int i = 0;
		while(!isEmpty()){
			Node tempNode = heap.extract();
			System.out.println(vertix);
			System.out.println(tempNode.getData());
			System.out.println(vertix==tempNode.getData());
			if(vertix == tempNode.getData()) {
				if(tempNode.getWeight().compareTo(distance) > 1) {
					tempArray[i] = new Node(vertix,distance);
				}else {
					tempArray[i] = new Node(tempNode.getData(), tempNode.getWeight());
				}
			}else tempArray[i] =  new Node(tempNode.getData(), tempNode.getWeight());
			i++;
		}
		for(int j = 0; j < tempArray.length;j++) {
			insert(tempArray[i].data, tempArray[i].weight);
		}
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
