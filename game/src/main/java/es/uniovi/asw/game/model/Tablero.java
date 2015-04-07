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
		tamaño = nArcos * tArco + nPasillos * this.tPasillo + nArcos +1; // incluyendo la casilla centro (la 0)
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
			if (n == 1)
				return new int []{n + 1, nArcos * tArco + tArco, calculaPosicionQuesito(n)};
			else
				return new int []{n + 1, n -1, calculaPosicionQuesito(n)};
		else if (n > nArcos * tArco + tArco && n < tamaño - 1 && posibleFin(n)) // si la casilla es la anterior al final
			return new int []{0, n -1}; // la siguiente será la casilla 0
		else
			if (n>= 0 && n <= tamaño -1)
				return new int []{n+1, n-1};
			else 
				return null;
	}
	
	/**
	 * Calcula la primera posición en la calle saliendo de un quesito
	 * @param n
	 * @return int
	 */
	public int calculaPosicionQuesito(int n)
	{
		return (tamaño - nPasillos*tPasillo) - 2 * ((int) n / (tArco +1)) + n -1;
	}
	
	/**
	 * Indica si la casilla pertenece a un quesito
	 * @param n
	 * @return
	 */
	public boolean posibleQuesito(int n)
	{
		return n % (tArco + 1) - 1 == 0;
	}
	
	/**
	 * Indica si la siguiente casilla será la 0
	 * @param n
	 * @return
	 */
	public boolean posibleFin(int n)
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
