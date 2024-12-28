package graph.adjacencylist.directedweightedgraph;

import java.util.function.Consumer;

import array.dynamicarray.DynamicArray;
import stack.dynamicstack.Stack;
import queue.dynamicqueue.Queue;
import hashtable.hashtablewithlinkedlist.HashTable;

public class Graph <T> {

	private DynamicArray<T> verticesLabels;
	private HashTable<Integer, GraphEdge> adjacencyList;

	public Graph(Class<T> c) {
		this.verticesLabels = new DynamicArray<T>(c);
		this.adjacencyList = new HashTable<Integer, GraphEdge>();
	}

	public int size() {
		return verticesLabels.size();
	}

	public boolean isEmpty() {
		return verticesLabels.isEmpty();
	}
	
	public void addVertex(T label) {
		verticesLabels.add(label);
	}
	
	public void addVertex(T label, Integer adjacentIndex, Integer weight) {
		if (adjacentIndex < 0 || adjacentIndex > size())
			throw new IllegalArgumentException("Index " + adjacentIndex + " is out of range from 0 to length " + (size()));
		verticesLabels.add(label);
		int newVertexIndex = size() - 1;
		adjacencyList.put(newVertexIndex, new GraphEdge(adjacentIndex, weight));
	}

	public void addVertex(T label, T adjacentLabel, Integer weight) {
		int adjacentIndex;
		if (label.equals(adjacentLabel))
			adjacentIndex = size();
		else
			adjacentIndex = verticesLabels.indexOf(adjacentLabel);
		addVertex(label, adjacentIndex, weight);
	}

	public T removeVertex(Integer index) {
		T removed = verticesLabels.remove(index);
		while (adjacencyList.containsKey(index)) {
			adjacencyList.remove(index);
		}
		
		HashTable<Integer, GraphEdge> newAdjacencyList = new HashTable<>();
		for (int i = 0; i <= size(); i++) {
			while (adjacencyList.containsKey(i)) {
				GraphEdge removedEdge = adjacencyList.remove(i);
				Integer adjacentIndex = removedEdge.getAdjacentIndex();
				if (adjacentIndex == index)
					continue;
				if (i > index) {
					if (adjacentIndex > index) {
						removedEdge.setAdjacentIndex(adjacentIndex - 1);
						newAdjacencyList.put(i - 1, removedEdge);	
					} else
						newAdjacencyList.put(i - 1, removedEdge);
				} else {
					if (adjacentIndex > index) {
						removedEdge.setAdjacentIndex(adjacentIndex - 1);
						newAdjacencyList.put(i, removedEdge);
					} else
						newAdjacencyList.put(i, removedEdge);
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

	public void addEdge(Integer vertexIndex1, Integer vertexIndex2, Integer weight) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2
					+ " is out of range from 0 to length " + (size()));
		removeEdge(vertexIndex1, vertexIndex2);
		adjacencyList.put(vertexIndex1, new GraphEdge(vertexIndex2, weight));
	}

	public void addEdge(T label1, T label2, Integer weight) {
		addEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2), weight);
	}

	public void removeEdge(Integer vertexIndex1, Integer vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2
					+ " is out of range from 0 to length " + (size()));
		adjacencyList.remove(vertexIndex1, new GraphEdge(vertexIndex2, null));
	}

	public void removeEdge(T label1, T label2) {
		removeEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}
	
	public Integer getWeight(Integer vertexIndex1, Integer vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2
					+ " is out of range from 0 to length " + (size()));
		DynamicArray<GraphEdge> valuesToReAdd = new DynamicArray<>(GraphEdge.class);
		Integer weight = null;
		while (adjacencyList.containsKey(vertexIndex1)) {
			GraphEdge edge = adjacencyList.remove(vertexIndex1);
			valuesToReAdd.add(edge);
			if (edge.getAdjacentIndex().equals(vertexIndex2)) {
				weight = edge.getWeight();
				break;
			}
		}
		for (GraphEdge value : valuesToReAdd)
			adjacencyList.put(vertexIndex1, value);
		return weight;
	}
	
	public Integer getWeight(T label1, T label2) {
		return getWeight(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}
	
	public void DFS(Consumer<T> consumer)  {
		if (isEmpty())
			throw new IllegalStateException("Graph is empty");
		
		HashTable<Integer, GraphEdge> newAdjacencyList = new HashTable<>();
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
				GraphEdge edge = adjacencyList.remove(currentIndex);
				Integer adjacentIndex = edge.getAdjacentIndex(); 
				Integer weight = edge.getWeight();
				newAdjacencyList.put(currentIndex, new GraphEdge(adjacentIndex, weight));
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
		
		HashTable<Integer, GraphEdge> newAdjacencyList = new HashTable<>();
		boolean[] visitedOrInQueue = new boolean[size()];
		Queue<Integer> queue = new Queue<>();
		
		queue.enqueue(0);
		visitedOrInQueue[0] = true;
		while (!queue.isEmpty()) {
			
			Integer currentIndex = queue.dequeue();
			consumer.accept(verticesLabels.get(currentIndex));
			
			while (adjacencyList.containsKey(currentIndex)) {
				GraphEdge edge = adjacencyList.remove(currentIndex);
				Integer adjacentIndex = edge.getAdjacentIndex(); 
				Integer weight = edge.getWeight();
				newAdjacencyList.put(currentIndex, new GraphEdge(adjacentIndex, weight));
				if (!visitedOrInQueue[adjacentIndex]) {
					queue.enqueue(adjacentIndex);
					visitedOrInQueue[adjacentIndex] = true;
				}
			}
		}
		this.adjacencyList = newAdjacencyList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Integer i = 0; i < size(); i++) {
			if (verticesLabels.get(i) != null)
				sb.append(verticesLabels.get(i) + " ->");
			else
				sb.append("i:" + i + " ->");
			DynamicArray<GraphEdge> valuesToReAdd = new DynamicArray<>(GraphEdge.class);
			while (adjacencyList.containsKey(i)) {
				GraphEdge edgeRemoved = adjacencyList.remove(i);
				valuesToReAdd.add(edgeRemoved);
				sb.append(" ");
				if (verticesLabels.get(edgeRemoved.getAdjacentIndex()) != null)
					sb.append(verticesLabels.get(edgeRemoved.getAdjacentIndex()));
				else
					sb.append("i:" + edgeRemoved.getAdjacentIndex());
				sb.append("(" + edgeRemoved.getWeight() + ")");
			}
			for (GraphEdge value : valuesToReAdd)
				adjacencyList.put(i, value);
			sb.append("\n");
		}
		return sb.toString();
	}
}
