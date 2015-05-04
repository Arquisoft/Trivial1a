package model;

import java.util.ArrayList;
import java.util.List;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.ObjectId;
import play.modules.mongodb.jackson.MongoDB;

import com.mongodb.BasicDBObject;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class Answer {
	
	private static JacksonDBCollection<Answer, String> answers = MongoDB.getCollection("answers", Answer.class, String.class);

	@Id
	@ObjectId
	public String id;
	public Boolean correct;
	public String answer;
	public String questionName;

	
	
	public Answer(){}
	
	public String getQuestionName() {
		return questionName;
	}


	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public Answer(String answer) {
		
		this.answer = answer;
		this.correct = false;
	}

	
	public static List<Answer> findByQuestion(String question){
		Answer correcta = null;
		List<Answer> lista = new ArrayList<Answer>();
		DBCursor<Answer> cursor= answers.find(new BasicDBObject("questionName", question));
		
		try {
			   while(cursor.hasNext()) {
				 lista.add(cursor.next());
				 if(cursor.curr().correct)
					 correcta=cursor.curr();
			   }
			} finally {
			   cursor.close();
			}
		lista.add(correcta);
		return lista;
		
	 }
	/**
	 * GetAnswer;
	 * 
	 * @return String con el contenido de la pregunta.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * GetIsCorrect;
	 * 
	 * @return Boolean que determina si es correcta o no la pregunta.
	 */
	public Boolean getIsCorrect() {
		return correct;
	}

	/**
	 * SetIsCorrect;
	 * 
	 * @param Se
	 *            asigna un valor booleano que indica si la pregunta es o no
	 *            correcta
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.correct = isCorrect;
	}
}