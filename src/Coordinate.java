
/** Custom built coordinate (2D point) class*/
public class Coordinate {
	
	private float x;
	private float y;
	
	public Coordinate (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		float x = this.x;
		return x;
	}
	
	public float getY() {
		float y = this.y;
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public boolean equals(Coordinate other){
		return (this.x == other.getX() && this.y == other.getY());
	}
	
	public void addY(float y) {
		this.y += y;
	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	@Override
	public Coordinate clone(){
		Coordinate copy = new Coordinate(this.x, this.y);
		return copy;
	}
	
	public void set(Coordinate coord){
		this.x = coord.getX();
		this.y = coord.getY();
	}
	
	public void print() {
		System.out.format("%f, %f\n", this.x, this.y);
	}
	
}
