
public class Target extends PressurePad {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "target.png";

	public Target(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		World.updateTargets(1);

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		World.updateTargets(-1);

	}

}
