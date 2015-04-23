package es.uniovi.asw.trivial.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.trivial.excepcion.BusinessException;
import es.uniovi.asw.trivial.infrastructure.Logger;
import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;

public class GIFTParser implements TrivialParser {

	private String file;
	private Logger logger;

	public GIFTParser(String file) {
		this.setFile(file);
		this.logger = new Logger();
	}

	@Override
	public Trivial parse() throws BusinessException {

		List<String> lines = readFile(file);
		Trivial trivial = new Trivial();
		Question question = null;
		Answer answer = null;

		for (String line : lines) {
			if (line.startsWith("$")) {
				question = new Question();
				question.setCategory(deleteEmpty(line.split(":"))[1]);
			} else if (line.startsWith("::") && (line.endsWith("{"))) {
				if (question == null)
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
				question = null;
			} else {
				if (!line.equals(""))
					logger.log(file, "Parser", "Carácter desconocido en la línea " 
							+ (lines.indexOf(line) + 1) 
							+ "! ¡Respete el formato GIFT del Manual del Sistema!");
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

	private List<String> readFile(String filename) throws BusinessException {

		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		String line = null;

		try {
			reader = new BufferedReader(new FileReader("src/main/java/resources/gift/" + filename));

			while ((line = reader.readLine()) != null)
				lines.add(line);

		} catch (FileNotFoundException fnf) {			
			String msg = "\nEl fichero " + filename + " no ha sido encontrado!";
			logger.log(file, "Parser", msg);
			throw new BusinessException(msg);	
		} catch (IOException io) {			
			String msg = "Error de Entrada/Salida: " + io.getMessage();
			logger.log(file, "Parser", msg);
			throw new BusinessException(msg);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					String msg = "\nNo ha sido posible cerrar el flujo de datos: " + e.getMessage();
					logger.log(file, "Parser", msg);
					throw new BusinessException(msg);
				}
			}
		}

		return lines;
	}
}