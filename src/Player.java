import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


public class Player extends Entity {
	
	private int delta;
	private Game game;
	public String latestpressed; 
	private int r;
	static int pastX;
	static int pastY;
	static int pastX2;
	static int pastY2;
	private int o = 0;
	private static pastCoords[] step = new pastCoords[100];
	
	public Player(Game game, int x, int y) {
		super(x, y);
		this.game = game;
	}

	@Override
	public void tick() {
		if(o == 0) {
			stepSet();
			o++;
		}
		
		headCalc();
		stepShift();
		stepCount();
	}
	
	public void headCalc() {
		pastX2 = pastX;
		pastY2 = pastY;
		pastX = x;
		pastY = y;
		
		if(game.getKeyManager().up) {
			if(latestpressed != "DOWN") {
				latestpressed = "UP";
				y -= 16;
				delta =  270;
				game.getKeyManager().reset();
				return;
			}
			game.getKeyManager().reset();
		}
		if(game.getKeyManager().down) {
			if(latestpressed != "UP") {
				latestpressed = "DOWN";
				y += 16;
				delta = r + 90;
				game.getKeyManager().reset();
				return;
			}
			game.getKeyManager().reset();
		}
		if(game.getKeyManager().left) {
			if(latestpressed != "RIGHT") {
				latestpressed = "LEFT";
				x -= 16;
				delta = r + 180;
				game.getKeyManager().reset();
				return;
			}
			game.getKeyManager().reset();
		}
		if(game.getKeyManager().right) {
			if(latestpressed != "LEFT") {
				latestpressed = "RIGHT";
				x += 16;
				delta = r + 0  ;
				game.getKeyManager().reset();
				return;
			}
			game.getKeyManager().reset();
		}
		
		
		if(latestpressed == "UP") {
			y -= 16;
		}
		if(latestpressed == "DOWN") {
			y += 16;
		}
		if(latestpressed == "LEFT") {
			x -= 16;
		}
		if(latestpressed == "RIGHT") {
			x += 16;
		}
		
	}
	
	public void stepSet() {
		for(int i = 0; i < 100; i++) {
			step[i] = new pastCoords(0, 0);
		}
	}
	
	public void stepCount() {
		step[0].setX(x);
		step[0].setY(y);
	}
	
	public void stepShift() {
		for(int i = 99; i > 0; i--) {
			step[i].x = step[i-1].x;
			step[i].y = step[i-1].y;
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		double rotationRequired = Math.toRadians (delta);
		double locationX = Assets.headPart.getWidth() / 2;
		double locationY = Assets.headPart.getHeight() / 2;
		
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(Assets.headPart, null), x, y, null);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static int getPastX(int i) {
		return step[i].x;
	}
	
	public static int getPastY(int i) {
		return step[i].y;
	}
	

}