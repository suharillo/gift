package com.giftproject;

public class QTrueFalse{
	
	private String value;
	private String title;
	private String question;
	
	private static int ID = 1;

	public QTrueFalse() {
		// TODO Auto-generated constructor stub
	}

	public String isValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

}
