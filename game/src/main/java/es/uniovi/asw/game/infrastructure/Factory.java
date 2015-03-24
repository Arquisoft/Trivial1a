package es.uniovi.asw.game.infrastructure;

import es.uniovi.asw.game.persistence.PersistenceFactory;
import es.uniovi.asw.game.persistence.impl.SimplePersistenceFactory;

public class Factory {
	
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
}