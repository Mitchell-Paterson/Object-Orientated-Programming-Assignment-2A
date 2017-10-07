import java.util.LinkedList;


public class Block extends Reversable {
	
	/** Pressure pad the block is interacting with */
	private PressurePad linkedPad;
	/* Saves which move number a move was made on */
	private LinkedList<Integer> moveIndex;
	
	public Block(String image_src, Coordinate coordinate, World world) {
		super(image_src, coordinate, world);
		moveIndex = new LinkedList<Integer>();
	}
	
	// TODO Not overriding, instead overloading, not sure if safe
	public void undo(int moves) {
		
		// Check we have move history
		if (!moveIndex.isEmpty()) {
			// Check we are at the current point in move history
			if (moveIndex.getLast() == moves) {
				moveIndex.removeLast();
				// Undo latest move
				super.undo();
				updatePad();
			}
		}
	}
	
	
	public void updatePad() {
		
		// Leave current PressurePad
		if (linkedPad != null) {
			linkedPad.toggle();
			linkedPad = null;
		}
		
		// Check if block has new pad now
		linkedPad = (PressurePad)
				checkWorld().getSpriteAt(getLocation(), PressurePad.class);
		
		// If new pad, activate it
		if (linkedPad != null) {
			linkedPad.toggle();
		}
		
		// Tell world to update it's target count
		checkWorld().updateTargets();
	}
	
	@Override
	public void beforeMove() {
		// Add move history
		addPrev(getLocation());
		moveIndex.add(checkWorld().getMoves());
	}
	
	@Override
	public void afterMove() {
		updatePad();
	}

}
