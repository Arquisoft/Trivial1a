package es.uniovi.asw.game.aplication;

import java.util.List;

import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;

public interface Game {
	
	public User[] getUsers();
	public void setView(View view);
	public boolean addUserToGame(String name,String passwd);
	public void deleteUserToGame(User user);
	public boolean registerNewUser(String name, String passwd);
	List<Question> loadQuestions();
	List<User> loadUsers();
	public Question getQuestion(String categoria);
	public boolean isAcorrectAnswer(String selectedAnswer);
	public User getCurrentUser();
	public int getCurrebtUserIndex();
	public Integer[] calculateNextPositions(int number);
	public void createBoard(int diameter, int size);
	public boolean isWinner();
	public int getNumUsers();
	public void run();
}
