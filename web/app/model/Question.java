package model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String category;
	private String name;
	private String question;
	private List<Answer> answers;
	private List<String> comments;
	private int successes;
	private int failures;
	
	public Question() {
		answers = new ArrayList<Answer>();
		comments = new ArrayList<String>();
		this.successes = 0;
		this.failures = 0;
	}

	/**
	 * GetCategory
	 * 
	 * @return String con la categoria a la que pertenece la Question
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * SetCategory
	 * 
	 * @param Asigna
	 *            la category @String
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * GetName
	 * 
	 * @return String con el nombre de la Question
	 */
	public String getName() {
		return name;
	}

	/**
	 * SetName
	 * 
	 * @param Asigna
	 *            el name @String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * GetQuestion
	 * 
	 * @return this Question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * SetQuestion
	 * 
	 * @param Asigna
	 *            la question @String
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	

	public int getSuccesses() {
		return successes;
	}

	public void setSuccesses(int successes) {
		this.successes = successes;
	}

	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	/**
	 * GetAnswers
	 * 
	 * @return List<Answers> con las {@Answers} de la Question
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * AddAnswer
	 * 
	 * @param Añade
	 *            la {@Answer} a la lista de {@Answer}
	 */
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	/**
	 * RemoveAnswer
	 * 
	 * @param Elimina
	 *            la {@Answer} a la lista de {@Answer}
	 */
	public void removeAnswer(Answer answer) {
		this.answers.remove(answer);
	}

	/**
	 * GetComments
	 * 
	 * @return List<String> con los comentarios de la Question
	 */
	public List<String> getComments() {
		return comments;
	}

	/**
	 * AddComment
	 * 
	 * @param Añade
	 *            el comment @String a la lista de comentarios
	 */
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	
	public void incSuccesses()
	{
		successes++;
	}
	
	public void incFailures()
	{
		failures++;
	}
}
