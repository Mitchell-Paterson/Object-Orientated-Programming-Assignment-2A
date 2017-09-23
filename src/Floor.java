
public class Floor extends Tile {
	
	private final static boolean TRAVERSABLE = true;
	private final static String SOURCE = "res/floor.png";
	
	public Floor(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}
}
