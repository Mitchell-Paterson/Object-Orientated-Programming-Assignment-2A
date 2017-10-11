
public abstract class Patroller extends Enemy {
	
	/** Distance and direction to step with every move */
	private int step;
	/** Axis to patrol along */
	private char axis;
	
	public Patroller(String image_src, Coordinate coordinate,
			int initialStep, char axis, World world) {
		super(image_src, coordinate, world);
		this.step = initialStep;
		this.axis = axis;
	}
	
	/** Decides which way to move along axis */
	public void patrol() {
		if (!move(step, axis)) {
			step *= -1;
			move(step, axis);
		}
	}

}
