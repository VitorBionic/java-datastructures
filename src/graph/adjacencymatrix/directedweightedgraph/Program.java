package graph.adjacencymatrix.directedweightedgraph;

import java.util.function.Consumer;

public class Program {
	public static void main(String[] args) {
		Graph<String> graph = new Graph<String>(String.class);

		System.out.println(graph);
		System.out.println();
		graph.addVertex("A");
		System.out.println(graph);
		System.out.println();
		graph.addVertex("B");
		graph.addEdge("A", "B", 3);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("C");
		graph.addEdge(0, 2, 0);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("D", "D", 22);
		graph.addEdge("A", "D", 10);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("E", 1, 9);
		graph.addEdge(1, 4, 13);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("F");
		System.out.println(graph);
		System.out.println();
		graph.addVertex("G", "C", 50);
		graph.addEdge("G", "F", 1);
		graph.addEdge(5, 6, 2);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("H", "F", 8);
		graph.addEdge("F", "H", 4);
		System.out.println(graph);
		System.out.println();
		graph.addVertex(null, 7, 7);
		graph.addEdge(8, 6, 5);
		graph.addEdge("C", null, 14);
		System.out.println(graph);
		System.out.println();
		graph.removeVertex("F");
		System.out.println(graph);
		System.out.println();
		graph.removeEdge(5, 2);
		System.out.println(graph);
		System.out.println();
		
		Consumer<String> print = (label) -> System.out.print(label + " ");
		graph.DFS(print);
		System.out.println();
		graph.BFS(print);
		
	}
}
