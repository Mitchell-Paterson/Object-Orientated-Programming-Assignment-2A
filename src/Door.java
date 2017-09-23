
public class Door extends Tile {
	
	private final static boolean STARTS_OPEN = false;
	private final static String SOURCE = Loader.SOURCE_FILE + "door.png";

	public Door(Coordinate coordinate) {
		super(SOURCE, coordinate, STARTS_OPEN);
	}

}
