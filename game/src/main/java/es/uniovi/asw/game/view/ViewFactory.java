package es.uniovi.asw.game.view;

import es.uniovi.asw.game.aplication.Game;

public class ViewFactory {

	public static View getDeffaultView(Game game) {

		return new DefaultView(game, "default View");
	}

	public static View getTestView1(Game game) {
		return new TestView1(game, "test view1");
	}

	public static View getTeView2(Game game) {
		return new TestView2(game, "test view2");
	}
}
