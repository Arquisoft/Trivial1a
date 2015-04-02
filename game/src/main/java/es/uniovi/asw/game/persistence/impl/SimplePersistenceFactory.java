package es.uniovi.asw.game.persistence.impl;

import es.uniovi.asw.game.persistence.PersistenceFactory;
import es.uniovi.asw.game.persistence.TrivialDAO;

/**
 * A factory for creating SimplePersistence objects.
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a> 
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public TrivialDAO createTrivialDAO() {
		return new TrivialGatewayImpl();
	}

	@Override
	public TrivialDAO createTrivialSimulator() {
		return new TrivialGatewaySimulator();
	}
}