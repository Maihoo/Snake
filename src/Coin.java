import java.awt.Graphics;

public class Coin {
	int x;
	int y;
	
	public Coin() {
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.coin, 26 + Rule.CoinX *16, 26 + Rule.CoinY *16, null);
	}
	
	public void setCoinX(int a) {
		x = a;
	}
	
	public void setCoinY(int b) {
		y = b;
	}
	
}
