
public class Block extends Sprite {
	
	private boolean onPressurePad = false;
	private PressurePad linkedPad = null;
	
	public Block(String image_src, Coordinate coordinate) {
		super(image_src, coordinate);
	}
	
	@Override
	public boolean move(int distance, char direction) {
		
		Coordinate temp = calculateMove(distance, direction, super.getLocation());
		
		// Check we can move there before moving
		if (World.traversable(temp) && !World.hasBlock(temp)) {
			
			super.setLocation(temp);
			
			// Will need to catch null error
			if (onPressurePad) {
				onPressurePad = false;
				linkedPad.deactivate();
				linkedPad = null;
			}
			if (World.hasPressurePad(temp)){
				onPressurePad = true;
				linkedPad = World.linkPad(temp);
				linkedPad.activate();
			}
			return true;
		}
	return false;
	}

}
