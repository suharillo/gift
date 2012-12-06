package com.giftproject;

import java.util.ArrayList;

public class QMatching {
	
	private String title;
	private String question;
	private ArrayList<String> answers1;
	private ArrayList<String> answers2;
	
	private String content = "";

	public QMatching() {
		answers1 = new ArrayList<String>();
		answers2 = new ArrayList<String>();
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

	public ArrayList<String> getAnswers1() {
		return answers1;
	}

	public void setAnswers1(ArrayList<String> answers1) {
		this.answers1 = answers1;
	}

	public ArrayList<String> getAnswers2() {
		return answers2;
	}

	public void setAnswers2(ArrayList<String> answers2) {
		this.answers2 = answers2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
