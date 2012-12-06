package com.giftproject;

import java.util.ArrayList;

public class QShort {

	private String title;
	private String question;
	private ArrayList<String> answers;
	private ArrayList<String> comments;
	private ArrayList<String> percent;
	
	private String content = "";
	
	public QShort() {
		answers = new ArrayList<String>();
		comments = new ArrayList<String>();
		percent = new ArrayList<String>();
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title == null)
			title = "";
		else
			this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		if(question == null)
			question = "";
		else
			this.question = question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public ArrayList<String> getComments() {
		return comments;
	}

	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}

	public ArrayList<String> getPercent() {
		return percent;
	}

	public void setPercent(ArrayList<String> percent) {
		this.percent = percent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
