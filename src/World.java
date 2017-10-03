import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	/** List of sprites in world */
	private List<Sprite> sprites;
	/** Count of targets covered */
	private int targetCount;
	/** Targets needed to win level */
	private int targetsNeeded;
	/** Game won or not */
	private static boolean gameWon;
	/** Sprite to be birthed */
	private Sprite foetusSprite;
	/** Moves done so far */
	private int moves;
	/** App world is contained in */
	private App app;
	
	public World(int level, App app) {
		
		String levelSource = Loader.SOURCE_FILE + "levels/" + level + ".lvl";
		
		sprites = Loader.loadSprites(levelSource, this);
		
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
		
		linkDoors();
		
		this.app = app;
		
	}
	
	public void update(Input input) {
		
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
		g.drawString("Targets: " + targetCount + "/" + targetsNeeded, 50, 70);
	}
	
	// Returns true if coordinates are an unblocked tile
	public boolean traversable(Coordinate coord) {
		
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
	public static boolean isTraversable(List<Sprite> spritesAt) {
		
		// Default to blocked
		boolean traversable = false;
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Tile) {
				// If multiple tiles, it only takes one blocked for false
				if (((Tile) sprite).isTraversable()) {
					traversable = true;
				} else {
					return false;
				}
			}
		}
		
		return traversable;
	}
	
	// Returns true if coordinates are an unblocked tile
	public boolean hasBlock(Coordinate coord) {
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : sprites) {
			// TODO Reprogram with getSpritesAt
			if (sprite.getLocation().equals(coord)) {
				if (sprite instanceof Block) {
					return true;
				}
			}
		}
		
		// Default to no blocks
		return false;
	}
	
	// Returns true if coordinates are an unblocked tile
	public static boolean gotBlock(List<Sprite> spritesAt) {
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Block) {
				return true;
			}
		}
		
		// Default to no blocks
		return false;
	}
	
	// Returns true if coordinates are an unblocked tile
	public static <S> boolean gotSprite(List<Sprite> spritesAt, Class<S> wanted) {
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : spritesAt) {
			if (wanted.isAssignableFrom(sprite.getClass())) {
				return true;
			}
		}
		
		// Default to no blocks
		return false;
	}
	
	public boolean push(int distance, char direction, Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Block) {
				if (!((Block)sprite).move(distance, direction)){
					return false;
				}
			}
		}
		
		// TODO Once it's moved, we check if we've met the target count
		return true;
	}
	
	public List<Sprite> getSpritesAt(Coordinate location) {
		
		List<Sprite> SpritesAt = new LinkedList<Sprite>();
		
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(location)) {
				SpritesAt.add(sprite);
			}
		}
		
		return SpritesAt;
	}
	
	public static PressurePad linkToPad(List<Sprite> spritesAt) {
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof PressurePad) {
				return (PressurePad) sprite;
			}
		}
		return null;
	}
	
	// TODO Definitely a way to merge linkpad and linkwall
	public CrackedWall linkCracked(Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof CrackedWall) {
				return (CrackedWall) sprite;
			}
		}
		return null;
	}
	
	// Change to count targets
	public void updateTargets(int increment) {
		targetCount += increment;
		if (targetCount >= targetsNeeded) {
			gameWon = true;
		}
	}
	
	public boolean won() {
		boolean gameWon = World.gameWon;
		return gameWon;
	}
	
	public void killSprite(Sprite dying) {
		sprites.remove(dying);
	}
	
	public void birthSprite(String imageName, Coordinate location) {
		foetusSprite = Loader.addSprite(imageName, location, this);
	}
	
	private void reset() {
		app.resetLvl();
	}
	
	private void undo() {
		
		if (moves > 0) {
			moves -= 1;
			for (Sprite sprite : sprites) {
				if (sprite instanceof Player) {
					((Player) sprite).undo();
				}
				else if (sprite instanceof Block) {
					Coordinate newLoc = ((Block) sprite).undo(moves);
					if (newLoc != null) {
						List<Sprite> spritesAt = getSpritesAt(newLoc);
						((Block) sprite).updatePad(spritesAt);
					}
				}
			}
		}
	}
	
	public void addMove() {
		
		moves += 1;
		
		// TODO Rogue and mage need to move whether player succeeds or not
		for (Sprite sprite : sprites) {
			if (sprite instanceof Rogue) {
				// Tells rogue to patrol along x axis
				// TODO Remove magic character
				((Rogue) sprite).patrol('x');
			} else if (sprite instanceof Mage) {
				// Tells mage to track player
				((Mage) sprite).trackingMove(this.getPlayerLocation());
			}
		}
	}
	
	public int getMoves() {
		int m = moves;
		return m;
	}
	
	public void linkDoors() {
		
		/*  Link first door to the first switch down,
		 *  second door to the second switch, etc
		 */
		for (Sprite sprite : sprites) {
			if (sprite instanceof Door) {
				for (Sprite sprite2 : sprites) {
					if (sprite2 instanceof Switch) {
						if (((Switch)sprite2).getDoor() == null) {
							((Switch)sprite2).linkDoor((Door)sprite);
							break;
						}
					}
				}
			}
		}
	}
	
	public void deadly(Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Enemy) {
				reset();
			}
		}
	}
	
	// TODO Merge this with deadly
	public void playerKill(Coordinate location) {
		
		List<Sprite> spritesAt = getSpritesAt(location);
		
		for (Sprite sprite : spritesAt) {
			if (sprite instanceof Player) {
				reset();
			}
		}
	}
	
	public Coordinate getPlayerLocation() {
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Player) {
				return sprite.getLocation();
			}
		}
		// TODO Make this into a proper error.
		System.out.println("Failed to find player location.");
		return null;
	}
}