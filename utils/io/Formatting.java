package io;

public class Formatting {
	
	private Formatting() {}
	
	/**
	 * checks if a string is an integer
	 * @param in string to test
	 * @return true if the string can be parsed to an integer
	 */
	public static boolean isNumber(String in) {
		try{
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isIntList(String list) {
		
		String[] listElements = list.split("\\s*,\\s*");
		
		for (int x = 0;x < listElements.length; x++) {
			try {
				Integer.parseInt(listElements[x]);
			} catch (NumberFormatException n) {
				return false;
			}
		}
		return true;
	}
}
