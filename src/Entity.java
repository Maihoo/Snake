import java.awt.Graphics;

public abstract class Entity {

	static int x, y;
	
	public Entity (int x, int y) {
		Entity.x = x;
		Entity.y = y;
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g); 
	
}
