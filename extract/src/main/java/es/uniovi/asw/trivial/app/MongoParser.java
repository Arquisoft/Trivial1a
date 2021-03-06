package es.uniovi.asw.trivial.app;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.infrastructure.Factory;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialDAO;

public class MongoParser implements TrivialParser {

	@Override
	public Trivial parse() throws BusinessException 
	{
		TrivialDAO dao = Factory.persistence.createTrivialDAO();
		Trivial trivial = new Trivial();
		
		for(Question q : dao.findAllQuestions())
			trivial.addQuestion(q);
		
		return trivial;
	}
}