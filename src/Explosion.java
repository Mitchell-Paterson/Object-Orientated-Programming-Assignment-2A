import java.util.Timer;
import java.util.TimerTask;

public class Explosion extends Sprite {

	private final static String SOURCE = Loader.SOURCE_FOLDER + "explosion.png";
	private final static int EXIST_TIME = 400;
	private World world;
	
	public Explosion(Coordinate coordinate, World world) {
		super(SOURCE, coordinate);
		this.world = world;
		// TODO How do we clean up timers?
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				dissapate();
			}
		};
		timer.schedule(task, EXIST_TIME);
	}
	
	private void dissapate() {
		world.killSprite(this);
	}

}
