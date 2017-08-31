package com.madwin.JavaBall;
import java.awt.Color;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.border.LineBorder;

import org.apache.commons.lang3.time.StopWatch;


public class GameTimer extends JTextPane {
	private static final long serialVersionUID = 1L;
	
	boolean running = false;
	
	StopWatch sw = new StopWatch();
	Thread timer = new Thread(new Runnable() {
		@Override
		public void run() {
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb, Locale.US);
			while (running) {
				sb.delete(0, sb.length());
				Long time = sw.getNanoTime();
				long min = TimeUnit.MINUTES.convert(sw.getNanoTime(), TimeUnit.NANOSECONDS);
				long sec = TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS) % 60;
				long msec = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) % 60;
				
				formatter.format("Time: %02d:%02d.%02d", min,sec,msec);
				setText(sb.toString());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			formatter.close();
		}		
	});
	
	public GameTimer() {
		setText("Time: 00:00.00");
		Color color = Color.WHITE;
	    UIDefaults defaults = new UIDefaults();
	    defaults.put("TextPane[Enabled].backgroundPainter", color);
	    putClientProperty("Nimbus.Overrides", defaults);
	    putClientProperty("Nimbus.Overrides.InheritDefaults", true);
		setBorder(new LineBorder(color, 3));
		setBackground(color);
	}
	
	public void startTimer() {
		running = true;
		sw.start();
		timer.run();		
	}
	
	public void stopTimer() {
		running = false;
		sw.stop();
		timer = null;
	}

}
