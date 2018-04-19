package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TetrisGridTest {
	
	// Provided simple clearRows() test
	// width 2, height 3 grid
	@Test
	public void testClear1() {
		boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, false, false},
			{false, true, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear2() {
		// case with all full rows
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{false, false, false},
			{false, false, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear3() {
		// basic case
		// width 5, height 3 grid
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, },
			{false, false, false},
			{false, false, false},
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, true, true, },
			{true, true, true, },
			{false, false, false},
			{false, false, false},
			{true, true, true, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear4() {
		// try the highest row as a full row
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, },
			{false, false, true},
			{false, false, true},
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, true, false, },
			{true, true, false, },
			{false, false, false},
			{false, false, false},
			{true, true, false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear5() {
		// try the lowest row as a a full row
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, },
			{true, true, false},
			{true, true, true},
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, false, false, },
			{true, false, false, },
			{false, false, false},
			{true, false, false},
			{true, false, false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear6() {
		// try simple case
		boolean[][] before =
		{	
			{true}

		};
		
		boolean[][] after =
		{	
			{false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear7() {
		boolean[][] before =
		{	
			{false}

		};
		
		boolean[][] after =
		{	
			{false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
}
