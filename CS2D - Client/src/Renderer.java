import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Renderer extends Thread {
	
	private Frame frame;
	private Panel panel;
	private GameState gamestate;
	private Map map;
	private InputHandle input;
	private String port;
	
	public Renderer(GameState gamestate, InputHandle input, Map map) {
		super();
		frame = new Frame();
		this.gamestate = gamestate;
		this.map = map;
		panel = new Panel(this.gamestate, map);
		this.input = input;
		
	}
	
	public void run() {
		frame.init();
		frame.add(panel);
		frame.addKeyListener(input); //make sure this should go here
		
		while(true) {
			
			panel.repaint();
			
			
			try {
				Thread.sleep(16);
				
			}catch(Exception e) {}
			
		}
			
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	
	
	
	
	
	
	
	
}
