package es.uniovi.asw.trivial.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import es.uniovi.asw.trivial.infrastructure.DBHelper;
import es.uniovi.asw.trivial.model.Answer;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Trivial;
import es.uniovi.asw.trivial.persistence.TrivialDAO;


public class TrivialGatewayImpl implements TrivialDAO
{
	private static final String DB_NAME = "trivialDataBase";

	private DB dataBase;
	
	public TrivialGatewayImpl() {
		if (dataBase == null)
			dataBase = DBHelper.getInstance().getDatabase(DB_NAME);
	}
	
	
	@Override
	public void saveQuestions(Trivial trivial) {
		
		DBCollection questionsTable = dataBase.getCollection("questions");
		DBCollection answersTable = dataBase.getCollection("answers");
		
		for(Question q : trivial.getQuestions()) {			
			BasicDBObject question = new BasicDBObject();
			question.put("question", q.getQuestion());
			question.put("name", q.getName());
			
			questionsTable.insert(question);
			
			for(Answer a : q.getAnswers()) {
				BasicDBObject answer = new BasicDBObject();	
				answer.put("answer", a.getAnswer());
				answer.put("correct", a.getIsCorrect());
				answer.put("questionName", q.getName());
				
				answersTable.insert(answer);
			}
		}
	}

	@Override
	public List<Question> findAllQuestions() {
		
		DBCollection questionsTable = dataBase.getCollection("questions");
		DBCollection answersTable = dataBase.getCollection("answers");
		
		DBCursor cursorQ = questionsTable.find();
		ArrayList<Question> questions = new ArrayList<Question>();
		
		while (cursorQ.hasNext()) {
			cursorQ.next();
			Question question = new Question();
			question.setName((String) cursorQ.curr().get("name"));
			question.setQuestion((String) cursorQ.curr().get("question"));
			
			BasicDBObject query = new BasicDBObject();
            query.put("questionName", question.getName());
            
            DBCursor cursorA = answersTable.find(query);
            while(cursorA.hasNext()) {
            	cursorA.next();
            	Answer answer = new Answer((String) cursorA.curr().get("answer"));
            	answer.setIsCorrect((Boolean) cursorA.curr().get("correct"));
            	question.addAnswer(answer);	
            }
			
			questions.add(question);
        }
		
		return questions;
	}

	@Override
	public void deleteAllQuestions() {
		dataBase.dropDatabase();
	}
	
	
	
	
	
	
//	public static void addQuestions(Trivial trivial)
//	{
//		//obtengo la base de datos solicitada (se crea de no existir)
//		DB db = DBHelpper.getInstance().getDatabase(DB_NAME);
//		//obtengo la tabla tabla solicitada (se crea de no existir)
//		DBCollection questionsTable = db.getCollection("questions");
//		DBCollection answersTable = db.getCollection("answers");
//		
//		for(Question q : trivial.getQuestions())
//		{			
//			
//			BasicDBObject question = new BasicDBObject();
//			question.put("question", q.getQuestion());
//			question.put("name", q.getName());
////			question.put("answers", q.getAnswers());
//			questionsTable.insert(question);
//			
//			for(Answer a : q.getAnswers())
//			{
//				BasicDBObject answer =new BasicDBObject();
//				answer.put("answer", a.getAnswer());
//				answer.put("correct", a.getIsCorrect());
//				answer.put("questionName", q.getName());
//				
//				answersTable.insert(answer);
//			}
//		}
//		System.out.println("preguntas añadidas correctamente");
//	}
//	
//	public static ArrayList<Question> loadQuestions()
//	{
//		DB db =DBHelpper.getInstance().getDatabase(DB_NAME);
//		DBCollection questionsTable = db.getCollection("questions");
//		DBCollection answersTable = db.getCollection("answers");
//		DBCursor cursorQ = questionsTable.find();
//		ArrayList<Question> questions = new ArrayList<Question>();
//		
//		while (cursorQ.hasNext()) 
//		{
//			cursorQ.next();
//			Question question = new Question();
//			question.setName((String) cursorQ.curr().get("name"));
//			question.setQuestion((String) cursorQ.curr().get("question"));
//			
//			BasicDBObject query = new BasicDBObject();
//            query.put("questionName", question.getName());
//            DBCursor cursorA = answersTable.find(query);
//            while(cursorA.hasNext())
//            {
//            	cursorA.next();
//            	Answer answer = new Answer((String) cursorA.curr().get("answer"));
//            	answer.setIsCorrect((Boolean) cursorA.curr().get("correct"));
//            	question.addAnswer(answer);
//            	
//            }
//			
//			questions.add(question);
//			
//			/*System.out.println(question.getName());
//			System.out.println(question.getQuestion());
//			for(Answer a : question.getAnswers())
//				System.out.println(a.getAnswer());*/
//        }
//		return questions;
//	}
//	
//	public static boolean isEmpty()
//	{
//		DB db =DBHelpper.getInstance().getDatabase(DB_NAME);
//		DBCollection questionsTable = db.getCollection("questions");
//		if(questionsTable.count()==0)
//			return true;
//		return false;
//	}
//	
//	public static void deleteAllQuestions()
//	{
//		DB db =DBHelpper.getInstance().getDatabase(DB_NAME);
//		db.dropDatabase();
//	}
//	
//	public static void updateQuestion(Question question)
//	{
//		
//	}
//	
//	public static void deleteQuestion(Question question)
//	{
//		
//	}
//	
//	public static Question getQuestion(String name)
//	{
//		return null;
//	}
//	
//	public static void printDatabases()
//	{
//		System.out.println("bases de datos disponibles");
//		ArrayList<String> dbs = (ArrayList<String>) DBHelpper.getInstance().getConnection().getDatabaseNames();
//        for (String db : dbs) {
//            System.out.println(" - " + db);
//        }
//	}

}
