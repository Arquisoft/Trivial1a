package es.uniovi.asw.trivial.persistence;

import java.util.List;

import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

public interface TrivialDAO {

	void saveQuestions(Trivial trivial);
	List<Question> findAllQuestions();
	void deleteAllQuestions();
}
