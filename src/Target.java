
public class Target extends PressurePad {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "target.png";
	private World world;

	public Target(Coordinate coordinate, World world) {
		super(SOURCE, coordinate);
		this.world = world;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		world.updateTargets(1);

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		world.updateTargets(-1);

	}

}
