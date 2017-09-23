
public class Wall extends Tile {
	
	private final static boolean TRAVERSABLE = true;
	private final static String SOURCE = Loader.SOURCE_FILE + "wall.png";
	
	public Wall(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}
}
