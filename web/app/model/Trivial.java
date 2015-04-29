package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.types.Color;
import model.util.Graph;

public class Trivial {

	private Graph graph;
	private Map<Color,List<Question>> questions;
	private List<Player> players;
	
	public Trivial() {
		
		this.graph = new Graph();
		this.players = new ArrayList<>();
		loadQuestions();
	}

	public Graph getGraph() {
		return graph;
	}

	public Map<Color,List<Question>> getQuestions() {
		return questions;
	}

	public void setQuestions(Map<Color,List<Question>> questions) {
		this.questions = questions;
	}
	
	public Question getQuestion(Color color) {
		
		List<Question> questions = this.questions.get(color);
		Integer n = Random.randomQuestion(questions.size()-1);
		
		return questions.get(n);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	public void game() {
		
		while (true) {
			for (Player p : players) {
				
				Answer a = null;
				do { // el mismo jugador lanza el dado mientras acierte preguntas una tras otra.
					Integer die = Random.throwDie();
					System.out.println("Dado: " + die);
					
					List<Box> positions = graph.getNextPositions(p.getActual().getId(), die);
					
					System.out.println("Posibles movimientos:");
					System.out.println("NOTA: El centro tiene el valor 7.");
					for (Box b : positions)
						System.out.println("\t" + b.getId());
					
					System.out.println("Introduce próxima posicion:");
					Scanner sc = new Scanner(System.in);
					
					// repetir mientas pos no sea una de las calculadas
					Integer pos = null;
					
					do {
						System.out.println("Introduce una posición de las posibles anteriores:");
						pos = sc.nextInt();
					} while (!containsBox(positions, pos));
					
					Box box = graph.getBox(pos);
					p.setActual(box);
					
					Question q = getQuestion(box.getCategory());
					
					System.out.println("Aqui iría la pregunta, junto a sus respuestas.");
					System.out.println("El usuario contesta, y se mira si es correcto. Si lo es, se mira si es box especial se le asigna la cuña");
					a = q.getAnswers().get(sc.nextInt());
					Boolean haveWedge = p.getWedges().contains(box.getCategory());
					
					if (a.getIsCorrect() && box.getIsHeadquarter()) {
						if (!haveWedge)
							p.addWedge(box.getCategory()); // si acierta y es casilla especial se le asigna la cuña
					} else if (!a.getIsCorrect() && box.getIsHeadquarter()) {
						if (haveWedge)
							p.removeWedge(box.getCategory()); // si no acierta y es especial y tiene la cuña, se la quitamos
					}
					sc.close();
				} while (a.getIsCorrect());
				
				
				/**
				 *  TODO
				 *  
				 *   Lanzar el dado.
				 *   llamar a graph.getNextPositions()
				 *   pintar los posibles movimientos sobre el tablero (poner las casillas parpadeando o por lo menos un color más claro).
				 *   una vez que se ha hecho click en el box, se obtiene el color del propio box.
				 *   se saca una pregunta Random de la lista correspondiente.
				 *   se espera a ver que contesta el usuario.
				 *   si contesta correctamente, se actualiza las estadisticas y se mira si hay quesito o no y lanza el mismo el dado, 
				 *   		sino el siguiente usuario.
				 *   
				 *   se mira si el jugador tiene todas las cuñas y si está en el centro del tablero. 
				 *   	si se cumple lo anterior, se le hacen 1 pregunta Random de cada una de las listas. Si contesta correctamente 4,
				 *   	gana la partida (break del while).
				 *   
				 *   	NOTA: va a ser complicadillo coordinar toda esta lógica de forma elegante con las vistas.
				 */
			}
		}
	}
	

	private boolean containsBox(List<Box> boxes, Integer pos) {

		for (Box b : boxes)
			if (b.getId() == pos)
				return true;
		
		return false;
	}

	private void loadQuestions() {
		this.questions = new HashMap<>();
		
		for (Color c : Color.values())
			this.questions.put(c, new ArrayList<>());
	}
}