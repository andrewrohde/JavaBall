package com.madwin.JavaBall;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BallGame extends JPanel implements MouseListener{
	private static final long serialVersionUID = 1L;

	public static final String MODE_EASY = "MODE_EASY";
	public static final String MODE_NORMAL = "MODE_NORMAL";
	public static final String MODE_HARD = "MODE_HARDY";
	
	public static final String STATE_START = "START";
	public static final String STATE_RUNNING = "RUNNING";
	public static final String STATE_END = "END";
	
	private GraphicsPanel graphicsPanel;
	private InfoPanel infoPanel;
	private Ball ball = new Ball();
	private Hole hole;
	private Barrier[] barriers;
	private int gameWidth = 500;
	private int gameHeight = 500;
	private int ballDiameter = ball.getDiameter();;
	private int holeDiameter;
	private GameTimer gameTimer;
	private ArrayList<GameListener> listeners = new ArrayList<GameListener>();
	private String gameState = "START";
	private Insets insets;
	
	private Graphics g;
	

	public BallGame(int width, int height, String difficulty) {
		setBorder(new LineBorder(Color.BLUE, 2));
		setLayout(null);
		insets = getInsets();
		
		gameTimer = new GameTimer();
		addMouseListener(this);
		hole = new Hole(difficulty);
		
		holeDiameter = hole.getDiameter();
		
		hole.setPosX(new Random().nextInt(gameWidth - holeDiameter));
		hole.setPosY(new Random().nextInt(gameHeight - holeDiameter));
		
		int posX, posY;
		do {			
			posX = new Random().nextInt(gameWidth - ballDiameter);
			posY = new Random().nextInt(gameHeight - ballDiameter);
		} while (ball.distanceFrom(hole) < ballDiameter);
		ball.setPosX(posX);
		ball.setPosY(posY);
		
		barriers = new Barrier[3];
		for (int i = 0; i < barriers.length; i++) {
			barriers[i] = new Barrier(gameWidth, gameHeight, ball, hole);
		}
		
		graphicsPanel = new GraphicsPanel(hole, ball, barriers);
		Dimension gPD = graphicsPanel.getPreferredSize();
		graphicsPanel.setBounds(insets.left, insets.top, gPD.width, gPD.height);
		
		infoPanel = new InfoPanel(gameTimer, difficulty);

		add(infoPanel);
		add(graphicsPanel);
		
		infoPanel.setBackground(Color.GREEN);
		
		
	}
	
	public Ball getBall() { return ball; }
	public Hole getHole() { return hole; }
	
	public boolean endGame() {
		return (hole.getDiameter()/2 - ballDiameter/2) > ball.distanceFrom(hole);
	}
	
	public void moveBall() {
		ball.move(gameWidth, gameHeight);
		for (Barrier barrier : barriers)
			if (barrier.collision(ball)) {
				ball.invertDX();
				ball.invertDY();
				ball.move(gameWidth, gameHeight); // should help prevent ball from getting stuck in barrier
				return; // don't want it to reverse twice in same move
			}
	}
	
	public void startGame() {
		gameState = STATE_RUNNING;
		(new Thread() {
			public void run() {		
				(new Thread() {
					public void run() {
						gameTimer.startTimer();
					}
				}).start();

				(new Thread() {
					public void run() {
						int iteration = 0;
							while(!endGame()) {
								if (iteration%5 == 0){
									repaint();
									iteration = 0;
								} else
									iteration++;
								try {
									Thread.sleep(2);
								} catch (InterruptedException e) { e.printStackTrace();	}
						}
					}
				}).start();				

				while(!endGame()) {
					moveBall();
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) { e.printStackTrace();	}
				}
				repaint();
				gameTimer.stopTimer();
				gameState = STATE_END;
				winner();
			}
		}).start();
	}
	
	public void winner() {

		g = getGraphics();
		String imgPath = "drawable/Winner_is_you.jpg";
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(imgPath));
		
		g.drawImage(img.getImage(), 0, 0, null);
		gameTimer.repaint();
	}
	
	public void gameEnded() {
		for (GameListener gl : listeners) 
			gl.gameEnded();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(gameState) {
		case STATE_START:
			startGame();
			break;
		case STATE_RUNNING:
			ball.invertDY();
			break;
		case STATE_END:
			gameEnded();
			break;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	public void addListener(GameListener gl) {
		listeners.add(gl);
	}
	
	interface GameListener {
		public void gameEnded();
	}
	
}
