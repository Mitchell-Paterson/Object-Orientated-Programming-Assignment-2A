
public class Target extends PressurePad {
	
	private final static String SOURCE = Loader.SOURCE_FOLDER + "target.png";
	private boolean on = false;

	public Target(Coordinate coordinate) {
		super(SOURCE, coordinate);
	}
	
	@Override
	public void toggle() {
		on = !on;
	}
	
	public boolean isOn() {
		boolean on = this.on;
		return on;
	}

}
