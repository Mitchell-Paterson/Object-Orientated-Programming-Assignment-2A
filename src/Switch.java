
public class Switch extends PressurePad {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "switch.png";

	public Switch(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}

	@Override
	public void activate() {
		World.toggleDoors();

	}

	@Override
	public void deactivate() {
		World.toggleDoors();

	}

}
