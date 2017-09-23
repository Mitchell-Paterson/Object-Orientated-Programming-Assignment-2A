import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	/** List of sprites in world */
	private List<Sprite> sprites;
	/** Pathing map (tells us where unblocked coords are) */
	private static List<Coordinate> pathing;
	
	public World(String level) {
		
		pathing = new ArrayList<Coordinate>();
		
		sprites = Loader.loadSprites(level, pathing);
		
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
		for (Coordinate coordinate : pathing) {
			if (coordinate.getX() == x && coordinate.getY() == y){
				return true;
			}
		}
		
		// Default to blocked
		return false;
	}
}