package es.uniovi.asw.trivial.infrastructure;

import es.uniovi.asw.trivial.persistence.PersistenceFactory;
import es.uniovi.asw.trivial.persistence.impl.SimplePersistenceFactory;

public class Factory {
	
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
}