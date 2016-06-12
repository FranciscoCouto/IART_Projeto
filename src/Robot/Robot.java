package Robot;

public class Robot{
	private Point p;
	private int capacity;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param cap
	 * 
	 * Construtor do Robot
	 */
	public Robot(int x, int y, int cap) {
		p=new Point(x,y);
		capacity=cap;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return a posiçao X  do robot
	 */
	public int getX() {
		return p.x;
	}

	/**
	 * 
	 * @return a posiçao Y do robot
	 */
	public int getY() {
		return p.y;
	}
	

	public void setX(int x) {
		this.p.x = x;
	}

	public void setY(int y) {
		this.p.y = y;
	}
	
	/**
	 * 
	 * @return a capacidade  do robot
	 */
	public int getCap(){
		return capacity;
	}

}
