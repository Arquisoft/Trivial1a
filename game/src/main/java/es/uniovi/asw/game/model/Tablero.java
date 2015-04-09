package es.uniovi.asw.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tablero {

	private int tamaño, nArcos, nPasillos, tArco, tPasillo;
	private int[] tablero;
	private ArrayList<Integer> limites;

	// tamaño para que funcione; nArcos = nPasillos = tArco = 6;
							// 	 tPasillo = 5;
	public Tablero(int nArcos, int nPasillos, int tArco, int tPasillo) {
		this.nArcos = nArcos;
		this.nPasillos = nPasillos;
		this.tArco = tArco;
		this.tPasillo = tPasillo;
		tamaño = nArcos * tArco + nPasillos * this.tPasillo + nArcos + 1; 
		limites = new ArrayList<Integer>();
		for (int i = 1; i<nPasillos+1; i++)
			limites.add(nArcos * tArco + tArco + tPasillo * i);
	}

	public int[] mover(int posiciones, int n) {
		int[] pos = new int[6]; 
		if (n == 0 && posiciones < 6)
			for (int i = 0; i < 6; i++)
				pos[i] = tamaño - posiciones - tPasillo * i;
		else if (n == 0 && posiciones == 6)
			for (int i = 0; i < 6; i++)
				pos[i] = 1 + i * (tArco + 1);
		else {
			if (n >= nArcos * tArco + tArco && n < tamaño - 1) 
				// es una casilla de los pasillos; máximo 5 movimientos
				return filtraPasillo(posiciones, n);
			else // es un quesito o casilla de los arcos
			{
				// maximo 3 movimientos
				if (posibleQuesito(n)) {
					int[] aux = calculaSiguienteCasilla(n);
					aux[0] = aux[0] + posiciones - 1;
					aux[1] = aux[1] - posiciones + 1;
					if (posiciones == 6)
						aux[2] = 0;
					else
						aux[2] = aux[2] + posiciones - 1;
					return aux;
				}
				// maximo 6 posibles movimientos
				else {
					return filtraArco(posiciones, n);
				}
			}
		}
		return pos;
	}

	public ArrayList<Integer> sacaTodo(int posiciones, int n) {
		int i = 0;
		ArrayList<Integer> conformado = new ArrayList<Integer>();
		conformado.add(n);
		while (i < posiciones) {
			int[] aux = null;
			ArrayList<Integer> copy = new ArrayList<Integer>(conformado);
			for (Integer e : copy) {
				aux = calculaSiguienteCasilla(e);
				if (aux != null) {
					for (int j = 0; j < aux.length; j++)
						if (!copy.contains(aux[j]))
							conformado.add(aux[j]);
				}
			}
			i++;
		}
		return conformado;
	}

	public int[] filtraArco(int posiciones, int n) {
		ArrayList<Integer> conformado = sacaTodo(posiciones, n);
		ArrayList<Integer> copy = new ArrayList<Integer>();
		Comparator<Integer> comparador = Collections.reverseOrder();
		Collections.sort(conformado, comparador);
		copy.add(conformado.get(0));
		copy.add(n + posiciones);
		if (n - posiciones <= 0) // caso especial
			copy.add(tArco * (tPasillo + 2) - posiciones + n);
		else
			copy.add(n - posiciones);
		// falta calcular la posición en el pasillo de menor número
		
		int[] r = new int[copy.size()];
		for (int j = 0; j < copy.size(); j++)
			r[j] = copy.get(j);
		return r;
	}
	
	public int[] filtraPasillo(int posiciones, int n) {
		// una vez en el pasillo solo se podrá mover hacia la casilla de meta no volver hacia atrás
		if ((n + posiciones + 2) % 5 == 0) //  entramos en la casilla central
			return new int[]{0};
		else if (medioCamino(posiciones, n)) // nos quedamos a medio camino de la casilla central
			return new int[]{n + posiciones};
		else{ // nos pasamos y podremos ir a todas los demás pasillos / rama
			return buscadorRamas(posiciones, n);
		}
	}

	// método que se tendrá que quitar y omitir por su equivalente 
	public boolean medioCamino(int posiciones, int n)
	{
		return posiciones + n <= limites.get(mejorAjuste(n));
	}
	
	public int mejorAjuste(int n)
	{
		for (int i = 0; i<limites.size(); i++)
			if (limites.get(i) >= n)
				return i;
		return 0;
	}
	
	public int[] buscadorRamas(int posiciones, int n) {
		int[] r = new int[nPasillos];
		int ajuste = mejorAjuste(n);
		int valorAjuste = limites.get(ajuste);
		int j = 0;
		int k = 1;
		int local = posiciones + n -valorAjuste - 2 ;
		for (int i = ajuste + 1; i<nPasillos; i++)
		{
			r[j] =  n + 5 * k - local;
			j++;
			k++;
		}
		// falta calcular las ramas inferiores y eliminar posiciones inválidas
		return r;
	}
	
	/**
	 * Calculará las siguientes posiciones que se podrán dar a partir de la
	 * casilla introducida Siendo 2 si la casilla que no sea un quesito y 3 si
	 * la casilla es un quesito.
	 * 
	 * @param n
	 * @return int[] con siguiente-atrás-(opcional)siguiente en el pasillo
	 */
	public int[] calculaSiguienteCasilla(int n) {
		if (n == 0) {
			int[] res = new int[nArcos];
			for (int i = 0; i < nArcos; i++)
				res[i] = tArco * nArcos + tArco + i * tPasillo;
			return res;
		} else if (n >= 1 && n <= nArcos * tArco && posibleQuesito(n)) 
			if (n == 1)
				return new int[] { n + 1, nArcos * tArco + tArco,
						calculaPosicionQuesito(n) };
			else
				return new int[] { n + 1, n - 1, calculaPosicionQuesito(n) };
		else if (n > nArcos * tArco + tArco && n < tamaño - 1 && posibleFin(n)) 
			return new int[] { 0, n - 1 }; // la siguiente será la casilla 0
		else if (n >= 0 && n <= tamaño - 1)
			return new int[] { n + 1, n - 1 };
		else
			return null;
	}

	/**
	 * Calcula la primera posición en la calle saliendo de un quesito
	 * 
	 * @param n
	 * @return int
	 */
	public int calculaPosicionQuesito(int n) {
		return (tamaño - nPasillos * tPasillo) - 2 * ((int) n / (tArco + 1))
				+ n - 1;
	}
	
	/**
	 * se hace la inversa de la función del quesito sobre la casilla que este pegada al quesito.
	 * 
	 */
	public int recuperaQuesito(int n)
	{
		// debido a que no se recuperará un numero entero no se calculará bien.. 
		return -1;
	}

	/**
	 * Indica si la casilla pertenece a un quesito
	 * 
	 * @param n
	 * @return
	 */
	public boolean posibleQuesito(int n) {
		return n % (tArco + 1) - 1 == 0;
	}

	/**
	 * Indica si la siguiente casilla será la 0
	 * 
	 * @param n
	 * @return
	 */
	public boolean posibleFin(int n) {
		return n % tPasillo - 2 == 0;
	}

	public int[] getTablero() {
		return tablero;
	}

	public void setTablero(int[] tablero) {
		this.tablero = tablero;
	}

}
