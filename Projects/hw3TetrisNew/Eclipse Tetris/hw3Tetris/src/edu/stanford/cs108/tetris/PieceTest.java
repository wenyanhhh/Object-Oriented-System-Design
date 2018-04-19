package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece s, sRotated;
	private Piece l1_1, l1_2, l1_3, l1_4;
	private Piece l2_1, l2_2, l2_3, l2_4;
	private Piece s1_1, s1_2;
	private Piece s2_1, s2_2;
	private Piece square;
	private Piece[] pieces;

	@Before
	public void setUp() throws Exception {
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		l1_1 = new Piece(Piece.L1_STR);
		l1_2 = l1_1.computeNextRotation();
		l1_3 = l1_2.computeNextRotation();
		l1_4 = l1_3.computeNextRotation();
		
		l2_1 = new Piece(Piece.L2_STR);
        l2_2 = l2_1.computeNextRotation();
        l2_3 = l2_2.computeNextRotation();
        l2_4 = l2_3.computeNextRotation();
		
        s1_1 = new Piece(Piece.S1_STR);
        s1_2 = s1_1.computeNextRotation();
        
        s2_1 = new Piece(Piece.S2_STR);
        s2_2 = s2_1.computeNextRotation();
        
        square = new Piece(Piece.SQUARE_STR);
        
		s = new Piece(Piece.STICK_STR);
		sRotated = s.computeNextRotation();
		
		pieces = Piece.getPieces();
				
	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		assertEquals(3, pyr3.getWidth());
		assertEquals(2, pyr3.getHeight());
		
		assertEquals(2, pyr4.getWidth());
		assertEquals(3, pyr4.getHeight());
		
		// Check size of l1 piece
		assertEquals(2, l1_1.getWidth());
		assertEquals(3, l1_1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(3, l1_2.getWidth());
		assertEquals(2, l1_2.getHeight());
		
		assertEquals(2, l1_3.getWidth());
		assertEquals(3, l1_3.getHeight());

		assertEquals(3, l1_4.getWidth());
		assertEquals(2, l1_4.getHeight());
		
		// Now try with some other piece, made a different way
		assertEquals(1, s.getWidth());
		assertEquals(4, s.getHeight());
		
		assertEquals(4, sRotated.getWidth());
		assertEquals(1, sRotated.getHeight());
		
		
	}
	
	
	// Test the skirt returned by a few pieces
	// Effectively we're also testing getPieces() here
	@Test
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, pyr2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, pyr4.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, l1_1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 0, 0}, l1_2.getSkirt()));
        assertTrue(Arrays.equals(new int[] {2, 0}, l1_3.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 1, 1}, l1_4.getSkirt()));
        
        assertTrue(Arrays.equals(new int[] {0, 0}, l2_1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {1, 1, 0}, l2_2.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 2}, l2_3.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 0, 0}, l2_4.getSkirt()));
        
        assertTrue(Arrays.equals(new int[] {0, 0, 1}, s1_1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {1, 0}, s1_2.getSkirt()));

        assertTrue(Arrays.equals(new int[] {1, 0, 0}, s2_1.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 1}, s2_2.getSkirt()));
        
        assertTrue(Arrays.equals(new int[] {0}, s.getSkirt()));
        assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, sRotated.getSkirt()));
        
        assertTrue(Arrays.equals(new int[] {0, 0}, square.getSkirt()));
	}
	
	
	// Test fast rotate for all pieces
	@Test
	public void testFastRotate() {
		// Stick
		assertTrue(!pieces[0].fastRotation().equals(pieces[0]));
		assertTrue(pieces[0].fastRotation().fastRotation().equals(pieces[0]));
		
		// L1
		assertTrue(!pieces[1].fastRotation().equals(pieces[1]));
		assertTrue(!pieces[1].fastRotation().fastRotation().equals(pieces[1]));
		assertTrue(!pieces[1].fastRotation().fastRotation().fastRotation().equals(pieces[1]));
		assertTrue(pieces[1].fastRotation().fastRotation().fastRotation().fastRotation().equals(pieces[1]));
		
		// L2
		assertTrue(!pieces[2].fastRotation().equals(pieces[2]));
		assertTrue(!pieces[2].fastRotation().fastRotation().equals(pieces[2]));
		assertTrue(!pieces[2].fastRotation().fastRotation().fastRotation().equals(pieces[2]));
		assertTrue(pieces[2].fastRotation().fastRotation().fastRotation().fastRotation().equals(pieces[2]));
		
		// S1
		assertTrue(!pieces[3].fastRotation().equals(pieces[3]));
		assertTrue(pieces[3].fastRotation().fastRotation().equals(pieces[3]));
		
		// S2
		assertTrue(!pieces[4].fastRotation().equals(pieces[4]));
		assertTrue(pieces[4].fastRotation().fastRotation().equals(pieces[4]));
		
		// Square
		assertTrue(pieces[5].fastRotation().equals(pieces[5]));
				
		// Pyramid
		assertTrue(!pieces[6].fastRotation().equals(pieces[6]));
		assertTrue(!pieces[6].fastRotation().fastRotation().equals(pieces[6]));
		assertTrue(!pieces[6].fastRotation().fastRotation().fastRotation().equals(pieces[6]));
		assertTrue(pieces[6].fastRotation().fastRotation().fastRotation().fastRotation().equals(pieces[6]));
		
	}
	
	// Test unique pieces
	@Test
	public void testUniquePieces() {
		assertFalse(s1_1.equals(s2_1));
        assertFalse(l1_1.equals(l1_2));
	}
	
	
}
