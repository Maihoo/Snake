import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right;
	
	public KeyManager() {
		keys = new boolean[256];
	}

	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	  //keys[e.getKeyCode()] = false;
	}
	
	public void reset() {	
		keys[KeyEvent.VK_W] = false;
		keys[KeyEvent.VK_S] = false;
		keys[KeyEvent.VK_A] = false;
		keys[KeyEvent.VK_D] = false;
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
}
