
public class Movable extends Sprite {
	
	private World world;
	
	public Movable(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate);
		this.world = world;
	}
	
	public World checkWorld() {
		return world;
	}
}
