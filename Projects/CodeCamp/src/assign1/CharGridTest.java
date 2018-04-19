// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		// basic cases
		// 2 rows and 3 columns
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	@Test
	public void testCharArea2() {
		// basic cases
		// 3 row and 3 columns
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}
	
	@Test
	public void testCharArea3() {
		// try empty grid
		char[][] grid = new char[][] {
				{' ', ' ', ' '},
				{' ', ' ', ' '},
				{' ', ' ', ' '}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(9, cg.charArea(' '));
	}
	
	@Test
	public void testCharArea4() {
		// try weird characters
		char[][] grid = new char[][] {
				{'1', '1', 'a'},
				{' ', '&', 'a'},
				{'&', 'a', ' '},
				{' ', ' ', ' '}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(4, cg.charArea('&'));
		assertEquals(2, cg.charArea('1'));
		assertEquals(0, cg.charArea('b'));
	}
	
	
	@Test
	public void testPlusCount1() {
		// basic case
		char[][] grid1 = new char[][] {
				{'a', 'a', 'p', ' ', ' ', ' ', 'x', ' '},
				{' ', ' ', 'p', ' ', ' ', 'x', 'x', 'x'},
				{'p', 'p', 'p', 'p', 'p', 'y', 'x', ' '},
				{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' '},
				{' ', ' ', 'p', ' ', ' ', 'y', ' ', ' '},
				{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z'},
			};
			
		char[][] grid2 = new char[][] {
				{'&', '&', '&', ' ', ' ', ' ', 'x', ' '},
				{' ', '&', '&', ' ', ' ', 'x', 'x', 'x'},
				{'&', '&', '$', '&', '&', 'y', 'x', ' '},
				{' ', ' ', '&', ' ', 'y', 'y', 'f', ' '},
				{' ', ' ', '&', ' ', ' ', 'y', ' ', ' '},
				{'z', 'z', 'z', 'z', 'z', 'z', 'z', 'z'}
			};
	
		char[][] grid3 = new char[][] {
				{ ' ', 't', ' ', 'x', ' ', ' ', ' '},
				{ 't', 't', 't', 'x', ' ', ' ', ' '},
				{ ' ', 't', ' ', 'x', ' ', ' ', ' '},
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
				{ ' ', ' ', ' ', 'x', ' ', ' ', ' '},
				{ ' ', ' ', ' ', 'x', ' ', 'x', ' '},
				{ ' ', ' ', ' ', 'x', ' ', ' ', ' '}
			};
		
		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		
		assertEquals(2, cg1.countPlus());
		assertEquals(1, cg2.countPlus());
		assertEquals(2, cg3.countPlus());
	}
	
	
	
	@Test
	public void testPlusCount2() {
		// simple case
		char[][] grid1 = new char[][] {
				{ '1' }	
			};
		
		char[][] grid2 = new char[][] {
				{ '1', '2', '3' }	
			};
			
		char[][] grid3 = new char[][] {
				{ '1' },
				{ '1' },
				{ '1' }
			};
			
		
		CharGrid cg1 = new CharGrid(grid1);
		CharGrid cg2 = new CharGrid(grid2);
		CharGrid cg3 = new CharGrid(grid3);
		
		assertEquals(0, cg1.countPlus());
		assertEquals(0, cg2.countPlus());
		assertEquals(0, cg3.countPlus());
	}

}

