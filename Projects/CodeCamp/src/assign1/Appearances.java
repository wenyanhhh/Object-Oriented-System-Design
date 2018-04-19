package assign1;

import java.util.*;


public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Map<T, Integer> map1 = countAppearance(a);
		Map<T, Integer> map2 = countAppearance(b);
		
		int count = 0;
		for (T elem : map1.keySet()) {
			if (map2.containsKey(elem)) {
				if (map1.get(elem) == map2.get(elem)) {
					count++;
				}
			}
		}
		return count; 
	}
	
	/**
	 * helper method which counts appearance of each element
	 * and store the number of appearance in a map
	 * @param c
	 * @return map of element as the key and number of appearance as the value
	 */
	private static <T> Map<T, Integer> countAppearance(Collection<T> c) {
		Map<T, Integer> map = new HashMap<T, Integer>();

		for (T elem : c) {
			if (map.containsKey(elem)) {
				map.put(elem, map.get(elem)+1);
			} else {
				map.put(elem, 1);
			}			
		}
		
		return map;
	}
	
}
