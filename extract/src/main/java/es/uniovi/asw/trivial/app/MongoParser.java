package es.uniovi.asw.trivial.app;

import java.io.IOException;

import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialGateway;

public class MongoParser implements TrivialParser
{

	@Override
	public Trivial parse() throws IOException 
	{
		Trivial trivial = new Trivial();
		for(Question q : TrivialGateway.loadQuestions())
			trivial.addQuestion(q);
		return trivial;
	}

}
