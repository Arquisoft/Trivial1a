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
	private int rightQuestions;
	private int failedQuestions;

	/**
	 * Instantiates a new user.
	 *
	 * @param name
	 *            the name
	 * @param passwd
	 *            the passwd
	 */
	public User(String name, String passwd, boolean admin, int rightQuestions, int failedQuestions) {
		this.name = name;
		this.passwd = passwd;
		//default false solo de debe modificar desde la aplicación de los administradores(en la bd)
		this.admin= admin;
		this.rightQuestions=rightQuestions;
		this.failedQuestions = failedQuestions;
	}

	
	

	public String getName() {
		return name;
	}



	public String getPasswd() {
		return passwd;
	}




	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}




	public int getRightQuestions() {
		return rightQuestions;
	}




	public void incRightQuestions() {
		rightQuestions++;
	}




	public int getFailedQuestions() {
		return failedQuestions;
	}




	public void incFailedQuestions() {
		failedQuestions++;
	}




	public boolean isAdmin(){
		return admin;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
