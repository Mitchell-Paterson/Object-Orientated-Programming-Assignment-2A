import java.util.List;

public class Rogue extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "rogue.png";
	/** Starting direction, always starts going left */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	
	public Rogue(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, world);
	}
	
	@Override
	/** Modify normal enemy move so we push as well */
	// TODO Rogue needs to move even when player doesn't
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (checkWorld().traversable(temp) && checkWorld().push(distance, direction, temp)) {
			checkWorld().playerKill(temp);
			super.setLocation(temp);
			return true;
		}
	return false;
	}
	
	@Override
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		if (checkWorld().traversable(newLoc) && checkWorld().push(distance, direction, newLoc)) {
			return true;
		}
		return false;
	}

}
