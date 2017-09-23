
public class Floor extends Tile {
	
	private final static boolean TRAVERSABLE = false;
	private final static String SOURCE = "res/floor.png";
	
	public Floor(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}
}
