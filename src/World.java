import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	/** List of sprites in world */
	private List<Sprite> sprites;
	/** Player character */
	private Player player;
	/** Pathing map (tells us where unblocked coords are) */
	private static List<Coordinate> pathing;
	
	public World(String level) {
		
		pathing = new ArrayList<Coordinate>();
		
		sprites = Loader.loadSprites(level, pathing);
		
		// Just assume player is last sprite for now
		player = (Player) sprites.get(sprites.size() - 1);
		
	}
	
	public void update(Input input, int delta) {
		
			player.giveInput(input, delta);
			
	}
	
	public void render(Graphics g) {
		
		// Loop through all tiles and render
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
	}
	
	// Returns true if coordinates are an unblocked tile
	public static boolean unBlocked(float x, float y) {
		
		// Loop through unblocked coords until match
		for (Coordinate coordinate : pathing) {
			if (coordinate.getX() == x && coordinate.getY() == y){
				return true;
			}
		}
		
		// Default to blocked
		return false;
	}
}