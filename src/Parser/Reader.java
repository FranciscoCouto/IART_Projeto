package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Robot.Box;
import Robot.Robot;
import Robot.Wall;
import Robot.Warehouse;

public class Reader {
	/**
	 * 
	 * @param filename
	 * @return
	 * 
	 * função responsavel por ver se existe ou nao o ficheiro e ler todas as linhas desse mesmo ficheiro
	 */
	public List<Object> readFile(String filename){
		List<Object> parser=new ArrayList<Object>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename)); //open file
			String line;
			while ((line = reader.readLine()) != null)
			{
				System.out.println(line);
				parser.add(understandLine(line));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(2);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read line");
			System.exit(3);
		}
		
		return parser;
	}

	/**
	 * 
	 * @param line
	 * @return
	 * 
	 * função responsavel por dividir a linha em partes e por criar os objetos que lê
	 */
	private Object understandLine(String line) {
		// TODO Auto-generated method stub
		String[] parts = line.split(" ");
		try{
			int x=Integer.parseInt(parts[1].substring(2));
			int y=Integer.parseInt(parts[2].substring(2));
			
			if(parts[0].substring(0,4).equals("robo")){
				int cap=Integer.parseInt(parts[3].substring(11));
				Robot rob=new Robot(x,y,cap);
				return rob;
			}
			else if(parts[0].substring(0,5).equals("caixa")){
				int weight=Integer.parseInt(parts[3].substring(5));
				Box box=new Box(x,y,weight);
				return box;
			}
			else if(parts[0].substring(0,7).equals("armazem")){
				Warehouse ware=new Warehouse(x,y);
				return ware;
			}
			else if(parts[0].substring(0,9).equals("obstaculo")){
				int x1=Integer.parseInt(parts[3].substring(2));
				int y1=Integer.parseInt(parts[4].substring(2));
				Wall obst=new Wall(x,y,x1,y1);
				return obst;
			}
			else{
				System.out.println("erro a ler o ficheiro");
				System.exit(1);
			}
		}
		catch(Exception e){
			System.out.println("erro a ler o ficheiro");
			System.exit(1);
		}
		return "error";
	}

}
