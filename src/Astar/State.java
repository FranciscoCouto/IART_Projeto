package Astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Robot.Point;

public class State implements Comparable<State>{
	
	private Point p;
	private int f; 
	private int g;
	private int h;
	private State parent;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * Contstrutor class State
	 */
	public State(int x, int y) {
		p=new Point(x,y);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param pai
	 * @param g1
	 * @param h1
	 * Construtor class State
	 */
	public State(int x, int y,State pai,int g1, int h1) {
		p=new Point(x,y);
		parent=pai;
		g=g1;
		h=h1;
		f=g+h;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @return Point od State
	 */
	public Point getPoint(){
		return p;
	}
	
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return p.x;
	}
	
	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return p.y;
	}
	
	/**
	 * 
	 * @return f
	 */
	public int getF() {
		return f;
	}
	
	/**
	 * 
	 * @return g
	 */
	public int getG() {
		return g;
	}
	
	/**
	 * 
	 * @return h
	 */
	public int getH() {
		return h;
	}
	
	/**
	 * 
	 * @param value
	 * Set F value
	 */
	public void setF(int value) {
		this.f = value;
	}
	
	/**
	 * 
	 * @param value
	 * Set H value
	 */
	public void setH(int value) {
		this.h = value;
	}
	
	/**
	 * 
	 * @param value
	 * Set G value
	 */
	public void setG(int value) {
		this.g = value;
	}
	
	 /**
	  * 
	  * @return Parent of State
	  */
	public State getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent
	 * Set Parent of State
	 */
	public void setParent(State parent) {
		this.parent = parent;
	}
	
	/**
	 * 
	 * @param estado
	 * @return
	 * Compare two different states by their position (X,Y)
	 */
	public boolean comparar(State estado){
		if(this.getX()==estado.getX() && this.getY()==estado.getY())
			return true;
		return false;
	}
	/**
	 * Compare two different states by their F
	 */
	public int compareTo(State estado) {
		 
		int compare = ((State) estado).getF(); 
 
		//ascendente
		return this.getF() - compare;
 
 
	}
	
	
	
	public static Comparator<State> StateComparator = new Comparator<State>() {
		public int compare(State estado1, State estado2) {
			return estado1.compareTo(estado2);
		}

	};
}
