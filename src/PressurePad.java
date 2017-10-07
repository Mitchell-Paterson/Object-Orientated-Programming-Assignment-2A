
public abstract class PressurePad extends Tile {

	private final static boolean TRAVERSABLE = true;
	
	public PressurePad(String image_src, Coordinate coordinate) {
		super(image_src, coordinate, TRAVERSABLE);
	}
	
	/*public void activate();
	abstract public void deactivate();
	*/
	abstract public void toggle();

}
