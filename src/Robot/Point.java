package Robot;

public class Point {
	
	public int x;
	public int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * 
	 * Construtor Point
	 */
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	public boolean equals(Object object)
	{
	    boolean isEqual= false;

	    if (object != null && object instanceof Point)
	    {
	        isEqual = (this.x == ((Point) object).x && this.y == ((Point) object).y);
	    }

	    return isEqual;
	}

	@Override
	public int hashCode() {
	    return this.x;
	}
}
