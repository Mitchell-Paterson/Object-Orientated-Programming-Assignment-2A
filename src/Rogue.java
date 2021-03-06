public class Rogue extends Patroller {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "rogue.png";
	/** Starting direction, always starts going left */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	/** Axis to patrol along */
	public final static char PATROL_AXIS = 'x';
	
	public Rogue(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, PATROL_AXIS, world);
	}
	
	// TODO Is the switcheroo okay?
	@Override
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		return (checkWorld().traversable(newLoc) && 
				checkWorld().push(distance, direction, newLoc));
	}
}
