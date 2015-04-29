package model;

import model.types.Color;
import model.types.Role;


public class Test {

	public static void main(String[] args) {
		
		// ESTA CLASE ES UNA SIMPLE PRUEBA
//		Graph t = new Graph();
//		t.print();
//		for (Box b : t.getNextPositions(65, 6)) {
//			System.out.println(b.getId());
//		}
		
		Trivial trivial = new Trivial();
		
		
		// Usuario que se recupera de la base de datos en el inicio de sesión
		// si hay varios jugadores, se añaden a la partida creada por el usuario que inició la sesión
		User u = new User("login");
		u.setName("Login");
		u.setSurName("Login");
		u.setRole(Role.USER);
		u.setPassword("pass");
		
		Player p = new Player(u);
		p.setActual(trivial.getGraph().getBox(7));
		p.setPiece(Color.BLUE);
		
		trivial.addPlayer(p);
		trivial.game();
	}
}