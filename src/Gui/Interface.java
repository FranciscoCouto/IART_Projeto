package Gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Gui.Pane;
import Robot.Box;
import Robot.Point;
import Robot.Robot;
import Robot.Warehouse;
public class Interface extends JFrame{
	

	private static final int WIDTH = 800;

	  private static final int HEIGHT = 600;
	  
	  JButton playB;
	  JButton exitB;
	  
	   Pane background; 
	   JLabel back;
	   JLabel robot;
	   JLabel obst2;
	   JLabel box2;
	   JLabel war2;
	   JLabel rasto;

	   final ImageIcon rob;
	   ImageIcon box;
	   ImageIcon obst;
	   ImageIcon war;
	   ImageIcon rasto1;
	   ImageIcon backg;
	  

	    //Button handlers:
	  	
	  	private PlayButtonHandler pbHandler;
	    private ExitButtonHandler ebHandler;
	    
        List<Point> path;
	      
	    int vezes=0;
	    int RobotX;
	    int RobotY;
	    
	    final Robot ObjRobot;
	    Warehouse ObjWare;
	    List<Box> ObjBoxes;
	    List<Point> ObjObstacles;
	 
	    /**
	     * 
	     * @param ObjRobot
	     * @param ObjWare
	     * @param ObjBoxes
	     * @param ObjObstacles
	     * @param PathRobot
	     * 
	     * Construtor da Interface
	     */
	 public Interface(final Robot ObjRobot, Warehouse ObjWare, List<Box> ObjBoxes, List<Point> ObjObstacles, List<Point> PathRobot)

	    {	
		 
		 	this.ObjRobot = ObjRobot;
		 	this.ObjWare = ObjWare;
		 	this.ObjBoxes = ObjBoxes;
		 	this.ObjObstacles = ObjObstacles;
		 	this.path = PathRobot;
		 	this.RobotX = ObjRobot.getX();
		 	this.RobotY = ObjRobot.getY();

	        setTitle("Robot box catching Simulator!");
	        setLayout(new BorderLayout());
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        
	        try {
				background = new Pane(ImageIO.read(new File("images/background.jpg")), 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        background.setLayout(null);
	        add(background);
	        
	        playB = new JButton("Run Simulation!");	
	        playB.setBounds(0, 550, 400, 50);
	        exitB = new JButton("Exit!");
	        exitB.setBounds(400, 550, 400, 50);
	        //exitB.setPreferredSize(new Dimension(60, 30)); 
	       
	        
	        pbHandler = new PlayButtonHandler();
	        ebHandler = new ExitButtonHandler();

	        playB.addActionListener(pbHandler);
	        exitB.addActionListener(ebHandler);  
	        
	        background.add(playB);
	        background.add(exitB);
	        
	        rob = new ImageIcon("images/robot.png");
			robot = new JLabel(rob);
			box = new ImageIcon("images/box.png");
			box2 = new JLabel(box);

			
			obst = new ImageIcon("images/obst.gif");
			obst2 = new JLabel(obst);
			war = new ImageIcon("images/warehouse.gif");
			war2 = new JLabel(war);
			rasto1 = new ImageIcon("images/box.png");
			rasto = new JLabel(rasto1);
			backg = new ImageIcon("images/square.jpg");
			back = new JLabel(backg);
   
			Dimension d = new Dimension(800,629);
            setPreferredSize(d);
            pack();
            setResizable(false);
            setVisible(true);

	    }
	 
	 /**
	  * 
	  * Ação ao cliclar no botão Exit
	  *
	  */
	 public class ExitButtonHandler implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	System.exit(0);

	        }
	    }
	 
	 /**
	  * 
	  * Função responsavel por, aquando carregar no botao "Run simulation!" , desenhar todos os objetos nas posições corretas e por fazer
	  * o robô seguir o seu trajeto
	  *
	  */
	 public class PlayButtonHandler implements ActionListener
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	
	        	List<Integer> ArrayX = new ArrayList<Integer>();
				List<Integer> ArrayY = new ArrayList<Integer>();
				
				ArrayX.add(ObjRobot.getX());
				ArrayY.add(ObjRobot.getY());
				ArrayX.add(ObjWare.getX());
				ArrayY.add(ObjWare.getY());
				
				//System.out.println("ROBOT: " + ObjRobot.getX() + ObjRobot.getY() + " ARMAZZEM: " + ObjWare.getX()+ ObjWare.getY());
				
		       for(int i =0; i < ObjBoxes.size(); i++) {
		    	   ArrayX.add(ObjBoxes.get(i).getX());
		    	   ArrayY.add(ObjBoxes.get(i).getY());
		    	   //System.out.println(ObjBoxes.get(i).getX() + ObjBoxes.get(i).getY());
		       }
		       
		       int maiorX = 0, maiorY = 0;
		       int menorX = 9999, menorY = 9999;
		       for(int p=0; p < ArrayX.size(); p++) {
		    	   if(ArrayX.get(p) > maiorX)
		    		   maiorX= ArrayX.get(p);
		    	   
		    	   if(ArrayX.get(p) < menorX)
		    		   menorX=ArrayX.get(p);
		    	  // System.out.println(ArrayX[p]);
		       }
		       
		       for(int l=0; l < ArrayY.size(); l++) {
		    	   if(ArrayY.get(l) > maiorY)
		    		   maiorY= ArrayY.get(l);
		    	   
		    	   if(ArrayX.get(l) < menorY)
		    		   menorY = ArrayX.get(l);
		    	   
		    	   //System.out.println(ArrayY[l]);
		       }
		       
