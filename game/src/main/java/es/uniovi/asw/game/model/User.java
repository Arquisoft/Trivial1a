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
	private int casilla;
	
	private boolean quesitoAmarillo;
	private boolean quesitoAzul;
	private boolean quesitoMarron;
	private boolean quesitoNaranja;
	private boolean quesitoRosa;
	private boolean quesitoVerde;

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
		casilla = 0; //Central
		quesitoAmarillo = false;
		quesitoAzul= false;
		quesitoMarron= false;
		quesitoNaranja= false;
		quesitoRosa= false;
		quesitoVerde= false;
		
	}

	

	public boolean isQuesitoAmarillo() {
		return quesitoAmarillo;
	}



	public void setQuesitoAmarillo(boolean quesitoAmarillo) {
		this.quesitoAmarillo = quesitoAmarillo;
	}



	public boolean isQuesitoAzul() {
		return quesitoAzul;
	}



	public void setQuesitoAzul(boolean quesitoAzul) {
		this.quesitoAzul = quesitoAzul;
	}



	public boolean isQuesitoMarron() {
		return quesitoMarron;
	}



	public void setQuesitoMarron(boolean quesitoMarron) {
		this.quesitoMarron = quesitoMarron;
	}



	public boolean isQuesitoNaranja() {
		return quesitoNaranja;
	}



	public void setQuesitoNaranja(boolean quesitoNaranja) {
		this.quesitoNaranja = quesitoNaranja;
	}



	public boolean isQuesitoRosa() {
		return quesitoRosa;
	}



	public void setQuesitoRosa(boolean quesitoRosa) {
		this.quesitoRosa = quesitoRosa;
	}



	public boolean isQuesitoVerde() {
		return quesitoVerde;
	}



	public void setQuesitoVerde(boolean quesitoVerde) {
		this.quesitoVerde = quesitoVerde;
	}



	public int getCasilla() {
		return casilla;
	}

	public void setCasilla(int casilla) {
		this.casilla = casilla;
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
