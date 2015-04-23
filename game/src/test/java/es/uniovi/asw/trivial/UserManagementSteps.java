package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("unused")
public class UserManagementSteps {
	 
//	private UserDb users ;
	private Boolean loginValue;

	@Given("^there are no users$")
	public void there_are_no_users() throws Throwable {
//		users = new UserDb();
	}

	@When("^I create a user \"(.+)\" with password \"(.+)\"$")
	public void i_create_a_user(String name, String password) throws Throwable {
//	    users.addUser(name,password);
	}

	@Then("^The number of users is (\\d+)$")
	public void the_number_of_users_is(int n) throws Throwable {
//	    assertThat(users.size()).isEqualTo(n);  
	}
	

	@Given("^a list of users:$")
	public void a_list_of_users(final List<User> userList) 
			throws Throwable {
//		users = new UserDb();
		for (User u : userList) {
//            users.addUser(u.name, u.password);
        }
	}
	
	@When("^I login with name \"(.+)\" and password \"(.+)\"$")
	public void i_login_with_name_and_password(String name, String password) throws Throwable {
//		loginValue = users.login(name, password);
	}
	
	@Then("^I receive a welcome message$")
	public void i_receive_a_welcome_message() throws Throwable {
		assertThat(loginValue).isEqualTo(true);
	}

	@Then("^I receive a failure message$")
	public void i_receive_a_failure_message() throws Throwable {
		assertThat(loginValue).isEqualTo(false);
	}	
	
	public static class User {
        private String name;
        private String password;
    }	
}
