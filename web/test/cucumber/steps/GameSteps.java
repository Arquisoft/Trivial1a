package cucumber.steps;

import java.util.ArrayList;
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
import cucumber.modelo.SimulaBD;
import cucumber.modelo.UserSimplificado;

public class GameSteps {

	private static SimulaBD bd;
	private static UserSimplificado activo;
	private static Trivial trivial;
	private static List<Integer> posiblesMovimientos;
	private static int ref;
	
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
	
	// mover ficha desde la casilla 6 con un 2 podré ir a 15, 31, 43, 56, 67, 4
	@Dada("^un tablero del trivial y estoy en la casilla 6 y me sale un 2$")
	public void tablero_iniciado_para_mover_del_6_con_un_2() throws Throwable {
		Graph tablero = new Graph();
		// posiblesMovimientos = tablero.getNextPositions(6, 2).stream().map(x -> x.getId()).collect(Collectors.toList());
		posiblesMovimientos = new ArrayList<Integer>();
		for (Box b : tablero.getNextPositions(6, 2))
			posiblesMovimientos.add(b.getId());
	}
	
	public void tablero_muevo_del_6_con_un_2() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>() 
				{{ add(19); add(31); add(43); add(56); add(67); add(4); }};
	}
	
	// mover ficha desde la casilla 1 con un 2 podré ir a 9, 72, 3
	@Dada("^un tablero del trivial y estoy en la casilla 1 y me sale un 2$")
	public void tablero_iniciado_para_mover_del_1_con_un_2() throws Throwable {
		Graph tablero = new Graph();
		// posiblesMovimientos = tablero.getNextPositions(1, 2).stream().map(x -> x.getId()).collect(Collectors.toList());
		posiblesMovimientos = new ArrayList<Integer>();
		for (Box b : tablero.getNextPositions(1, 2))
			posiblesMovimientos.add(b.getId());
	}
	
	public void tablero_muevo_del_1_con_un_2() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>() 
				{{ add(9); add(72); add(3); }};
		for (Integer i : movimientos)
			Assert.assertTrue(posiblesMovimientos.contains(i));
	}
	
	// mover ficha desde la casilla 15 con un 5 podré ir a 7, 23, 10
	@Dada("^un tablero del trivial y estoy en la casilla 15 y me sale un 5$")
	public void tablero_iniciado_para_mover_del_15_con_un_5() throws Throwable {
		Graph tablero = new Graph();
		// posiblesMovimientos = tablero.getNextPositions(15, 5).stream().map(x -> x.getId()).collect(Collectors.toList());
		posiblesMovimientos = new ArrayList<Integer>();
		for (Box b : tablero.getNextPositions(15, 5))
			posiblesMovimientos.add(b.getId());
	}
	
	public void tablero_muevo_del_15_con_un_5() throws Throwable {
		ArrayList<Integer> movimientos = new ArrayList<Integer>() 
				{{ add(7); add(23); add(10); }};
		for (Integer i : movimientos)
			Assert.assertTrue(posiblesMovimientos.contains(i));
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
		// Player player = trivial.getPlayers().stream().filter(x -> x.getUser().getLogin().equals("Pepe")).collect(Collectors.toList()).get(0);
		Player player = null;
		for (Player p : trivial.getPlayers())
			if (p.getUser().getLogin().equals("Pepe"))
				player = p;
		int n = player.getWedges().size();
		player.getWedges().add(new Graph().getBox(1).getCategory());
		Assert.assertTrue((n + 1) == player.getWedges().size());
	}
	
	@Dada("^un jugador con el quesito de la casilla 1 que cae en una casilla 1$")
	public void jugador_con_quesitos_casilla_1() throws Throwable {
		trivial = new Trivial();
		Player player = new Player(new User("Pepe"));
		player.setActual(new Box(1));
		player.setPiece(new Graph().getBox(1).getCategory());
		trivial.addPlayer(player);
	}
	
	@Entonces("^acierta la pregunta y no se le asigna el quesito al tenerlo ya$")
	public void no_asignar_pregunta_casilla_1() throws Throwable {
		Player player = null;
		for (Player p : trivial.getPlayers())
			if (p.getUser().getLogin().equals("Pepe"))
				player = p;
		int n = player.getWedges().size();
		player.getWedges().add(new Graph().getBox(1).getCategory());
		Assert.assertTrue((n) == player.getWedges().size());
	}
	
	@Dada("^un jugador con el quesito de la casilla 1 que cae en la casilla 14$")
	public void jugador_con_quesitos_casilla_14() throws Throwable {
		trivial = new Trivial();
		Player player = new Player(new User("Pepe"));
		player.setActual(new Box(1));
		player.setPiece(new Graph().getBox(1).getCategory());
		trivial.addPlayer(player);
	}
	
	@Entonces("^acierta la pregunta y se le asigna el quesito$")
	public void asignar_pregunta_casilla_14() throws Throwable {
		Player player = null;
		for (Player p : trivial.getPlayers())
			if (p.getUser().getLogin().equals("Pepe"))
				player = p;
		int n = player.getWedges().size();
		player.getWedges().add(new Graph().getBox(14).getCategory());
		Assert.assertTrue((n + 1) == player.getWedges().size());
	}
}