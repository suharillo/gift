package com.giftproject;

import java.awt.Dimension;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;

public class Tab4Frame extends JPanel {
	private JTextField jtfTitle;
	private JTable table;
	private JButton btnPreview;
	private JTextArea jtaQuestion;
	
	private ArrayList<String> answer;
	private ArrayList<String> percent;
	private ArrayList<String> comments;

	public Tab4Frame() {
		setLayout(new MigLayout("", "[grow][][grow]", "[][][][][][][][][]"));
		
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
		
		JLabel lblAnswer = new JLabel("-= answer =-");
		add(lblAnswer, "cell 1 4 2 1,alignx center");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 5 2 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Answer", "%", "Comment"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		scrollPane_1.setMaximumSize(new Dimension(1000, 75));
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
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {});
			}
		});
		add(btnAdd, "flowx,cell 2 6,alignx right");
		add(btnDelete, "cell 2 6,alignx right");
		
		JButton button = new JButton("<- clear all ->");
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(button, "cell 1 7,growx");
		
		btnPreview = new JButton("<- preview ->");
		btnPreview.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnPreview, "cell 1 8,growx");

	}
	
	public void removeSelectedFromTable(JTable from) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			model.removeRow(rows[i] - i);
		}
	}
	
	public String answers() {

		String answers = "";
		answer = new ArrayList<String>();
		comments = new ArrayList<String>();
		percent = new ArrayList<String>();

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

			answer.add(ans);
			percent.add(per);
			comments.add(com);

			answers = answers + "="
					+ displayPercentage(percent.get(i)) + answer.get(i)
					+ displayComments(comments.get(i)) + " \n";

		}

		return answers;

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
		if (string.equals("null"))
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

}
