import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Loader {
	
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static List<Sprite> loadSprites(String filename,
										   List<Coordinate> pathing) {
		
		/**line from .lvl file */
		String line;
		
		// Create new linkedlist of sprites to store
		List<Sprite> sprites = new ArrayList<Sprite>();
		
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
				addSprite(imageName, sprites, pathing, coords);
						
			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return sprites;
	}
	
	
	/** Adds new pathing & sprite from data */
	public static void addSprite(String imageName, List<Sprite> sprites,
								 List<Coordinate> pathing, Coordinate coords) {
		
		// Cheeky file path constructor
		String source;
		// If these deviated, we would feed them into this method
		String folder = "res/";
		String filetype = ".png";
		// Special case
		if (imageName.equals("player")) {
			imageName = "player_left";
		}
		source = folder + imageName + filetype;
		
		// Decide which sprite to load
		switch (imageName) {
			
			case "floor":
				sprites.add(new Tile(source, coords));
				pathing.add(coords);
				break;
			
			case "wall":
				sprites.add(new Tile(source, coords));
				break;
			
			case "stone":
				sprites.add(new Sprite(source, coords));
				pathing.add(coords);
				break;
			
			case "target":
				sprites.add(new Tile(source, coords));
				pathing.add(coords);
				break;
			
			case "player_left":
				sprites.add(new Player(source, coords));
				break;
			
			// If we can't find what sprite to load
			default:
				System.out.format("Couldn't find %s.\n", imageName);
		}
	}
	
}