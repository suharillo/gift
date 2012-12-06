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
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class Tab1Frame extends JPanel{
	private JLabel jlbName;
	private JTextField jtfTitle;
	private JLabel jlbQuestion;
	
	private JLabel jlbAnswer;
	private ButtonGroup rbChoice;
	
	private JRadioButton jrbTrue;
	private JRadioButton jrbFalse;
	
	private JButton jbPreview;
	
	private JPanel radioPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTextArea jtaQuestion;
	private JButton jbClearTab1;
	
	public Tab1Frame() {
		setLayout(new MigLayout("", "[grow]", "[][][][][][][][]"));
		rbChoice = new ButtonGroup();
		
		jlbName = new JLabel("-= title =-");
		add(jlbName, "cell 0 0,alignx center");
		
		jtfTitle = new JTextField(30);
		add(jtfTitle, "cell 0 1,growx");
		jlbQuestion = new JLabel("-= question =-");
		add(jlbQuestion, "cell 0 2,alignx center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3,grow");
		
		jtaQuestion = new JTextArea();
		jtaQuestion.setRows(5);
		scrollPane.setViewportView(jtaQuestion);
		jlbAnswer = new JLabel("-= correct answer =-");
		add(jlbAnswer, "cell 0 4,alignx center");
		
		jrbTrue = new JRadioButton("T");
		jrbFalse = new JRadioButton("F");
		rbChoice.add(jrbTrue);
		rbChoice.add(jrbFalse);
		
		radioPanel = new JPanel();
		add(radioPanel, "cell 0 5,alignx center");
		radioPanel.add(jrbTrue);
		radioPanel.add(jrbFalse);
		
		jbPreview = new JButton("<- preview ->");
		jbPreview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		jbClearTab1 = new JButton("<- clear all ->");
		jbClearTab1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(jbClearTab1, "cell 0 6,alignx center");
		add(jbPreview, "cell 0 7,alignx center,aligny top");
		
		jbClearTab1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setTitle("");
				setQuestion("");
				
				repaint();
				revalidate();
			}
		});
		
	}
	
	public void setTitle(String s) {
		jtfTitle.setText(s);
	}
	
	public void setQuestion(String s) {
		jtaQuestion.setText(s);
	}
	
	public String getTitle() {
		return jtfTitle.getText();
	}

	public String getQuestion(){
		return jtaQuestion.getText();
	}
	
	public String getSelectedRadioButtonText() {
		for (Enumeration<AbstractButton> buttons = rbChoice.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
	}

	public JButton getJbPreview() {
		return jbPreview;
	}

	public void setJbPreview(JButton jbPreview) {
		this.jbPreview = jbPreview;
	}
	
	
	
}
