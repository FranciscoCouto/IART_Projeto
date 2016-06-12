package Robot;
import java.util.ArrayList;
import java.util.List;

import Parser.Reader;
import Astar.A_star;
import Astar.A_star_Robot;
import Astar.State;
import Gui.Interface;
public class main {
	    public static void main(String[] args) {
	    	
	        System.out.println("Hello, World!");
	        Reader parser=new Reader();
	        
	        
	        List<Object> allobject=parser.readFile("input.txt");
	        
	       
	        Robot rob=null;
	        Warehouse ware=null;
	        List<Box> boxes = new ArrayList<Box>();
	        List<Wall> obstacles = new ArrayList<Wall>();
	        System.out.println(allobject);
	        int size=allobject.size();
	        for(int counter=0; counter<size;counter++){
	        	Object what=allobject.get(counter);
	        	System.out.println(what);
	        	if(what instanceof Robot)
	        		rob=(Robot) what;
	        	else if(what instanceof Warehouse)
	        		ware=(Warehouse) what;
	        	else if(what instanceof Box)
	        		boxes.add((Box) what);
	        	else if(what instanceof Wall){
	        		obstacles.add((Wall) what);
	        		System.out.println(((Wall) what).getX1()+ "  "+((Wall) what).getY1()+"  "+((Wall) what).getX2()+"  "+((Wall) what).getY2());}
	        	else{
	        		System.out.println("Argument error");
	        		System.exit(4);
	        	}
	        }
	        	

	        List<Point> obs = new ArrayList<Point>();
	        List<Point> o = new ArrayList<Point>();
	        
	        
	        for(Wall obstaculos : obstacles){
	        	State initial = new State (obstaculos.getX1(),obstaculos.getY1(),null,0,0);
	        	System.out.println("X-" +obstaculos.getX1()+" Y-"+obstaculos.getY1());
		        State target =  new State (obstaculos.getX2(),obstaculos.getY2());
		        System.out.println("X-" +obstaculos.getX2()+" Y-"+obstaculos.getY2());
		        A_star A = new A_star(initial, target, o, "Diagonal","4d");
		        obs.addAll(A.start());
		       // A.printList();
	        }
	        
	        System.out.println("------------------------------------");
	        int counter=0;
	        List<List<Point>> todosPoints = new ArrayList<List<Point>>();
	        int[] custo = new int[boxes.size()];
	        int[] peso = new int[boxes.size()];
	       /* for(Box box: boxes){
	        	State initial = new State (rob.getX(),rob.getY(),null,0,0);
	        	State target =  new State (box.getX(),box.getY());
	        	A_star B = new A_star(initial, target, obs, "Diagonal","8d");
	        	todosPoints.add(B.start());
	        	custo[counter]=B.getCost();
	        	peso[counter]=(int) box.getWeight();
	        	counter++;
	        	B.printList();
	        }
	        */
	        
	        State initial = new State (rob.getX(),rob.getY(),null,0,0);
        	State target =  new State (ware.getX(),ware.getY());
	        A_star_Robot C = new A_star_Robot(rob, obs, ware,boxes);
	        List<Point> PathRobot =  C.start();
	       // C.printList();
	       
	        
	        Interface intObj = new Interface(rob, ware, boxes, obs, PathRobot);
	    }
	   

	    
}
