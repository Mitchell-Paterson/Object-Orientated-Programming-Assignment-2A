import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	/** List of sprites in world */
	private static List<Sprite> sprites;
	
	public World(String level) {
		
		sprites = Loader.loadSprites(level);
		
	}
	
	public void update(Input input) {
		
		for (Sprite sprite : sprites) {
			sprite.update(input);
		}
			
	}
	
	public void render(Graphics g) {
		
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
	}
	
	// Returns true if coordinates are an unblocked tile
	public static boolean unBlocked(float x, float y) {
		
		// Loop through unblocked coords until match
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().getX() == x
					&& sprite.getLocation().getY() == y) {
				if (sprite instanceof Tile) {
					return Tile.isBlocked((Tile) sprite);
				}
			}
			
		}
		
		// Default to blocked
		return false;
	}
}