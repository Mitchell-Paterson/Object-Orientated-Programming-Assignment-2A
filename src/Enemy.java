/** Allows player identify what will kill them */
public abstract class Enemy extends Movable {
	
	public Enemy(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate, world);
	}

}
