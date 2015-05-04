package persistance.impl;

import persistance.PersistenceFactory;
import persistance.QuestionDAO;
import persistance.UserDAO;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UserDAO createUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public QuestionDAO createQuestionDAO() {
		return new QuestionDAOImpl();
	}
}