package com.giftproject;

import java.awt.Dimension;

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

		
		
		mainFrame.add(mainPanel);
		mainFrame.setMinimumSize(new Dimension(900, 400));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // place window in the centre of the screen
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
