package es.uniovi.asw.trivial.model;

public class Answer {
	
	private String answer;
	private Boolean isCorrect;

	public Answer(String answer) {
		this.answer = answer;
		this.isCorrect = false;
	}
	
	public String getAnswer() {
		return answer;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}