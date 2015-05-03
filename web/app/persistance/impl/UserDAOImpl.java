package persistance.impl;

import java.util.List;

import com.mongodb.BasicDBObject;

import model.User;
import net.vz.mongodb.jackson.JacksonDBCollection;
import persistance.UserDAO;
import play.modules.mongodb.jackson.MongoDB;

public class UserDAOImpl implements UserDAO {

	private JacksonDBCollection<User, String> users;

	public UserDAOImpl() {
		
		users = MongoDB.getCollection("users", User.class, String.class);
	}
	
	@Override
	public void save(User user) {
		users.save(user);
	}

	@Override
	public User findByLogin(String login) {
		
		return users.findOne(new BasicDBObject("login", login));
	}

	@Override
	public List<User> getUsers() {
		return users.find().toArray();
	}
}