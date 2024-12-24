package graph.adjacencymatrix.directedweightedgraph;

import java.util.function.Consumer;

import array.dynamicarray.DynamicArray;
import stack.dynamicstack.Stack;
import queue.dynamicqueue.Queue;

public class Graph <T> {
	
	private DynamicArray<T> verticesLabels;
	private Integer[][] adjacencyMatrix;

	public Graph(Class<T> c) {
		this.verticesLabels = new DynamicArray<T>(c);
		this.adjacencyMatrix = new Integer[verticesLabels.getCapacity()][verticesLabels.getCapacity()];
	}

	public int size() {
		return verticesLabels.size();
	}

	public boolean isEmpty() {
		return verticesLabels.isEmpty();
	}

	private void rebuildMatrix() {
		if (adjacencyMatrix.length != verticesLabels.getCapacity()) {
			Integer[][] temp = adjacencyMatrix;
			adjacencyMatrix = new Integer[verticesLabels.getCapacity()][verticesLabels.getCapacity()];
			for (int i = 0; i < size(); i++) {
				for (int j = 0; j < size(); j++)
					adjacencyMatrix[i][j] = temp[i][j];
			}
		}
	}
	
	public void addVertex(T label) {
		verticesLabels.add(label);
		rebuildMatrix();
	}

	public void addVertex(T label, int adjacentIndex, Integer weight) {
		verticesLabels.add(label);
		rebuildMatrix();
		int newVertexIndex = size() - 1;
		adjacencyMatrix[newVertexIndex][adjacentIndex] = weight;
	}

	public void addVertex(T label, T adjacentLabel, Integer weight) {
		int index;
		if (label.equals(adjacentLabel))
			index = size();
		else
		    index = verticesLabels.indexOf(adjacentLabel);
		addVertex(label, index, weight);
	}

	public T removeVertex(int index) {
		T removed = verticesLabels.remove(index);
		int removedVertexIndex = index;
		while (index < size()) {
			adjacencyMatrix[index] = adjacencyMatrix[index + 1];
			index++;
		}
		adjacencyMatrix[index] = new Integer[verticesLabels.getCapacity()];
		for (int i = 0; i < size(); i++) {
			if (adjacencyMatrix[i][removedVertexIndex] != null)
				adjacencyMatrix[i][removedVertexIndex] = null;
		}
		for (int i = 0; i < size(); i++) {
			for (int j = removedVertexIndex; j < size(); j++) {
				adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
			}
			adjacencyMatrix[i][size()] = null;
		}
		return removed;
	}

	public void removeVertex(T label) {
		int i = verticesLabels.indexOf(label);
		removeVertex(i);
	}

	public void addEdge(int vertexIndex1, int vertexIndex2, Integer weight) {
		adjacencyMatrix[vertexIndex1][vertexIndex2] = weight;
	}

	public void addEdge(T label1, T label2, Integer weight) {
		addEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2), weight);
	}

	public void removeEdge(int vertexIndex1, int vertexIndex2) {
		adjacencyMatrix[vertexIndex1][vertexIndex2] = null;
	}

	public void removeEdge(T label1, T label2) {
		removeEdge(verticesLabels.indexOf(label1), verticesLabels.indexOf(label2));
	}
	
	public Integer getWeight(int vertexIndex1, int vertexIndex2) {
		return adjacencyMatrix[vertexIndex1][vertexIndex2];
	}
	
	public Integer getWeight(T label1, T label2) {
		return adjacencyMatrix[verticesLabels.indexOf(label1)][verticesLabels.indexOf(label2)];
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
				if (adjacencyMatrix[currentIndex][i] != null && !visited[i]) {
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
				if (adjacencyMatrix[currentIndex][i] != null && !visitedOrInQueue[i]) {
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
				if (adjacencyMatrix[i][j] != null) {
					sb.append(" ");
					if (verticesLabels.get(j) != null)
						sb.append(verticesLabels.get(j));
					else
						sb.append("i:" + j);
					sb.append("(" + getWeight(i, j) + ")");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
