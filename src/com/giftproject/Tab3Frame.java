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

public class Tab3Frame extends JPanel {
	private JTextField jtfTitle;
	private JTable table;
	private JButton btnPreview;
	private JTextArea jtaQuestion;

	public Tab3Frame() {
		setLayout(new MigLayout("", "[grow][grow]",
				"[][][][grow][][center][][]"));

		JLabel lblTitle = new JLabel("Title:");
		add(lblTitle, "cell 0 0");

		jtfTitle = new JTextField();
		add(jtfTitle, "cell 0 1 2 1,growx");
		jtfTitle.setColumns(10);

		JLabel lblQuestion = new JLabel("Question:");
		add(lblQuestion, "cell 0 2 2 1");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 2 1,grow");

		jtaQuestion = new JTextArea();
		jtaQuestion.setRows(5);
		scrollPane.setViewportView(jtaQuestion);

		JLabel lblMatchingAnswers = new JLabel("Matching Answers:");
		add(lblMatchingAnswers, "cell 0 4");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 5 2 1,growx,aligny top");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null },
				{ null, null }, }, new String[] { "Name 1", "Name 2" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane_1.setMaximumSize(new Dimension(1000, 130));
		scrollPane_1.setViewportView(table);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeSelectedFromTable(table);
			}
		});
		add(btnDelete, "flowx,cell 1 6,alignx right,aligny top");

		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {});
			}
		});
		add(btnAdd, "cell 1 6,alignx right,aligny top");

		btnPreview = new JButton("Preview");
		add(btnPreview, "cell 0 7");

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
				m1="";
			m1 = convert(m1);
			String m2 = (String) table.getModel().getValueAt(i, 1);
			if (m2 == null)
				m2 = "";
			m2 = convert(m2);

			finalString = finalString + "=" + m1 + " -> " + m2 + "\n";
		}
		return finalString;
	}

}