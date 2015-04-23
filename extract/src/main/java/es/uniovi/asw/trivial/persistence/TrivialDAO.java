package es.uniovi.asw.trivial.persistence;

import java.util.List;

import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

/**
 * Interfaz que proporciona los servicios de persistencia
 * @author Trivial1a
 *
 */
public interface TrivialDAO {

	/**
	 * SaveQuestions guarda las {@Question} relacionadas con el trivial
	 * @param {@Trivial}
	 */
	void saveQuestions(Trivial trivial);
	
	/**
	 * FindAllQuestions devuelve en una List de {@Question} las {@Question} almacenadas
	 * @return List<Question> {@Question}
	 */
	List<Question> findAllQuestions();
	
	/**
	 * DeleteAllQuestions borra todas las {@Question}
	 */
	void deleteAllQuestions();
}
