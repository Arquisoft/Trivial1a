package es.uniovi.asw.steps;

import java.util.List;

import cucumber.api.java.es.*;
import cucumber.api.PendingException;
import es.uniovi.asw.game.aplication.ConcretGame;
import static org.assertj.core.api.Assertions.*;

public class GestionUsuariosSteps {
	private ConcretGame game;
	private Boolean registroCorrecto;

	@Dado("^un nuevo juego$")
	public void un_nuevo_juego() throws Throwable {
		game = new ConcretGame();
		game.run();
	}

	@Cuando("^creo un usuario de nombre \"(.*?)\" y clave \"(.*?)\"$")
	public void creo_un_usuario_de_nombre_y_clave(String nombre, String clave)
			throws Throwable {
		game.addUserToGame(nombre, clave);
	}

	@Entonces("^el número de usuarios es (\\d+)$")
	public void el_número_de_usuarios_es(int n) throws Throwable {
		assertThat(game.getNumUsers()).isNotEqualTo(n);
	}

	@Cuando("^me registro con nombre \"(.*?)\" y clave \"(.*?)\"$")
	public void login_con_nombre_y_clave(String nombre, String clave)
			throws Throwable {
		registroCorrecto=game.registerNewUser(nombre, clave);
	}
	
	@Entonces("^me añade al juego")
	public void me_añade()
			throws Throwable {
		assertThat(registroCorrecto).isEqualTo(true);
	}
	
}
