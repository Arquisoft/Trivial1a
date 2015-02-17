package es.uniovi.asw.trivial.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

public class TrivialMenu {

	private TrivialApp app;

	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

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
			} else {
				System.out.println("Opción desconocida!");
			}

		} while (true);
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
		System.out.println("0 - Salir");
		System.out.println("1 - Leer fichero");
		System.out.println("2 - Mostrar contenido del fichero");
	}
}
