package es.uniovi.asw;

import es.uniovi.asw.game.aplication.ConcretGame;
import es.uniovi.asw.game.aplication.Game;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class App {
	public static void main(String[] args) {

		
		Game game = new ConcretGame();
		game.run();
	}
}
