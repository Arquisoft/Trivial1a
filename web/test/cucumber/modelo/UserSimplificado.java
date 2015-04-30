package cucumber.modelo;

public class UserSimplificado{
	public String user;
	public String password;
	public boolean admin;
	
	public UserSimplificado(String user, String password, boolean admin){
		this.user = user;
		this.password = password;
		this.admin = admin;
	}
}
