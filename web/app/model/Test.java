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
		
		Trivial trivial = new Trivial("pepe");
		
		
		// Usuario que se recupera de la base de datos en el inicio de sesión
		// si hay varios jugadores, se añaden a la partida creada por el usuario que inició la sesión
		User u = new User("login");
		u.name = "Login";
		u.surName = "Login";
		u.role = Role.USER;
		u.password = "pass";
		
		Player p = new Player("pepe");
		p.setActual(trivial.getGraph().getBox(7));
		p.setPiece(Color.BLUE);
		
		trivial.addPlayer(p);
		trivial.game();
	}
}