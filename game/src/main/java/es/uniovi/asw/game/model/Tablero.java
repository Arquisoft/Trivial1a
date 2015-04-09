package es.uniovi.asw.game.model;

import java.util.ArrayList;

public class Tablero {

	
	private int tamaño, nArcos, nPasillos, tArco, tPasillo;
	private int[] tablero;
	
	// tamaño para que funcione; nArcos = nPasillos = tArco = 6;
	//							 tPasillo = 5;
	public Tablero(int nArcos, int nPasillos, int tArco, int tPasillo)
	{
		this.nArcos = nArcos;
		this.nPasillos = nPasillos;
		this.tArco = tArco;
		this.tPasillo = tPasillo;
		tamaño = nArcos * tArco + nPasillos * this.tPasillo + nArcos +1; // incluyendo la casilla centro (la 0)
	}
	
	public int[] mover(int posiciones, int n)
	{
		int [] pos = new int[6]; // tamaño 6 será el maximo numero de movimientos diferentes
		if (n == 0 && posiciones < 6)
			for (int i = 0; i<6; i++)
				pos[i] = tamaño - posiciones - tPasillo*i;
		else if (n == 0 && posiciones == 6)
			for (int i = 0; i<6; i++)
				pos[i] = 1+i*(tArco+1);
		else{
			if (n >= nArcos * tArco + tArco && n< tamaño -1) // es una casilla de los pasillos
				return sacaTodo(posiciones, n);
			else // es una quesito o casilla de los arcos
			{
				// maximo 3 movimientos
				if (posibleQuesito(n))
				{
					int [] aux = calculaSiguienteCasilla(n);
					aux[0] = aux[0] + posiciones - 1;
					aux[1] = aux[1] - posiciones + 1;
					if (posiciones == 6)
						aux[2] = 0;
					else
						aux[2] = aux[2] + posiciones - 1;
					return aux;
				}
				// maximo 6 posibles movimientos
				else 
				{
					return sacaTodo(posiciones, n);
				}
			}
		}
		return pos;
	}
	
	public int[] sacaTodo(int posiciones, int n)
	{
		int i = 0;
		ArrayList<Integer> conformado = new ArrayList<Integer>();
		conformado.add(n);
		while(i < posiciones)
		{	
			int[] aux = null;
			ArrayList<Integer> copy = new ArrayList<Integer>(conformado);
			for (Integer e : copy)
			{
				aux = calculaSiguienteCasilla(e);
				if (aux != null){
				for (int j = 0; j<aux.length; j++)
					if (!copy.contains(aux[j]))
						conformado.add(aux[j]);
				}
			}
			i++;
		}
		// filtramos
		ArrayList<Integer> copy = new ArrayList<Integer>();
		int mayor = 0;
		for (Integer e : conformado){
			if (e > 36 || e == n - posiciones || e == n + posiciones)
			{
				copy.add(e);
				i++;
			}
			if (e > mayor)
				mayor = e;
		}
		conformado = new ArrayList<Integer>(copy);
		for (Integer e : copy)
			if (e  + posiciones > mayor && e != mayor)
				conformado.remove(e);

		int[] r = new int[conformado.size()];
		for(int j = 0; j<conformado.size(); j++)
			r[j] = conformado.get(j);
		return r;
	}
	
	/**
	 * Calculará las siguientes posiciones que se podrán dar a partir de la casilla introducida
	 * Siendo 2 si la casilla que no sea un quesito y 3 si la casilla es un quesito.
	 * @param n
	 * @return int[] con siguiente-atrás-(opcional)siguiente en el pasillo
	 */
	public int[] calculaSiguienteCasilla(int n)
	{    
		if (n == 0){
			int[] res = new int[nArcos];
			for (int i = 0; i<nArcos;i++)
				res[i] = tArco*nArcos+tArco + i * tPasillo;
			return res;
		}
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
