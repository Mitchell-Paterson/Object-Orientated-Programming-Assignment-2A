
public class CrackedWall extends Tile {
	
	private static final boolean TRAVERSABLE = false;
	private static final String SOURCE = Loader.SOURCE_FOLDER + "cracked_wall.png";
			
	public CrackedWall(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}
	
	// Nothing to see here, move along.
	
}
