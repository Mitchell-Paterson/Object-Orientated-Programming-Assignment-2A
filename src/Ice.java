import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ice extends Block {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "ice.png";
	/** Delay between movements in milliseconds */
	private final static int SLIDE_DELAY = 250;
	
	public Ice(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}
	
	@Override
	public boolean move(int distance, char direction) {
		if (super.move(distance, direction)) {
			// TODO Clean this schedulor up somehow
			final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		    executorService.schedule(new Runnable() {
		        @Override
		        public void run() {
		        	slide(distance, direction);
		        }
		    }, SLIDE_DELAY, TimeUnit.MILLISECONDS);
		    return true;
		} else {
			return false;
		}
	}

	public boolean slide(int distance, char direction) {
		
		if (slide_move(distance, direction)) {
			final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		    executorService.schedule(new Runnable() {
		        @Override
		        public void run() {
		        	slide(distance, direction);
		        }
		    }, SLIDE_DELAY, TimeUnit.MILLISECONDS);
		    return true;
		} else {
			return false;
		}
	}
	
	public boolean slide_move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		List<Sprite> spritesAt = checkWorld().getSpritesAt(temp);
		
		if(moveChecks(temp, distance, direction)) {
			// beforeMove omitted
			super.setLocation(temp);
			afterMove(spritesAt);
			return true;
		}
		return false;
	}
}
