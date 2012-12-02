package com.giftproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class GUI {

	private JFrame mainFrame;
	private JPanel mainPanel;

	// panels included in mainPanel
	private JPanel jpLeft;
	private JPanel jpMiddle;

	private JTabbedPane jtpTabs; // sticked to jpLeft
	private Tab1Frame jpTab1;
	private Tab2Frame jpTab2;
	private Tab3Frame jpTab3;
	private Tab4Frame jpTab4;

	public JTextArea jtaPreview;
	private JButton jbGenerate;

	public GUI() {

		mainFrame = new JFrame("GIFT");
		mainPanel = new JPanel(new MigLayout("", "[grow][300]", "[grow]"));
		
		jpLeft = new JPanel(new MigLayout("","[grow]","[grow]"));
		jpMiddle = new JPanel(new MigLayout("","[grow]","[grow][100]"));
		jpLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		jpMiddle.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jtaPreview = new JTextArea();
		jtaPreview.setBorder(BorderFactory.createLineBorder(Color.black));
		jbGenerate = new JButton("submit");
		jpMiddle.add(jtaPreview, "grow, wrap");
		jpMiddle.add(jbGenerate, "grow");
		
		jtpTabs = new JTabbedPane();
		jpLeft.add(jtpTabs, "grow");
		jpTab1 = new Tab1Frame();
		jpTab2 = new Tab2Frame();
		jpTab3 = new Tab3Frame();
		jpTab4 = new Tab4Frame();
		
		jtpTabs.addTab("True/False", jpTab1);
		jtpTabs.addTab("Multiple Choice", jpTab2);
		jtpTabs.addTab("Matching", jpTab3);
		jtpTabs.addTab("Short", jpTab4);
		
		mainPanel.add(jpLeft, "grow");
		mainPanel.add(jpMiddle, "grow");
		
		addActionListeners();
		
		
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setMinimumSize(new Dimension(900, 500));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // place window in the centre of the screen
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	private void addActionListeners() {
		jpTab1.getJbPreview().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				StringBuilder sbGiftFormat = new StringBuilder();
				
				String strQName = jpTab1.getName();
				String strQContent = jpTab1.getQuestion() + " ";
				String strCorrect = jpTab1.getSelectedRadioButton();
				String strTitle = "::"+strQName+"::";
				String strAnswer = "{"+strCorrect+"}";
				
				sbGiftFormat.append(strTitle+" "+strQContent+" "+strAnswer);
				
				jtaPreview.setText(sbGiftFormat.toString());
			}
		});
		
		jpTab2.getBtnPreview().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab2.convert(jpTab2.getJtfTitle().getText()));
				String question = setQuestion(jpTab2.convert(jpTab2.getJtaQuestion().getText()));
				String correctAnswer = setCorrectAnswer(jpTab2.correctAnswer());
				String wrongAnswers = setAnswers(jpTab2.wrongAnswers());
				sbGiftFormat.append(title + question + " {\n"+correctAnswer + wrongAnswers + "}");
				
				jtaPreview.setText(sbGiftFormat.toString());
				
			}
		});
		
		jpTab3.getBtnPreview().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab3.convert(jpTab3.getJtfTitle().getText()));
				String question = setQuestion(jpTab3.convert(jpTab3.getJtaQuestion().getText()));
				String answers = jpTab3.setAnswers();
				sbGiftFormat.append(title+question+" {\n"+answers+"}");
				
				jtaPreview.setText(sbGiftFormat.toString());
			}
		});
		
		jpTab4.getBtnPreview().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab4.convert(jpTab4.getJtfTitle().getText()));
				String question = setQuestion(jpTab4.convert(jpTab4.getJtaQuestion().getText()));
				String answers = setAnswers(jpTab4.answers());
				sbGiftFormat.append(title + question + " {\n"+ answers + "}");
				
				jtaPreview.setText(sbGiftFormat.toString());
			}
		});
	}

	protected String setAnswers(String wrongAnswers) {
		return wrongAnswers;
	}

	private String setTitle(String strQName) {
		return "::" + strQName + "::";
	}
	
	private String setQuestion(String strQestion) {
		return strQestion;
	}

	private String setCorrectAnswer(String strAnswer) {
		return "=" + strAnswer + "\n";
		
	}

}
