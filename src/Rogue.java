
public class Rogue extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "rogue.png";
	/** Current direction */
	private boolean goingLeft = true;
	
	public Rogue(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}
	
	public void patrol() {
		
		if (goingLeft) {
			if (!move(-1, 'x')) {
				goingLeft = false;
				move(1, 'x');
			}
		} else {
			if (!move(1, 'x')) {
				goingLeft = true;
				move(-1, 'x');
			}
		}
	}
	
	@Override
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
