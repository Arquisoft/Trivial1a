package es.uniovi.asw.trivial.persistence;


public interface PersistenceFactory {
	
	TrivialDAO createTrivialDAO();
	TrivialDAO createTrivialSimulator();
}