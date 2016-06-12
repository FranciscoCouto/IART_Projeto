package Astar;

import java.util.ArrayList;
import java.util.List;

import Robot.Point;

public class State_Robot extends State {
	private int boxes_picked;
	private int weight;
	private int current_weight;
	private List<Point> path;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param weight
	 * @param g1
	 * @param h1
	 * @param estado_pai
	 * Construtor class State_Robot
	 */
	public State_Robot(int x, int y,int weight,int g1, int h1,State_Robot estado_pai) {
		super(x, y, estado_pai, g1, h1);
		path = new ArrayList<Point>();
		this.weight = weight;
		boxes_picked = 0;
		current_weight = weight;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return current weight
	 */
	public int getCurrentWeight(){
		return current_weight;
	}
	/**
	 * 
	 * @return true if State_Robot has a path and false otherwise
	 */
	public boolean hasPath(){
		return path.isEmpty();
	}
	
	/**
	 * 
	 * @param path
	 * Sets path of State_Robot
	 */
	public void setPath(List<Point>path){
		this.path=path;
	}
	
	/**
	 * 
	 * @return path
	 */
	public List<Point> getPath(){
		return path;
	}
	
	/**
	 * Increase number of boxes picked up
	 */
	public void incBoxesPicked(){
		boxes_picked++;
	}
	
	/**
	 * Returns weight of Current Box 
	 * @return
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * Sets the current weight
	 * @param w
	 */
	public void setCurrentWeight(int w){
		current_weight=w;
	}
	
	/**
	 * returns number of boxes picked up
	 * @return
	 */
	public int getBoxesPicked(){
		return boxes_picked;
	}

}
