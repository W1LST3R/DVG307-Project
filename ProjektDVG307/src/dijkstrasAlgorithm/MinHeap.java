package dijkstrasAlgorithm;

public class MinHeap<T extends Comparable<T>> implements Heapish<T> {
	private T[] storage;
	private int N = 0;

	public MinHeap(T[] storage) // user injects array big enough
	{
		this.storage = storage;
	}

	public void insert(T data) // add and restore heap props (HP)
	{
		//If its the first element to be added to the heap
		if (size() == 0) {
			storage[N] = data;
			N++;
		} else {
			storage[N] = data;
			//index of child is size -1 so size before we increses the size
			int child = N;
			N++;
			//To get the parent node
			int parent = (child - 1) / 2;
			//while parent is larger than the added child
			//data is the newest value added
			while (storage[parent].compareTo(data) > 0) {
				swap(parent, child);
				//child is the index bellow the parent
				child = parent;
				//Gets the index for the parent
				parent = (child - 1) / 2;
			}
		}
	}
	/*
	 *                    
	 *                    
	 *  ------            -----            ------
	 *   -    -          -    -           -    -
	 *    -    -        - -   -          -    -
	 *     -    -         -   -         -    -
	 *      -    -        -   -        -    -
	 *       -    -       -   -       -    -
	 *        -    -      -----      -    -
	 *         -    -    -     -    -    -
	 *          -    -  -   -   -  -    -
	 *           -    --   - -   -     -
	 *            -       -   -       -
	 *             -     --   --     -
	 *              -   - -   - -   -
	 *               ---  -----  ---             
	 *    
	 *         
	 */

	//Långsammare men tydligare implemenation av extract
	public T extract() // remove smallest, restore HP and return
	{
		T top = top();
		N--;
		storage[0] = storage[N];
		int parent = 0;
		int leftChild;
		int rightChild;
		//lastIndex is the total number of nodes
		int lastIndex = N;
		String side ="Right";
		//while the parent index is bellow lastindex it will continue
		while (lastIndex > parent) {
			//Gets index for the right and left child
			leftChild = (2 * parent) + 1;
			rightChild = (2 * parent) + 2;
			//If both childsindex are bellow lastindex
			if(rightChild < lastIndex && leftChild < lastIndex){
				//If a child has a value less than parent
				if(storage[parent].compareTo(storage[rightChild]) > 0 || storage[parent].compareTo(storage[leftChild]) > 0) {
					//if both children have the same value, then it picks every other side
					if (storage[rightChild].compareTo(storage[leftChild]) == 0) {
						if (side == "Right") {
							swap(parent, rightChild);
							parent = rightChild;
							side ="Left";
						} else {
							swap(parent, leftChild);
							parent = leftChild;
							side ="Right";
						}
					//checks to see which child is smaller
					} else if (storage[rightChild].compareTo(storage[leftChild]) > 0) {
						swap(parent, leftChild);
						parent = leftChild;

					} else {
						swap(parent, rightChild);
						parent = rightChild;
					}
				//if no child is less than parent
				}else parent = N;
			//Can have i child to the left and not to the right
			}else if(leftChild < lastIndex) {
				if(storage[parent].compareTo(storage[leftChild]) > 0) {
						swap(parent, leftChild);
						parent = leftChild;
				}else parent = N;
			}else parent = N;
		}

		return top;
	}
	
	/*
	 * Snabbare implementation av extract
	public T extract() // remove smallest, restore HP and return
	{
		T returnVal = storage[0];
		
		// flytta sista värdet till första platsen
		storage[0] = storage[N - 1];
		
		// cleara sista värdet, kanske onödigt
		//storage[N - 1] = null;
		
		// minska storleken med 1
		N--;
		
		int parent = 0;
		int child = 0;
		
		// Spara värdet som ska flyttas
		T valToMove = storage[parent];

		while(parent < (N-1) && child < (N-1) && (parent*2 + 1) < N) {
			// kolla vilket barn som är minst
			if(storage[parent*2 + 1] != null && storage[parent*2 + 2] != null && storage[parent*2 + 1].compareTo(storage[parent*2 + 2]) > 0) child = parent*2 + 2;
			else child = parent*2 + 1;
			
			// Om parent har värde > barnet, byt plats på dem
			if(storage[parent].compareTo(storage[child]) > 0) {
				storage[parent] = storage[child];
				storage[child] = valToMove;
			}
			parent = child;
		}
		return returnVal;
	}*/
	
	public T top() // return smallest, don't remove
	{
		return storage[0];
	}

	public void clear() // make heap appear empty
	{
		N = 0;
	}

	public int size() // well, you know
	{
		return N;
	}

	public boolean empty() // well, you know
	{
		boolean flag = true;
		if (size() != 0)
			flag = false;
		return flag;
	}

	public boolean isHeap() // bjussar på denna då den kan hjälpa debugging
	{
		for (int i = 1; i < N; i++)
			if (storage[i].compareTo(storage[(i - 1) / 2]) < 0)
				return false;
		return true;
	}
	
	
	//swaps values from to places in an array
	public void swap(int i, int j) {
		T temp = storage[i];
		storage[i] = storage[j];
		storage[j] = temp;
	}

	public void print() { // se nedan
		if (N > 0) // fulmetod för att se heapen. ok om N < 80 typ
		{
			System.out.format("N = %d\n", N);
			double log2 = Math.log(N) / Math.log(2);
			int depth = (int) (1 + log2);
			int fullN = (int) (Math.pow(2, depth)) - 1;
			int div = 1;
			for (int e = 0; e < (int) (1 + log2); e++) {
				for (int i = 0; i < Math.pow(2, e); i++) {
					int idx = (int) (Math.pow(2, e) - 1 + i);
					if (idx < N) {
						if (i == 0)
							System.out.format("%s%02d", strn((fullN - 1) / div - 1), storage[idx]);
						else
							System.out.format("%s%02d", strn(2 * (fullN - 1) / div - 1), storage[idx]);
					}
				}
				div *= 2;
				System.out.println();
			}
		}
		System.out.println("\n- - - - - - - - - - - - - - - - - - -");
	}

	private String strn(int n) {
		String result = "";
		while (n-- > 0)
			result += " ";
		return result;
	}
}
