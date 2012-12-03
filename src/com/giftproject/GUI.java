package com.giftproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GUI {

	private ArrayList<Object> qContent;
	private ArrayList<JRadioButton> jrQuestions;
	private ButtonGroup bgAll;

	private JFrame mainFrame;
	private JPanel mainPanel;

	// panels included in mainPanel
	private JPanel jpLeft;
	private JPanel jpMiddle;
	private JPanel jpRight;
	private JPanel jpRightTop;

	private JTabbedPane jtpTabs; // sticked to jpLeft
	private Tab1Frame jpTab1;
	private Tab2Frame jpTab2;
	private Tab3Frame jpTab3;
	private Tab4Frame jpTab4;

	public JTextArea jtaPreview;
	private JButton jbGenerate;
	private JButton jbDelete;
	private JButton jbSave;
	private JScrollPane scrollPane;
	private JPanel jpQuestionList;

	public GUI() {

		qContent = new ArrayList<Object>();
		jrQuestions = new ArrayList<JRadioButton>();
		bgAll = new ButtonGroup();

		mainFrame = new JFrame("GIFT");
		mainPanel = new JPanel(new MigLayout("", "[grow][300][300]", "[grow]"));

		jpLeft = new JPanel(new MigLayout("", "[grow]", "[grow]"));
		jpMiddle = new JPanel(new MigLayout("", "[grow]", "[grow][100]"));
		jpRight = new JPanel(new MigLayout("", "[grow][]", "[grow][100]"));
		jpLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		jpMiddle.setBorder(BorderFactory.createLineBorder(Color.black));
		jpRight.setBorder(BorderFactory.createLineBorder(Color.black));

		jtaPreview = new JTextArea();
		jtaPreview.setBorder(BorderFactory.createLineBorder(Color.black));
		jbGenerate = new JButton("submit");
		jpMiddle.add(jtaPreview, "grow, wrap");
		jpMiddle.add(jbGenerate, "grow");

		jpRightTop = new JPanel();
		jpRightTop.setBorder(BorderFactory.createLineBorder(Color.black));
		jbDelete = new JButton("delete");
		jbSave = new JButton("save");
		jpRight.add(jpRightTop, "cell 0 0 2 1,grow");
		jpRightTop.setLayout(new MigLayout("", "[grow]", "[grow]"));

		scrollPane = new JScrollPane();
		jpRightTop.add(scrollPane, "cell 0 0,grow");

		jpQuestionList = new JPanel();
		scrollPane.setViewportView(jpQuestionList);
		jpQuestionList.setLayout(new MigLayout("", "[]", "[]"));
		jpRight.add(jbDelete, "cell 0 1,grow");
		jpRight.add(jbSave, "cell 1 1,grow");

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
		mainPanel.add(jpRight, "grow");

		addActionListeners();

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setMinimumSize(new Dimension(900, 500));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // place window in the centre of
												// the screen
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
				String strCorrect = jpTab1.getSelectedRadioButtonText();
				String strTitle = "::" + strQName + "::";
				String strAnswer = "{" + strCorrect + "}";

				sbGiftFormat.append(strTitle + " " + strQContent + " "
						+ strAnswer);

				jtaPreview.setText(sbGiftFormat.toString());
			}
		});

		jpTab2.getBtnPreview().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab2.convert(jpTab2.getJtfTitle()
						.getText()));
				String question = setQuestion(jpTab2.convert(jpTab2
						.getJtaQuestion().getText()));
				String correctAnswer = setCorrectAnswer(jpTab2.correctAnswer());
				String wrongAnswers = setAnswers(jpTab2.wrongAnswers());
				sbGiftFormat.append(title + question + " {\n" + correctAnswer
						+ wrongAnswers + "}");

				jtaPreview.setText(sbGiftFormat.toString());

			}
		});

		jpTab3.getBtnPreview().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab3.convert(jpTab3.getJtfTitle()
						.getText()));
				String question = setQuestion(jpTab3.convert(jpTab3
						.getJtaQuestion().getText()));
				String answers = jpTab3.setAnswers();
				sbGiftFormat.append(title + question + " {\n" + answers + "}");

				jtaPreview.setText(sbGiftFormat.toString());
			}
		});

		jpTab4.getBtnPreview().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder sbGiftFormat = new StringBuilder();
				String title = setTitle(jpTab4.convert(jpTab4.getJtfTitle()
						.getText()));
				String question = setQuestion(jpTab4.convert(jpTab4
						.getJtaQuestion().getText()));
				String answers = setAnswers(jpTab4.answers());
				sbGiftFormat.append(title + question + " {\n" + answers + "}");

				jtaPreview.setText(sbGiftFormat.toString());
			}
		});

		jbGenerate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jtpTabs.getSelectedIndex() == 0) {
					QTrueFalse qtf = new QTrueFalse();
					qtf.setTitle(jpTab1.getName());
					qtf.setQuestion(jpTab1.getQuestion());
					qtf.setValue(jpTab1.getSelectedRadioButtonText());

					qContent.add(qtf);
				} else if (jtpTabs.getSelectedIndex() == 1) {
					QMultipleChoice qmc = new QMultipleChoice();
					qmc.setTitle(jpTab2.getJtfTitle().getText());
					qmc.setQuestion(jpTab2.getJtaQuestion().getText());
					String correctAnswer = jpTab2.getTable_1().getModel()
							.getValueAt(0, 0).toString();
					if (correctAnswer == null)
						correctAnswer = "";
					String correctComment = jpTab2.getTable_1().getModel()
							.getValueAt(0, 1).toString();
					if (correctComment == null)
						correctComment = "";
					qmc.setCorrectAnswer(correctAnswer);
					qmc.setCorrectComment(correctComment);
					qmc.setIncorrectAnswers(jpTab2.getAnswer());
					qmc.setIncorrectComments(jpTab2.getComments());
					qmc.setIncorrectPercent(jpTab2.getPercent());

					qContent.add(qmc);
				}

				JRadioButton q = new JRadioButton("Q1");
				q.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int index = getSelectedIndex();

						System.out.println(qContent.get(index).getClass());
						System.out.println(QTrueFalse.class.toString());
						System.out.println(QMultipleChoice.class.toString());

						if (qContent.get(index).getClass() == QTrueFalse.class) {
							QTrueFalse tempQ = (QTrueFalse) qContent.get(index);
							jtpTabs.setSelectedIndex(0);
							jpTab1.setName(tempQ.getTitle());
							jpTab1.setQuestion(tempQ.getQuestion());
						} else if (qContent.get(index).getClass() == QMultipleChoice.class) {
							QMultipleChoice tempQ = (QMultipleChoice) qContent
									.get(index);
							jtpTabs.setSelectedIndex(1);
							jpTab2.setTitle(tempQ.getTitle());
							jpTab2.setQuestion(tempQ.getQuestion());
							jpTab2.setCorrectAnswer(tempQ.getCorrectAnswer());
							jpTab2.setCorrectComment(tempQ.getCorrectComment());
							System.out.println(tempQ.getIncorrectAnswers().size());
							//jpTab2.setRows(tempQ.getIncorrectAnswers().size());
							jpTab2.setAnswer(tempQ.getIncorrectAnswers());
							jpTab2.setComments(tempQ.getIncorrectComments());
							jpTab2.setPercent(tempQ.getIncorrectPercent());
						}

						
						mainPanel.repaint();
						mainPanel.revalidate();
					}

					
				});
				clearTab2();
				bgAll.add(q);
				jrQuestions.add(q);
				repaintQuestions();
			}
		});

		jbDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = getSelectedIndex();
				if (index != -1) {
					JRadioButton btr = jrQuestions.get(index);
					jrQuestions.remove(index);
					bgAll.remove(btr);
					qContent.remove(index);
					repaintQuestions();
				}

			}
		});
	}

	protected int getSelectedIndex() {
		int i = 0;
		for (Enumeration<AbstractButton> buttons = bgAll.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return i;
			}
			i++;
		}

		return -1;
	}

	protected void repaintQuestions() {

		jpQuestionList.removeAll();
		for (JRadioButton rb : jrQuestions) {
			jpQuestionList.add(rb, "wrap");
		}
		jpQuestionList.repaint();
		jpQuestionList.revalidate();
	}
	
	private void clearTab2() {
		jpTab2.setTitle("");
		jpTab2.setQuestion("");
		jpTab2.setCorrectAnswer("");
		jpTab2.setCorrectComment("");
		removeAllTableRows();
		addOneRow();
		
	}

	private void addOneRow() {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) jpTab2.getTable().getModel();
		model.addRow(new Object[] {"","",""});
		
	}

	private void removeAllTableRows() {
		int rowCount = jpTab2.getTable().getModel().getRowCount();
		for (int i=0; i<rowCount; i++){
			DefaultTableModel model = (DefaultTableModel) jpTab2.getTable().getModel();
			model.removeRow(i);
		}
		System.out.println("Row count "+rowCount);
		
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
