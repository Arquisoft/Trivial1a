package es.uniovi.asw.game.persistence;

/**
 * Interfaz-factoria que proporciona los servicios de persistencia
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public interface PersistenceFactory {
	
	TrivialDAO createTrivialDAO();
	TrivialDAO createTrivialSimulator();
}