import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 16, height = 16;
	
	public static BufferedImage middlePart, headPart, coin, bg;
	
	public static BufferedImage bgr = ImageLoader.loadImage("/textures/background.png");
	public static BufferedImage gameover = ImageLoader.loadImage("/textures/gameover.png");
	public static BufferedImage win = ImageLoader.loadImage("/textures/win.png");
		
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));
		middlePart 	= sheet.crop(0*(width), 0*(height), width, height);
		headPart 	= sheet.crop(1*(width), 0*(height), width, height);
		coin 		= sheet.crop(2*(width), 0*(height), width, height);
	}
}
