package persistance.impl;

import persistance.PersistenceFactory;
import persistance.UserDAO;

public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UserDAO createUserDAO() {
		return new UserDAOImpl();
	}
}