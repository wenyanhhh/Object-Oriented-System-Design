// Board.java
package edu.stanford.cs108.tetris;
import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	
	private boolean[][] grid;
	private boolean[][] backupGrids;
	
	private boolean DEBUG = true;
	boolean committed;
	
	private int[] widths;
	private int[] heights;
	private int[] refWidths;
	private int[] refHeights;
	
	private int maxHeight;
	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board (int width, int height) {
		this.width = width;
		this.height = height;
		
		committed = true;

		grid = new boolean[width][height];
		backupGrids = new boolean[width][height];
		
		widths = new int[height];
		heights = new int[width];
		refWidths =  new int[height];
		refHeights = new int[width];
		
		maxHeight = 0;
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		return maxHeight; 
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		// check the heights[], widths[] and maxHeight.
		if (DEBUG) {
			System.out.println(this);
			int[] heights1 = new int[width];
			int[] widths1 = new int[height];
			int maxHeight1 = 0;
					
			//iterate over the whole grid
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (grid[i][j]) {
						heights1[i] = j + 1;
						widths1[j]++;
						maxHeight1 = Math.max(maxHeight1, j+1);			
					}
				}			
			}
					
			//compare
			for (int i = 0; i < heights.length; i++) {
				if (heights1[i] != heights[i]) {
					throw new RuntimeException("Find Heights Error");			
				}
			}
			
			for (int j = 0; j < widths.length; j++) {
				if (widths1[j] != widths[j]) {
					throw new RuntimeException("Find Widths Error");		
				}			
			}
							
			if (maxHeight1!= maxHeight) {
				throw new RuntimeException("Find Max Height Error");	
			}			
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int dropHeight = 0;
		for(int i = 0 ; i < piece.getWidth(); i++)
		{
			int y = getColumnHeight(x+i) - piece.getSkirt()[i];
			if(y > dropHeight)
				dropHeight = y;
		}		
		return dropHeight;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		if (x >= 0 && x < heights.length) {
			return heights[x]; 
		}			
		return -1;
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		if (y >= 0 && y < widths.length) {
			return widths[y];
		}
		
		return -1;
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return true;
		}
		
		if (grid[x][y]) {
			return true;
		}
		
		return false;
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
		committed = false;
		
		
		//backup
		System.arraycopy(widths, 0, refWidths, 0, widths.length);
		System.arraycopy(heights, 0, refHeights, 0, heights.length);
		for(int i = 0;i < grid.length; i++) {
			System.arraycopy(grid[i], 0, backupGrids[i], 0, grid[i].length);		
		}
								
		int result = PLACE_OK;
				
		if (x < 0 || x + piece.getWidth() > width || y < 0 || y + piece.getHeight() > height) {		
			result = PLACE_OUT_BOUNDS;	
			return result;
		}
				
		for (int i = 0; i < piece.getBody().length; i++) {
			int xValue = x + piece.getBody()[i].x;
			int yValue = y + piece.getBody()[i].y;

			if (grid[xValue][yValue] == true) {
				result = PLACE_BAD;
				return result;
			} else {
				grid[xValue][yValue] = true;	
				//update heights[], widths[], max height	
				widths[yValue] = widths[yValue] + 1;
				heights[xValue] = Math.max(heights[xValue], yValue + 1);
				maxHeight = Math.max(maxHeight, heights[xValue]);
									
				if(widths[yValue] == width) {
					result = PLACE_ROW_FILLED;		
				}
			}			
		}		

		sanityCheck();
				
		return result;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		int rowsCleared = 0;
		if (committed) {
			//backup
			System.arraycopy(widths, 0, refWidths, 0, widths.length);
			System.arraycopy(heights, 0, refHeights, 0, heights.length);
			for(int i = 0;i < grid.length;i++) {
				System.arraycopy(grid[i], 0, backupGrids[i], 0, grid[i].length);		
			}		
			committed = false;
		}								
				
		for (int row = 0; row < maxHeight; row++) {
			if (isRowFull(row)) {
				clearTheRow(row);	//update widths[] in the meantime		
				rowsCleared++;
				row--; // check if there is adjacent rows
				maxHeight--;	 //update max heights
			}	
		}		
		
		//update heights[]
		for (int i = 0; i < width; i++) {
			int updateHeight = 0;
			for (int j = 0; j < maxHeight; j++) { 
				if (grid[i][j]) {
					updateHeight = Math.max(updateHeight, j + 1);
				}
			}
			heights[i] = updateHeight;
		}	
		
		updateMaxHeight();
		sanityCheck();		
	
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		//can't do undo in committed status;
		if(!committed) {		
			int[] temp = refWidths;
			refWidths = widths;
			widths = temp;
			
			temp = refHeights;
			refHeights = heights;
			heights = temp;

			boolean[][] tempGrid = backupGrids;
			backupGrids = grid;
			grid = tempGrid;
					
			committed = true;
			updateMaxHeight();
			sanityCheck();
			
		}
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
	
	// helper for max height
	private void updateMaxHeight() {
		maxHeight = heights[0];
		for (int i = 1; i < heights.length; i++) {
			maxHeight = Math.max(maxHeight, heights[i]);
		}	
	}
	
	// helper1 for clearRows
	private boolean isRowFull(int row) {
		for (int col = 0; col < width; col++) {
			if(!grid[col][row]) {
				return false;
			}
		}
		return true;
	}
	
	// helper2 for clearRows
	private void clearTheRow(int row) {
		for (int curr = row; curr < maxHeight; curr++) {
			for (int col = 0; col < width; col++) {
				if(curr < maxHeight - 1) {
					grid[col][curr] = grid[col][curr + 1];
				} else if (curr == maxHeight - 1) {
					grid[col][curr] = false;		
				}
			}
			
			if (curr < maxHeight - 1) {
				widths[curr] = widths[curr + 1];	
			} else {
				 widths[maxHeight - 1] = 0;	
			}		  
		}
	}
	
	
	
	
}


