import java.util.LinkedList;
import java.util.List;


public class Block extends Reversable implements Mobile {
	
	private boolean onPressurePad = false;
	private PressurePad linkedPad = null;
	private LinkedList<Integer> moveIndex;
	
	public Block(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate, world);
		moveIndex = new LinkedList<Integer>();
	}
	
	@Override
	public void reset() {
		super.reset();
		moveIndex.clear();
	}
	
	// TODO Not overriding, instead overloading, not sure if safe
	public Coordinate undo(int moves) {
		
		if (moveIndex.size() > 0) {
			if (moveIndex.getLast() == moves) {
				moveIndex.removeLast();
				return super.undo();
			}
		}
		return null;
	}
	
	
	public void updatePad(List<Sprite> spritesAt) {
		
		// Will need to catch null error
		if (onPressurePad) {
			onPressurePad = false;
			linkedPad.deactivate();
			linkedPad = null;
		}
		linkedPad = World.linkToPad(spritesAt);
		if (linkedPad != null) {
			onPressurePad = true;
			linkedPad.activate();
		}
	}
	
	@Override
	public void beforeMove(List<Sprite> spritesAt) {
		addPrev(this.getLocation());
		moveIndex.add(checkWorld().getMoves());
	}
	
	@Override
	public void afterMove(List<Sprite> spritesAt) {
		updatePad(spritesAt);
	}

}
