package graph.adjacencylist.undirectedunweightedgraph;

import java.util.function.Consumer;

public class Program {
public static void main(String[] args) {
		
		Graph<String> graph = new Graph<String>(String.class);
		
		System.out.println(graph);
		System.out.println();
		graph.addVertex("A");
		System.out.println(graph);
		System.out.println();
		graph.addVertex("B", 0);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("C", 2, 0);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("D", 1);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("E", 3);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("F", 0, 3);
		System.out.println(graph);
		System.out.println();
		graph.addVertex("G", 1);
		System.out.println(graph);
		System.out.println();
		graph.removeVertex("A");
		System.out.println(graph);
		System.out.println();
		graph.addVertex("H");
		System.out.println(graph);
		System.out.println();
		graph.addEdge("H", "C");
		System.out.println(graph);
		System.out.println();
		
		graph.removeEdge(1, 6);
		System.out.println(graph);
		System.out.println();
		graph.addVertexByLabels(null, "H", "B");
		System.out.println(graph);
		graph.removeVertex("C");
		System.out.println(graph);
		System.out.println();
		
		
		Consumer<String> print = (label) -> System.out.print(label + " ");
		graph.DFS(print);
		System.out.println();
		graph.BFS(print);
		
	}
}
