package com.madwin.JavaBall;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	
	int diameter = 25;
	Color color = Color.GREEN;
	private int posY;
	private int posX;
	
	int dx = 1;
	int dy = 1;
	
	public int getDiameter() { return diameter; }
	public Color getColor() { return color; }
	
	public void setPosX(int x) { posX = x; }
	public void setPosY(int y) { posY = y; }
	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	public void setDX(int dx) { this.dx = dx; }
	public void setDY(int dy) { this.dy = dy; }
	public void invertDY() { dy = -dy; }
	public void invertDX() { dx = -dx; }
	
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval(posX, posY, diameter, diameter);
	}
	
	public void move(int width, int height) {
		setPosX(posX + dx);
		setPosY(posY + dy);
		
		if ((posX + diameter) >= width ||
			 posX <= 0) {
			dx = -dx;
		}
		
		if ((posY + diameter) >= height ||
			 posY <= 0) {
			dy = -dy;
		}
	}
	
	public double distanceFrom(Hole hole) {
		int holeX = hole.getPosX();
		int holeY = hole.getPosY();
		int holeDiameter = hole.getDiameter();
		int ballCenterX = posX + diameter / 2;
		int ballCenterY = posY + diameter / 2;
		int holeCenterX = holeX + holeDiameter / 2;
		int holeCenterY = holeY + holeDiameter / 2;
		double distance = Math.sqrt(Math.pow((holeCenterX - ballCenterX),2) + Math.pow((holeCenterY - ballCenterY), 2));
		return distance;
	}
}
