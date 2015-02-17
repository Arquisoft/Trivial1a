package es.uniovi.asw.trivial.app;

import java.io.IOException;

import es.uniovi.asw.trivial.model.Trivial;

public interface TrivialParser {
	public Trivial parse() throws IOException;
}
