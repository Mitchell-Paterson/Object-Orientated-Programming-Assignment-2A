
public abstract class Enemy extends Sprite implements Mobile {
	
	private int step;
	
	public Enemy(String image_src, Coordinate coordinate, int initialStep) {
		super(image_src, coordinate);
		step = initialStep;
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, this.getLocation());
		
		// We check it's okay to walk on first
		if (World.traversable(temp) && !World.hasBlock(temp)) {
			super.setLocation(temp);
			World.playerKill(temp);
			return true;
		}
		
		// Couldn't move there
		return false;
	}
	
	/** Decides which way to move along axis */
	public void patrol(char axis) {

		if (!move(step, axis)) {
			step *= -1;
			move(step, axis);
		}
	}

}
