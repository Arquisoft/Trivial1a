package model;

import play.data.validation.Constraints.Required;

public class Register {


	public String userName ;

	public String name;

	public String surName ;

	public String password;

	public String password2;

	public String validate() {
		if(userName.isEmpty() || name.isEmpty() || surName.isEmpty() || password.isEmpty() || password2.isEmpty())
			return "Introduzca todos los datos";
			
		if(User.existUserName(userName))
			return "Nombre de usuario ya existente";
		
		if(!password.equals(password2))
			return "Las contraseñas no coinciden";
				
		return null;
	}

}
