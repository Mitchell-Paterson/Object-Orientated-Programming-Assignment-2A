import java.util.List;

import org.newdawn.slick.Input;

public class Player extends Reversable implements Mobile{
	
	private static final String SOURCE = Loader.SOURCE_FILE + "player_left.png";
	
	private World world;
	
	public Player(Coordinate coordinate, World world) {
		super(SOURCE, coordinate);
		this.world = world;
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
	
	public boolean move(Coordinate newLoc, List<Sprite> spritesAtNew, int moves) {
		
		// Check we can move there before moving
		if (World.isTraversable(spritesAtNew) && !World.gotBlock(spritesAtNew)) {
			
			addPrev(super.getLocation());
			
			super.setLocation(newLoc);
			
			return true;
		}
	return false;
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// We check it's okay to walk on and everything there can be pushed away
		if (world.traversable(temp) && world.push(distance, direction, temp.clone())) {
			super.setLocation(temp);
			world.addMove();
			world.deadly(temp);
			return true;
		}
	return false;
	}
	
	@Override
	public Coordinate undo() {
		Coordinate newLoc = super.undo();
		if (newLoc != null) {
			World.deadly(newLoc);
			return newLoc;
		}
		return null;
	}
	
}