package es.uniovi.asw.game.view;

import es.uniovi.asw.game.aplication.Game;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class ViewFactory {

	public static View getDeffaultView(Game game) {

		return new DefaultView(game, "default View");
	}

	public static View getTestView1(Game game) {
		return new TestView1(game, "test view1");
	}

	public static View getTestView2(Game game) {
		return new TestView2(game, "test view2");
	}
}
