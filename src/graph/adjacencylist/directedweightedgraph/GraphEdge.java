package graph.adjacencylist.directedweightedgraph;

import java.util.Objects;

public class GraphEdge {
	
	private Integer adjacentIndex;
	private Integer weight;
	
	public GraphEdge(Integer adjacentIndex, Integer weight) {
		this.adjacentIndex = adjacentIndex;
		this.weight = weight;
	}
	
	public Integer getAdjacentIndex() {
		return adjacentIndex;
	}
	
	public void setAdjacentIndex(Integer adjacentIndex) {
		this.adjacentIndex = adjacentIndex;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adjacentIndex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphEdge other = (GraphEdge) obj;
		return Objects.equals(adjacentIndex, other.adjacentIndex);
	}
}
