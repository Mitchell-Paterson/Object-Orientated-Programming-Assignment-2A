import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite {
	
	/** x & y coordinates */
	protected Coordinate coordinate;
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
	
	/** For moving a sprite x blocks along. It is inherent to the sprite
	 * class, as multiple sprites will need to move in future
	 * @param distance
	 * @param direction
	 */
	protected void move(int distance, char direction) {
		
		float realDist = distance * App.TILE_SIZE;
		float x = coordinate.getX();
		float y = coordinate.getY();
		
		if (direction == 'y') {
			if (World.unBlocked(x, y + realDist)) {
				y += realDist;
				coordinate.setY(y);
			}
		}
		if (direction == 'x') {
			if (World.unBlocked(x + realDist, y)) {
				x += realDist;
				coordinate.setX(x);
			}
		}
	}
	
	public void render(Graphics g) {
		image.drawCentered(coordinate.getX(), coordinate.getY());
	}
}