import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	
	public int ticks;
	public double ts, ts2;
	public int resultFPS;
	
	private Display display;
	public int width, height;
	public String title;
	
	private int h = 0;
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public static int FPS = 4;
	
	//States
	private States gameState;
	
	//Input
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		States.setState(gameState);
	}
	
	int x = 250, y = 250;
	
	private void tick() {
		keyManager.tick();
	}
	
	
	private void tickR() {
		if(States.getState() != null) {
			States.getState().tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screan
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(States.getState() != null) {
			States.getState().render(g);
		}
		
		g.setFont(new Font("Arial", Font.PLAIN, 15)); 
		g.setColor(Color.WHITE);
		byte[] data = (resultFPS + " fps").getBytes();
	    g.drawBytes(data, 0, data.length, width-60, 20);

		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();

		int fps = FPS*20;
		double timePerTick = 100/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(running) {
			timePerTick = 1000000000/(FPS*20);
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				if(h == 20) {
					tickR();
					h = 0;
				}
				

				ts2 = System.currentTimeMillis();
				
				if(ticks % 50 == 0) {
					resultFPS = (int) (50000/(ts2-ts));
					ts = System.currentTimeMillis();
				}
				
				ticks++;
				
				h++;
				tick();
				render();
				delta--;
			}
			
			if(timer >= 1000000000) {
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void setFPS(int f) {
		FPS = f;
	}
}