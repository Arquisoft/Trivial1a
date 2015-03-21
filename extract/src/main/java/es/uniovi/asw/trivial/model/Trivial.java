package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

public class Trivial {
	
	private List<Question> questions;

	public Trivial() {
		questions = new ArrayList<Question>();
	}

	
	/**
     * GetQuestions; 
     * @return    List<Questions> devuelve una lista de {@Question}
     */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * AddQuestion
	 * @param 		Añade la {@Question} a la lista de {@Question}
	 */
	public void addQuestion(Question question) {
		questions.add(question);
	}

	/**
	 * RemoveQuestion
	 * @param 		Elimina la {@Question} a la lista de {@Question}
	 */
	public void removeQuestion(Question question) {
		questions.remove(question);
	}
}