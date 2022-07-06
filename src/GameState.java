import java.awt.Graphics;

public class GameState extends States {

	private Player player;
	private Rule rule;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 26, 26);
		rule = new Rule(game);
	}
	
	@Override
	public void tick() {
		player.tick();
		rule.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bgr, 0, 0, null);
		player.render(g);
		rule.render(g);
	}
	
}
