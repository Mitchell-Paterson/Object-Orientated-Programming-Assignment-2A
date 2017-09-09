public class Parser {
	
	/** Top left coordinate of game map */
	private static Coordinate offset;
	
	// Constants for parsing world size
	final int WIDTH = 0;
	final int LENGTH = 1;
	
	/** Assign offsets here (for centering map) */
	public Parser (String line) {
		
		String[] splitLine = line.split(",");
		
		// Parse level width and height
		int levelWidth = Integer.parseInt(splitLine[WIDTH]);
		int levelHeight = Integer.parseInt(splitLine[LENGTH]);
		
		// Work out offsets
		offset = new Coordinate(
				(App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE))/2,
				(App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE))/2);
		
	}
	
	// Constants for parsing image name
	final int IMAGE = 0;
	
	/** Parse image name from file line */
	public String image(String line) {
		
		String[] splitLine = line.split(",");
		
		return splitLine[IMAGE];
	}
	
	// Constants for parsing coords
	final int X = 1;
	final int Y = 2;
	
	/** Parse coordinates from file line */
	public Coordinate coordinates(String line) {
		
		String[] splitLine = line.split(",");
		
		// parse x and y coords from array
		int xParsed = (Integer.parseInt(splitLine[X]));
		int yParsed = (Integer.parseInt(splitLine[Y]));
		
		// Add offset so our map is centered
		float x = xParsed * App.TILE_SIZE + offset.getX();
		float y = yParsed * App.TILE_SIZE + offset.getY();
		
		// Package into coordinate and return
		Coordinate coordinates = new Coordinate(x, y);
		
		return coordinates;
	}
}