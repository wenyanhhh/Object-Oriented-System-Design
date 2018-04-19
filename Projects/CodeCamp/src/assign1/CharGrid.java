// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int area = 0;
		boolean contain = false;
		
		// set the initial boundary value
		int xleft = grid[0].length, xright = 0;
		int yup = grid.length, ydown = 0;
		
		// loop over the grid to look for the given char
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x] == ch) {
					// adjust the boundary value
					xleft = Math.min(xleft, x);
					xright = Math.max(xright, x);
					yup = Math.min(yup, y);
					ydown = Math.max(ydown, y);
					
					// signal that the grid contains the given char
					contain = true;
				}
			}
		}
		
		// if the grid contains the given char, calculate the area
		if (contain) {
			area = (xright - xleft + 1) * (ydown - yup + 1);
		}
		
		// return the value
		return area;
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		// there won't be a valid plus in a grid smaller than 3 * 3
		if (grid.length < 3 || grid[0].length < 3) return 0;
		
		int countplus = 0;
		// count starting from margin of 1
		for (int row = 1; row < grid.length-1; row++) {
			for (int col = 1; col < grid[0].length-1; col++) {
				// don't count blank plus
				if (grid[row][col] != ' ') {
					if (isValidPlus(row, col)) {
						countplus++;
					}
				}
			}
		}
		return countplus; 
	}
	
	/**
	 * check valid plus
	 * of which all the arms have two or more chars 
	 * and are all the same length
	 * @param row
	 * @param col
	 * @return a boolean value
	 */
	private boolean isValidPlus(int row, int col) {
		int left = armLength(row, col, 0, -1);
		int right = armLength(row, col, 0, 1);
		int up = armLength(row, col, 1, 0);
		int down = armLength(row, col, -1, 0);
		
		if (left >= 1 && left == right && left == up && left == down) return true;
		
		return false;
	}
	
	/**
	 * check the length of an arm
	 * stop counting if the arm is out of bound
	 * or there is no more same char
	 * @param row
	 * @param col
	 * @param shiftrow
	 * @param shiftcol
	 * @return the length of the arm
	 */
	private int armLength(int row, int col, int shiftrow, int shiftcol) {
		int nextrow = row + shiftrow;
		int nextcol = col + shiftcol;
		
		if (nextrow < grid.length && nextrow >=0 && nextcol < grid[0].length && nextcol >=0) {
			if (grid[row][col] == grid[nextrow][nextcol]) {
				return 1 + armLength(nextrow, nextcol, shiftrow, shiftcol);
			}
		}
		return 0;
	}
	
}
