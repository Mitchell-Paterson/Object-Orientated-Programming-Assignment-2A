import org.newdawn.slick.Input;

public class Player extends Reversable implements Mobile{
	
	private static final String SOURCE = Loader.SOURCE_FILE + "player_left.png";
	
	public Player(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}
	
	/** Takes input and converts to a move order */
	@Override
	public void update(Input input) {
		
		boolean moved = false;
		Coordinate prevLocation = super.getLocation();
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			moved = move(-1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			moved = move(1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			moved = move(-1, 'x');
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			moved = move(1, 'x');
		}
		
		if (moved) {
			addPrev(prevLocation);
		}
		
		
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (World.traversable(temp) && World.push(distance, direction, temp)) {
			World.addMove();
			super.setLocation(temp);
			return true;
		}
	return false;
	}
	
}