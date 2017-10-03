import java.util.List;

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
		
		Coordinate temp = Mobile.calculateMove(distance, direction, this.getLocation());
		List<Sprite> spritesAt = world.getSpritesAt(temp);
		
		if(moveChecks(spritesAt, temp, distance, direction)) {
			beforeMove(spritesAt);
			super.setLocation(temp);
			afterMove(spritesAt);
			return true;
		}
		return false;
	}
	
	public boolean moveChecks(List<Sprite> spritesAt, Coordinate newLoc, int distance, char direction) {
		if (World.isTraversable(spritesAt) && !World.gotBlock(spritesAt)) {
			return true;
		}
		return false;
	}
	
	public void beforeMove(List<Sprite> spritesAt) {
		
	}
	
	public void afterMove(List<Sprite> spritesAt) {
		
	}
	
	public static Coordinate calculateMove(int distance, char direction, Coordinate location) {
		
		Coordinate temp = location.clone();
		
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
