import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Skeleton extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "skull.png";
	/** Delay between moves in milliseconds */
	private final static int WANDER_DELAY = 1000;
	/** Current direction, always starts going up */
	private boolean goingUp = true;
	
	public Skeleton(Coordinate coordinate) {
		super(SOURCE, coordinate);
		wander();
	}
	
	/** Tells skeleton to wander every second */
	private void wander() {
		// Stack overflow executor service
	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            wanderMove();
	        }
	    }, WANDER_DELAY, WANDER_DELAY, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (World.traversable(temp)) {
			super.setLocation(temp);
			World.playerKill(temp);
			return true;
		}
	return false;
	}
	
	/** Decides which way to move and does so */
	private void wanderMove() {
		// TODO Make this more elegant and remove magic numbers
		if (goingUp) {
			if (!move(-1, 'y')) {
				goingUp = false;
				move(1, 'y');
			}
		} else {
			if (!move(1, 'y')) {
				goingUp = true;
				move(-1, 'y');
			}
		}
	}

}
