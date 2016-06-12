package Robot;

public class Warehouse{
	private Point p;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * 
	 * Construtor da Warehouse
	 */
	public Warehouse(int x, int y) {
		p=new Point(x,y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return a posi�ao X do armazem
	 */
	public int getX() {
		return p.x;
	}

	/**
	 * 
	 * @return a posi�ao Y do armazem
	 */
	public int getY() {
		return p.y;
	}

	public void setX(int value) {
		this.p.x = value;
	}

	public void setY(int value) {
		this.p.y = value;
	}

}
