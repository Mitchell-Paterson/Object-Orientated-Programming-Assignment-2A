import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Loader {
	
	public final static String SOURCE_FILE = "res/";
	
	/**
	 * Loads the sprites from a given level file.
	 * @param filename
	 * @return
	 */
	public static List<Sprite> loadSprites(String filename, World world) {
		
		/**line from .lvl file */
		String line;
		
		// Create new linkedlist of sprites to store
		List<Sprite> sprites = new LinkedList<Sprite>();
		
		// Open file
		try (BufferedReader br =
	            new BufferedReader(new FileReader(filename))) {
			
			// Establish the parser for this file, reading in world dimensions
			Parser parser = new Parser(br.readLine());
			
			// main loop through file
			while ((line = br.readLine()) != null) {
				
				// parses current line
				Coordinate coords = parser.coordinates(line);
				String imageName = parser.image(line);
				
				// Decide which image to load
				sprites.add(addSprite(imageName, coords, world));
						
			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return sprites;
	}
	
	
	/** Adds new sprite from data */
	public static Sprite addSprite(String imageName, Coordinate coords, World world) {
		
		// Decide which sprite to load
		switch (imageName) {
			
			case "floor":
				return new Floor(coords);
			
			case "wall":
				return new Wall(coords);
			
			case "stone":
				return new Stone(coords, world);
			
			case "target":
				return new Target(coords, world);
			
			case "player":
				return new Player(coords, world);
			
			case "tnt":
				return new TNT(coords, world);
				
			case "cracked":
				return new CrackedWall(coords);
			
			case "explosion":
				return new Explosion(coords, world);
				
			case "ice":
				return new Ice(coords, world);
				
			case "door":
				return new Door(coords);
			
			case "switch":
				return new Switch(coords);
				
			case "rogue":
				return new Rogue(coords, world);
			
			case "skeleton":
				return new Skeleton(coords, world);
			
			case "mage":
				return new Mage(coords, world);
			
			// If we can't find what sprite to load
			default:
				System.out.format("Couldn't find %s.\n", imageName);
				return null;
		}
	}
	
}