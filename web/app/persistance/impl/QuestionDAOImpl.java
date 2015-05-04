package persistance.impl;

import java.util.List;

import model.Question;
import net.vz.mongodb.jackson.JacksonDBCollection;
import persistance.QuestionDAO;
import play.modules.mongodb.jackson.MongoDB;

public class QuestionDAOImpl implements QuestionDAO{

	private JacksonDBCollection<Question, String> questions;

	public QuestionDAOImpl() {
		
		questions = MongoDB.getCollection("questions", Question.class, String.class);
	}
	
	@Override
	public List<Question> getQuestions() { 
		return questions.find().toArray();
	}

}
