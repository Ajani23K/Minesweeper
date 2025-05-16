package minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameInterface extends Main{
	private class MineTile extends JButton{
		int row;
		int column;
		
		public MineTile(int r, int c) {
			
			row = r;
			column = c;
		}
		public int getTileRow(){
			
			return row;
		}
		public int getTileColumn(){
			
			return column;
		}
		
	}
	int tileSize = 70;
	int boardWidth;
	int boardHeight;
	int width;
	int height;
	
	boolean gameover = false;
	boolean win = false;
	
	JFrame frame = new JFrame("minesweeper");
	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	JPanel gamepanel = new JPanel();
	Grid grid;
	MineTile[][] gameboard;
	
	public GameInterface(int width, int height, int numbomb){
		this.height = height;
		this.width = width;
		grid = new Grid(width,height,numbomb);
		boardWidth = tileSize * grid.getNumRows();
		boardHeight = tileSize * grid.getNumColumns();
		gameboard = new MineTile [boardWidth][boardHeight];
		frame.setSize(boardWidth , boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		
		label.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("minesweeper");
		label.setOpaque(true);
		
		panel.setLayout(new BorderLayout());
		panel.add(label);
		frame.add(panel, BorderLayout.NORTH);
		
		gamepanel.setLayout(new GridLayout(width, height));
		frame.add(gamepanel);
		
		
		
		for(int r = 0; r <width; r++) {
			for(int c = 0; c < height; c++) {
				
				MineTile tile = new MineTile(r,c);
				
				int count = grid.getCountAtLocation(tile.getTileRow(),tile.getTileColumn());
				
				gameboard[r][c] = tile;
				
				tile.setFocusable(false);
				tile.setMargin(new Insets(0,0,0,0));
				tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
				
				tile.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(gameover || win) {
							
						}else {
						if(grid.isBombAtLocation(tile.getTileRow(),tile.getTileColumn())==true){
							
							System.out.println("Game Over");
							gameover = true;
							
							revealbombs();
							WinorLossOption(false);
							
							
							
							
							
						}else{
							if(count==0){
								
								System.out.println(count);
								tile.setEnabled(false);
								removeTiles(tile.getTileRow(),tile.getTileColumn());
								
								if(checkwin()) {
									revealbombs();
									win = true;
									WinorLossOption(win);
									System.out.println("Win");
								}
								
							}else{
								
								
								System.out.println(count);
								tile.setEnabled(false);
								tile.setText(Integer.toString(count));
								
								if(checkwin()) {
									
									revealbombs();
									win = true;
									WinorLossOption(win);
									System.out.println("Win");
								}
								
								
							}
							
							
						}
					}
					}
					 
				});
				
				
				gamepanel.add(tile);
				
				
				
			}
			
			
		}
		frame.setVisible(true);
	}
	public void removeTiles(int nearbyrows, int nearbycolumns) {
		int count = 0;
		ZeroTiles[] zero = new ZeroTiles[9];
		 for (int i = nearbyrows-1; i <= nearbyrows+1; i++) {
			 for(int j = nearbycolumns-1; j <= nearbycolumns+1; j++) {
				 
				if(i >= 0 && i < width && j >= 0 && j < height) {
					
					
						
						if(grid.getCountAtLocation(i,j)==0) {
							if(gameboard[i][j].isEnabled()) {
								
								gameboard[i][j].setEnabled(false);
								
								ZeroTiles ztile = new ZeroTiles(i,j);
								zero[count] = ztile;
								count++;
							}
							
						}else {
							
							gameboard[i][j].setEnabled(false);
							gameboard[i][j].setText(Integer.toString(grid.getCountAtLocation(i,j)));
							
							
						}
					}
					
				}
			 }
		 
		 if(count > 0) {
			 for(int t = 0; t < count; t++) {
			removeTiles(zero[t].getZTileRow(), zero[t].getZTileColumn());
			 }
		 }	
	}
	
	public boolean checkwin() {
		int uncheckedtiles = 0;
		for(int checkrow = 0; checkrow < width; checkrow++) {
			for(int checkcol = 0; checkcol < height; checkcol++) {
				
				if(grid.isBombAtLocation(checkrow,checkcol)==false) {
				
					if(gameboard[checkrow][checkcol].isEnabled()==true) {
						
						uncheckedtiles++;
				
				}
			}
		
			}
		}
		System.out.println("Number of tiles need to be checked "+uncheckedtiles);
		if(uncheckedtiles==0) {	
			
			return true;
		}else {
			
			return false;
		}
	}
	
	public void revealbombs() {
		
		for(int bombrow = 0; bombrow < width; bombrow++) {
			for(int bombcol = 0; bombcol < height; bombcol++) {
				
				if(grid.isBombAtLocation(bombrow,bombcol)) {
					
					gameboard[bombrow][bombcol].setText("💥");
				}else {
					
					gameboard[bombrow][bombcol].setEnabled(false);
					if(grid.getCountAtLocation(bombrow, bombcol)>0) {
					gameboard[bombrow][bombcol].setText(Integer.toString(grid.getCountAtLocation(bombrow, bombcol)));
					}
				}
				
			}	
		}
		
	}
	public void WinorLossOption(boolean won) {
		Object[] options = {"Yes","No"};
		String winner = "";
		String outcome = "";
		if(won){
		 winner = "You Won! Would you like to try again?";
		 outcome = "You Won!";
		}else {
			
			winner = "Game Over! Would you like to try again?";	
			outcome = "You Lose!";
		}
		int input = JOptionPane.showOptionDialog(null, winner, outcome, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if(input==JOptionPane.YES_OPTION) {
			frame.dispose();
			startscreen();
		}else {
			
			System.exit(0);
		}
		
	}
	/*public void win() {
	JFrame winscreen = new JFrame("Congrats You Won!");
	JPanel winpanel = new JPanel();
	JLabel winlabel = new JLabel("Winner!");
	JButton endgame = new JButton("Click to Close");
	winscreen.setSize(300,200);
	winscreen.setLocationRelativeTo(null);
	winscreen.setResizable(false);
	winscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	winlabel.setFont(new Font("Arial", Font.CENTER_BASELINE,10));
	winlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	winlabel.setText("Winner!");
	winlabel.setOpaque(true);
	
	winpanel.setLayout(new BoxLayout(winpanel, BoxLayout.Y_AXIS));
	
	endgame.setAlignmentX(Component.CENTER_ALIGNMENT);
    endgame.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
    	
    	
    });
    winpanel.add(winlabel);
    winpanel.add(Box.createRigidArea(new Dimension(0, 10)));   
    winpanel.add(Box.createRigidArea(new Dimension(0, 10)));
    winpanel.add(endgame);
    
    winscreen.add(winpanel);
    winscreen.setVisible(true);
}*/
	
	/*public void gameover() {
	JFrame gameoverscreen = new JFrame("💥 Game Over 💥");
	JPanel gameoverpanel = new JPanel();
	JLabel gameoverlabel = new JLabel("gameover");
	JButton endgame = new JButton("Click to Close");
	gameoverscreen.setSize(300,200);
	gameoverscreen.setLocationRelativeTo(null);
	gameoverscreen.setResizable(false);
	gameoverscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	gameoverlabel.setFont(new Font("Arial", Font.CENTER_BASELINE,10));
	gameoverlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	gameoverlabel.setText("GAME OVER");
	gameoverlabel.setOpaque(true);
	
	gameoverpanel.setLayout(new BoxLayout(gameoverpanel, BoxLayout.Y_AXIS));
	
	endgame.setAlignmentX(Component.CENTER_ALIGNMENT);
   endgame.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			 TODO Auto-generated method stub
			System.exit(0);
		}
    	
    	
    });
    
    gameoverpanel.add(gameoverlabel);
    gameoverpanel.add(Box.createRigidArea(new Dimension(0, 10)));   
    gameoverpanel.add(Box.createRigidArea(new Dimension(0, 10)));
    gameoverpanel.add(endgame);
    
    gameoverscreen.add(gameoverpanel);
	gameoverscreen.setVisible(true);
	
}*/
	
}