package Astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Robot.Point;

public class A_star {
	List<State> openList = new ArrayList<State>();
	List<State> closeList = new ArrayList<State>();
	List<Point> obstacles = new ArrayList<Point>();
	List<Point> points = new ArrayList<Point>();
	Heuristic heuristic = new Heuristic();
	State goal, inicio;
	int maxCost;
	String heuristicOption, directions;
	
	/**
	 * 
	 * @param inicial
	 * @param goal1
	 * @param obs
	 * @param heuristicOpt
	 * @param direction
	 * Construtor class A_Star
	 */
	public A_star(State inicial, State goal1, List<Point> obs,
			String heuristicOpt, String direction) {
		directions = direction;
		inicio = inicial;
		goal = goal1;
		heuristicOption = heuristicOpt;
		obstacles = obs;
		openList.clear();
		closeList.clear();
		openList.add(inicial);
	}

	/**
	 * Starts algorithm
	 * @return list of points that represent the path from inicial to goal
	 */
	public List<Point> start() {
		State present = null;
		while (!openList.isEmpty()) {

			present = openList.get(0);
			if (present.comparar(goal))
				break;
			closeList.add(present);
			openList.remove(0);
			if (getSurrounding(present) == 1)
				break;

		}
		maxCost = goal.getF();
		backtrack(goal);
		if (points.size() != 1)
			points.remove(points.size() - 1);

		return points;
	}

	/**
	 * returns max cost from inicial to goal
	 * @return
	 */
	public int getCost() {
		return goal.getF();
	}

	/**
	 * adds points to list recursivly
	 * @param estado
	 */
	private void backtrack(State estado) {

		if (!estado.comparar(inicio)) {
			backtrack(estado.getParent());
		}
		points.add(estado.getPoint());
	}

	/**
	 * prints points that represent the path from inicial to goal
	 */
	public void printList() {
		for (Point point : points) {
			System.out.println("X: " + point.x + " , Y: " + point.y);
		}

	}
	
	/**
	 * adds neighbour points to open list
	 * @param estado
	 * @return
	 */
	private int getSurrounding(State estado) {
		Point ponto = new Point(estado.getX(), estado.getY());
		ponto.x -= 1;
		ponto.y -= 1;
		checkPoint(estado, ponto, true);

		ponto.y += 1;
		checkPoint(estado, ponto, false);

		ponto.y += 1;
		checkPoint(estado, ponto, true);

		ponto.x += 1;
		checkPoint(estado, ponto, false);

		ponto.x += 1;
		checkPoint(estado, ponto, true);

		ponto.y -= 1;
		checkPoint(estado, ponto, false);

		ponto.y -= 1;
		checkPoint(estado, ponto, true);

		ponto.x -= 1;
		checkPoint(estado, ponto, false);

		Collections.sort(openList, State.StateComparator);
		State ne = openList.get(0);
		if (ne.comparar(goal)) {
			goal.setParent(ne);
			goal.setF(ne.getF());
			return 1;
		}
		return 0;
	}

	/**
	 * checks points if their are possible
	 * if there are obstacles they are not accepted
	 * @param estado
	 * @param ponto
	 * @param diagonal
	 * @return
	 */
	private int checkPoint(State estado, Point ponto, boolean diagonal) {
		if (diagonal && directions.equals("4d"))
			return 0;
		State estadonovo = new State(ponto.x, ponto.y, estado, 0, 0);

		if (isWall(ponto))
			return 0;

		// ////////////////////////7777
		// calcular h e g e f
		int g = findG(diagonal, estado), h = findH(estadonovo), f = g + h;
		estadonovo.setG(g);
		// /////////////////////////////////heuristica
		estadonovo.setH(h);
		estadonovo.setF(f);

		int index, sizeOpen = openList.size(), sizeClose = closeList.size();

		for (index = 0; index < sizeOpen; index++) {
			if (openList.get(index).getPoint().equals(estadonovo.getPoint())) {
				if (openList.get(index).getF() > estadonovo.getF()) {
					openList.remove(index);
					openList.add(estadonovo);
					return 0;
				}
				return 0;
			}
		}

		for (index = 0; index < sizeClose; index++) {
			if (closeList.get(index).getPoint().equals(estadonovo.getPoint())) {
				if (closeList.get(index).getF() > estadonovo.getF()) {
					closeList.remove(index);
					closeList.add(estadonovo);
					return 0;
				}
				return 0;
			}
		}

		openList.add(estadonovo);
		return 0;
	}

	/**
	 * returns value of g
	 * penalty for diagonals
	 * @param diag
	 * @param estado
	 * @return
	 */
	private int findG(boolean diag, State estado) {
		if (!diag)
			return estado.getG() + 10;
		else
			return estado.getG() + 14;

	}

	/**
	 * returns value of H
	 * penalty for diagonals
	 * @param estado
	 * @return
	 */
	private int findH(State estado) {
		if (heuristicOption.equals("Manhattan"))
			return heuristic.Manhattan(estado, goal);
		else
			return heuristic.Diagonal(estado, goal);
	}

	/**
	 * checks if ponto has a wall(obstacle)
	 * @param ponto
	 * @return
	 */
	private boolean isWall(Point ponto) {
		if (!obstacles.isEmpty())
			if (obstacles.contains(ponto))
				return true;
		return false;
	}

}
