package es.uniovi.asw.game.model;

import java.util.ArrayList;
import java.util.List;

public class BoardBox 
{
	private int number;
	private BoardBox right;
	private BoardBox left;
	private BoardBox center;
	
	public BoardBox(int number) {
		this.number = number;
		right = null;
		left = null;
		center = null;
	}
	
	public void addRight( BoardBox adjacent)
	{
		right=adjacent;
	}
	
	public void addLeft( BoardBox adjacent)
	{
		left=adjacent;
	}
	
	public void addcenter( BoardBox adjacent)
	{
		center=adjacent;
	}
	

	public int getNumber() {
		return number;
	}

	public BoardBox getRight() {
		return right;
	}

	public BoardBox getLeft() {
		return left;
	}

	public BoardBox getCenter() {
		return center;
	}
		
	
}
