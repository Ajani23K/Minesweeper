package minesweeper;

public class ZeroTiles {

	private int r;
	private int c;
	
	public ZeroTiles(int r, int c) {
		
		this.r = r;
		this.c = c;
		
	}
	public int getZTileRow(){
		
		return r;
	}
	public int getZTileColumn(){
		
		return c;
	}
}
