package es.uniovi.asw.game.persistence;

import java.util.List;

import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;

public interface TrivialDAO {
	boolean addUser(User user);

	User loadUser(String username, String passwd);

	void updateUser(User user);
	void updateQuestion(Question question);
	boolean existUser(User user);

	List<Question> findAllQuestions();
	List<User> findAllUsers();
	void dropDatabase();

}
