package es.uniovi.asw.game.persistence;

/**
 * Interfaz-factoria que proporciona los servicios de persistencia
 * @author Trivial1a
 *
 */
public interface PersistenceFactory {
	
	TrivialDAO createTrivialDAO();
	TrivialDAO createTrivialSimulator();
}