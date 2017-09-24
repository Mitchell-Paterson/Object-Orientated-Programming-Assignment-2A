
public abstract class Tile extends Sprite {
	
	private boolean traversable;
	
	public Tile(String image_src, Coordinate coordinate, boolean traversable) {
		super(image_src, coordinate);
		this.traversable = traversable;
	}
	
	public boolean isTraversable() {
		boolean traversable = this.traversable;
		return traversable;
	}
	
}
