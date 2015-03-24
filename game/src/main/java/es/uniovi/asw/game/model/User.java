package es.uniovi.asw.game.model;

/**
 * The Class User.
 */
public class User {

	/** The name. */
	private String name;

	/** The passwd. */
	private String passwd;
	private boolean admin;

	/**
	 * Instantiates a new user.
	 *
	 * @param name
	 *            the name
	 * @param passwd
	 *            the passwd
	 */
	public User(String name, String passwd, boolean admin) {
		this.name = name;
		this.passwd = passwd;
		//default false solo de debe modificar desde la aplicación de los administradores(en la bd)
		this.admin= admin;
	}

	public String getName() {
		return name;
	}

	public String getPasswd() {
		return passwd;
	}

	public boolean isAdmin(){
		return admin;
	}
}
