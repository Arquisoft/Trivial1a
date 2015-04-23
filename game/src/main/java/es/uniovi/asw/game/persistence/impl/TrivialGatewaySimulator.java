package es.uniovi.asw.game.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.persistence.TrivialDAO;

public class TrivialGatewaySimulator implements TrivialDAO {

	private static List<User> users = new ArrayList<User>();
	@Override
	
	public boolean addUser(User user) {
		if(!existUser(user)){
			users.add(user);
			return true;
		}
		return false;
	}

	@Override
	public User loadUser(String username, String passwd) 
	{
		User admin = new User("admin", "admin", true, 20, 2);
		if(!existUser(admin))
			users.add(admin);
		
		for(User u : users)
		if(u.getName().equals(username)&&u.getPasswd().equals(passwd))
			return u;
		
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
		
		Question q1 = new Question();
		q1.addComment("pregunta simulada");
		q1.setName("de que color");
		q1.setCategory("deportes");
		q1.setQuestion("¿de que color es el caballo blanco de Santiago?");
		Answer a1 = new Answer("negro");
		a1.setIsCorrect(false);
		Answer a2 = new Answer("blanco");
		a2.setIsCorrect(true);
		Answer a3 = new Answer("amarillo");
		a3.setIsCorrect(false);
		q1.addAnswer(a1);
		q1.addAnswer(a2);
		q1.addAnswer(a3);
		
		Question q2 = new Question();
		q2.addComment("otra pregunta simulada");
		q2.setName("que");
		q2.setCategory("deportes");
		q2.setQuestion("¿que deporte es el más visto en España?");
		Answer a2_1 = new Answer("el futbol");
		a2_1.setIsCorrect(true);
		Answer a2_2 = new Answer("el tenis");
		a2_2.setIsCorrect(false);
		Answer a2_3 = new Answer("la natación");
		a2_3.setIsCorrect(false);
		Answer a2_4 = new Answer("el baloncesto");
		a2_4.setIsCorrect(false);
		q2.addAnswer(a2_1);
		q2.addAnswer(a2_2);
		q2.addAnswer(a2_3);
		q2.addAnswer(a2_4);
		
		
		questions.add(q1);
		return questions;
	}

	@Override
	public void dropDatabase() {
		users.clear();
		
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public boolean existUser(User user) 
	{
		for(User u :users)
			if(u.getName().equals(user.getName()) && u.getPasswd().equals(user.getPasswd()))
			return true;
		return false;
	}

}
