
public class Target extends PressurePad {
	
	private final static boolean TRAVERSABLE = true;
	private final static String SOURCE = Loader.SOURCE_FILE + "target.png";

	public Target(Coordinate coordinate) {
		super(SOURCE, coordinate, TRAVERSABLE);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

}
