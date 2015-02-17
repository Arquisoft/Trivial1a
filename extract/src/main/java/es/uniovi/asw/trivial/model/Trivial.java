package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

public class Trivial {
	
	private List<Question> questions;

	public Trivial() {
		questions = new ArrayList<Question>();
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}

	public void removeQuestion(Question question) {
		questions.remove(question);
	}
}