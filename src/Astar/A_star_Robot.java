package Astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Robot.Box;
import Robot.Point;
import Robot.Robot;
import Robot.Warehouse;

public class A_star_Robot {
	private List<Point> points;
	private State_Robot robo;
	private List<Point> obstaculos;
	private State_Robot armazem;
	private int maxweight,numboxes;
	private List<State_Robot> caixas;
	private List<State_Robot> openList,closedList;
	private int boxes_picked;
	
	/**
	 * 
	 * @param robo
	 * @param obstaculos
	 * @param armazem
	 * @param caixas1
	 * Construtor class A_star_Robot
	 */
	public A_star_Robot(Robot robo, List<Point> obstaculos, Warehouse armazem, List<Box> caixas1){
		openList = new ArrayList<State_Robot>();
		closedList = new ArrayList<State_Robot>();
		caixas = new ArrayList<State_Robot>();
		points = new ArrayList<Point>();
		
		State_Robot estado_pai = null;
		maxweight = robo.getCap();
		this.robo = new State_Robot(robo.getX(),robo.getY(),0,0,0,estado_pai);
		this.obstaculos = obstaculos;
		this.armazem = new State_Robot(armazem.getX(),armazem.getY(),0,0,0,estado_pai);
		
		
		for(Box caixa:caixas1){
			State_Robot estado = new State_Robot(caixa.getX(),caixa.getY(),caixa.getWeight(),0,0,estado_pai);
			caixas.add(estado);
		}
		numboxes = caixas.size();
		openList.add(this.robo);
		
	}
	
	/**
	 * Starts algorithm
	 * @return
	 */
	public List<Point> start(){
		
		State_Robot present = null;
		while (!openList.isEmpty()) {

			present = openList.get(0);	

			closedList.add(present);
			openList.remove(0);
			getSurrounding(present);
			if(present.comparar(armazem) && present.getBoxesPicked()==numboxes)
				break;

		}
		//backtrack();
		getPoints();
		for(Point kl:points){
			System.out.println(kl.x+"  "+kl.y);
		}
		return points;
	}
	/**
	 * finds points of path
	 */
	private void getPoints(){
		int size=closedList.size()-1,counter;
		for(counter=0;counter<size;counter++){
			 A_star A = new A_star(closedList.get(counter), closedList.get(counter+1), obstaculos, "Diagonal","8d");
		     points.addAll(A.start());
		}
	}
	
	/**
	 * adds points to list recursivly
	 * @return
	 */
	private List<Point> backtrack(){
		List<Point> ol = new ArrayList<Point>();
		for(State_Robot point:closedList){
			ol.add(point.getPoint());
		}
		return ol;
	}
	
	/**
	 * finds avaiable points(boxes or warehouse)
	 * @param estado
	 * @return
	 */
	private int getSurrounding(State_Robot estado){
		
		if(estado.comparar(robo)){
			for(State_Robot novoestado: caixas){
				if(!novoestado.comparar(estado)){
					checkState(estado,novoestado);
				}
				//if boxes_picked=numcaixas ->armazem
				//get g
				//inc g
				//inc current_weight
				//if new weight>maxweight break
				//inc boxes_picked
				//set father
			}
		}
		if(estado.getCurrentWeight()!=0){
			State_Robot novoestado = armazem;
			checkState(estado,novoestado);
		}
		return 0;
	}
	/**
	 * checks point
	 * @param estado
	 * @param novoestado
	 * @return
	 */
	private int checkState(State_Robot estado, State_Robot novoestado){
		novoestado.setParent(estado);
		novoestado.incBoxesPicked();
		novoestado = g_Robot(estado,novoestado);
		novoestado.setCurrentWeight(estado.getCurrentWeight()+novoestado.getWeight());
		if(novoestado.comparar(armazem))
			novoestado.setCurrentWeight(0);
		if(novoestado.getCurrentWeight()>maxweight)
			return 0;
		
		
		int index, sizeOpen = openList.size(), sizeClose = closedList.size();

		for (index = 0; index < sizeOpen; index++) {
			if (openList.get(index).getPoint().equals(novoestado.getPoint())) {
				if (openList.get(index).getF() > novoestado.getF()) {
					openList.remove(index);
					openList.add(novoestado);
					return 0;
				}
				return 0;
			}
		}

		for (index = 0; index < sizeClose; index++) {
			if (closedList.get(index).getPoint().equals(novoestado.getPoint()) && !novoestado.comparar(armazem)) {
				if (closedList.get(index).getF() > novoestado.getF()) {
					closedList.remove(index);
					closedList.add(novoestado);
					return 0;
				}
				return 0;
			}
		}
		
		openList.add(novoestado);
		Collections.sort(openList, State.StateComparator);
		if(novoestado.comparar(armazem) && novoestado.getBoxesPicked()==numboxes)
		{
			closedList.add(novoestado);
			return 1;
		}
		return 0;
		
	}
	/**
	 * gets value of g
	 * @param estado
	 * @param novoestado
	 * @return
	 */
	private State_Robot g_Robot(State_Robot estado, State_Robot novoestado){
		  A_star A = new A_star(estado, novoestado, obstaculos, "Diagonal","8d");
	     A.start();
	      int g=estado.getG()+A.getCost();
	      novoestado.setG(g);
	      novoestado.setF(g);
	     return novoestado;
	}
}