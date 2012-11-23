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

	private JTabbedPane jtpTabs; // sticked to jpLeft
	private JPanel jpTab1;
	private JPanel jpTab2;
	private JPanel jpTab3;
	private JPanel jpTab4;

	private JTextArea jtaPreview;
	private JButton jbGenerate;

	public GUI() {

		mainFrame = new JFrame("GIFT");
		mainPanel = new JPanel(new MigLayout("", "[grow][300]", "[grow]"));
		
		jpLeft = new JPanel(new MigLayout("","[grow]","[grow]"));
		jpRight = new JPanel(new MigLayout("","[grow]","[grow][100]"));
		jpLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		jpRight.setBorder(BorderFactory.createLineBorder(Color.black));
		
		jtaPreview = new JTextArea();
		jtaPreview.setBorder(BorderFactory.createLineBorder(Color.black));
		jbGenerate = new JButton("submit");
		jpRight.add(jtaPreview, "grow, wrap");
		jpRight.add(jbGenerate, "grow");
		
		jtpTabs = new JTabbedPane();
		jpLeft.add(jtpTabs, "grow");
		jpTab1 = new Tab1Frame();
		jpTab2 = new JPanel(new MigLayout());
		jpTab3 = new JPanel(new MigLayout());
		jpTab4 = new JPanel(new MigLayout());
		
		jtpTabs.addTab("Tab1", jpTab1);
		jtpTabs.addTab("Tab2", jpTab2);
		jtpTabs.addTab("Tab3", jpTab3);
		jtpTabs.addTab("Tab4", jpTab4);
		
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
