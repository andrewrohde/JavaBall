package com.madwin.JavaBall;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class TestFrame {
	
	static JFrame test = new JFrame();
	static JButton ok = new JButton("OK");


	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				replace();
			}
        });
        
        
        test.add(ok);
        test.pack();
        test.setVisible(true);

	}

	    
	public static void replace() {
		test.getContentPane().removeAll();
	    
		test.add(new JTextField("Hey"));
	    test.getContentPane().revalidate();
	}

}
