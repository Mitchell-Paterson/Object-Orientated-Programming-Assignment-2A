public class Rogue extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "rogue.png";
	/** Starting direction, always starts going left */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	/** Axis to patrol along */
	private final static char PATROL_AXIS = 'x';
	
	public Rogue(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, world);
	}
	
	@Override
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		return (checkWorld().traversable(newLoc) && checkWorld().push(distance, direction, newLoc));
	}
	
	// TODO Check if this kind of "Overload" is okay
	public void patrol() {
		patrol(PATROL_AXIS);
	}

}
