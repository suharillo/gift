package com.giftproject;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tab2Frame extends JPanel {
	private JTextField jtfTitle;
	private JTextField jtfCorrectAnswer;
	private StringBuffer sb;
	private JTextArea jtaQuestion;
	private JTable table;
	
	private ArrayList<String> answer;
	private ArrayList<Integer> percent;
	private ArrayList<String> comments;

	public Tab2Frame() {
		setLayout(new MigLayout("", "[138.00,grow][grow]",
				"[][][][grow][][grow][][][][]"));

		JLabel lblTitle = new JLabel("Title:");
		add(lblTitle, "cell 0 0 2 1");

		jtfTitle = new JTextField();
		add(jtfTitle, "cell 0 1 2 1,growx");
		jtfTitle.setColumns(10);

		JLabel lblQuestion = new JLabel("Question:");
		add(lblQuestion, "cell 0 2 2 1");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 3 2 1,grow");

		jtaQuestion = new JTextArea(5, 1);
		scrollPane_1.setViewportView(jtaQuestion);

		JLabel lblCorrectAnswer = new JLabel("Correct Answer:");
		add(lblCorrectAnswer, "cell 0 4");

		jtfCorrectAnswer = new JTextField();
		add(jtfCorrectAnswer, "cell 0 5 2 1,growx,aligny top");
		jtfCorrectAnswer.setColumns(10);

		JButton btnPreview = new JButton("Preview");
		btnPreview.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				String title = setTitle(jtfTitle.getText());
				String question = setQuestion(jtaQuestion.getText());
				String correctAnswer = jtfCorrectAnswer.getText();
				sb.append(title);
			}
		});

		JLabel lblIncorrectAnswers = new JLabel("Incorrect Answers:");
		add(lblIncorrectAnswers, "cell 0 6");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 7 2 1,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "Answer", "%", "Comments" }) {
			Class[] columnTypes = new Class[] { Object.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(21);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(189);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		add(btnPreview, "cell 0 8,growx");

		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {});
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				removeSelectedFromTable(table);
			}
		});
		add(btnDelete, "flowx,cell 1 8,alignx right");
		add(btnAdd, "cell 1 8,alignx right");

	}

	private String setTitle(String strQName) {
		return ":: " + strQName + "\n";
	}

	private String setQuestion(String strQestion) {
		return ":: " + jtaQuestion + " {\n";
	}
	
	private String setCorrectAnswer(String strCorrect) {
		
		return "=" + strCorrect + "\n";
	}
	
	private String wrongAnswers() {
		
		String ANS = "";
		
		for(int i=0; i<table.getRowCount(); i++) {
			
			String ans = (String) table.getModel().getValueAt(i, 0);
			
			answer.add(ans);
			percent.add((Integer) table.getModel().getValueAt(i, 1));
			comments.add((String) table.getModel().getValueAt(i, 2));
			ANS = "~%"+percent.get(i)+"%"+answer.get(i)+"#"+comments.get(i)+"\n";
			
		}
		
		return ANS;
		
	}

	public void removeSelectedFromTable(JTable from) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
	}

}
