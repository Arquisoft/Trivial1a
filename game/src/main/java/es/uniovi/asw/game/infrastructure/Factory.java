package es.uniovi.asw.game.infrastructure;

import es.uniovi.asw.game.persistence.PersistenceFactory;
import es.uniovi.asw.game.persistence.impl.SimplePersistenceFactory;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class Factory {
	
	public static PersistenceFactory persistence = new SimplePersistenceFactory();
}