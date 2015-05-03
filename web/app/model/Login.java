package model;

import play.data.validation.Constraints.Required;


public class Login {


	public String userName="";
	
	public String password="";
	
	public String validate() {
				
				if(password.isEmpty()){
					return "Introduzca password";
				}
				
		        if (User.authenticate(userName, password) == null) {
		            return "Usuario o password incorrecto";
		       }
		        return null;
	}
	
}