package es.uniovi.asw.steps;

import org.junit.Assert;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.modelo.SimulaBD;
import es.uniovi.asw.modelo.UserSimplificado;

public class GameSteps {

	private SimulaBD bd;
	private UserSimplificado activo;
	
	@Dada("^la lista de usuarios del sistema")
	public void la_siguiente_lista_de_usuarios() throws Throwable {
		bd = new SimulaBD();
		Assert.assertFalse(bd.findPreguntas().equals(null));
	}

	@Cuando("^introduzco el nombre \"(.*?)\" y la clave \"(.*?)\"$")
	public void introduzco_el_nombre_y_la_clave(String usuario, String password) throws Throwable {	
		activo = bd.findUsuario(usuario, password);
	}

	//login exitoso
	@Entonces("^puedo entrar en el sistema")
	public void puedo_entrar_en_el_sistema() throws Throwable {
		Assert.assertFalse(activo == null);
	}
	
	//login fallido
	@Entonces("^no puedo entrar en el sistema")
	public void no_puedo_entrar_en_el_sistema() throws Throwable {
		Assert.assertTrue(activo == null);
	}
	
	//registro exitoso
	@Entonces("^el usuario con el nombre \"(.*?)\" y la clave \"(.*?)\" registrado con exito")
	public void puedo_registrarme_en_el_sistema(String user, String password) throws Throwable {
		Assert.assertTrue(bd.insertUsuario(new UserSimplificado(user, password, false)));
	}
	
	//registro fallido
	@Entonces("^usuario no registrado")
	public void no_puedo_registrarme_en_el_sistema() throws Throwable {
		Assert.assertFalse(bd.insertUsuario(activo));
	}

}