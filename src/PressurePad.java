
public abstract class PressurePad extends Tile {

	private final static boolean TRAVERSABLE = true;
	
	public PressurePad(String image_src, Coordinate coordinate,
			boolean traversable) {
		super(image_src, coordinate, TRAVERSABLE);
	}
	
	abstract public void activate();
	abstract public void deactivate();

}
