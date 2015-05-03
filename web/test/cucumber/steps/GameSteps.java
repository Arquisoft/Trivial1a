package cucumber.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.Box;
import model.Player;
import model.Trivial;
import model.User;
import model.util.Graph;

import org.junit.Assert;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import cucumber.modelo.SimulaBD;
import cucumber.modelo.UserSimplificado;

public class GameSteps {

	private static SimulaBD bd;
	private static UserSimplificado activo;
	private static Trivial trivial;
	private static List<Integer> posiblesMovimientos;
	private static int ref;
	private static Player estadisticas;
	
	@Dada("^la lista de usuarios del sistema$")
	public void la_siguiente_lista_de_usuarios() throws Throwable {
		bd = new SimulaBD();
		Assert.assertFalse(bd.findPreguntas().equals(null));
	}

	@Cuando("^introduzco el nombre \"(.*?)\" y la clave \"(.*?)\"$")
	public void introduzco_el_nombre_y_la_clave(String usuario, String password) throws Throwable {	
		activo = bd.findUsuario(usuario, password);
	}

	//login exitoso
	@Entonces("^puedo entrar en el sistema$")
	public void puedo_entrar_en_el_sistema() throws Throwable {
		Assert.assertFalse(activo == null);
	}
	
	//login fallido
	@Entonces("^no puedo entrar en el sistema$")
	public void no_puedo_entrar_en_el_sistema() throws Throwable {
		Assert.assertTrue(activo == null);
	}
	
	//registro exitoso
	@Entonces("^el usuario con el nombre \"(.*?)\" y la clave \"(.*?)\" registrado con exito$")
	public void puedo_registrarme_en_el_sistema(String user, String password) throws Throwable {
		Assert.assertTrue(bd.insertUsuario(new UserSimplificado(user, password, false)));
	}
	
	//registro fallido
	@Entonces("^usuario no registrado$")
	public void no_puedo_registrarme_en_el_sistema() throws Throwable {
		Assert.assertFalse(bd.insertUsuario(activo));
	}
	
	//// Fin gestión usuarios
	
	/// Jugar una partida
	@Dada("^una aplicacion iniciada$")
	public void aplicacion_iniciada() throws Throwable {
		trivial = new Trivial();
	}
	
	//añadir jugadores
	@Cuando("^añado un jugador$")
	public void añado_jugador() throws Throwable {
		ref = trivial.getPlayers().size();
		trivial.addPlayer(new Player(new User("pepe")));
	}
	
	// borrar jugadores
	@Cuando("^borro un jugador que existe en la partida$")
	public void borro_jugador() throws Throwable {
		añado_jugador(); // para verificar que exista
		ref = trivial.getPlayers().size();
		trivial.removePlayer(new Player(new User("pepe")));
	}
	
	@Entonces("^tendre otro jugador mas en la partida$")
	public void jugador_mas_en_partida() throws Throwable {
		Assert.assertTrue(trivial.getPlayers().size() == ref+1);
	}
	
	@Entonces("^tendre otro jugador menos en la partida$")
	public void jugador_menos_en_partida() throws Throwable {
		Assert.assertTrue(trivial.getPlayers().size() == ref);
	}
	
	//estadisticas
	@Dada("^un jugador con ninguna pregunta acertada ni fallada$")
	public void jugador_sin_estadisticas() throws Throwable {
		estadisticas = new Player(new User("Pepe"));
		estadisticas.setWins(0);
		estadisticas.setFails(0);
	}
	
	@Cuando("^acierta una pregunta$")
	public void acierta_pregunta() throws Throwable {
		ref = estadisticas.getWins();
		estadisticas.setWins(estadisticas.getWins()+1); // quizas un metodo de incrementar y decrementar
	}
	
	@Entonces("^tendra una pregunta mas acertada$")
	public void pregunta_mas_acertada() throws Throwable {
		Assert.assertTrue((ref + 1)==estadisticas.getWins());
	}
	
	@Y("^si falla una pregunta$")
	public void falla_pregunta() throws Throwable {
		ref = estadisticas.getFails();
		estadisticas.setFails(estadisticas.getFails()+1);
	}
	
	@Entonces("^tendra una pregunta mas fallada$")
	public void pregunta_mas_fallada() throws Throwable {
		Assert.assertTrue((ref + 1)==estadisticas.getFails());		
	}
	
	// creacion tablero
	@Dada("^un nuevo juego de trivial$")
	public void juego_nuevo() throws Throwable {
		trivial = new Trivial();
	}
	
	@Entonces("^compruebo el numero de casillas y los colores de ellas$")
	public void comprueba_casillas_preguntas() throws Throwable {
		Assert.assertTrue((trivial.getGraph().getBoxes().length - 2) % 6 == 0); 
		Assert.assertTrue((trivial.getPlayers().size() == 0)); 
		Assert.assertTrue((trivial.getGraph() != null));
		//	Assert.assertTrue((!trivial.getQuestions().isEmpty()));
	}
	
