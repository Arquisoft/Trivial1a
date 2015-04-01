package es.uniovi.asw.game.aplication;

import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;

public interface Game {
	public User[] getUsers();
	public void setView(View view);
	public boolean addUserToGame(String name,String passwd);
	public void deleteUserToGame(User user);
	public boolean registerNewUser(String name, String passwd);
}
