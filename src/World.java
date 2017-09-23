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
	public static boolean unBlocked(Coordinate coord) {
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(coord)) {
				if (sprite instanceof Tile) {
					return Tile.isBlocked((Tile) sprite);
				}
			}
		}
		
		// Default to blocked
		return false;
	}
	
	public static void push(int distance, char direction, Coordinate location){
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(location)) {
				if (sprite instanceof Block) {
					if (!sprite.move(distance, direction)){
						
					}
				}
			}
		}
	}
}