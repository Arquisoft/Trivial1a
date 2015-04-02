package es.uniovi.asw.game.aplication;

import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;

/**
 * Interfaz que define las funcionalidades del juego,
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public interface Game {
	
	/**
	 * Devuelve una lista con los  usuarios asociados a la partida
	 * @return User[]
	 */
	public User[] getUsers();
	
	/**
	 * Establece la vista con la que trabajará el vista con la que trabajará el juego.
	 * @param view
	 */
	public void setView(View view);
	
	/**
	 * Añade un usuario en la lista de los jugadores activos en la partida
	 * @param name	 String nombre asociada al nuevo usuario
	 * @param passwd String contraseña asociada al nuevo usuario
	 * @return
	 */
	public boolean addUserToGame(String name,String passwd);
	
	/**
	 * Borra un usuario en la lista de los jugadores activos en la partida
	 * @param user User usuario a borrar 
	 */
	public void deleteUserToGame(User user);
	
	/**
	 * A partir de un nombre y una contraseña permite registrar un nuevo usuario que podrá estar
	 * activo en el juego.
	 * @param name		String nombre asociada al nuevo usuario
	 * @param passwd	String contraseña asociada al nuevo usuario
	 * @return
	 */
	public boolean registerNewUser(String name, String passwd);
}
