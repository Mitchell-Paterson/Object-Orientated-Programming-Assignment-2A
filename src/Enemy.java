public abstract class Enemy extends Movable {
	
	/** Distance and direction to step with every move */
	private int step;
	
	public Enemy(String image_src, Coordinate coordinate, int initialStep, World world) {
		super(image_src, coordinate, world);
		step = initialStep;
	}
	
	/** Decides which way to move along axis */
	public void patrol(char axis) {
		if (!move(step, axis)) {
			step *= -1;
			move(step, axis);
		}
	}
}
