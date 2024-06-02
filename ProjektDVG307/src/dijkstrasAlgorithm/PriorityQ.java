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

	//updates the queue with the new node if it was not all ready in the queue
	public void update(DATA vertix,WEIGHT distance) {
		@SuppressWarnings("unchecked")
		Node[] tempArray = (Node[]) Array.newInstance(Node.class, size()+1);
		int i = 0;
		//adds the new node to the temp array
		tempArray[i++] = new Node(vertix, distance);
		int flag = -1;
		while(!isEmpty()){
			Node tempNode = heap.extract();
			//if the new node is all ready in the queue compare which has the lowest weight and save it
			if(vertix == tempNode.getData()) {
				if(tempNode.getWeight().compareTo(distance) > 1) {
					flag = i;
				}else {
					flag = i;
					tempArray[0] = new Node(tempNode.getData(), tempNode.getWeight());
				}
			//adds the node to the temp array
			}else tempArray[i] =  new Node(tempNode.getData(), tempNode.getWeight());
			i++;
		}
		//adds every node to the queue
		for(int j = 0; j < tempArray.length;j++) {
			if(flag != j) {
				insert(tempArray[j].data, tempArray[j].weight);
			}
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