	// mover ficha desde la casilla 6 con un 2 podré ir a 15, 31, 43, 55, 67, 4
	@Dada("^un tablero del trivial y estoy en la casilla 6 y me sale un 2$")
	public void tablero_iniciado_para_mover_del_6_con_un_2() throws Throwable {
		Graph tablero = new Graph();
		posiblesMovimientos = tablero.getNextPositions(6, 2).stream().map(x -> x.getId()).collect(Collectors.toList());
	}
	
	@Entonces("^podre ir a las posiciones 19, 31, 43, 55, 67 y 4$")
	public void tablero_muevo_del_6_con_un_2() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>();
		movimientos.addAll(Arrays.asList(19, 31, 43, 55, 67, 4));
		movimientos.stream().forEach(x -> Assert.assertTrue(posiblesMovimientos.contains(x)));	
	}
	
	// mover ficha desde la casilla 1 con un 2 podré ir a 9, 72, 3
	@Dada("^un tablero del trivial y estoy en la casilla 1 y me sale un 2$")
	public void tablero_iniciado_para_mover_del_1_con_un_2() throws Throwable {
		Graph tablero = new Graph();
		posiblesMovimientos = tablero.getNextPositions(1, 2).stream().map(x -> x.getId()).collect(Collectors.toList());
	}
	
	@Entonces("^podre ir a las posiciones 9, 72, 3$")
	public void tablero_muevo_del_1_con_un_2() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>();
		movimientos.addAll(Arrays.asList(9, 72, 3));
		movimientos.stream().forEach(x -> Assert.assertTrue(posiblesMovimientos.contains(x)));	
	}
	
	// mover ficha desde la casilla 15 con un 5 podré ir a 7, 23, 10
	@Dada("^un tablero del trivial y estoy en la casilla 15 y me sale un 5$")
	public void tablero_iniciado_para_mover_del_15_con_un_5() throws Throwable {
		Graph tablero = new Graph();
		posiblesMovimientos = tablero.getNextPositions(15, 5).stream().map(x -> x.getId()).collect(Collectors.toList());
	}
	
	@Entonces("^podre ir a las posiciones 7, 23, 10$")
	public void tablero_muevo_del_15_con_un_5() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>();
		movimientos.addAll(Arrays.asList(7, 23, 10));
		movimientos.stream().forEach(x -> Assert.assertTrue(posiblesMovimientos.contains(x)));	
	}
	
	// acertar casilla
	@Dada("^un jugador sin quesitos que cae en la casilla 1$")
	public void jugador_sin_quesitos_casilla_quesito() throws Throwable {
		trivial = new Trivial();
		Player player = new Player(new User("Pepe"));
		player.setActual(new Box(1));
		trivial.addPlayer(player);
	}
	
	@Entonces("^acierta la pregunta y se le asigna un quesito$")
	public void asignar_pregunta_casilla_1() throws Throwable {
		Player player = trivial.getPlayers().stream().filter(x -> x.getUser().
				userName.equals("Pepe")).collect(Collectors.toList()).get(0);
		int n = player.getWedges().size();
		player.addWedge(new Graph().getBox(1).getCategory());
		Assert.assertTrue((n + 1) == player.getWedges().size());
	}
	
	@Dada("^un jugador con el quesito de la casilla 1 que cae en una casilla 1$")
	public void jugador_con_quesitos_casilla_1() throws Throwable {
		trivial = new Trivial();
		Player player = new Player(new User("Pepe"));
		player.setActual(new Box(1));
		player.addWedge(new Graph().getBox(1).getCategory());
		trivial.addPlayer(player);
	}
	
	@Entonces("^acierta la pregunta y no se le asigna el quesito al tenerlo ya$")
	public void no_asignar_pregunta_casilla_1() throws Throwable {
		Player player = trivial.getPlayers().stream().filter(x -> x.getUser().
				userName.equals("Pepe")).collect(Collectors.toList()).get(0);;
		int n = player.getWedges().size();
//error		
		player.addWedge(new Graph().getBox(1).getCategory()); // no se deberia poder añadir; deberia ser 
			// addWedge la encargada de comprobar si se puede añadir
		Assert.assertTrue((n) == player.getWedges().size());
	}	
	
	@Dada("^un jugador con el quesito de la casilla 1 que cae en la casilla 14$")
	public void jugador_con_quesitos_casilla_14() throws Throwable {
		trivial = new Trivial();
		Player player = new Player(new User("Pepe"));
		player.setActual(new Box(1));
		player.addWedge(new Graph().getBox(1).getCategory());
		trivial.addPlayer(player);
	}
	
	@Entonces("^acierta la pregunta y se le asigna el quesito$")
	public void asignar_pregunta_casilla_14() throws Throwable {
		Player player = trivial.getPlayers().stream().filter(x -> x.getUser().
				userName.equals("Pepe")).collect(Collectors.toList()).get(0);;
		int n = player.getWedges().size();
		player.addWedge(new Graph().getBox(14).getCategory());
		Assert.assertTrue((n + 1) == player.getWedges().size());
	}
}