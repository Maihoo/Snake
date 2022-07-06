import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

public class Rule {
	
	public Tail[] tails = new Tail[100];
	public pastCoords[] coords = new pastCoords[100];
	public Coin coin = new Coin();
	public boolean gameover;
	int t = 0;
	
	Game game;
	static int CoinX = 0;
	static int CoinY = 0;
	boolean ren = false;
	int i = -1;
	
	public Rule (Game game) {
		this.game = game;
	}
	
	public void coinGen() {
		CoinX = (int) (Math.random() * 28);
		CoinY = (int) (Math.random() * 28);
		
		for(int j = 0; j < i; j++) {
			if(CoinX == Player.getPastX(j+1) && CoinY == Player.getPastY(j+1)) {
			coinGen();
		}
	}

	}
	
	public void tick() {
		if(i > 0)
		tails[1].tick();
		
	}
	
	public void render(Graphics g) {	
		if(CoinX == 0 && CoinY == 0) coinGen();
		
		if(i == -1) {
			tailSet();
			i++;
			System.out.println("Länge: " + (i+1));
			}
		
		if(Player.x == (26 + CoinX*16) && Player.y == (26 + CoinY*16)) {
			i++;
			System.out.println("GLÜCKWUNSCH! Länge: " + (i+1));
			
			if(t == 2) {
				Game.setFPS(Game.FPS + 1);
				t = 0;
			}
			else {
				t++;
			}
			tails[i].setL(i);
			tails[i].ren(true);
			coinGen();
			checktailCoin(i);
		}
		
		coin.setCoinX(CoinX);
		coin.setCoinY(CoinY);
		coin.render(g);
		
		for(int w = 1; w < 100; w++) {
			tails[w].render(g);
		}
		
		if(i>3) {checktail(i);}
		
		checktailCoin(i);
		boarderCheck();
		
		if(i == 88) {		
			g.drawImage(Assets.win, 0, 0, null);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		
		if(gameover) {
			g.drawImage(Assets.gameover, 0, 0, null);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void checktail(int i) {
		
		for(int j = 0; j < i; j++) {
				if(Player.x == Player.getPastX(j+1) && Player.y == Player.getPastY(j+1)) {
				gameover = true;
			}
		}
	}
	
	public void checktailCoin(int i) {
		
		for(int j = 0; j < i; j++) {
				if(CoinX == Player.getPastX(j) && CoinY == Player.getPastY(j)) {
				coinGen();
				checktailCoin(i);
			}
		}
	}
	
	public void tailSet() {
		for(int u = 1; u < 100; u++) {
			tails[u] = new Tail(0);
		}
	}
	
	public void boarderCheck() {
		if(Player.x < 26 || Player.x > 458 || Player.y < 26 || Player.y > 458) {
			gameover = true;
		}
	}

}
