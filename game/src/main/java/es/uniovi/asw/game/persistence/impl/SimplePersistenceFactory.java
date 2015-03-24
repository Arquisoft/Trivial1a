package es.uniovi.asw.game.persistence.impl;

import es.uniovi.asw.game.persistence.PersistenceFactory;
import es.uniovi.asw.game.persistence.TrivialDAO;

/**
 * A factory for creating SimplePersistence objects.
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