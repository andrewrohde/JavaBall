package com.madwin.JavaBall;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class StartScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton easyButton = new JButton("Easy");
	private JButton normalButton = new JButton("Normal");
	private JButton hardButton = new JButton("Hard");
	
	
	public StartScreen() {
		add(easyButton);
		add(normalButton);
		add(hardButton);
		setBorder(new LineBorder(Color.YELLOW));
	}
	
	public JButton[] getButtons() {
		return new JButton[] {easyButton, normalButton, hardButton};
	}

}
