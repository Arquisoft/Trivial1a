package es.uniovi.asw.trivial.app;

import es.uniovi.asw.trivial.model.Trivial;

public interface Parser {
	Trivial parse(String file);
}
