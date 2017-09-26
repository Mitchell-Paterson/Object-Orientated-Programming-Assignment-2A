
public class Rogue extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "rogue.png";
	/** Starting direction, always starts going left */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	
	public Rogue(Coordinate coordinate) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION);
	}
	
	@Override
	/** Modify normal enemy move so we push as well */
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (World.traversable(temp) && World.push(distance, direction, temp)) {
			World.playerKill(temp);
			super.setLocation(temp);
			return true;
		}
		
	return false;
	
	}

}
