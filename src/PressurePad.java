
public abstract class PressurePad extends Tile {

	private final static boolean TRAVERSABLE = true;
	
	public PressurePad(String image_src, Coordinate coordinate) {
		super(image_src, coordinate, TRAVERSABLE);
	}
	
	abstract public void toggle();

}
