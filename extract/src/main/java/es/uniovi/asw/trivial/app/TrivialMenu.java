package es.uniovi.asw.trivial.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

public class TrivialMenu {

	private TrivialApp app;
	private BufferedReader input;
	
	private String sourceFile;

	public TrivialMenu() {
		input = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run() throws IOException {

		showMenu();

		do {
			try {
				String[] line = input.readLine().split(" ");
	
				if (line[0].equals("0"))
					return;
	
				if (line[0].equals("1")) {
					System.out.println("Introduce el nombre del fichero:");
					line = input.readLine().split(" ");
					sourceFile = line[0];
	
					if (sourceFile.endsWith(".gift") || sourceFile.endsWith(".txt")) {
						try {
							app = new TrivialApp(new GIFTParser(sourceFile));
							System.out.println("\nFichero cargado correctamente!\n");
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					} else {
						System.out.println("\nEl fichero tiene un formato desconocido! "
								+ "Los formatos aceptados son: .gift, .txt!\n");
					}
				} else if (line[0].equals("2")) {
					showTrivial(app.getTrivial());
				} else if (line[0].equals("3")) {
					app.setSerializer(new JSonSerializer());
					app.toJSon(sourceFile);
				} else if(line[0].equals("4")) {
					app.saveToDataBase();
				} else if(line[0].equals("5")) {
					app.setSerializer(new JSonSerializer());
					app.toJSon(sourceFile);
					app.saveToDataBase();
				} else {
					System.out.println("Opción desconocida!");
				}
			} catch (NullPointerException e) {
				System.out.println("\nNo hay ningún fichero cargado!\n");
			} catch (BusinessException e) {
				System.out.println(e.getMessage());
			}
			
			showMenu();

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
		System.out.println("\t\tWelcome to Trivial\n");
		System.out.println("1 - Leer Fichero");
		System.out.println("2 - Mostrar Contenido del Fichero");
		System.out.println("3 - Guardar Preguntas en JSon");
		System.out.println("4 - Guardar Preguntas en la Base de Datos");
		System.out.println("5 - Guardar en JSon + Guardar en Base de Datos");
		System.out.println("0 - Salir");
	}
}