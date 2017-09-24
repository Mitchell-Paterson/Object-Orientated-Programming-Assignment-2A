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
	
	public void render(Graphics g) {
		image.drawCentered(coordinate.getX(), coordinate.getY());
	}
	
	public Coordinate getLocation() {
		Coordinate coord = coordinate.clone();
		return coord;
	}
	
	public void setLocation(Coordinate location) {
		coordinate.set(location);
	}
}