//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	
	private boolean[][] grid;
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		clearRowsHelper(0);
	}
	
	/**
	 * check all rows from the given row
	 * and clear all rows marked as a full row
	 * @param row
	 */

	private void clearRowsHelper(int row) {
		for (int y = row; y < grid[0].length; y++) {
			boolean fullrow = true;
			for (int x=0; x< grid.length; x++) {
				if ( !grid[x][y] ) {
					fullrow = false;
					break;
				}
			}
			if ( fullrow ) {
				shiftRows(y);
				addEmptyRow();
				// re-invoke this method from the same row
				clearRowsHelper(y);
				break;
			}
		}
	}
	
	/**
	 * Delete the full row by covering it with the row above
	 * shift each row above down by one
	 * @param row
	 */
	private void shiftRows(int row) {
		for (int y = row+1; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				grid[x][y-1] = grid[x][y];
			}
		}
	}
	
	// Fill the top row with all 'false' values
	private void addEmptyRow() {
		for (int x=0; x<grid.length; x++) {
			grid[x][grid[0].length-1] = false;
		}
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}
}
