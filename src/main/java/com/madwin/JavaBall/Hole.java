package com.madwin.JavaBall;
import java.awt.Color;
import java.awt.Graphics2D;

public class Hole {
	
	int diameter;
	Color color = Color.BLACK;
	private int posY;
	private int posX;
	
	public Hole(String difficulty) {
		switch (difficulty) {
		case BallGame.MODE_EASY:
			diameter = 80;
			break;
		case BallGame.MODE_NORMAL:
			diameter = 50;
			break;
		case BallGame.MODE_HARD:
			diameter = 30;
			break;
		}
	}
	
	public int getDiameter() { return diameter; }
	public Color getColor() { return color; }
	
	public void setPosX(int x) { posX = x; }
	public void setPosY(int y) { posY = y; }
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval(posX, posY, diameter, diameter);
	}

}
