package persistance;

import java.util.List;

import model.User;

public interface UserDAO {

	void save(User user);
//	void update(User user);
//	void delete(String login);
	User findByLogin(String login);
	List<User> getUsers();
}