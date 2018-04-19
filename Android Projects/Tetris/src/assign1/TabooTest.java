// TabooTest.java
// Taboo class tests -- nothing provided.
package assign1;

import static org.junit.Assert.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.junit.Test;

public class TabooTest {
	
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}
	
	@Test
    public void testNoFollow1() {
		// case with null in the list
		List<String> rules = stringToList("a");
		rules.add("b");
		rules.add(null);
		rules.add("c");
		rules.add("d");
		
		Taboo<String> t = new Taboo<String>(rules);
		
        assertEquals( Collections.emptySet(), t.noFollow("b"));
    }
	
	@Test
	public void testTaboo1() {
		// basic case
		List<String> rules = stringToList("acab");
		Taboo<String> t = new Taboo<String>(rules);
		
		List<String> testlist = stringToList("acbxca");
		t.reduce(testlist);
		assertEquals(stringToList("axc"), testlist);
	}
	
	@Test
	public void testTaboo2() {
		// try special character space
		List<String> rules = stringToList("ac ab");
		Taboo<String> t = new Taboo<String>(rules);
		
		List<String> testlist = stringToList("acbxca");
		t.reduce(testlist);
		assertEquals(stringToList("axca"), testlist);
	}
	
	@Test
	public void testTaboo3() {
		// try null in the list
		List<String> rules = stringToList("a");
		rules.add("b");
		rules.add(null);
		rules.add("c");
		rules.add("d");
		
		Taboo<String> t = new Taboo<String>(rules);
		
		List<String> testlist = stringToList("bcx");
		t.reduce(testlist);
		assertEquals(stringToList("bcx"), testlist);
	}
	
}
