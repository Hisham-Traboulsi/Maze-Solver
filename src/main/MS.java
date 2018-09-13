package main;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MS {
	
	private int width, height;
	private int startX, startY;
	private int endX, endY;
	private int [][] maze;
	
	ArrayList<Point> path = new ArrayList<>();
	
	public MS(){
		readData();
	}
	
	//Read data from the file
	public void readData(){
		
		try{
			//------------CHANGE THE FILE NAME HERE TO TEST DIFFERENT MAZES-------------
			String filename = "src//main//large_input.txt";
			
			Scanner sc = new Scanner(new File(filename));
			
			//Get and Store the dimensions of the maze
			String dimensions [] = sc.nextLine().split(" ");
			width = Integer.parseInt(dimensions[0]);
			height = Integer.parseInt(dimensions[1]);
			maze = new int[height][width];
			
			//Get and store the position of the maze
			String startingPos [] = sc.nextLine().split(" ");
			startX = Integer.parseInt(startingPos[0]);
			startY = Integer.parseInt(startingPos[1]);
			
			//Get and store the end position of the maze
			String endingPos [] = sc.nextLine().split(" ");
			endX = Integer.parseInt(endingPos[0]);
			endY = Integer.parseInt(endingPos[1]);
			
			int colCounter = 0;
			String mazeRowData [] = null;		
			
			//create the maze programmatically 
			while(sc.hasNextLine()){
				
				mazeRowData = sc.nextLine().split(" ");
				
				for(int rowCounter = 0; rowCounter < mazeRowData.length; rowCounter++){
					
					maze[colCounter][rowCounter] = Integer.parseInt(mazeRowData[rowCounter]);
				}
				colCounter++;
			}
			
			sc.close();
			
		}catch(Exception ex){
			System.out.println("The file cannot be found");
			//ex.printStackTrace();
		}
	}
	
	
	
	public boolean searchPath(int [][] maze, int x, int y, List<Point> path){
		
		//Check if the current node is the end node
		if(x == endX && y == endY){
			path.add(new Point(x,y));
			return true;
		}
		
		
		if(maze[y][x] == 0){
			//set nodes that have been check from a value of 0 to 3
			maze[y][x] = 3;
			
			//Check the nodes in all 4 directions of the current node
			//WEST
            if (searchPath(maze, x -1, y, path)) {
            	path.add(new Point(x,y));
            	//If node is part of the path set its value to 2 and add it to the path list
            	maze[y][x] = 2;
                return true;
            }
            //EAST
            if (searchPath(maze, x + 1, y, path)) {
            	path.add(new Point(x,y));
            	maze[y][x] = 2;
                return true;
            }
            //NORTH
            if (searchPath(maze, x, y-1, path)) {
            	path.add(new Point(x,y));
            	maze[y][x] = 2;
                return true;
            }
            //SOUTH
            if (searchPath(maze, x, y+1, path)) {
            	path.add(new Point(x,y));
            	maze[y][x] = 2;
                return true;
            }
		}

		return false;
		
	}

	public void printPath(){
		
		for(int row = 0; row<height; row++){
			
			for (int col = 0; col<width; col++){
				
				if(col == startX && row == startY){
					System.out.print("S");
				}else if(col == endX && row == endY){
					System.out.print("E");
				}else if(maze[row][col] == 0){
					System.out.print(" ");
				}else if(maze[row][col] == 1){
					System.out.print("#");
				}else if(maze[row][col] == 2){//If the cell value is 2 then it is part of the path
					System.out.print("X");
				}else if(maze[row][col] == 3){//if the cell value is 3 then that means the cell was checked but its not part of the path
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		MS ms = new MS();
		if(!ms.searchPath(ms.maze, ms.startX, ms.startY, ms.path)){
			System.out.println("There is no solution for the entered maze.");
		}
		ms.printPath();
	}
	
	
	

}
