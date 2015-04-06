package es.uniovi.asw.game.aplication;

import java.awt.Frame;

import es.uniovi.asw.game.infrastructure.Factory;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;
import es.uniovi.asw.game.view.ViewFactory;

/**
 * Clase que implementa la interfaz Game
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 *
 */
public class ConcretGame implements Game
{
	View currentView;
	User[] users;
	int numUsers;
	
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
		User user = new User(name, passwd, false);
		return Factory.persistence.createTrivialDAO().addUser(user);
	}
	
	

}
