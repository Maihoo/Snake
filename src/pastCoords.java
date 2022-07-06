
public class pastCoords {
	
	public int x;
	public int y;
	
	public pastCoords(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public void status() {
		System.out.println("Status: x: " + x);
		System.out.println("Status: y: " + y);
	}
}
