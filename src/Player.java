import java.util.List;

import org.newdawn.slick.Input;

public class Player extends Reversable implements Mobile{
	
	private static final String SOURCE = Loader.SOURCE_FILE + "player_left.png";
	
	public Player(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}
	
	/** Takes input and converts to a move order */
	@Override
	public void update(Input input) {
		
		boolean moved = false;
		Coordinate prevLocation = getLocation();
		
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
	public boolean moveChecks(List<Sprite> spritesAt, Coordinate newLoc, int distance, char direction) {
		if (World.isTraversable(spritesAt) 
				&& checkWorld().push(distance, direction, newLoc.clone())) {
			return true;
		}
		return false;
	}
	
	@Override
	public void afterMove(List<Sprite> spritesAt) {
		checkWorld().addMove();
		checkWorld().deadly(getLocation());
	}

	
	@Override
	public Coordinate undo() {
		Coordinate newLoc = super.undo();
		if (newLoc != null) {
			checkWorld().deadly(newLoc);
			return newLoc;
		}
		return null;
	}
	
}