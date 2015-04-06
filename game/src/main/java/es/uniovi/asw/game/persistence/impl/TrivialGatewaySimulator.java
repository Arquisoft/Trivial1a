package es.uniovi.asw.game.persistence.impl;

import java.util.List;

import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.persistence.TrivialDAO;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
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
	public boolean updateUser(User user, User Data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Question> findAllQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

}
