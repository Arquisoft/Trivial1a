package persistance;

public interface PersistenceFactory {

	UserDAO createUserDAO();
	QuestionDAO createQuestionDAO();
}