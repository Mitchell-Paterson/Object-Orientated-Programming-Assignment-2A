import java.lang.Math;

public class Mage extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "mage.png";
	/** Unused for Mage, as she decides the direction to go */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	
	public Mage(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, world);
	}
	
	public void trackingMove(Coordinate playerLoc) {
		
		Coordinate mageLoc = getLocation();
		
		float distX = playerLoc.getX() - mageLoc.getX();
		float distY = playerLoc.getY() - mageLoc.getY();
		
		int sign = 1;
		if (distX <= 0) {
			sign = -1;
		}
		
		if (Math.abs(distX) > Math.abs(distY)) {
			if(move(sign, 'x')) {
				// If we get here, we've moved in x direction
				return;
			}
		}
		// If we get here, we didn't want to move in x direction
		move(sign, 'y');
	}
}
