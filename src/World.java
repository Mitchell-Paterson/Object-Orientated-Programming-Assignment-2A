import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	/** List of sprites in world */
	private static List<Sprite> sprites;
	/** Count of targets covered */
	private static int targetCount;
	/** Targets needed to win level */
	private static int targetsNeeded;
	
	
	public World(String level) {
		
		sprites = Loader.loadSprites(level);
		
		// Init target data
		targetCount = 0;
		targetsNeeded = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Target) {
				targetsNeeded += 1;
			}
		}	
		
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
		// Need to put the location in constant
		g.drawString("Targets = " + targetCount + "/" + targetsNeeded, 50, 50);
	}
	
	// Returns true if coordinates are an unblocked tile
	public static boolean traversable(Coordinate coord) {
		
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
	
	// Returns true if coordinates are an unblocked tile
	public static boolean hasBlock(Coordinate coord) {
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(coord)) {
				if (sprite instanceof Block) {
					return true;
				}
			}
		}
		
		// Default to no blocks
		return false;
	}
	
	public static boolean push(int distance, char direction, Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Block) {
				if (!sprite.move(distance, direction)){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean hasPressurePad(Coordinate location){
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof PressurePad) {
				return true;
			}
		}
		return false;
	}
	
	private static List<Sprite> getSpritesAt(Coordinate location) {
		
		List<Sprite> SpritesAt = new LinkedList<Sprite>();
		
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(location)) {
				SpritesAt.add(sprite);
			}
		}
		
		return SpritesAt;
	}
	
	public static PressurePad linkPad(Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof PressurePad) {
				return (PressurePad) sprite;
			}
		}
		System.out.println("linkPad() failed to find pad.");
		return null;
	}
	
	public static void updateTargets(int increment) {
		targetCount += increment;
	}
}