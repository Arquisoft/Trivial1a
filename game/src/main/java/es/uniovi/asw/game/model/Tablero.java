package es.uniovi.asw.game.model;

public class Tablero {

	
	private int tamaño, nArcos, nPasillos, tArco, tPasillo;
	private int[] tablero;
	
	public Tablero(int nArcos, int nPasillos, int tArco, int tPasillo)
	{
		this.nArcos = nArcos;
		this.nPasillos = nPasillos;
		this.tArco = tArco;
		this.tPasillo = tPasillo;
		tamaño = nArcos * tArco + nPasillos * this.tPasillo + nArcos +1; // incluyendo la casilla centro
	}
	
	/**
	 * Calculará las siguientes posiciones que se podrán dar a partir de la casilla introducida
	 * Siendo 2 si la casilla que no sea un quesito y 3 si la casilla es un quesito.
	 * @param n
	 * @return int[] con siguiente-atrás-(opcional)siguiente en el pasillo
	 */
	public int[] calculaSiguienteCasilla(int n)
	{    
		if (n == 0)
			return null; // fin del juego
		else if (n >= 1 && n <= nArcos * tArco && posibleQuesito(n)) // si la casilla es un quesito
			if (n == 1){
				int [] posiciones = {n + 1, nArcos * tArco + tArco, calculaPosicionQuesito(n)};
				return posiciones; 
			} else{
				int [] posiciones = {n + 1, n -1, calculaPosicionQuesito(n)};
				return posiciones;
			}
		else if (n > nArcos * tArco + tArco && n < tamaño - 1 && posibleFin(n)){ // si la casilla es la anterior al final
			int [] posiciones = {0, n -1}; // la siguiente será la casilla 0
			return posiciones;
		}
		else
			return null;
	}
	
	private int calculaPosicionQuesito(int n){
		return (tamaño - nArcos*tArco) - 2 * ((int) n / (tArco +1)) + n;
	}
	
	private boolean posibleQuesito(int n)
	{
		return n % (tArco + 1) - 1 == 0;
	}
	
	private boolean posibleFin(int n)
	{
		return n % tPasillo - 2 == 0;
	}

	public int[] getTablero() {
		return tablero;
	}

	public void setTablero(int[] tablero) {
		this.tablero = tablero;
	}
	
}
