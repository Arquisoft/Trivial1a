package model;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;
import model.types.Color;

public class Question {
	
	private static JacksonDBCollection<Question, String> questions = MongoDB.getCollection("questions", Question.class, String.class);
	
	@Id
	@ObjectId
	public String id;
	
	public String category;
	public String name;
	public String question;
	
	public List<Answer> answers;
	public List<String> comments;
	public int successes;
	public int failures;
	
	public Question() {
		answers = new ArrayList<Answer>();
		comments = new ArrayList<String>();
		this.successes = 0;
		this.failures = 0;
	}

	
	public static List<Question> findByCategory(Color color) {
		 		
		 		List<Question> list = new ArrayList<Question>();
		 		BasicDBObject query = new BasicDBObject();
		 
		 		switch (color) {
		 		case PINK:
		 			query.append("category","espectaculos");
		 			break;
		 		case YELLOW:
		 			query.append("category","arte y literatura");
		 			break;
		 		case ORANGE:
		 			query.append("category","deportes");
		 			break;
		 		case BLUE:
		 			query.append("category","geografia");
		 			break;
		 		case BROWN:
		 			query.append("category","historia");
		 			break;
		 		case GREEN:
		 			query.append("category","ciencias");
		 			break;
		 		default:
		 			break;
		 		}
		 
		 		
		 		DBCursor<Question> cursor = questions.find(query);
		 		
		 		try {
		 			   while(cursor.hasNext()) {
		 				   list.add(cursor.next()) ;
		 			   }
		 			} finally {
		 			   cursor.close();
		 			}
		 
		 		return list;
			}
	
	
	
	public static List<Question> findAll() {
		return questions.find().toArray();
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
		 
		return Answer.findByQuestion(getName());
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
