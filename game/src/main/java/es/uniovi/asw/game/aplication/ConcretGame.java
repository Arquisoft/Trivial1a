package es.uniovi.asw.game.aplication;

import java.awt.Frame;
import java.util.List;

import es.uniovi.asw.game.infrastructure.Factory;
import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.BoardBox;
import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;
import es.uniovi.asw.game.view.ViewFactory;

public class ConcretGame implements Game
{
	View currentView;
	User[] users;
	int currentUser;
	Question currentQuestion;
	int numUsers;
	private BoardBox board;
	
	public ConcretGame() {
		currentView = ViewFactory.getTestView1(this);
		users = null;
	}
	
	public void run()
	{
		/*cambia los usuarios de la partida de una vista a otra en caso de cambiarla.
		 * Si la nueva vista admite menos usuarios que la antigua los ultimos en ser introducidos 
		 * se perderán */
		User[] newUsers = new User[currentView.getNumColores()];
		if(users!=null && newUsers.length<users.length)
			currentView.notifyLostUsers(users.length-newUsers.length);
		if(users!=null)
			for(int i=0; i< newUsers.length;i++)
				if(i<users.length)
					newUsers[i]=users[i];
		users=newUsers;
		currentView.render();
	}

	public User[] getUsers(){
		return users;
	}
	
	@SuppressWarnings("static-access")
	public void setView(View view){
		for(Frame f : currentView.getFrames())
			f.dispose();
		this.currentView = view;
		run();
	}
	
	public boolean addUserToGame(String name,String passwd){
		if(numUsers >= users.length)
			return false;
		User user = Factory.persistence.createTrivialDAO().loadUser(name, passwd);
		if(user!=null)
		{
			users[numUsers] = user;
			numUsers++;
			return true;
		}
		
		return false;
	}
	
	public void deleteUserToGame(User user){
		User[] aux = new User[users.length];
		
		for(int i=0; i< users.length; i++)
		{
			if(users[i]!=null && users[i]!= user)
				for(int j=0; j< aux.length; j++)
					if (aux[j]==null)
						aux[j] = users[i];
			if(users[i]==user)
				numUsers--;
		}
		users = aux;
		
	}
	
	public boolean registerNewUser(String name, String passwd){
		User user = new User(name, passwd, false,0,0);
		return Factory.persistence.createTrivialDAO().addUser(user);
	}

	@Override
	public List<Question> loadQuestions() {
		return Factory.persistence.createTrivialDAO().findAllQuestions();
	}
	
	@Override
	public List<User> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getQuestion(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAcorrectAnswer(Answer selectedAnswer) {
		for(Answer a : currentQuestion.getAnswers())
			if(a == selectedAnswer && a.getIsCorrect())
			{
				users[currentUser].incRightQuestions();
				Factory.persistence.createTrivialDAO().updateUser(users[currentUser]);
				currentQuestion.incSuccesses();
				Factory.persistence.createTrivialDAO().updateQuestion(currentQuestion);
				return true;
			}
		users[currentUser].incFailedQuestions();
		Factory.persistence.createTrivialDAO().updateUser(users[currentUser]);
		currentQuestion.incFailures();
		Factory.persistence.createTrivialDAO().updateQuestion(currentQuestion);
		changeUser();
		return false;
	}
	private void changeUser()
	{
		if(currentUser<numUsers)
			currentUser++;
		else
			currentUser = 0;
	}

	@Override
	public User getCurrentUser() {
		return users[currentUser];
	}

	@Override
	public Integer[] calculateNextPositions(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBoard(int diameter, int size) {
		board = new BoardBox(0);
		createBoard(board,diameter, size);
	}
	
	//no funciona
	public void createBoard(BoardBox box, Integer diameter, Integer size) 
	{ 
		//genera en el grafo la circunferencia del tablero
		if(box.getNumber() <diameter)
		{
			if(box.getRight()==null)
			{
				BoardBox right = new BoardBox(box.getNumber()+1);
				right.addLeft(box);
				box.addRight(right);
			}
		}
		//creará las rasmas al centro
		if((box.getNumber()+1)%(numUsers+1)==0)
		{
			BoardBox center = new BoardBox(0);
			box.addcenter(center);
		}
		
	}

	@Override
	public boolean isWinner() {
		// TODO Auto-generated method stub
		return false;
	}

}
