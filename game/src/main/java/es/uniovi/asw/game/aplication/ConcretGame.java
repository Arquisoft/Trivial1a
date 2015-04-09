package es.uniovi.asw.game.aplication;

import java.awt.Frame;
import java.util.List;

import es.uniovi.asw.game.infrastructure.Factory;
import es.uniovi.asw.game.model.Answer;
import es.uniovi.asw.game.model.BoardBox;
import es.uniovi.asw.game.model.Question;
import es.uniovi.asw.game.model.Tablero;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;
import es.uniovi.asw.game.view.ViewFactory;

public class ConcretGame implements Game
{
	private View currentView;
	private User[] users;
	private int currentUser;
	private Question currentQuestion;
	private int numUsers;
	private Tablero board;
	private int tirada;
	private Question[] questions;
	
	public ConcretGame() {
		currentView = ViewFactory.getTestView2(this);
		users = null; // ¿Aquí que se guardarán todos los usuarios del sistema? ¿Al estilo de las preguntas?
		
		questions = Factory.persistence.createTrivialDAO().findAllQuestions().toArray(new Question[0]);
		if(questions.length==0)
		questions = Factory.persistence.createTrivialSimulator().findAllQuestions().toArray(new Question[0]);
	}
	
	public void run()
	{
		/*cambia los usuarios de la partida de una vista a otra en caso de cambiarla.
		 * Si la nueva vista admite menos usuarios que la antigua los ultimos en ser introducidos 
		 * se perderán */
		//el numero de usuarios admitidos depende del numero de colores de la vista. no es siempre el mismo
		numUsers=0;
		User[] newUsers = new User[currentView.getNumColores()];
		if(users!=null && newUsers.length<users.length)
			currentView.notifyLostUsers(users.length-newUsers.length);
		if(users!=null)
			for(int i=0; i< newUsers.length;i++)
				if(i<users.length && users[i]!=null)
				{
					newUsers[i]=users[i];
					numUsers++;
					newUsers[i].setCasilla(0); 				//Central
															//resetear quesitos
					newUsers[i].setQuesitoAmarillo(false);
					newUsers[i].setQuesitoAzul(false);
					newUsers[i].setQuesitoMarron(false);
					newUsers[i].setQuesitoNaranja(false);
					newUsers[i].setQuesitoRosa(false);
					newUsers[i].setQuesitoVerde(false);
				}
		users=newUsers;
		
		createBoard(1, 1);
		
				
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
		// se cargan una vez cuando por ejemplo se llama al constructor
		return Factory.persistence.createTrivialDAO().findAllQuestions();
	}
	
	@Override
	public List<User> loadUsers() {
		return Factory.persistence.createTrivialDAO().findAllUsers();
	}

	@Override
	public Question getQuestion(String categoria) {
		return getRandomQuestion(categoria);
	}
	private Question getRandomQuestion(String categoria){
		Question q = null;
		int i = 0; // guarda random
		while(q == null){
			i = (int) (Math.random()* questions.length);
			if (questions[i].getCategory().equals(categoria))
				q = questions[i];
		}	// se deberia considerar guardar un historico con las preguntas ya realizadas...
		currentQuestion = q;
		return q;
	}
	
	@Override
	public boolean isAcorrectAnswer(String selectedAnswer) {
		for(Answer a : currentQuestion.getAnswers())
			if(a.getAnswer().equals(selectedAnswer) && a.getIsCorrect())
			{
				users[currentUser].incRightQuestions();
				// se deberia considerar actualizar los usuarios una vez finalizado el juego, no cada
				// vez que se acierte una pregunta
				Factory.persistence.createTrivialDAO().updateUser(users[currentUser]);
				currentQuestion.incSuccesses();
				Factory.persistence.createTrivialDAO().updateQuestion(currentQuestion);
				
				//FIXME Comprobar quesito y añadir
							
				
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
		if(currentUser<numUsers-1)
			currentUser++;
		else
			currentUser = 0;
	}

	@Override
	public User getCurrentUser() {
		return users[currentUser];
	}
	@Override
	public int getCurrebtUserIndex() {
		return currentUser;
	}
	@Override

	public void moverUser(int casilla){
		
		getCurrentUser().setCasilla(casilla);		
	}
	
	@Override
	public int[] calculateNextPositions() {

		return board.mover(tirada, getCurrentUser().getCasilla() );
	}
	
	@Override
	public int tirarDado(){
		tirada = (int) (Math.random()*5 + 1);
		return tirada;
	}

	/*@Override
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

	*/
	
	@Override
	public void createBoard(int diameter, int size) {
		// tamaño para que funcione; nArcos = nPasillos = tArco = 6;
				//							 tPasillo = 5;
		board = new Tablero(6, 6, 6, 5);
		
	
	}
	
	@Override
	public boolean isWinner() {
		
		if(		getCurrentUser().isQuesitoAmarillo() && getCurrentUser().isQuesitoAzul() && getCurrentUser().isQuesitoMarron()
				&& getCurrentUser().isQuesitoNaranja() && getCurrentUser().isQuesitoRosa() && getCurrentUser().isQuesitoVerde() )
		{
			return true;
			
		}
		
		return false;
	}

	@Override
	public int getNumUsers() {

		return numUsers;
	}
}
