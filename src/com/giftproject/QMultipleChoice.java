package com.giftproject;

import java.util.ArrayList;

public class QMultipleChoice {

	private String title;
	private String question;
	private String CorrectAnswer;
	private String CorrectComment;
	private ArrayList<String> incorrectAnswers;
	private ArrayList<String> incorrectComments;
	private ArrayList<String> incorrectPercent;
	
	public QMultipleChoice() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return CorrectAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		CorrectAnswer = correctAnswer;
	}

	public String getCorrectComment() {
		return CorrectComment;
	}

	public void setCorrectComment(String correctComment) {
		CorrectComment = correctComment;
	}

	public ArrayList<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public ArrayList<String> getIncorrectComments() {
		return incorrectComments;
	}

	public void setIncorrectComments(ArrayList<String> incorrectComments) {
		this.incorrectComments = incorrectComments;
	}

	public ArrayList<String> getIncorrectPercent() {
		return incorrectPercent;
	}

	public void setIncorrectPercent(ArrayList<String> incorrectPercent) {
		this.incorrectPercent = incorrectPercent;
	}

}
