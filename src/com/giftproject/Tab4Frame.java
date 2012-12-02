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

public class Tab4Frame extends JPanel {
	private JTextField jtfTitle;
	private JTable table;
	private JButton btnPreview;
	private JTextArea jtaQuestion;
	
	private ArrayList<String> answer;
	private ArrayList<String> percent;
	private ArrayList<String> comments;

	public Tab4Frame() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblTitle = new JLabel("Title:");
		add(lblTitle, "cell 0 0 2 1");
		
		jtfTitle = new JTextField();
		add(jtfTitle, "cell 0 1 2 1,growx");
		jtfTitle.setColumns(10);
		
		JLabel lblQuestion = new JLabel("Question:");
		add(lblQuestion, "cell 0 2");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 2 1,grow");
		
		jtaQuestion = new JTextArea();
		jtaQuestion.setRows(5);
		scrollPane.setViewportView(jtaQuestion);
		
		JLabel lblAnswer = new JLabel("Answer:");
		add(lblAnswer, "cell 0 4 2 1");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 5 2 1,grow");
		
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
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeSelectedFromTable(table);
			}
		});
		add(btnDelete, "flowx,cell 1 6,alignx right");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {});
			}
		});
		add(btnAdd, "cell 1 6,alignx right");
		
		btnPreview = new JButton("Preview");
		add(btnPreview, "cell 0 8");

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
