package es.uniovi.asw.trivial.persistence.impl;

import es.uniovi.asw.trivial.persistence.PersistenceFactory;
import es.uniovi.asw.trivial.persistence.TrivialDAO;


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