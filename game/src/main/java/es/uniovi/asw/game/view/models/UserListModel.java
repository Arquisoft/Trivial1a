package es.uniovi.asw.game.view.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import es.uniovi.asw.game.model.User;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class UserListModel extends AbstractListModel {
	ArrayList<User> users = new ArrayList<User>();

	@Override
	public Object getElementAt(int index) {
		return users.get(index);
	}

	@Override
	public int getSize() {
		return users.size();
	}

	public void addUser(User u) {
		users.add(u);
	}

	public User getUserAt(int index) {
		return users.get(index);
	}

}
