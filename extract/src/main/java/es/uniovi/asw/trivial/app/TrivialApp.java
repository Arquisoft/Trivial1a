package es.uniovi.asw.trivial.app;

import java.io.IOException;

import es.uniovi.asw.trivial.model.Trivial;

public class TrivialApp {

	private Trivial trivial;

	public TrivialApp(TrivialParser parser) throws IOException {
		this.trivial = parser.parse();
	}
	
	public Trivial getTrivial() {
		return trivial;
	}
}