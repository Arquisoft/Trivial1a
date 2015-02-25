package es.uniovi.asw.trivial.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

public class GIFTParser implements TrivialParser {

	private String file;

	public GIFTParser(String file) {
		this.setFile(file);
	}

	@Override
	public Trivial parse() throws IOException {
		List<String> lines = readFile(file);
		Trivial trivial = new Trivial();
		Question question = null;
		Answer answer = null;

		for (String line : lines) {
			if (line.startsWith("$")) {
				//question = new Question();
				question.setCategory(deleteEmpty(line.split("$CATEGORY:"))[0]);
			} else if (line.startsWith("::") && (line.endsWith("{"))) {
				question = new Question();
				String[] tokens = deleteEmpty(line.split("[::{]"));
				question.setName(tokens[0]);
				question.setQuestion(tokens[1]);
			} else if (line.startsWith("=")) {
				answer = new Answer(deleteEmpty(line.split("="))[0]);
				answer.setIsCorrect(true);
				question.addAnswer(answer);
			} else if (line.startsWith("~")) {
				answer = new Answer(deleteEmpty(line.split("~"))[0]);
				question.addAnswer(answer);
			} else if (line.startsWith("####") || line.startsWith("//")) {
				question.addComment(deleteEmpty(line.split("[####//]"))[0]);
			} else if (line.startsWith("}")) {
				trivial.addQuestion(question);
			}

			if (line.startsWith("::") && (line.endsWith("}"))) {
				String[] tokens = deleteEmpty(line.split("[::{~}]"));
				question = new Question();
				question.setName(tokens[0]);
				question.setQuestion(tokens[1]);

				int i = 2;
				while (i < tokens.length) {
					if (tokens[i].startsWith("=")) {
						answer = new Answer(deleteEmpty(tokens[i].split("="))[0].toString());
						answer.setIsCorrect(true);
					} else {
						answer = new Answer(tokens[i]);
					}

					question.addAnswer(answer);
					i++;
				}

				trivial.addQuestion(question);
			}
		}

		return trivial;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	private String[] deleteEmpty(String[] tokens) {

		List<String> array = new ArrayList<String>();

		for (int i = 0; i < tokens.length; i++) {
			if (!tokens[i].isEmpty())
				array.add(tokens[i]);
		}

		return array.toArray(new String[array.size()]);
	}

	private List<String> readFile(String filename) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		String line = null;

		try {
			reader = new BufferedReader(new FileReader(filename));

			while ((line = reader.readLine()) != null)
				lines.add(line);

		} catch (FileNotFoundException fnf) {
			 throw new IOException("El fichero " + filename + " no ha sido encontrado!");
		} catch (IOException io) {
			 throw new IOException("Error de Entrada/Salida: " + io.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new IOException("No ha sido posible cerrar el flujo de datos: " + e.getMessage());
				}
			}
		}

		return lines;
	}
}