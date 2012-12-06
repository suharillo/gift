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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tab2Frame extends JPanel {
	private JTextField jtfTitle;
	private StringBuffer sb;
	private JTextArea jtaQuestion;
	private JTable table_wrong;
	private JButton btnPreview;

	private JTable table_correct;

	public Tab2Frame() {

		setLayout(new MigLayout("", "[138.00,grow][grow]",
				"[][][][grow][][grow][][][center][][][][]"));

		JLabel lblTitle = new JLabel("-= title =-");
		add(lblTitle, "cell 0 0 2 1,alignx center");

		jtfTitle = new JTextField();
		add(jtfTitle, "cell 0 1 2 1,growx");
		jtfTitle.setColumns(10);

		JLabel lblQuestion = new JLabel("-= question =-");
		add(lblQuestion, "cell 0 2 2 1,alignx center");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 3 2 1,grow");

		jtaQuestion = new JTextArea(5, 1);
		scrollPane_1.setViewportView(jtaQuestion);

		JLabel lblCorrectAnswer = new JLabel("-= correct answer =-");
		add(lblCorrectAnswer, "cell 0 4 2 1,alignx center");

		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 0 5 2 1,grow");

		table_correct = new JTable();
		table_correct.setModel(new DefaultTableModel(new Object[][] { { null,
				null }, }, new String[] { "Answer", "Comment" }));
		table_correct.getColumnModel().getColumn(0).setResizable(false);
		table_correct.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_correct.getColumnModel().getColumn(0).setMinWidth(200);
		table_correct.getColumnModel().getColumn(1).setPreferredWidth(210);
		table_correct.getModel().setValueAt("", 0, 0);
		table_correct.getModel().setValueAt("", 0, 1);
		scrollPane_2.setMinimumSize(new Dimension(100, 35));
		scrollPane_2.setMaximumSize(new Dimension(1000, 35));
		scrollPane_2.setViewportView(table_correct);

		JLabel lblIncorrectAnswers = new JLabel("-= wrong answers =-");
		add(lblIncorrectAnswers, "cell 0 7 2 1,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 8 2 1,grow");

		table_wrong = new JTable();
		table_wrong.setModel(new DefaultTableModel(new Object[][] { { null,
				null, null }, }, new String[] { "Answer", "%", "Comment" }) {
			Class[] columnTypes = new Class[] { Object.class, Integer.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_wrong.getColumnModel().getColumn(0).setResizable(false);
		table_wrong.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_wrong.getColumnModel().getColumn(0).setMinWidth(200);
		table_wrong.getColumnModel().getColumn(1).setResizable(false);
		table_wrong.getColumnModel().getColumn(1).setPreferredWidth(21);
		table_wrong.getColumnModel().getColumn(2).setResizable(false);
		table_wrong.getColumnModel().getColumn(2).setPreferredWidth(189);
		table_wrong.setRowHeight(20);
		table_wrong.getModel().setValueAt("", 0, 0);
		table_wrong.getModel().setValueAt("", 0, 1);
		table_wrong.getModel().setValueAt("", 0, 2);

		scrollPane.setMaximumSize(new Dimension(1000, 150));
		scrollPane.setViewportView(table_wrong);

		JButton btnClearTab2 = new JButton("<- clear all ->");
		btnClearTab2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClearTab2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearTitle();
				clearQuestion();
				clearCorrectAnswer();
				clearWrongAnswers();

				repaint();
				revalidate();
			}
		});

		JButton btnDelete = new JButton("<- del row ->");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				removeSelectedFromTable(table_wrong);
			}
		});

		JButton btnAdd = new JButton("<- add row ->");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_wrong
						.getModel();
				model.addRow(new Object[] { "", "", "" });
			}
		});
		add(btnAdd, "flowx,cell 1 9,alignx right");
		add(btnDelete, "cell 1 9,alignx right");
		add(btnClearTab2, "cell 0 10,growx");

		btnPreview = new JButton("<- preview ->");
		btnPreview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnPreview, "cell 0 11,growx");

	}

	protected void clearWrongAnswers() {
		setRows(1);

	}

	protected void clearCorrectAnswer() {
		table_correct.getModel().setValueAt("", 0, 0);
		table_correct.getModel().setValueAt("", 0, 1);

	}

	protected void clearQuestion() {
		jtaQuestion.setText("");

	}

	protected void clearTitle() {
		jtfTitle.setText("");

	}

	public void setQuestion(String s) {
		jtaQuestion.setText(s);
	}

	public void setCorrectAnswer(String strCorrect) {

		table_correct.getModel().setValueAt(strCorrect, 0, 0);
	}

	public void setCorrectComment(String strCorrect) {

		table_correct.getModel().setValueAt(strCorrect, 0, 1);
	}

	public String wrongAnswers() {

		String wrongAnswers = "";

		for (int i = 0; i < table_wrong.getRowCount(); i++) {

			String ans = (String) table_wrong.getModel().getValueAt(i, 0);
			String per = table_wrong.getModel().getValueAt(i, 1) + "";
			String com = (String) table_wrong.getModel().getValueAt(i, 2);

			if (ans == null)
				ans = "";
			if (per == null)
				per = "";
			if (com == null)
				com = "";

			ans = convert(ans);
			com = convert(com);

			wrongAnswers = wrongAnswers + "~" + displayPercentage(per) + ans
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
		if (string.equals("null") || string.equals(""))
			return "";
		else
			return "%" + string + "%";
	}

	private String displayComments(String comment) {
		if (comment.equals(""))
			return "";
		else
			return " #" + comment;
	}

	public void removeSelectedFromTable(JTable from) {
		DefaultTableModel model = (DefaultTableModel) this.table_wrong
				.getModel();
		int[] rows = table_wrong.getSelectedRows();
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
		String ans = (String) table_correct.getModel().getValueAt(0, 0);
		String comm = (String) table_correct.getModel().getValueAt(0, 1);
		if (comm.equals(""))
			comm = "";
		else
			comm = "#" + convert(comm);

		ans = convert(ans);
		return ans + comm;
	}

	public JTable getTable() {
		return table_wrong;
	}

	public void setTable(JTable table) {
		this.table_wrong = table;
	}

	public JTable getTable_1() {
		return table_correct;
	}

	public void setTable_1(JTable table_1) {
		this.table_correct = table_1;
	}

	public ArrayList<String> getAnswer() {
		ArrayList<String> answer = new ArrayList<String>();
		for (int i = 0; i < table_wrong.getModel().getRowCount(); i++) {
			if (!((table_wrong.getModel().getValueAt(i, 0)) == null)) {
				answer.add(table_wrong.getModel().getValueAt(i, 0).toString());
			} else
				answer.add("");
		}
		return answer;
	}

	public void setAnswer(ArrayList<String> answer) {

		for (int i = 0; i < answer.size(); i++) {
			table_wrong.getModel().setValueAt(answer.get(i), i, 0);
		}
	}

	public ArrayList<String> getPercent() {
		ArrayList<String> percent = new ArrayList<String>();
		for (int i = 0; i < table_wrong.getModel().getRowCount(); i++) {
			if (!((table_wrong.getModel().getValueAt(i, 1)) == null)) {
				String percentValue = table_wrong.getModel().getValueAt(i, 1)
						.toString();
				percent.add(percentValue);
			} else
				percent.add("");

		}
		return percent;
	}

	public void setPercent(ArrayList<String> percent) {
		for (int i = 0; i < percent.size(); i++) {
			table_wrong.getModel().setValueAt(percent.get(i), i, 1);
		}
	}

	public ArrayList<String> getComments() {
		ArrayList<String> comments = new ArrayList<String>();
		for (int i = 0; i < table_wrong.getModel().getRowCount(); i++) {
			if (!((table_wrong.getModel().getValueAt(i, 2)) == null)) {
				comments.add(table_wrong.getModel().getValueAt(i, 2).toString());
			} else
				comments.add("");
		}
		return comments;
	}

	public void setComments(ArrayList<String> comments) {

		for (int i = 0; i < comments.size(); i++) {
			table_wrong.getModel().setValueAt(comments.get(i), i, 2);
		}
	}

	public void setRows(int rowCount) {
		int rows = table_wrong.getModel().getRowCount();
		DefaultTableModel model = (DefaultTableModel) this.table_wrong
				.getModel();
		for (int i = 0; i < rows; i++)
			model.removeRow(0);
		for (int i = 0; i < rowCount; i++)
			model.addRow(new Object[] {});

	}

	public void setTitle(String title) {
		jtfTitle.setText(title);

	}
}
