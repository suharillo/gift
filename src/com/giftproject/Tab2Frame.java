package com.giftproject;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tab2Frame extends JPanel {
	private JTextField jtfTitle;
	private StringBuffer sb;
	private JTextArea jtaQuestion;
	private JTable table;
	private JButton btnPreview;
	
	private JTable table_1;

	public Tab2Frame() {
		
		setLayout(new MigLayout("", "[138.00,grow][grow]",
				"[][][][grow][][grow][][][center][][][]"));

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

		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 0 5 2 1,grow");

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, }, new String[] { "Answer",
						"Comment" }));
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(0).setMinWidth(200);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(210);
		table_1.getModel().setValueAt("", 0, 0);     
		table_1.getModel().setValueAt("", 0, 1); 
		scrollPane_2.setMinimumSize(new Dimension(100, 35));
		scrollPane_2.setMaximumSize(new Dimension(1000, 35));
		scrollPane_2.setViewportView(table_1);

		JLabel lblIncorrectAnswers = new JLabel("Incorrect Answers:");
		add(lblIncorrectAnswers, "cell 0 7");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 8 2 1,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "Answer", "%", "Comment" }) {
			Class[] columnTypes = new Class[] { Object.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(21);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(189);
		table.setRowHeight(20);
		table.getModel().setValueAt("", 0, 0);
		table.getModel().setValueAt("", 0, 1);
		table.getModel().setValueAt("", 0, 2);
		
		scrollPane.setMaximumSize(new Dimension(1000, 150));
		scrollPane.setViewportView(table);

		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {"","",""});
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				removeSelectedFromTable(table);
			}
		});
		add(btnDelete, "flowx,cell 1 9,alignx right");
		add(btnAdd, "cell 1 9,alignx right");

		btnPreview = new JButton("Preview");
		add(btnPreview, "cell 0 10,growx");

	}

	public void setQuestion(String s) {
		jtaQuestion.setText(s);
	}

	public void setCorrectAnswer(String strCorrect) {

		table_1.getModel().setValueAt(strCorrect, 0, 0);
	}
	
	public void setCorrectComment(String strCorrect) {

		table_1.getModel().setValueAt(strCorrect, 0, 1);
	}

	public String wrongAnswers() {

		String wrongAnswers = "";

		for (int i = 0; i < table.getRowCount(); i++) {
			
			

			String ans = (String) table.getModel().getValueAt(i, 0);
			String per = table.getModel().getValueAt(i, 1) + "";
			String com = (String) table.getModel().getValueAt(i, 2);

			if (ans == null)
				ans = "";
			if (per == null)
				per = "";
			if (com == null)
				com = "";
			
			ans = convert(ans);
			com = convert(com);

			wrongAnswers = wrongAnswers + "~"
					+ displayPercentage(per) +ans
					+ displayComments(com) + " \n";

		}

		return wrongAnswers;

	}

	public String convert(String string) {

		string = string.replaceAll("\\s+", " ");
		string = string.trim();

		string = string.replace("~", "\\~");
		string = string.replace("=", "\\=");
		string = string.replace("#", "\\#");
		string = string.replace("{", "\\{");
		string = string.replace("}", "\\}");

		return string;
	}

	private String displayPercentage(String string) {
		if (string.equals(""))
			return "";
		else
			return "%" + string + "% ";
	}

	private String displayComments(String comment) {
		if (comment.equals(""))
			return "";
		else
			return " #" + comment;
	}

	public void removeSelectedFromTable(JTable from) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
	}

	public JButton getBtnPreview() {
		return btnPreview;
	}

	public void setBtnPreview(JButton btnPreview) {
		this.btnPreview = btnPreview;
	}

	public JTextField getJtfTitle() {
		return jtfTitle;
	}

	public void setJtfTitle(JTextField jtfTitle) {
		this.jtfTitle = jtfTitle;
	}

	public JTextArea getJtaQuestion() {
		return jtaQuestion;
	}

	public void setJtaQuestion(JTextArea jtaQuestion) {
		this.jtaQuestion = jtaQuestion;
	}

	public String correctAnswer() {
		// /return
		String ans = (String) table_1.getModel().getValueAt(0, 0);
		String comm = (String) table_1.getModel().getValueAt(0, 1);
		if (comm.equals(""))
			comm = "";
		else
			comm = "#" + convert(comm);

		ans = convert(ans);
		return ans + comm;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTable getTable_1() {
		return table_1;
	}

	public void setTable_1(JTable table_1) {
		this.table_1 = table_1;
	}

	public ArrayList<String> getAnswer() {
		ArrayList<String> answer = new ArrayList<String>();
		for (int i=0; i<table.getModel().getRowCount();i++) {
			answer.add(table.getModel().getValueAt(i, 0).toString());
		}
		return answer;
	}

	public void setAnswer(ArrayList<String> answer) {

		for(int i=0; i<answer.size(); i++) {
			table.getModel().setValueAt(answer.get(i), i, 0);
		}
	}

	public ArrayList<String> getPercent() {
		ArrayList<String> percent = new ArrayList<String>();
		for (int i=0; i<table.getModel().getRowCount();i++) {
			percent.add(table.getModel().getValueAt(i, 1).toString());
		}
		return percent;
	}

	public void setPercent(ArrayList<String> percent) {
		for(int i=0; i<percent.size(); i++) {
			table.getModel().setValueAt(percent.get(i), i, 1);
		}
	}

	public ArrayList<String> getComments() {
		ArrayList<String> comments = new ArrayList<String>();
		for (int i=0; i<table.getModel().getRowCount();i++) {
			comments.add(table.getModel().getValueAt(i, 2).toString());
		}
		return comments;
	}

	public void setComments(ArrayList<String> comments) {

		for(int i=0; i<comments.size(); i++) {
			table.getModel().setValueAt(comments.get(i), i, 2);
		}
	}

	public void setRows(int rowCount) {
		int rows = table.getModel().getRowCount();
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		for (int i=0; i<rows; i++)
			model.removeRow(i);
		for (int i=0; i<rowCount; i++)
			model.addRow(new Object[] {});
		
	}

	public void setTitle(String title) {
		jtfTitle.setText(title);
		
	}
}
