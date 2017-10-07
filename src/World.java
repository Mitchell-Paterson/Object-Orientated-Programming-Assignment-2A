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
	/** Sprites to add to sprites list */
	private List<Sprite> spritesToBirth;
	/** Sprites to delete from sprites list */
	private List<Sprite> spritesToDie;
	/** Moves done so far */
	private int moves;
	/** App this world is contained in */
	private App app;
	/** HUD x coordinate, in pixels */
	private static final int HUD_X_LOCATION = 50;
	/** Move counter y location, in pixels */
	private static final int MOVES_Y_LOCATION = 50;
	/** Target counter y location, in pixels */
	private static final int TARGETS_Y_LOCATION = 70;
	
	public World(int level, App app) {
		
		String levelSource = Loader.SOURCE_FOLDER + "levels/" + level + ".lvl";
		
		sprites = Loader.loadSprites(levelSource, this);
		
		// Count how many targets we need to win
		targetCount = 0;
		targetsNeeded = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Target) {
				targetsNeeded += 1;
			}
		}
		
		moves = 0;
		linkDoors();
		this.app = app;
		spritesToBirth = new LinkedList<Sprite>();
		spritesToDie = new LinkedList<Sprite>();
	}
	
	public void update(Input input) {
		
		if (input.isKeyPressed(Input.KEY_Z)) {
			undo();
		}
		
		for (Sprite sprite : sprites) {
			sprite.update(input);
		}
		
		// Add and remove queued sprites
		if (spritesToBirth != null) {
			sprites.addAll(spritesToBirth);
			spritesToBirth.clear();
		}
		if (spritesToDie != null) {
			sprites.removeAll(spritesToDie);
			spritesToDie.clear();
		}
		
		
	}
	
	public void render(Graphics g) {
		
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
		
		// Draw move count and targets so far
		g.drawString("Moves: " + moves, HUD_X_LOCATION, MOVES_Y_LOCATION);
		g.drawString("Targets: " + targetCount + "/" + targetsNeeded,
				HUD_X_LOCATION, TARGETS_Y_LOCATION);
	}
	
	// Returns true if coordinates are an unblocked tile
	public boolean traversable(Coordinate coord) {
		
		// Default to blocked
		boolean traversability = false;
		
		// Loop through sprites checking for tile on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(coord)) {
				if (sprite instanceof Tile) {
					// If multiple tiles, it only takes one blocked for false
					if (((Tile) sprite).isTraversable()) {
						traversability = true;
					} else {
						return false;
					}
				}
			}
		}
		return traversability;
	}
	
	// Returns true if coordinates have sprite
	public boolean gotSprite(Coordinate coord, Class<?> type) {
		
		// Loop through sprites checking for sprite on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(coord) && type.isInstance(sprite)) {
				return true;
			}
		}
		
		// Default to doesn't contain sprite
		return false;
	}
	
	public boolean push(int distance, char direction, Coordinate location) {
		
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(location) && sprite instanceof Block) {
				if (!((Block)sprite).move(distance, direction)){
					return false;
				}
			}
		}
		
		// TODO Once it's moved, we check if we've met the target count
		return true;
	}
	
	/* Gets the first sprite of specified type it encounters at location */
	public Sprite getSpriteAt(Coordinate location, Class<?> type) {
		
		// Loop through sprites checking for sprite on coord
		for (Sprite sprite : sprites) {
			if (sprite.getLocation().equals(location) && type.isInstance(sprite)) {
				return sprite;
			}
		}
		
		// Default to doesn't contain sprite
		return null;
	}

	public void killSprite(Sprite dying) {
		spritesToDie.add(dying);
	}
	
	public void birthSprite(String imageName, Coordinate location) {
		spritesToBirth.add(Loader.addSprite(imageName, location, this));
	}
	
	public void reset() {
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
					((Block) sprite).undo(moves);
				}
			}
		}
	}
	
	public void addMove() {
		
		moves += 1;
		int count = 0;
		
		// TODO Rogue and mage need to move whether player succeeds or not
		for (Sprite sprite : sprites) {
			
			if (sprite instanceof Rogue) {
				// Tells rogue to patrol along x axis
				// TODO Remove magic character
				((Rogue) sprite).patrol();
			} else if (sprite instanceof Mage) {
				// Tells mage to track player
				((Mage) sprite).trackingMove(getPlayerLocation());
			}
		}
	}
	
	public void updateTargets() {
		
		// Count targets that have a block
		int count = 0;
		for (Sprite sprite: sprites) {
			if (sprite instanceof Target && ((Target) sprite).isOn()) {
				count += 1;
			}
		}
		
		// Update count and check if level won
		targetCount = count;
		if (targetCount >= targetsNeeded) {
			app.nextLvl();
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
	
	private Coordinate getPlayerLocation() {
		
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