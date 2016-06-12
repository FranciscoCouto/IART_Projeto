package Robot;

import java.util.ArrayList;
import java.util.List;

public class Wall{
	private Point p1;
	private Point p2;
	//Vai ser preciso criar 2 pontos para contruir a "parede"
	
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * 
	 * Construtor da Wall
	 */
	public Wall(int x1, int y1, int x2, int y2) {
		p1 = new Point(x1,y1);
		p2 = new Point(x2, y2);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return o X do primeiro ponto da parede
	 */
	public int getX1() {
		return p1.x;
	}
	
	/**
	 * 
	 * @return o Y do primeiro ponto da parede
	 */
	public int getY1() {
		return p1.y;
	}
	
	/**
	 * 
	 * @return o X do segundo ponto da parede
	 */
	public int getX2() {
		return p2.x;
	}
	
	/**
	 * 
	 * @return o Y do segundo ponto da parede
	 */
	public int getY2() {
		return p2.y;
	}
	
	/**
	 * 
	 * @return o ponto 1
	 */
	public Point getPoint1(){
		return p1;
	}
	
	/**
	 * 
	 * @return o ponto 2
	 */
	public Point getPoint2(){
		return p2;
	}
	
	/**
	 * Funºao responsavel por retornar os dois pontos da parede
	 * @return
	 */
	public List<Point> getCoord(){
		List<Point> x = new ArrayList<Point>();
		x.add(p1);
		x.add(p2);
		return x;
	}

}
