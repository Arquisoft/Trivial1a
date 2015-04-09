package es.uniovi.asw.game.view.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import es.uniovi.asw.game.model.User;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */

public class UserListModel extends AbstractListModel<User> {

	private static final long serialVersionUID = 1L;
	
	ArrayList<User> users = new ArrayList<User>();

	@Override
	public User getElementAt(int index) {
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
