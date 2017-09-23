import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Explosion extends Sprite {

	private final static String SOURCE = Loader.SOURCE_FILE + "explosion.png";
	private final static int EXIST_TIME = 400;
	
	public Explosion(Coordinate coordinate) {
		super(SOURCE, coordinate);
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.schedule(new Runnable() {
	        @Override
	        public void run() {
	        	dissapate();
	        }
	    }, EXIST_TIME, TimeUnit.MILLISECONDS);
		
	}
	
	private void dissapate() {
		World.killSprite(this);
	}

}
