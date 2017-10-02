
public class Stone extends Block {
	
	private static final String SOURCE = Loader.SOURCE_FILE + "stone.png";
	
	public Stone(Coordinate coordinate, World world) {
		super(SOURCE, coordinate, world);
	}

}
