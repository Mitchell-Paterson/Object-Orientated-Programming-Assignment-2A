import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Skeleton extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "skull.png";
	/** Delay between moves in milliseconds */
	private final static int WANDER_DELAY = 1000;
	/** Starting direction, always starts going up */
	private final static int INITIAL_AXIS_DIRECTION = -1;
	/** Axis to patrol along */
	private final static char PATROL_AXIS = 'y';
	
	public Skeleton(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, INITIAL_AXIS_DIRECTION, world);
		wander();
	}
	
	/** Tells skeleton to wander every second */
	private void wander() {
		// Stack overflow executor service
	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	        	// Move along y axis
	        	patrol(PATROL_AXIS);
	        }
	    }, WANDER_DELAY, WANDER_DELAY, TimeUnit.MILLISECONDS);
	}
}
