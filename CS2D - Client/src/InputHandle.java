import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandle implements KeyListener{

	private boolean[] Keys = new boolean[14];
	private boolean active = false;

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			Keys[0] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Keys[1] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Keys[2] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Keys[3] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Keys[4] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Keys[5] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Keys[6] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Keys[7] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_1) {
			Keys[8] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			Keys[9] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			Keys[10] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_4) {
			Keys[11] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_5) {
			Keys[12] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_6) {
			Keys[13] = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			Keys[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Keys[1] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			Keys[2] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Keys[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Keys[4] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Keys[5] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Keys[6] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Keys[7] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_1) {
			Keys[8] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			Keys[9] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			Keys[10] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_4) {
			Keys[11] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_5) {
			Keys[12] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_6) {
			Keys[13] = false;
		}
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean[] getKeys() {
		return Keys;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
	
	
	
	

}
