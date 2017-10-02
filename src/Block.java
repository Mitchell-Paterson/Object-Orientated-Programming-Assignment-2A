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
	
	// Overrides, but I'm messing with things
	public boolean move(Coordinate newLoc, List<Sprite> spritesAtNew, int moves) {
		
		// Check we can move there before moving
		if (World.isTraversable(spritesAtNew) && 
				!World.gotSprite(spritesAtNew, Block.class)) {
			
			addPrev(super.getLocation());
			moveIndex.add(moves);
			
			super.setLocation(newLoc);
			
			updatePad(spritesAtNew);
			
			return true;
		}
	return false;
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
	
	
	public void updatePad(List<Sprite> spritesAtNew) {
		
		// Will need to catch null error
		if (onPressurePad) {
			onPressurePad = false;
			linkedPad.deactivate();
			linkedPad = null;
		}
		if ((linkedPad = checkWorld().linkToPad(spritesAtNew)) != null){
			onPressurePad = true;
			linkedPad.activate();
		}
	}

	@Override
	public boolean move(int distance, char direction) {
		
			Coordinate temp = Mobile.calculateMove(distance, direction, super.getLocation());
			
			// Check we can move there before moving
			if (checkWorld().traversable(temp) && !checkWorld().hasBlock(temp)) {
				
				addPrev(this.getLocation());
				moveIndex.add(checkWorld().getMoves());
				
				super.setLocation(temp);
				
				// updatePad();
				
				return true;
			}
		return false;
	}

}
