package com.madwin.JavaBall;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Barrier {
	
	int posX;
	int posY;
	Point origin;
	int width;
	int height;
	int minimumSize = 30;
	int maxSizeRatio = 3; //default=6 
	Point[] collisionPoints = new Point[9];	
	
	public Barrier(int windowWidth, int windowHeight, Ball ball, Hole hole) {
		do {
			width = new Random().nextInt(windowWidth/maxSizeRatio);
			height = new Random().nextInt(windowHeight/maxSizeRatio);
		} while (width < minimumSize || height < minimumSize);
		
		do {
			posX = new Random().nextInt(windowWidth - width);
			posY = new Random().nextInt(windowHeight - height);
			origin = new Point(posX, posY);
			initCollisionPoints();
		} while(posX > windowWidth - width || posY > windowHeight - height || 
				collision(hole) || collision(ball));
	}
		
	public void paint(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillRect(posX, posY, width, height);
	}
	
	public boolean collision(Ball ball) {
		int radius = ball.getDiameter()/2;
		int cX = ball.getPosX() + radius;
		int cY = ball.getPosY() + radius;
		
		
		if (cX - radius <= posX+width && cX + radius >= posX &&
			cY - radius <= posY+height && cY + radius >= posY)
				return true;		
		return false;
	}
	
	public boolean collision(Hole hole) {
		int radius = hole.getDiameter()/2;
		int cX = hole.getPosX() + radius;
		int cY = hole.getPosY() + radius;
		Point center = new Point(cX, cY);
		
		for (Point p : collisionPoints) {
			if (p.distanceFrom(center) <= radius)
				return true;
		}
		return false;
	}
	
	public void initCollisionPoints() {	
		
		for (int i = 0; i < 9; i++)
			collisionPoints[i] = new Point(0,0);
		
		for (int i = 0; i < 9; i++){
			collisionPoints[i].setX(origin.getX() + (width/2)*(i%3));
		}
		for (int i = 0; i < 9; i++) {
			collisionPoints[i].setY(origin.getY() + (height/2)*(int)(i/3));
		}	
		
	}

}
