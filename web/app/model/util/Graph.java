package model.util;

import java.util.ArrayList;
import java.util.List;

import model.Box;
import model.types.Color;

public class Graph {

	public static final Double INFINITE = Double.MAX_VALUE;
	public static final Integer SIZE = 6;
	
	private int matrixSize;
	private int[] wedges;
	public int[] getWedges() {
		return wedges;
	}

	public void setWedges(int[] wedges) {
		this.wedges = wedges;
	}

	private int[] centers;
	private Box[] boxes;
	private boolean[][] edges;
	
	public Graph() {
		
		this.matrixSize = SIZE*12+2;
		
		initializeWedges();
		initializeCenterBoxes();
		initializeBoxes();
		initializeCategories();
		initializeGraph();
	}
	
	public Box[] getBoxes() {
		return boxes;
	}

	public Box getBox(Integer position) {
		return boxes[position];
	}

	public List<Box> getNextPositions(Integer source, Integer die) {
		
		double[] cost = new double[matrixSize];
		boolean[] visitedNodes = new boolean[matrixSize];
		
		int actual = getNode(source);
		
		for (int i=0; i<matrixSize; i++)
			cost[i] = (edges[actual][i] != true) ? INFINITE : 1;
			
		cost[actual] = 0;
		int minCost;
		
		while(!visited(visitedNodes)) {
			if ((minCost = getMinCoste(cost, visitedNodes)) == -1)
				break;
			visitedNodes[minCost]= true;
				
			for (int i=0; i<matrixSize; i++)
				cost[i] = (edges[minCost][i] && cost[i] > cost[minCost] + 1) ? (cost[minCost] + 1) : cost[i];
		}
		
		List<Box> nextBoxes = new ArrayList<>();
		
		for (int i=0; i<matrixSize; i++)
			if (cost[i] == die)
				nextBoxes.add(boxes[i]);
		
		return nextBoxes;
	}

	public void print() {
		
		for (int i=1; i<matrixSize; i++) {
			for (int j=1; j<matrixSize; j++) {
				if (edges[i][j])
					System.out.print("T [" + i + "," + j +"], ");
			}
			System.out.println();
		}
	}

	private void initializeWedges() {
		
		this.wedges = new int[SIZE];
		
		wedges[0]=1;
		wedges[1]=SIZE*2+2;
		
		for (int i=2; i<SIZE; i++)
			wedges[i] = wedges[i-1] + SIZE*2;
		
		System.out.print("Wedges: ");
		print(wedges);
	}

	private void initializeCenterBoxes() {
		
		this.centers = new int[SIZE];
		
		centers[0] = SIZE;
		centers[1] = centers[0]+SIZE*2+1;
		
		for (int i=2; i<SIZE; i++)
			centers[i] = centers[i-1]+SIZE*2;
		
		System.out.print("Centers: ");
		print(centers);
	}

	private void initializeBoxes() {
		
		this.boxes = new Box[matrixSize];
		
		for (int i=1; i<matrixSize; i++) {
			boxes[i] = new Box(i);
			if (contains(wedges,i))
				boxes[i].setIsHeadquarter(true);
				
		}	
	}

	private void initializeCategories() {
		
		int[] p = {1,18,23,29,37,39,44,52,58,67};
		int[] bl = {2,8,16,22,31,38,54,59,65,73};
		int[] o = {3,10,10,26,42,47,53,61,63,68};
		int[] b = {4,13,15,20,28,34,43,50,66,71};
		int[] g = {5,11,17,25,27,32,40,46,55,62};
		int[] y = {6,14,30,35,41,49,51,56,64,70};
		
		for (int i=0; i<p.length; i++) {
			
			boxes[p[i]].setCategory(Color.PINK);
			boxes[bl[i]].setCategory(Color.BLUE);
			boxes[o[i]].setCategory(Color.ORANGE);
			boxes[b[i]].setCategory(Color.BROWN);
			boxes[g[i]].setCategory(Color.GREEN);
			boxes[y[i]].setCategory(Color.YELLOW);
		}
		
		boxes[SIZE+1].setCategory(Color.CENTER);
	}

	private void initializeGraph() {
		
		this.edges = new boolean[matrixSize][matrixSize];
		
		int last=0;
		for (int i=1; i<matrixSize; i++) {
			for (int j=1; j<matrixSize; j++) {
				if (i!=j) {
					if (contains(centers,i)) {
						edges[i][SIZE+1] = true;
						edges[SIZE+1][i] = true;
						i=wedges[last];
						j=wedges[last]+SIZE-1;
						if (last==0)
							j++;
						last++;
					} else {
						edges[i][j] = true;
						edges[j][i] = true;
						i=j;
					}
				}	
			}
		}
		edges[1][matrixSize-1] = true;
		edges[matrixSize-1][1] = true;
	}

	private int getNode(Integer node) {
		
		for (int i=1;i<matrixSize;i++)
			if (boxes[i].getId().equals(node))
				return boxes[i].getId();
		
		return -1;
	}
	
	private boolean visited(boolean[] v) {
		
		for (int i=0; i<v.length; i++)
			if (!v[i])
				return false;
		
		return true;
	}
	
	private int getMinCoste(double[] d, boolean[] v) {
		
		double coste = INFINITE;
		int posMinNode = -1;
		
		for (int i=1; i<matrixSize; i++) {
			if (d[i] < coste && d[i] != 0 && !v[i]) {
				coste = d[i];
				posMinNode = i;
			}		
		}	
		
		return posMinNode;
	}

	private boolean contains(int[] wedges, int a) {
		
		for (int i=0; i<wedges.length; i++)
			if (wedges[i] == a)
				return true;
		
		return false;
	}

	private void print(int[] array) {
		
		for (int i=0; i<array.length; i++)
			System.out.print(array[i] + "   ");
		System.out.println();
	}
}