package com.giftproject;

import java.awt.Color;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class Tab1Frame extends JPanel{

	private JPanel jpContent;
	private JLabel jlbName;
	private JTextField jtfName;
	private JLabel jlbQuestion;
	private JTextArea jtaQuestion;
	
	private JLabel jlbAnswer;
	private ButtonGroup rbChoice;
	
	private JRadioButton jrbTrue;
	private JRadioButton jrbFalse;
	
	private JButton jbPreview;
	
	private JPanel radioPanel = new JPanel();
	
	public Tab1Frame() {
		setLayout(new MigLayout("","[grow]","[]"));
		
		jpContent = new JPanel(new MigLayout());
		
		jlbName = new JLabel("Question Title");
		jlbQuestion = new JLabel("Question:");
		jlbAnswer = new JLabel("Correct answer:");
		
		jtfName = new JTextField(30);
		jtaQuestion = new JTextArea(5,1);
		jtaQuestion.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jrbTrue = new JRadioButton("True");
		jrbFalse = new JRadioButton("False");
		
		jbPreview = new JButton("Preview");
		rbChoice = new ButtonGroup();
		rbChoice.add(jrbTrue);
		rbChoice.add(jrbFalse);
		
		radioPanel = new JPanel();
		radioPanel.add(jrbTrue);
		radioPanel.add(jrbFalse);
		
		jpContent.add(jlbName, "wrap");
		jpContent.add(jtfName, "wrap");
		jpContent.add(jlbQuestion, "wrap");
		jpContent.add(jtaQuestion, "grow, wrap");
		jpContent.add(jlbAnswer, "wrap");
		jpContent.add(radioPanel, "wrap");
		jpContent.add(jbPreview);
		
		add(jpContent);
	}
	
	public void setName(String s) {
		jtfName.setText(s);
	}
	
	public void setQuestion(String s) {
		jtaQuestion.setText(s);
	}
	
	public String getName(){
		return jtfName.getText();
	}

	public String getQuestion(){
		return jtaQuestion.getText();
	}
	
	public String getSelectedRadioButton() {
		for (Enumeration<AbstractButton> buttons = rbChoice.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
	}
	
}
