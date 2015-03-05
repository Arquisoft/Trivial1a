package es.uniovi.asw.trivial.persistence;


public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public TrivialDAO createTrivialDAO() {
		return new TrivialGatewayImpl();
	}
}