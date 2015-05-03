package model;

import play.data.validation.Constraints.Required;

public class Login {

	@Required
	private String login;
	@Required
	private String password;

	public Login() {

		this.login = "";
		this.password = "";
	}

	public Login(String login, String password) {

		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validate() {
		
		User user = User.findByLogin(login);

		if (user == null || !user.password.equals(password)) {
			return "¡El usuario y/o la contraseña no se corresponden con "
					+ "ningún usuario válido en el sistema!";
		}

		return null;
	}
}