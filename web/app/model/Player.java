package model;

import java.util.ArrayList;
import java.util.List;

import model.types.Color;

public class Player {

	private String name;
	private Box actual;
	private Color piece;
	private List<Color> wedges;
	private Integer wins, fails;
	private boolean pepe;
	
	public boolean isPepe() {
		return pepe;
	}

	public void setPepe(boolean pepe) {
		this.pepe = pepe;
	}

	public Player(String name) {
		
		this.name = name;
		this.wedges = new ArrayList<>();
		this.setWins(0);
		this.setFails(0);
		actual = new Box(7);
	}
	
	public String getUser() {
		return name;
	}
	
	public void setUser(String name) {
		this.name = name;
	}

	public Box getActual() {
		return actual;
	}

	public void setActual(Box actual) {
		this.actual = actual;
	}

	public Color getPiece() {
		return piece;
	}

	public void setPiece(Color piece) {
		this.piece = piece;
	}

	public List<Color> getWedges() {
		return wedges;
	}

	public void addWedge(Color wedge) {
		this.wedges.add(wedge);
	}

	public void removeWedge(Color wedge) {
		this.wedges.remove(wedge);
	}
	
	public void haveWedge(Color wedge) {
		this.wedges.contains(wedge);
	}
	
	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Integer getFails() {
		return fails;
	}

	public void setFails(Integer fails) {
		this.fails = fails;
	}

	public boolean gano() {
		if(pepe)return pepe;
		return (getWedges().size()==6);
	}
}