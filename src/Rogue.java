
public class Rogue extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "rogue.png";
	/** Starting direction, always starts going left */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	
	private World world;
	
	public Rogue(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION);
		this.world = world;
	}
	
	@Override
	/** Modify normal enemy move so we push as well */
	// TODO Rogue needs to move even when player doesn't
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (world.traversable(temp) && world.push(distance, direction, temp)) {
			world.playerKill(temp);
			super.setLocation(temp);
			return true;
		}
		
	return false;
	
	}

}
