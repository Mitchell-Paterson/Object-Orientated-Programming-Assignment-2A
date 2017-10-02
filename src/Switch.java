
public class Switch extends PressurePad {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "switch.png";
	private Door linkedDoor;

	public Switch(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}

	@Override
	public void activate() {
		linkedDoor.toggle();

	}

	@Override
	public void deactivate() {
		linkedDoor.toggle();

	}
	
	public void linkDoor(Door door) {
		linkedDoor = door;
	}
	
	public Door getDoor() {
		return linkedDoor;
	}

}
