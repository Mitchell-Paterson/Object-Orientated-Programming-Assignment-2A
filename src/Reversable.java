import java.util.LinkedList;

public class Reversable extends Movable {

	private LinkedList<Coordinate> prevLocations;
	
	public Reversable(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate, world);
		prevLocations = new LinkedList<Coordinate>();
	}
	
	public void undo() {
		if (prevLocations.size() > 0) {
			Coordinate prevLoc = prevLocations.pollLast();
			super.setLocation(prevLoc);
		}
	}
	
	public void addPrev(Coordinate previousLocation) {
		prevLocations.add(previousLocation);
	}

}
