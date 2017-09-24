import java.util.LinkedList;


public class Block extends Reversable implements Mobile {
	
	private boolean onPressurePad = false;
	private PressurePad linkedPad = null;
	private LinkedList<Integer> moveIndex;
	
	public Block(String image_src, Coordinate coordinate) {
		super(image_src, coordinate);
		moveIndex = new LinkedList<Integer>();
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
		
		// Check we can move there before moving
		if (World.traversable(temp) && !World.hasBlock(temp)) {
			
			addPrev(this.getLocation());
			moveIndex.add(World.getMoves());
			
			super.setLocation(temp);
			
			linkPad();
			
			return true;
		}
	return false;
	}
	
	@Override
	public void reset() {
		super.reset();
		moveIndex.clear();
	}
	
	@Override
	public void undo() {
		
		if (moveIndex.size() > 0) {
			if (moveIndex.getLast() == World.getMoves()) {
				moveIndex.removeLast();
				super.undo();
			}
		}
	}
	
	
	public void linkPad() {
		
		// Will need to catch null error
		if (onPressurePad) {
			onPressurePad = false;
			linkedPad.deactivate();
			linkedPad = null;
		}
		if (World.hasPressurePad(super.getLocation())){
			onPressurePad = true;
			linkedPad = World.linkPad(super.getLocation());
			linkedPad.activate();
		}
	}

}
