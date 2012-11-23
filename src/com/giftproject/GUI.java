package com.giftproject;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class GUI {

	private JFrame mainFrame;
	private JPanel mainPanel;

	// panels included in mainPanel
	private JPanel jpLeft;
	private JPanel jpRight;

	private JTabbedPane jtp; // sticked to jpLeft
	private JPanel jTab1;
	private JPanel jTab2;
	private JPanel jTab3;
	private JPanel jTab4;

	private JTextArea jtaPreview;
	private JButton jbGenerate;

	public GUI() {

		mainFrame = new JFrame("GIFT");
		mainPanel = new JPanel(new MigLayout("", "[grow][300]", "[grow]"));
		
		jpLeft = new JPanel();
		jpRight = new JPanel(new MigLayout("","[grow]","[grow][100]"));
		jpLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		jpRight.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jtaPreview = new JTextArea();
		jtaPreview.setBorder(BorderFactory.createLineBorder(Color.black));
		jbGenerate = new JButton("submit");
		jpRight.add(jtaPreview, "grow, wrap");
		jpRight.add(jbGenerate, "grow");
		
		mainPanel.add(jpLeft, "grow");
		mainPanel.add(jpRight, "grow");
		
		
		mainFrame.add(mainPanel);
		mainFrame.setMinimumSize(new Dimension(900, 400));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // place window in the centre of the screen
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
