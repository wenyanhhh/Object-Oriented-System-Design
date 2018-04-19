package assign1;


// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adjacent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if (str.length() == 0) return 0;
		int max = 1, current = 1;
		for (int i = 0; i < str.length()-1; i++) {
			if (str.charAt(i) == str.charAt(i+1)) {
				current++;
			} else {
				if (current > max) {
					max = current;
				}
				if (str.substring(i+1).length() < max) break;
				current = 1;
			}
		}
		return max;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		// when the string is empty, return nothing
        if (str.length() == 0) return "";
        // check each character recursively
        if (!Character.isDigit(str.charAt(0))) {
            return Character.toString(str.charAt(0)) + blowup(str.substring(1));
        } else if (str.length() > 1) {
            return blowupHelper(Character.getNumericValue(str.charAt(0)), str.charAt(1)) + blowup(str.substring(1));
        }
        return "";
    }
	
	/**
	 * Given the numeric value of the digit
	 * and the following character
	 * repeat the character for given many times
	 * @param num
	 * @param nextch
	 * @return newly generated string
	 */
    private static String blowupHelper(int num, char nextch) {
        String buffer = "";
        for (int i = 0; i < num; i++) {
        		buffer += nextch;
        }
        return buffer;
    }
}

