package es.uniovi.asw.web.model;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.web.model.types.Color;

public class Player {

	private User user;
	private Box actual;
	private Color piece;
	private List<Color> wedges;
	private Integer wins, fails;
	
	public Player(User user) {
		
		this.user = user;
		this.wedges = new ArrayList<>();
		this.setWins(0);
		this.setFails(0);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
}