import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Sprite {
	
	/** x & y coordinates */
	private Coordinate coordinate;
	/** Sprite image to be displayed */
	private Image image;
	
	public Sprite(String image_src, Coordinate coordinate) {
		
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.coordinate = coordinate;
		
	}
	
	public void update(Input input) {
		
	}
	
	/** For moving a sprite x blocks along. It is inherent to the sprite
	 * class, as multiple sprites will need to move in future
	 * @param distance
	 * @param direction
	 */
	public boolean move(int distance, char direction) {
		
		Coordinate temp = calculateMove(distance, direction, coordinate.copy());
		
		if (World.traversable(temp)) {
			coordinate.set(temp);
			return true;
		}
		
		return false;
	}
	
	public static Coordinate calculateMove(int distance, char direction, Coordinate temp) {
		
		float realDist = distance * App.TILE_SIZE;
		
		if (direction == 'y') {
			temp.addY(realDist);
		}
		else if (direction == 'x') {
			temp.addX(realDist);
		}
		return temp;
	}
	
	public void render(Graphics g) {
		image.drawCentered(coordinate.getX(), coordinate.getY());
	}
	
	public Coordinate getLocation() {
		Coordinate coord = coordinate.copy();
		return coord;
	}
	
	public void setLocation(Coordinate location) {
		coordinate.set(location);
	}
}