import org.newdawn.slick.Input;

public class Player extends Sprite implements Mobile, Pusher{
	
	private static final String SOURCE = Loader.SOURCE_FILE + "player_left.png";
	
	public Player(Coordinate coordinate) {
		super(SOURCE, coordinate);
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
		
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = calculateMove(distance, direction, super.getLocation());
		
		if (World.unBlocked(temp)) {
			super.setLocation(temp);
			World.push(distance, direction, super.getLocation());
			return true;
		}
	return false;
	}
}