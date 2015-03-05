package es.uniovi.asw.trivial.persistence.impl;

import java.util.List;

import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialDAO;

public class TrivialGatewaySimulator implements TrivialDAO {

	private Trivial dataBase;

	@Override
	public void saveQuestions(Trivial trivial) {
		this.dataBase = trivial;
	}

	@Override
	public List<Question> findAllQuestions() {
		return dataBase.getQuestions();
	}

	@Override
	public void deleteAllQuestions() {
		if (dataBase != null)
			dataBase.getQuestions().clear();
	}
}