package model;

import play.data.validation.Constraints.Required;

public class Login {
	@Required
	public String name = "";
	public String password = "";
	
	public String validate() {
		
		if(password.isEmpty()){
			return "Introduzca password";
		}
		
        if (UserOld.authenticate(name, password) == null) {
            return "Usuario o password incorrecto";
        }
        return null;
    }

}
