import org.newdawn.slick.Input;

public class Player extends Reversable {
	
	private static final String SOURCE = Loader.SOURCE_FOLDER + "player_left.png";
	
	public Player(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}
	
	/** Takes input and converts to a move order */
	@Override
	public void update(Input input) {
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			move(-1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			move(1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			move(-1, 'x');
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			move(1, 'x');
		}
		if (checkWorld().gotSprite(getLocation(), Enemy.class)) {
			checkWorld().reset();
		}
	}
	
	@Override
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		
		if (checkWorld().traversable(newLoc) 
				&& checkWorld().push(distance, direction, newLoc.clone())) {
			checkWorld().addMove(true, newLoc);
			return true;
		}
		checkWorld().addMove(false, newLoc);
		return false;
	}
	
	@Override
	public void beforeMove() {
		addPrev(getLocation());
	}
}