package persistance.impl;

import java.util.List;

import model.User;
import persistance.UserDAO;

public class UserDAOImpl implements UserDAO {

	//private DB dataBase;

	public UserDAOImpl() {
		
		//DB.algo();
	}
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}