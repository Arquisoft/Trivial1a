package es.uniovi.asw.game.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.persistence.TrivialDAO;

public class TrivialGatewaySimulator implements TrivialDAO {

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User loadUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> findAllQuestions() {
		List<Question> questions = new ArrayList<Question>();
		Question q = new Question();
		q.addComment("pregunta simulada");
		q.setName("de que color");
		q.setCategory("DEPORTE");
		q.setQuestion("¿de que color es el caballo blanco de Santiago?");
		Answer a1 = new Answer("negro");
		a1.setIsCorrect(false);
		Answer a2 = new Answer("blanco");
		a2.setIsCorrect(true);
		q.addAnswer(a1);
		q.addAnswer(a2);
		questions.add(q);
		return questions;
	}

	@Override
	public void dropDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
