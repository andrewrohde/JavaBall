package com.madwin.JavaBall;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = 200;
	private int height = 500;
	private GameTimer gameTimer;
	private JTextPane diffText = new JTextPane();
	
	
	public InfoPanel(GameTimer gameTimer, String difficulty) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.gameTimer = gameTimer;
		setPreferredSize(new Dimension(width, height));
		Dimension infoSize = getPreferredSize(); 
		setBounds(500, 0, infoSize.width, infoSize.height);
		
		diffText.setText("Difficulty:\n" + difficulty);
		
		add(diffText);		
		add(this.gameTimer);		
	}

}