		       for(int k=0; k < ObjObstacles.size(); k++) {
		    	   if(ObjObstacles.get(k).x > maiorX) 
		    		   maiorX = ObjObstacles.get(k).x;
		    	   
		    	   if(ObjObstacles.get(k).x < menorX)
		    		   menorX = ObjObstacles.get(k).x;
		    	   
		    	   if(ObjObstacles.get(k).y > maiorY)
		    		   maiorY = ObjObstacles.get(k).y;
		    	   
		    	   if(ObjObstacles.get(k).y < menorY)
		    		   menorY = ObjObstacles.get(k).y;
		       }
		     
				//System.out.println("MAIOR X" + maiorX + " MAior Y" + maiorY + " MEnor X" + menorX + " MenorY"+menorY);
		        
				maiorX += 2;
				maiorY += 2;
			    menorX -= 2; 
			    menorY -= 2;
				
			    int dimensaoX = maiorX - menorX;
			    int dimensaoY = maiorY - menorY;
			    //System.out.println("Dim X" + dimensaoX + " Dim Y" + dimensaoY);
			    
			    final int tamanhoX = 800 / dimensaoX;
			    final int tamanhoY = 600 / dimensaoY;
			    //System.out.println("TAM X" + tamanhoX + " TAM Y" + tamanhoY);
			   
			    
		        robot.setLayout(null);
		        robot.setBounds((ObjRobot.getX()+1)*tamanhoX, (ObjRobot.getY()+1)*tamanhoY,tamanhoX, tamanhoY);
		        
		        
		        for(int i=0; i< ObjBoxes.size(); i++){
		        	JLabel newLabel = new JLabel(box);
		        	newLabel.setLayout(null);
		        	newLabel.setBounds((ObjBoxes.get(i).getX()+1)*tamanhoX, (ObjBoxes.get(i).getY()+1)*tamanhoY, tamanhoX, tamanhoY);
		        	background.add(newLabel);
		        }
		        
		        for(int i=0; i< ObjObstacles.size(); i++){
		        	JLabel newLabel = new JLabel(obst);
		        	newLabel.setLayout(null);
		        	newLabel.setBounds((ObjObstacles.get(i).x+1)*tamanhoX, (ObjObstacles.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		        	background.add(newLabel);
		        }
		        
		        war2.setLayout(null);
		        war2.setBounds((ObjWare.getX()+1)*tamanhoX, (ObjWare.getY()+1)*tamanhoY, tamanhoX, tamanhoY);
		        
		        background.add(robot);
		        background.add(war2);
		        
		        
		        Timer timer = new Timer(500, new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	
		            	int i = vezes;
		     	        vezes++;
		     	        if(vezes == path.size()+1){
		     	        	
		     	        	System.exit(0);
		     	        
		     	        }
		     	        
		     	        //for(int i=0; i < path.size(); i++) {
		     	        	int x = ObjRobot.getX();
		     	        	int y = ObjRobot.getY();
		     	        	
		     	        	System.out.println("X- "+x + " Y " + y + "    " + "PX " + path.get(i).x + "  PY "+ path.get(i).y);
		     	        	
		     	        	//andar na hortizontal para postiva
		     	        	if(path.get(i).x == x+1 && path.get(i).y == y){
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setX(path.get(i).x);
		     		        	System.out.println(ObjRobot.getX());
		     		        	
		     	        	}
		     	        	//andar na vertical para postiiva
		     	        	else if(path.get(i).y == y+1 && path.get(i).x == x) {
		     	        		robot.setBounds((ObjRobot.getX()+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        
		     	        	}
		     	        	//andar na horizontal para negativo
		     	        	else if(path.get(i).x == x-1 && path.get(i).y == y) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setX(path.get(i).x);
		     		        
		     	        	}  
		     	        	//andar na vertical para negativo
		     	        	else if(path.get(i).y == y-1 && path.get(i).x == x) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        
		     	        	} 
		     	        	//andar na diagonal ++
		     	        	else if(path.get(i).y == y+1 && path.get(i).x == x+1) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        	ObjRobot.setX(path.get(i).x);
		     		        
		     	        	} 
		     	        	//andar na diagonal --
		     	        	else if(path.get(i).y == y-1 && path.get(i).x == x-1) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        	ObjRobot.setX(path.get(i).x);
		     		       
		     	        	} 
		     	        	//andar na diagonal +-
		     	        	else if(path.get(i).y == y-1 && path.get(i).x == x+1) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        	ObjRobot.setX(path.get(i).x);
		     		        	
		     	        	} 
		     	        	//andar na diagonal -+
		     	        	else if(path.get(i).y == y+1 && path.get(i).x == x-1) {
		     	        		robot.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
		     		        	background.add(robot);
		     		        	ObjRobot.setY(path.get(i).y);
		     		        	ObjRobot.setX(path.get(i).x);
		     		        	
		     	        	} 
		     	        	else if(path.get(i).y == y && path.get(i).x == x) {
		     	        		if(x == RobotX && y == RobotY) { }
		     	        		else {
			     	        		JLabel newLabel = new JLabel(backg);
			    		        	newLabel.setLayout(null);
			    		        	newLabel.setBounds((path.get(i).x+1)*tamanhoX, (path.get(i).y+1)*tamanhoY, tamanhoX, tamanhoY);
			    		        	background.add(newLabel);
		     	        		}
		     	        	} 
		     	             	        	     	        	
		     	        //}
		     	        
		                repaint();
		            }
		        });
		        
		       
		        timer.setRepeats(true);
		        timer.setCoalesce(true);
		        timer.start();

	

	        }
	    }
	 
}
