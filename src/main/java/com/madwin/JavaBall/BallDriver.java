package com.madwin.JavaBall;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.Color;

public class BallDriver extends JFrame implements BallGame.GameListener{

	private static final long serialVersionUID = 1L;
	
	int frameWidth = 700;
	int frameHeight = 530;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	
	int locationX = (screenSize.width - frameWidth) / 2;
	int locationY = (screenSize.height - frameHeight) / 2;

	Container cont;
	StartScreen start;	
	BallGame ballGame;
	CardLayout cardLayout = new CardLayout();
	
	public BallDriver() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setResizable(true);
		setTitle("BallGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
		pack();
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(cardLayout);
		cardLayout.preferredLayoutSize(this);
		cont = getContentPane();
		startScreen();
	}
	
	private void startScreen() {
		start = new StartScreen();
		start.setBackground(Color.DARK_GRAY);
		JButton[] startButtons = start.getButtons();
		setupButtons(startButtons);
		getContentPane().add(start, "start");
		displayStartScreen();
	}
	
	private void startGame(String difficulty) throws InterruptedException {
		
		ballGame = new BallGame(frameWidth, frameHeight, difficulty);
		ballGame.addListener(this);
		getContentPane().add(ballGame, "game");
		cardLayout.show(cont, "game");
	}	
	
	public void displayStartScreen() {
		cardLayout.show(cont, "start");
	}
	
	private void setupButtons(final JButton[] buttons) {
		for (JButton button : buttons) {
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Object object = e.getSource();
					try {
						if (object == buttons[0])						
							startGame(BallGame.MODE_EASY);
						if (object == buttons[1])
							startGame(BallGame.MODE_NORMAL);
						if (object == buttons[2])
							startGame(BallGame.MODE_HARD);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}
	
	@Override
	public void gameEnded() {
		cardLayout.show(cont, "start");
	}
	
	public BallGame getBallGame() {return ballGame;}
	
//	public static BallDriver getDriver() {return ballDriver;}
	
	private static BallDriver ballDriver;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				  ballDriver = new BallDriver();
				  ballDriver.setVisible(true);				  
			}
		});
	}
}
