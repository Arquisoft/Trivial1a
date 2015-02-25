package es.uniovi.asw.trivial.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialGateway;

public class TrivialMenu {

	private TrivialApp app;
	private BufferedReader input;

	public TrivialMenu() {
		input = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run() throws IOException {

		showMenu();

		do {
			String[] line = input.readLine().split(" ");

			if (line[0].equals("0"))
				return;

			if (line[0].equals("1")) {
				System.out.println("Introduce el nombre del fichero:");
				line = input.readLine().split(" ");
				String file = line[0];

				if (file.endsWith(".gift") || file.endsWith(".txt")) {
					try {
						app = new TrivialApp(new GIFTParser(file));
						System.out.println("Fichero cargado correctamente!");
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("El fichero tiene un formato desconocido! "
							+ "Los formatos aceptados son: .gift, .txt!");
				}
			} else if (line[0].equals("2")) {
				if (app != null) {
					showTrivial(app.getTrivial());
				} else {
					System.out.println("No hay ningún fichero cargado!");
				}
			} else if (line[0].equals("3")) {
				if(app != null)
					TrivialGateway.addQuestions(app.getTrivial());
				else 
					System.out.println("No hay ningún fichero cargado!");
			} else if(line[0].equals("4")) {
				if(TrivialGateway.isEmpty()) {
					System.out.println("La base de datos no contiene ninguna pregunta!");
				} else {
					app = new TrivialApp(new MongoParser());
				}
			} else if(line[0].equals("5")) {
				TrivialGateway.deleteAllQuestions();
				System.out.println("Base de datos borrada!");
			} else if(line[0].equals("6")) {
				play(app.getTrivial());
			}
			else {
				System.out.println("Opción desconocida!");
			}
			
			showMenu();

		} while (true);
	}

	private void play(Trivial trivial) throws IOException {
		
		Integer wins = 0;
		
		for (Question q : trivial.getQuestions()) {
			showQuestion(q);
			
			String[] line = input.readLine().split(" ");
			Integer userAnswer = readUserAnswer(line);
			
			while (userAnswer < 1 || userAnswer > q.getAnswers().size()) {
				showQuestion(q);
				System.out.println("\n¡Respuesa inválida! Nueva respuesta:\n");
				line = input.readLine().split(" ");
				readUserAnswer(line);
			}

			if (q.getAnswers().get(userAnswer-1).getIsCorrect())
				wins++;
		}
		
		System.out.format("Total Preguntas: %d.\n", trivial.getQuestions().size());
		System.out.format("Respuestas Correctas: %d.\n", wins);
	}

	private Integer readUserAnswer(String[] line) {
		try {
			return Integer.parseInt(line[0]);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private void showQuestion(Question question) {
		System.out.println(question.getQuestion());
		
		Integer i=1;
		
		for (Answer a : question.getAnswers()) {
			System.out.println((i++) + ") " + a.getAnswer());
		}
	}

	private void showTrivial(Trivial trivial) {
		System.out.println("\tPreguntas del Trivial:\n");
		
		for (Question question : trivial.getQuestions()) {
			System.out.println(question.getQuestion());
			
			for (Answer answer : question.getAnswers()) {
				System.out.println("  " + answer.getAnswer());
			}
			
			System.out.println();
		}
	}

	private void showMenu() {
		System.out.println("\t\tWellcome to Trivial Extractor\n");
		System.out.println("1 - Leer fichero");
		System.out.println("2 - Mostrar contenido del fichero");
		System.out.println("3 - Guardar preguntas en la base de datos");
		System.out.println("4 - Leer de la base de datos");
		System.out.println("5 - Borrar la base de datos");
		System.out.println("6 - ¡Jugar!");
		System.out.println("0 - Salir");
	}
}