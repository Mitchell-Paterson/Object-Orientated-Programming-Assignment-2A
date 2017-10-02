import java.util.LinkedList;

public class Reversable extends Movable {

	private LinkedList<Coordinate> prevLocations;
	private final Coordinate FIRST_LOCATION;
	
	public Reversable(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate, world);
		prevLocations = new LinkedList<Coordinate>();
		FIRST_LOCATION = (coordinate.clone());
	}
	
	public void reset() {
		super.setLocation(FIRST_LOCATION);
		prevLocations.clear();
	}
	
	public Coordinate undo() {
		if (prevLocations.size() > 0) {
			Coordinate prevLoc = prevLocations.pollLast();
			super.setLocation(prevLoc);
			return prevLoc;
		}
		return null;
	}
	
	public void addPrev(Coordinate previousLocation) {
		prevLocations.add(previousLocation);
	}

}
