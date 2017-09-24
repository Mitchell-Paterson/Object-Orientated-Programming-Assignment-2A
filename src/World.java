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
	/** Game won or not */
	private static boolean gameWon;
	/** Sprite to be birthed */
	private static Sprite foetusSprite;
	/** Moves done so far */
	private static int moves;
	
	
	public World(String level) {
		
		sprites = Loader.loadSprites(level);
		
		// Init
		targetCount = 0;
		targetsNeeded = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Target) {
				targetsNeeded += 1;
			}
		}
		
		gameWon = false;
		moves = 0;
		
	}
	
	public void update(Input input) {
		
		if (input.isKeyPressed(Input.KEY_R)) {
			reset();
		}
		if (input.isKeyPressed(Input.KEY_Z)) {
			undo();
		}
		
		for (Sprite sprite : sprites) {
			sprite.update(input);
		}
		if (foetusSprite != null) {
			sprites.add(foetusSprite);
			foetusSprite = null;
		}
	}
	
	public void render(Graphics g) {
		
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
		// Need to put the location in constant
		g.drawString("Moves: " + moves, 50, 50);
	}
	
	// Returns true if coordinates are an unblocked tile
	public static boolean traversable(Coordinate coord) {
		
		// Default to blocked
		boolean traversable = false;
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(coord)) {
				if (sprite instanceof Tile) {
					// If multiple tiles, it only takes one blocked for false
					if (((Tile) sprite).isTraversable()) {
						traversable = true;
					} else {
						return false;
					}
				}
			}
		}
		
		return traversable;
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
				if (!((Block)sprite).move(distance, direction)){
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
	
	// Definitely a way to merge linkpad and linkwall
	public static CrackedWall linkCracked(Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof CrackedWall) {
				return (CrackedWall) sprite;
			}
		}
		return null;
	}
	
	public static void updateTargets(int increment) {
		targetCount += increment;
		if (targetCount >= targetsNeeded) {
			gameWon = true;
		}
	}
	
	public boolean won() {
		boolean gameWon = World.gameWon;
		return gameWon;
	}
	
	public static void killSprite(Sprite dying) {
		sprites.remove(dying);
	}
	
	public static void birthSprite(String imageName, Coordinate location) {
		foetusSprite = Loader.addSprite(imageName, location);
	}
	
	private void reset() {
		moves = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Reversable) {
				((Reversable) sprite).reset();
			}
		}
	}
	
	private void undo() {
		
		if (moves > 0) {
			moves -= 1;
			for (Sprite sprite : sprites) {
				if (sprite instanceof Player) {
					((Reversable) sprite).undo();
				}
				else if (sprite instanceof Block) {
					((Reversable) sprite).undo();
				}
			}
		}
	}
	
	public static void addMove() {
		moves += 1;
	}
	
	public static int getMoves() {
		int m = moves;
		return m;
	}
}