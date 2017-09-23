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
	public static List<Sprite> loadSprites(String filename) {
		
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
				sprites.add(addSprite(imageName, coords));
						
			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return sprites;
	}
	
	
	/** Adds new sprite from data */
	public static Sprite addSprite(String imageName, Coordinate coords) {
		
		// Decide which sprite to load
		switch (imageName) {
			
			case "floor":
				return new Floor(coords);
			
			case "wall":
				return new Wall(coords);
			
			case "stone":
				return new Stone(coords);
			
			case "target":
				return new Target(coords);
			
			case "player":
				return new Player(coords);
			
			case "tnt":
				return new TNT(coords);
				
			case "cracked":
				return new CrackedWall(coords);
			
			case "explosion":
				return new Explosion(coords);
				
			case "ice":
				return new Ice(coords);
				
			case "door":
				return new Door(coords);
			
			case "switch":
				return new Switch(coords);
			
			// If we can't find what sprite to load
			default:
				System.out.format("Couldn't find %s.\n", imageName);
				return null;
		}
	}
	
}