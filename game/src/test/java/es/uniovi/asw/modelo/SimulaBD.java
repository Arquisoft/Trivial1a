package es.uniovi.asw.modelo;

import java.util.ArrayList;

/**
 * Clase que simula los accesos a una base de datos real.
 * 
 * @author Trivial 1a
 *
 */
public class SimulaBD {

	// simula los usuarios de la base de datos
	private static ArrayList<UserSimplificado> tablaUsuarios = new ArrayList<UserSimplificado>();
	// simula las preguntas de la base de datos
	private static  ArrayList<PreguntaSimplificada> tablaPreguntas = new ArrayList<PreguntaSimplificada>();

	public SimulaBD(){
		tablaUsuarios.add(new UserSimplificado("admin", "admin", true)); 
		tablaUsuarios.add(new UserSimplificado("admin2", "admin2", true));
		tablaUsuarios.add(new UserSimplificado("pepe", "pepe", false));
		tablaUsuarios.add(new UserSimplificado("luis", "luis", false));
			
		tablaPreguntas.add(new PreguntaSimplificada("¿Cual es la capital de España?", "Madrid")); 
		tablaPreguntas.add(new PreguntaSimplificada("¿Cual es la capital de Asturias?", "Oviedo"));
		tablaPreguntas.add(new PreguntaSimplificada("¿Cuantas copas de Europa tiene el Real Madrid?", "10")); 
	}

	/**
	 * Simula un Select * from tablaUsuarios; formateado en una lista de
	 * usuarios
	 * 
	 * @return ArrayList<UserSimplificado>
	 */
	public static ArrayList<UserSimplificado> findUsuarios() {
		return tablaUsuarios;
	}

	/**
	 * Simula un Select * from tablaPreguntas; formateado en una lista de
	 * preguntas
	 * 
	 * @return ArrayList<PreguntaSimplificada>
	 */
	public ArrayList<PreguntaSimplificada> findPreguntas() {
		return tablaPreguntas;
	}

	/**
	 * Simula un Select nombre, password, admin where nombre = nombre ...
	 * formateado en una instancia de Usuario
	 * 
	 * @return Usuario
	 */
	public UserSimplificado findUsuario(String usuario, String password) {
		for (UserSimplificado u : tablaUsuarios)
			if (u.user.equals(usuario) && u.password.equals(password))
				return u;
		return null;
	}

	/**
	 * Simula un Select pregunta, respuesta where pregunta = pregunta...
	 * Formateado en un booleano que indicar� si la pregunta esta almacenada en
	 * la BD con esa respuesta para esa pregunta
	 * 
	 * @return boolean
	 */
	public boolean findPregunta(String pregunta, String respuesta) {
		for (PreguntaSimplificada p : tablaPreguntas)
			if (p.pregunta.equals(pregunta) && p.respuesta.equals(respuesta))
				return true;
		return false;
	}
	
	/**
	 * Simula un Insert en la tabla de usuarios
	 * @return boolean
	 */
	public boolean insertUsuario(UserSimplificado user) {
		boolean encontrado = false;
		for (UserSimplificado u : tablaUsuarios)
			if (u.user.equals(user.user))
				encontrado = true;
		if (!encontrado){
			System.out.println("--------->");
			tablaUsuarios.add(user);
			return true;
		}else
			return false;
	}
	
	/**
	 * Simula un Insert en la tabla de preguntas
	 * @return boolean
	 */
	public boolean insertPregunta(PreguntaSimplificada pregunta) {
		boolean encontrado = false;
		for (PreguntaSimplificada u : tablaPreguntas)
			if (u.pregunta.equals(pregunta.pregunta))
				encontrado = true;
		if (!encontrado){
			tablaPreguntas.add(pregunta);
			return true;
		}else
			return false;
	}
}