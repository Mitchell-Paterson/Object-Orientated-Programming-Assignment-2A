import java.util.Timer;
import java.util.TimerTask;


public class Ice extends Block {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "ice.png";
	/** Delay between movements in milliseconds */
	private final static int SLIDE_DELAY = 250;
	
	public Ice(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}
	
	@Override
	public boolean move(int distance, char direction) {
		// Use normal block move at first, to save position
		if (super.move(distance, direction)) {
			// Then start sliding
		    slide(distance, direction);
		    return true;
		}
		return false;
	}

	public void slide(int distance, char direction) {
	
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// Keep sliding till blocked
				if (!slide_move(distance, direction)) {
					cancel();
				}
			}
		};
		timer.schedule(task, SLIDE_DELAY, SLIDE_DELAY);
	}
	
	// Special move to use only while sliding, instead of normal move
	public boolean slide_move(int distance, char direction) {
		
		Coordinate temp = calculateMove(distance, direction, super.getLocation());
		
		if(moveChecks(temp, distance, direction)) {
			// beforeMove omitted, no move saving
			super.setLocation(temp);
			afterMove();
			return true;
		}
		return false;
	}
}
