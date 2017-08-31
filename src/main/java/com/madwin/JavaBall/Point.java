package com.madwin.JavaBall;

public class Point {
	
	int x, y;
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public double distanceFrom(Point p) {
		return Math.sqrt(Math.pow((p.getX() - x),2) + 
				Math.pow((p.getY() - y), 2));
		
	}
}
