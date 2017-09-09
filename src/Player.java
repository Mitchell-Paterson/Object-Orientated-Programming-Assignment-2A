import org.newdawn.slick.Input;

public class Player extends Sprite {
	
	
	public Player(String image_src, Coordinate coordinate) {
		super(image_src, coordinate);
	}
	
	/** Takes input and converts to a move order */
	public void giveInput(Input input, int delta) {
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			move(-1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			move(1, 'y');
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			move(-1, 'x');
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			move(1, 'x');
		}
		
	}

}