package es.uniovi.asw.game.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.persistence.DBHelper;
import es.uniovi.asw.game.persistence.TrivialDAO;

public class TrivialGatewayImpl implements TrivialDAO {
	private static final String DB_NAME = "trivialDataBase";
	private DB dataBase;

	public TrivialGatewayImpl() {
		if (dataBase == null)
			dataBase = DBHelper.getInstance().getDatabase(DB_NAME);
	}

	@Override
	public boolean addUser(User user) {
		if(!existUser(user))
		{
			DBCollection usersTable = dataBase.getCollection("users");
			BasicDBObject the_user = new BasicDBObject();
			the_user.put("name", user.getName());
			the_user.put("password", user.getPasswd());
			the_user.put("admin", user.isAdmin());
			the_user.put("rightQuestions", user.getRightQuestions());
			the_user.put("failedQuestions", user.getFailedQuestions());
			usersTable.insert(the_user);
			return true;
		}
		return false;
	}

	@Override
	public User loadUser(String username, String passwd) 
	{
		DBCollection usersTable = dataBase.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		query.put("name", username);
		query.put("password", passwd);
		DBCursor cursorQ = usersTable.find(query);
		if(cursorQ.hasNext()){
			cursorQ.next();
			return new User((String)cursorQ.curr().get("name"),
					(String)cursorQ.curr().get("password"), 
					(Boolean)cursorQ.curr().get("admin"),
					(Integer)cursorQ.curr().get("rightQuestions"),
					(Integer)cursorQ.curr().get("failedQuestions"));
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		
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
			question.setSuccesses((Integer)cursorQ.curr().get("successes"));
			question.setFailures((Integer)cursorQ.curr().get("failures"));
			question.setCategory((String) cursorQ.curr().get("category"));
			
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
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		DBCollection usersTable = dataBase.getCollection("users");
		DBCursor cursor = usersTable.find();
		ArrayList<User> users = new ArrayList<User>();
		while(cursor.hasNext()){
			cursor.next();
			User user = new User((String)cursor.curr().get("name"), 
					(String)cursor.curr().get("password"), 
					(Boolean)cursor.curr().get("admin"),
					(Integer)cursor.curr().get("rightQuestions"),
					(Integer)cursor.curr().get("failedQuestions"));
			users.add(user);
		}
		return users;
	}
	
	private boolean existUser(User user)
	{
		DBCollection usersTable = dataBase.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		query.put("name", user.getName());
		query.put("password", user.getPasswd());
		DBCursor cursorQ = usersTable.find(query);
		
		if(cursorQ.hasNext())
			return true;
		return false;
	}

	@Override
	public void dropDatabase() {
		dataBase.dropDatabase();
		
	}

}
