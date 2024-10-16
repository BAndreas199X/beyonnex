package beyonnex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMethods {

	private StringMethods() {}
	
	
	/**
	 * It is counted how many of each letters are included in a word
	 * This count is persisted in a Map collection which gets returned
	 */
	public static Map<String,Integer> stringToLetterMap(String fString){
		
		Map<String,Integer> resultMap = new HashMap<>();
		
		stringToLetterList(fString)
			.forEach(lttr -> resultMap.put(lttr, resultMap.getOrDefault(lttr, 0)));
		
		return resultMap;
	}
	
	
	/**
	 * a string is broken down into its letters, these are returned in a List
	 */
	public static List<String> stringToLetterList(String fString){
		
		return Arrays.asList(fString.split(""));
	}
	
	
	/**
	 * removes all capitalisation and spaces from a string and returns it
	 */
	public static String stripDownString(String fString) {
		
		return fString.replaceAll("\\s+", "").toLowerCase();
	}
}
