import java.awt.Graphics;

public class Tail{

	static int x;
	static int y;
	private int l;
	private boolean render;
	
	public Tail(int l) {
		this.l = l;
	}
	

	public void tick() {
		
	}
	

	public void ren(boolean ren) {
		render = ren;
	}
	
	
	public void render(Graphics g) {	
		if(render) g.drawImage(Assets.middlePart, Player.getPastX(l), Player.getPastY(l), null);
		
	}
	
	public void setL(int l) {
		this.l = l;
	}
	
	public void status() {
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("l: " + l);
		System.out.println("Render: " + render);
	}
}
