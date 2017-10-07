public class Movable extends Sprite {
	
	private World world;
	
	public Movable(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate);
		this.world = world;
	}
	
	public World checkWorld() {
		return world;
	}
	

	public boolean move(int distance, char direction) {
		
		Coordinate newLoc = calculateMove(distance, direction);
		
		if(moveChecks(newLoc, distance, direction)) {
			beforeMove();
			super.setLocation(newLoc);
			afterMove();
			return true;
		}
		return false;
	}
	
	public boolean moveChecks(Coordinate newLoc, int distance, char direction) {
		if (checkWorld().traversable(newLoc) && !checkWorld().gotSprite(newLoc, Block.class)) {
			return true;
		}
		return false;
	}
	
	// Empty by default
	public void beforeMove() {
		
	}
	
	// Empty by default
	public void afterMove() {
		
	}
	
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
