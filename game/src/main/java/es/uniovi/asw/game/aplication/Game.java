package es.uniovi.asw.game.aplication;

import es.uniovi.asw.game.infrastructure.Factory;
import es.uniovi.asw.game.model.User;
import es.uniovi.asw.game.view.View;
import es.uniovi.asw.game.view.ViewFactory;

public class Game 
{
	View currentView;
	User[] users;
	int numUsers;
	
	public Game() {
		currentView = ViewFactory.getDeffaultView();
		users = null;
	}
	
	public void run()
	{
		System.out.println("aplicación iniciada");
		User[] newUsers = new User[currentView.getNumColores()];
		for(int i=0; i< newUsers.length;i++)
			if(i<users.length)
				newUsers[i]=users[i];
		users=newUsers;
		currentView.render();
	}

	public User[] getUsers(){
		return users;
	}
	
	public void setView(View view){
		this.currentView = view;
		run();
	}
	
	public boolean addUser(String name,String passwd){
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
	
	
	
	public boolean registerNewUser(String name, String passwd){
		User user = new User(name, passwd, false);
		return Factory.persistence.createTrivialDAO().addUser(user);
	}

}
