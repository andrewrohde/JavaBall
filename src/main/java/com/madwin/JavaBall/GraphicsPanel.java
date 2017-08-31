package com.madwin.JavaBall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GraphicsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private int width = 500;
	private int height = 500;
	private Hole hole;
	private Ball ball;
	private Barrier[] barriers;
	
	public GraphicsPanel(Hole hole, Ball ball, Barrier[] barriers) {
		this.hole = hole;
		this.ball = ball;
		this.barriers = barriers;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.RED);
		setBorder(new LineBorder(Color.GREEN, 3));
		setLayout(null);
	}	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		hole.paint(g2d);
		for (int i = 0; i < barriers.length; i++) {
			barriers[i].paint(g2d);
		}
		ball.paint(g2d);
	}

}
