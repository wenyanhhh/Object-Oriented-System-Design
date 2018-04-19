/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
	private ArrayList<T> rules;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.rules = new ArrayList<T>(rules);
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		Set<T> tabooset = new HashSet<T>();
		for (int i = 0; i < rules.size(); i++) {
			T it = rules.get(i);
			if ( it != null && it.equals(elem) && (i+1) < rules.size() ) {
				if ( rules.get(i+1) != null ) {
					tabooset.add(rules.get(i+1));
				}
			}
		}
		// return empty set if an element is not constrained in the rules
		if (tabooset.size() == 0) return Collections.emptySet();
		return tabooset;
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		reduceHelper(list, 1, list.get(0));
	}
	
	/**
	 * recursive method which remove element from specific index
	 * @param list
	 * @param curindex
	 * @param last
	 */
	private void reduceHelper(List<T> list, int curindex, T last) {
		if (curindex < list.size()) {
			if ( noFollow(last).contains(list.get(curindex)) ) {
				list.remove(curindex);
				reduceHelper(list, curindex, last);
			} else {
				reduceHelper(list, curindex + 1, list.get(curindex));
			}
		}
	}
}
