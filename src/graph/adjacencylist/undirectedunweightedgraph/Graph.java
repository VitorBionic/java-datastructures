package graph.adjacencylist.undirectedunweightedgraph;

import java.util.function.Consumer;

import array.dynamicarray.DynamicArray;
import stack.dynamicstack.Stack;
import queue.dynamicqueue.Queue;
import hashtable.hashtablewithlinkedlist.HashTable;

public class Graph<T> {

	private DynamicArray<T> verticesLabels;
	private HashTable<Integer, Integer> adjacencyList;

	public Graph(Class<T> c) {
		this.verticesLabels = new DynamicArray<T>(c);
		this.adjacencyList = new HashTable<Integer, Integer>();
	}

	public int size() {
		return verticesLabels.size();
	}

	public boolean isEmpty() {
		return verticesLabels.isEmpty();
	}

	public void addVertex(T label, Integer... adjacentsIndexes) {
		verticesLabels.add(label);
		int newVertexIndex = size() - 1;
		for (Integer i : adjacentsIndexes) {
			if (i < 0 || i >= size())
				throw new IllegalArgumentException("Index " + i + " is out of range from 0 to length " + (size()));
			adjacencyList.put(newVertexIndex, i);
			adjacencyList.put(i, newVertexIndex);
		}
	}

	@SuppressWarnings("unchecked")
	public void addVertexByLabels(T label, T... adjacents) {
		Integer[] indexes = new Integer[adjacents.length];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = verticesLabels.indexOf(adjacents[i]);
		}
		addVertex(label, indexes);
	}

	public T removeVertex(Integer index) {
		T removed = verticesLabels.remove(index);
		while (adjacencyList.containsKey(index)) {
			if (adjacencyList.get(index) != index)
				adjacencyList.remove(adjacencyList.get(index), index);
			adjacencyList.remove(index);
		}
		
		HashTable<Integer, Integer> newAdjacencyList = new HashTable<>();
		for (int i = 0; i <= size(); i++) {
			while (adjacencyList.containsKey(i)) {
				Integer adjacentIndexRemoved = adjacencyList.remove(i);
				if (i > index) {
					if (adjacentIndexRemoved > index)
						newAdjacencyList.put(i - 1, adjacentIndexRemoved - 1);
					else
						newAdjacencyList.put(i - 1, adjacentIndexRemoved);
				} else {
					if (adjacentIndexRemoved > index)
						newAdjacencyList.put(i, adjacentIndexRemoved - 1);
					else
						newAdjacencyList.put(i, adjacentIndexRemoved);
				}
			}
		}
		adjacencyList = newAdjacencyList;
		return removed;
	}

	public void removeVertex(T label) {
		int i = verticesLabels.indexOf(label);
		removeVertex(i);
	}

	public void addEdge(Integer vertexIndex1, Integer vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2
					+ " is out of range from 0 to length " + (size()));
		removeEdge(vertexIndex1, vertexIndex2);
		adjacencyList.put(vertexIndex1, vertexIndex2);
		adjacencyList.put(vertexIndex2, vertexIndex1);
	}

	public void addEdge(T label1, T label2) {
		addEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}

	public void removeEdge(Integer vertexIndex1, Integer vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2
					+ " is out of range from 0 to length " + (size()));
		adjacencyList.remove(vertexIndex1, vertexIndex2);
		adjacencyList.remove(vertexIndex2, vertexIndex1);
	}

	public void removeEdge(T label1, T label2) {
		removeEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}
	
	public void DFS(Consumer<T> consumer)  {
		if (isEmpty())
			throw new IllegalStateException("Graph is empty");
		
		HashTable<Integer, Integer> newAdjacencyList = new HashTable<>();
		boolean[] visited = new boolean[size()];
		Stack<Integer> stack = new Stack<>();
		
		stack.push(0);
		while (!stack.isEmpty())  {
			
			Integer currentIndex = stack.peek();
			
			if (!visited[currentIndex]) {
				consumer.accept(verticesLabels.get(currentIndex));
				visited[currentIndex] = true;
			}
			
			if (adjacencyList.containsKey(currentIndex)) {
				Integer adjacentIndex = adjacencyList.remove(currentIndex); 
				newAdjacencyList.put(currentIndex, adjacentIndex);
				if (!visited[adjacentIndex])
				    stack.push(adjacentIndex);
			} else
				stack.pop();
		}
		adjacencyList = newAdjacencyList;
	}
	
	public void BFS(Consumer<T> consumer) {
		if (isEmpty())
			throw new IllegalStateException("Graph is Empty");
		
		HashTable<Integer, Integer> newAdjacencyList = new HashTable<>();
		boolean[] visitedOrInQueue = new boolean[size()];
		Queue<Integer> queue = new Queue<>();
		
		queue.enqueue(0);
		visitedOrInQueue[0] = true;
		while (!queue.isEmpty()) {
			
			Integer currentIndex = queue.dequeue();
			consumer.accept(verticesLabels.get(currentIndex));
			
			while (adjacencyList.containsKey(currentIndex)) {
				Integer adjacentIndex = adjacencyList.remove(currentIndex);
				newAdjacencyList.put(currentIndex, adjacentIndex);
				if (!visitedOrInQueue[adjacentIndex]) {
					queue.enqueue(adjacentIndex);
					visitedOrInQueue[currentIndex] = true;
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer i = 0; i < size(); i++) {
			if (verticesLabels.get(i) != null)
				sb.append(verticesLabels.get(i) + " ->");
			else
				sb.append("i:" + i + " ->");
			DynamicArray<Integer> valuesToReAdd = new DynamicArray<>(Integer.class);
			while (adjacencyList.containsKey(i)) {
				Integer removed = adjacencyList.remove(i);
				valuesToReAdd.add(removed);
				sb.append(" ");
				if (verticesLabels.get(removed) != null)
					sb.append(verticesLabels.get(removed));
				else
					sb.append("i:" + removed);
			}
			for (Integer value : valuesToReAdd)
				adjacencyList.put(i, value);
			sb.append("\n");
		}
		return sb.toString();
	}
}
