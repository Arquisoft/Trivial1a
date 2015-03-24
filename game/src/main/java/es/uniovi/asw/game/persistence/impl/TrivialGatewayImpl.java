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
			return new User((String)cursorQ.curr().get("name"), (String)cursorQ.curr().get("password"), (Boolean)cursorQ.curr().get("admin"));
		}
		return null;
	}

	@Override
	public boolean updateUser(User user, User Data) {
		// TODO Auto-generated method stub
		return true;
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
	
	private boolean existUser(User user)
	{
		return true;
	}

}
