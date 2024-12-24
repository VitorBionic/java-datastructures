package graph.adjacencymatrix.undirectedunweightedgraph;

import java.util.function.Consumer;

import array.dynamicarray.DynamicArray;
import stack.dynamicstack.Stack;
import queue.dynamicqueue.Queue;

public class Graph <T> {
	
	private DynamicArray<T> verticesLabels;
	private boolean[][] adjacencyMatrix;

	public Graph(Class<T> c) {
		this.verticesLabels = new DynamicArray<T>(c);
		this.adjacencyMatrix = new boolean[verticesLabels.getCapacity()][verticesLabels.getCapacity()];
	}

	public int size() {
		return verticesLabels.size();
	}

	public boolean isEmpty() {
		return verticesLabels.isEmpty();
	}

	private void rebuildMatrix() {
		if (adjacencyMatrix.length != verticesLabels.getCapacity()) {
			boolean[][] temp = adjacencyMatrix;
			adjacencyMatrix = new boolean[verticesLabels.getCapacity()][verticesLabels.getCapacity()];
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++)
					adjacencyMatrix[i][j] = temp[i][j];
			}
		}
	}

	public void addVertex(T label, int... adjacentsIndexes) {
		verticesLabels.add(label);
		rebuildMatrix();
		int newVertexIndex = size() - 1;
		for (int i : adjacentsIndexes) {
			if (i < 0 || i >= size())
				throw new IllegalArgumentException("Index " + i + " is out of range from 0 to length " + (size()));
			adjacencyMatrix[newVertexIndex][i] = true;
			adjacencyMatrix[i][newVertexIndex] = true;
		}
	}

	@SuppressWarnings("unchecked")
	public void addVertexByLabels(T label, T... adjacents) {
		int[] indexes = new int[adjacents.length];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = verticesLabels.indexOf(adjacents[i]);
		}
		addVertex(label, indexes);
	}

	public T removeVertex(int index) {
		T removed = verticesLabels.remove(index);
		int removedVertexIndex = index;
		while (index < size()) {
			adjacencyMatrix[index] = adjacencyMatrix[index + 1];
			index++;
		}
		adjacencyMatrix[index] = new boolean[verticesLabels.getCapacity()];
		for (int i = 0; i < size(); i++) {
			for (int j = removedVertexIndex; j < size(); j++) {
				adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
			}
			adjacencyMatrix[i][size()] = false;
		}
		return removed;
	}

	public void removeVertex(T label) {
		int i = verticesLabels.indexOf(label);
		removeVertex(i);
	}

	public void addEdge(int vertexIndex1, int vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2 + " is out of range from 0 to length " + (size()));
		adjacencyMatrix[vertexIndex1][vertexIndex2] = true;
		adjacencyMatrix[vertexIndex2][vertexIndex1] = true;
	}

	public void addEdge(T label1, T label2) {
		addEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}

	public void removeEdge(int vertexIndex1, int vertexIndex2) {
		if ((vertexIndex1 < 0 || vertexIndex1 >= size()) || (vertexIndex2 < 0 || vertexIndex2 >= size()))
			throw new IllegalArgumentException("Indexes " + vertexIndex1 + " or " + vertexIndex2 + " is out of range from 0 to length " + (size()));
		adjacencyMatrix[vertexIndex1][vertexIndex2] = false;
		adjacencyMatrix[vertexIndex2][vertexIndex1] = false;
	}

	public void removeEdge(T label1, T label2) {
		removeEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}

	public void DFS(Consumer<T> consumer) {
		if (isEmpty())
			throw new IllegalStateException("Graph is empty");
		
		boolean[] visited = new boolean[size()];
		Stack<int[]> stack = new Stack<>();
		
		stack.push(new int[]{0, 0});
		
		while (!stack.isEmpty()) {
			int[] currentIndexAndCurrentAdjacent = stack.peek();
			
			int currentIndex = currentIndexAndCurrentAdjacent[0];
			int currentAdjacent = currentIndexAndCurrentAdjacent[1];
			
			if (!visited[currentIndex]) {
				consumer.accept(verticesLabels.get(currentIndexAndCurrentAdjacent[0]));
				visited[currentIndex] = true;
			}
			
			for (int i = currentAdjacent + 1; i < size(); i++) {
				if (adjacencyMatrix[currentIndex][i] && !visited[i]) {
					currentIndexAndCurrentAdjacent[1] = i;
					stack.push(new int[] {i, 0});
					break;
				}
				if (i == size() - 1)
					stack.pop();
			}
			if (currentAdjacent ==  size() - 1)
				stack.pop();
		}
	}
	
	public void BFS(Consumer<T> consumer) {
		if (isEmpty())
			throw new IllegalStateException("Graph is empty");
		
		boolean[] visitedOrInQueue = new boolean[size()];
		Queue<Integer> queue = new Queue<>();
		
		queue.enqueue(0);
		while (!queue.isEmpty()) {
			
			int currentIndex = queue.dequeue();
			consumer.accept(verticesLabels.get(currentIndex));
			
			if (!visitedOrInQueue[currentIndex])
			    visitedOrInQueue[currentIndex] = true;
			
			for (int i = 1; i < size(); i++) {
				if (adjacencyMatrix[currentIndex][i] && !visitedOrInQueue[i]) {
					queue.enqueue(i);
					visitedOrInQueue[i] = true;
				}
			}
			
		}
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			if (verticesLabels.get(i) != null)
				sb.append(verticesLabels.get(i) + " ->");
			else
				sb.append("i:" + i + " ->");
			for (int j = 0; j < size(); j++) {
				if (adjacencyMatrix[i][j]) {
					sb.append(" ");
					if (verticesLabels.get(j) != null)
						sb.append(verticesLabels.get(j));
					else
						sb.append("i:" + j);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}