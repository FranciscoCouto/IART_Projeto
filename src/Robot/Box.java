package Robot;

public class Box{
 private Point p;
 private int weight;
 
 	/**
 	 * 
 	 * @param x
 	 * @param y
 	 * @param w
 	 * 
 	 * Construtor BOx
 	 */
	public Box(int x, int y, int w) {
		p=new Point(x,y);
		weight=w;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return a posiçao X da Caixa
	 */
	public int getX() {
		return p.x;
	}
	
	/**
	 * 
	 * @return a posição Y da Caixa
	 */
	public int getY() {
		return p.y;
	}
	
	/**
	 * 
	 * @return o peso da Caixa
	 */
	public int getWeight(){
		return weight;
	}
}
