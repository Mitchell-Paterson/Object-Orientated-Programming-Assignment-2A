
public class CrackedWall extends Tile {
	
	private static final boolean TRAVERSABLE = false;
	private static final String SOURCE = Loader.SOURCE_FILE + "cracked_wall.png";
			
	public CrackedWall(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
		coordinate.print();
	}

}
