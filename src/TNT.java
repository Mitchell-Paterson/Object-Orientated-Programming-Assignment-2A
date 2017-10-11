
public class TNT extends Block {

	private static final String SOURCE = Loader.SOURCE_FOLDER + "tnt.png";
	private CrackedWall linkedCracked = null;

	public TNT(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}
	
	/** Checks if we need to explode on new location */
	@Override
	public void afterMove() {
		
		// For now, it's only ever possible for cracked wall to be below
		// TODO This can be improved, just check one step ahead of it's move
		Coordinate below  = super.getLocation();
		below.addY(1 * App.TILE_SIZE);
		
		linkedCracked = (CrackedWall) checkWorld().getSpriteAt(below, CrackedWall.class);
		if (linkedCracked != null) {
			checkWorld().killSprite(linkedCracked);
			checkWorld().birthSprite("explosion", super.getLocation());
			checkWorld().killSprite(this);
		}
	}
}
