package Robot;

public class Boxes{
 private Point p;
 private float weight;
	public Boxes(int x, int y, float w) {
		p=new Point(x,y);
		weight=w;
		// TODO Auto-generated constructor stub
	}
	
	public int getX() {
		return p.x;
	}

	public int getY() {
		return p.y;
	}
	public float getWeight(){
		return weight;
	}
}
