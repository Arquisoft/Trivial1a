package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	private String category;
	private String name;
	private String question;
	private List<Answer> answers;
	private List<String> comments;

	public Question() {
		answers = new ArrayList<Answer>();
		comments = new ArrayList<String>();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	public void removeAnswer(Answer answer) {
		this.answers.remove(answer);
	}

	public List<String> getComments() {
		return comments;
	}

	public void addComment(String comment) {
		this.comments.add(comment);
	}
}