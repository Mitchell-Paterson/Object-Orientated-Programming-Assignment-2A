
public class TNT extends Block {

	private static final String SOURCE = Loader.SOURCE_FILE + "tnt.png";
	private CrackedWall linkedCracked = null;

	public TNT(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}
	
	/** Move method now checks if we need to explode on new location */
	@Override
	public boolean move(int distance, char direction) {
		if (super.move(distance, direction)) {
			checkExplode();
			return true;
		}
		return false;
	}
	
	/** Checks if TNT next to cracked wall */
	private void checkExplode() {
		
		// For now, it's only ever possible for cracked wall to be below
		Coordinate below  = super.getLocation();
		below.addY(1 * App.TILE_SIZE);
		
		if ((linkedCracked = World.linkCracked(below)) != null) {
			explode();
		}
	}
	
	/** Destroys TNT, cracked wall and generates explosion */
	private void explode() {
		World.killSprite(linkedCracked);
		World.birthSprite("explosion", super.getLocation());
		World.killSprite(this);
	}
}
