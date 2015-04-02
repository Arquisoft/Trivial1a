package es.uniovi.asw.game.model;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class Answer {
	private String answer;
	private Boolean isCorrect;

	public Answer(String answer) {
		this.answer = answer;
		this.isCorrect = false;
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
		return isCorrect;
	}

	/**
	 * SetIsCorrect;
	 * 
	 * @param Se
	 *            asigna un valor booleano que indica si la pregunta es o no
	 *            correcta
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
