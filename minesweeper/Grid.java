package minesweeper;

import java.util.Random;

public class Grid {

	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows, numColumns, numBombs;
	
	public Grid(){
		
		bombGrid = new boolean[10][10];
		countGrid = new int [10][10];
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns){
		bombGrid = new boolean[rows][columns];
		countGrid = new int [rows][columns];
		numRows = rows;
		numColumns = columns;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns, int numBombs){
		bombGrid = new boolean[rows][columns];
		countGrid = new int [rows][columns];
		numRows = rows;
		numColumns = columns;
		this.numBombs = numBombs;
		createBombGrid();
		createCountGrid();
	}
	public int getNumRows(){
		
		return numRows;
	}
	public int getNumColumns(){
		
		return numColumns;
	}
	public int getNumBombs(){
		
		return numBombs;
	}
	public boolean[][] getBombGrid(){
		boolean [][] grid = new boolean[numRows][numColumns];
		
		for(int i = 0; i < bombGrid.length; i++) {
			
			for(int t = 0; t < bombGrid[i].length; t++) {
				
				grid[i][t] = bombGrid [i][t];
				
			}
			
		}
		return grid;
	}
	public int[][] getCountGrid(){
			int [][] grid = new int[numRows][numColumns];
		
		for(int i = 0; i < countGrid.length; i++) {
			
			for(int t = 0; t < countGrid[i].length; t++) {
				
				grid[i][t] = countGrid [i][t];
				
			}
			
		}
		return grid;
	}
	public boolean isBombAtLocation(int row, int column){
		if(bombGrid[row][column]==true) {
			
			
			return true;
		}
		return false;
	}
	public int getCountAtLocation(int row, int column){
		
		return countGrid[row][column];
		
	}
	private void createBombGrid(){
		
	for(int i = 0; i < bombGrid.length; i++) {
			
			for(int t = 0; t < bombGrid[i].length; t++) {
				
				bombGrid[i][t] = false;
				
			}
			
		}
			int bombcount = numBombs;
			Random num = new Random();
			while(bombcount > 0) {
				int row = num.nextInt(numRows);
				int column = num.nextInt(numColumns);
				
				if(!bombGrid[row][column]==true) {
					bombGrid[row][column] = true;
	                bombcount--;
					
				}
				
			}
	}
	private void createCountGrid(){
		
		for(int row = 0; row < bombGrid.length; row++) {
			for(int column = 0; column < bombGrid[row].length; column++) {
				int count = 0;
				 for (int i = -1; i <= 1; i++) {
					 for(int t = -1; t <= 1; t++) {
						 
						 int nearRow = row + i;
						 int nearColumn = column + t;
						 
						 if(nearRow >= 0 && nearRow < bombGrid.length && nearColumn >= 0 && nearColumn < bombGrid[row].length) {
							 if(bombGrid[nearRow][nearColumn]==true) {
								 
								 count++;
							 }
							 
						 }
						 
					 }
					 
				 }
				 countGrid[row][column] = count;
			}
			
			
		}
		
	}
	
}
