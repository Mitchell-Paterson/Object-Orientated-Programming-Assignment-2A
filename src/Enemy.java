import java.util.List;

public abstract class Enemy extends Movable implements Mobile {
	
	private int step;
	
	public Enemy(String image_src, Coordinate coordinate, int initialStep, World world) {
		super(image_src, coordinate, world);
		step = initialStep;
	}
	
	/*
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, this.getLocation());
		
		// We check it's okay to walk on first
		if (checkWorld().traversable(temp) && checkWorld().hasBlock(temp)) {
			super.setLocation(temp);
			checkWorld().playerKill(temp);
			return true;
		}
		
		// Couldn't move there
		return false;
	}
	*/
	
	/** Decides which way to move along axis */
	public void patrol(char axis) {

		if (!move(step, axis)) {
			step *= -1;
			move(step, axis);
		}
	}
	
	@Override
	public void afterMove(List<Sprite> spritesAt) {
		/*
		if player at same spot {
			world.reset;
		}
		*/
		// checkWorld().playerKill(spritesAt);
	}

}
