import org.newdawn.slick.Graphics;

public class Door extends Tile {
	
	private final static boolean STARTS_OPEN = false;
	private final static String SOURCE = Loader.SOURCE_FILE + "door.png";
	private boolean open;

	public Door(Coordinate coordinate) {
		super(SOURCE, coordinate, STARTS_OPEN);
		open = STARTS_OPEN;
	}
	
	public void toggle() {
		open = !open;
		super.toggleTraversable();
	}
	
	@Override
	public void render(Graphics g) {
		if (!open) {
			super.render(g);
		}
	}

}
