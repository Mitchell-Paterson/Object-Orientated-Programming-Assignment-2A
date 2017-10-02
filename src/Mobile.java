
public interface Mobile {
	
	/** For moving a sprite x blocks along.
	 * @param distance
	 * @param direction
	 */
	public boolean move(int distance, char direction);
	
	public static Coordinate calculateMove(int distance, char direction, Coordinate location) {
		
		Coordinate temp = location.clone();
		
		float realDist = distance * App.TILE_SIZE;
		
		if (direction == 'y') {
			temp.addY(realDist);
		}
		else if (direction == 'x') {
			temp.addX(realDist);
		}
		return temp;
	}
}
