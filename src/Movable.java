
public abstract class Movable extends Sprite {
	
	/** Pointer to world the Movable is stored in */
	private World world;
	
	public Movable(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate);
		this.world = world;
	}
	
	/** Access link to world */
	public World checkWorld() {
		return world;
	}
	
	/** Main move method 
	 * @param distance Distance to move, use negative to go backwards.
	 * @param direction The axis along which we move
	 * @return Returns boolean, indicating whether the move was successful or not.
	 */
	public boolean move(int distance, char direction) {
		
		Coordinate newLoc = calculateMove(distance, direction);
		
		if(moveChecks(newLoc, distance, direction)) {
			beforeMove();
			// TODO Does stuff like this need a reference to super?
			setLocation(newLoc);
			afterMove();
			// Move succeeded
			return true;
		}
		// Move failed
		return false;
	}
	
	/** Checks to take before moving. Has default implementation in Movable.
	 * @param newLoc New location to check
	 * @param distance How far we are moving
	 * @param direction Axis we are moving along
	 * @return If the move can happen or not
	 */
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		return (world.traversable(newLoc) && !world.gotSprite(newLoc, Block.class));
	}
	
	// Empty by default
	// TODO This "semi-abstract" thing... is it okay?
	public void beforeMove() {}
	
	// Empty by default
	public void afterMove() {}
	
	/** Calculates where the sprite will be from the move
	 * @param distance Distance to move, use negative to go backwards.
	 * @param direction The axis along which we move
	 * @return Location of sprite after move
	 */
	public Coordinate calculateMove(int distance, char direction) {
		
		Coordinate temp = getLocation();
		
		float realDist = distance * App.TILE_SIZE;
		
		if (direction == 'y') {
			temp.addY(realDist);
		}
		else if (direction == 'x') {
			temp.addX(realDist);
		}
		return temp;
	}
}
