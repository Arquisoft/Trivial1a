package es.uniovi.asw.trivial.persistence.impl;

import java.util.ArrayList;
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
		if (dataBase != null)
			return dataBase.getQuestions();
		
		return new ArrayList<Question>();
	}

	@Override
	public void deleteAllQuestions() {
		if (dataBase != null)
			dataBase.getQuestions().clear();
	}
}