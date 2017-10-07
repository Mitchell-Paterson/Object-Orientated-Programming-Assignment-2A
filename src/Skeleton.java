import java.util.Timer;
import java.util.TimerTask;

public class Skeleton extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "skull.png";
	/** Delay between moves in milliseconds */
	private final static int PATROL_DELAY = 1000;
	/** Starting direction, always starts going up */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	/** Axis to patrol along */
	private final static char PATROL_AXIS = 'y';
	
	public Skeleton(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, world);
		Timer timer = new Timer();
		timer.schedule(new PatrolTask(), PATROL_DELAY, PATROL_DELAY);
	}
	
	class PatrolTask extends TimerTask {
		@Override
		public void run() {
			patrol(PATROL_AXIS);
		}
	}
}
