package es.uniovi.asw;

import es.uniovi.asw.game.aplication.ConcretGame;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class App {
	public static void main(String[] args) {
//		for (int i = 0; i < 43 ;i++) {
//			System.out.println("i: "+i+"\t"+((i-2%42)));
//		}
//		
		ConcretGame game = new ConcretGame();
		game.run();
	}

}
