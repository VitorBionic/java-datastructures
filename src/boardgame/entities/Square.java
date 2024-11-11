package boardgame.entities;

import boardgame.enums.Status;
import array.unorderedarray.UnorderedArray;

public class Square {
	
	private int id;
	private UnorderedArray<Integer> players;
	private Integer marker;
	private Integer owner;
	private Status status;
	
	public Square(int id, int playersCount) {
		this.id = id;
		this.players = new UnorderedArray<>(playersCount);
		this.owner = null;
		this.status = Status.FREE;
	}
	
	public int getId() {
		return id;
	}
	
	public UnorderedArray<Integer> getPlayers() {
		return players;
	}
	
	public void addPlayer(Integer player) {
		this.players.insert(player);
	}
	public void removePlayer(Integer player) {
		players.remove(player);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setStatus(int statusId) {
		switch (statusId) {
		case 0:
			this.status = Status.FREE;
			break;
		case 1:
			this.status = Status.MARKED;
			break;
		case 2:
			this.status = Status.PROPERTY;
			break;
		}
	}
	
	public Integer getMarker() {
		return marker;
	}
	
	public void setMarker(Integer marker) {
		this.marker  = marker;
	}
	
	public Integer getOwner() {
		return owner;
	}
	
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}
