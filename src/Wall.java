
public class Wall extends Tile {
	
	private final static boolean TRAVERSABLE = false;
	private final static String SOURCE = Loader.SOURCE_FOLDER + "wall.png";
	
	public Wall(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}
}
