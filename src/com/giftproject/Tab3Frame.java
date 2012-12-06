package com.giftproject;

import java.awt.Dimension;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tab3Frame extends JPanel {
	private JTextField jtfTitle;
	private JTable table;
	private JButton btnPreview;
	private JTextArea jtaQuestion;

	public Tab3Frame() {
		setLayout(new MigLayout("", "[][][]", "[][][][][][center][][][]"));

		JLabel lblTitle = new JLabel("-= title =-");
		add(lblTitle, "cell 1 0 2 1,alignx center");

		jtfTitle = new JTextField();
		add(jtfTitle, "cell 1 1 2 1,growx");
		jtfTitle.setColumns(10);

		JLabel lblQuestion = new JLabel("-= question =-");
		add(lblQuestion, "cell 1 2 2 1,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 3 2 1,grow");

		jtaQuestion = new JTextArea();
		jtaQuestion.setRows(5);
		scrollPane.setViewportView(jtaQuestion);

		JLabel lblMatchingAnswers = new JLabel("-= matchig answers =-");
		add(lblMatchingAnswers, "cell 0 4 3 1,alignx center");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 5 2 1,growx,aligny top");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null },
				{ null, null }, }, new String[] { "Name 1", "Name 2" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane_1.setMaximumSize(new Dimension(1000, 130));
		scrollPane_1.setViewportView(table);

		JButton btnDelete = new JButton("<- del row ->");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeSelectedFromTable(table);
			}
		});

		JButton btnAdd = new JButton("<- add row ->");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {});
			}
		});
		add(btnAdd, "flowx,cell 2 6,alignx right,aligny top");
		add(btnDelete, "cell 2 6,alignx right,aligny top");

		JButton button = new JButton("<- clear all ->");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("");
				setQuestion("");
				
				setRows(2);
				repaint();
				revalidate();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(button, "cell 1 7,growx");

		btnPreview = new JButton("<- previev ->");
		btnPreview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnPreview, "cell 1 8,growx");

	}
	
	public void setRows(int rowCount) {
		int rows = table.getModel().getRowCount();
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		for (int i=0; i<rows; i++)
			model.removeRow(0);
		for (int i=0; i<rowCount; i++)
			model.addRow(new Object[] {});
		
	}

	public void removeSelectedFromTable(JTable from) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
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

	public JTextField getJtfTitle() {
		return jtfTitle;
	}

	public void setJtfTitle(JTextField jtfTitle) {
		this.jtfTitle = jtfTitle;
	}

	public JButton getBtnPreview() {
		return btnPreview;
	}

	public void setBtnPreview(JButton btnPreview) {
		this.btnPreview = btnPreview;
	}

	public JTextArea getJtaQuestion() {
		return jtaQuestion;
	}

	public void setJtaQuestion(JTextArea jtaQuestion) {
		this.jtaQuestion = jtaQuestion;
	}

	public String setAnswers() {
		// ArrayList<String> match1 = new ArrayList<String>();
		// ArrayList<String> match2 = new ArrayList<String>();

		String finalString = "";

		for (int i = 0; i < table.getRowCount(); i++) {
			String m1 = (String) table.getModel().getValueAt(i, 0);
			if (m1 == null)
				m1 = "";
			m1 = convert(m1);
			String m2 = (String) table.getModel().getValueAt(i, 1);
			if (m2 == null)
				m2 = "";
			m2 = convert(m2);

			finalString = finalString + "=" + m1 + " -> " + m2 + "\n";
		}
		return finalString;
	}

	public ArrayList<String> getAnswer1() {

		ArrayList<String> answer = new ArrayList<String>();
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			if (!((table.getModel().getValueAt(i, 0)) == null)) {
				answer.add(table.getModel().getValueAt(i, 0).toString());
			}
			else
				answer.add("");
		}
		return answer;

	}
	
	public ArrayList<String> getAnswer2() {

		ArrayList<String> answer = new ArrayList<String>();
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			if (!((table.getModel().getValueAt(i, 1)) == null)) {
				answer.add(table.getModel().getValueAt(i, 1).toString());
			}
			else
				answer.add("");
		}
		return answer;

	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void setTitle(String title) {
		jtfTitle.setText(title);
		
	}
	
	public void setQuestion(String s) {
		jtaQuestion.setText(s);
	}
	
	public void setAnswer1(ArrayList<String> answer) {

		for(int i=0; i<answer.size(); i++) {
			table.getModel().setValueAt(answer.get(i), i, 0);
		}
	}
	
	public void setAnswer2(ArrayList<String> answer) {

		for(int i=0; i<answer.size(); i++) {
			table.getModel().setValueAt(answer.get(i), i, 1);
		}
	}

}
