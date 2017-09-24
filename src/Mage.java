
public class Mage extends Enemy {
	
	private final static String SOURCE = Loader.SOURCE_FILE + "mage.png";
	
	public Mage(Coordinate coordinate) {
		super(SOURCE, coordinate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean move(int distance, char direction) {
		// TODO Auto-generated method stub
		return false;
	}

}
