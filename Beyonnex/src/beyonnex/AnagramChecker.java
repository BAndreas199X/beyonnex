package beyonnex;

public class AnagramChecker {
	
	/**
	 * 1) all spaces are eliminated and capitalisation is removed for both strings
	 * 2) if these modified strings have different lengths, they can't meet the 
	 * 	definition of anagram and false is automatically returned
	 * 3) if the Strings are identical, they're not anagrams by definition, false
	 * 	is automatically returned
	 * 4) a function for both Strings is called that counts how many of each letters 
	 * 	are in each word. If these counts are identical, the definition of anagram is 
	 * 	met, and true is returned. Otherwise, false is returned 
	 */
	public static boolean areAnagrams(String firstInput, String secondInput) {
		
		String firstInputMod = StringMethods.stripDownString(firstInput);
		String secondInputMod = StringMethods.stripDownString(secondInput);
		
		if(firstInputMod.length() != secondInputMod.length()) {
			
			return false;
		}else if(firstInputMod.equals(secondInputMod)) {
			
			return false;
		}else{
			
			return StringMethods.stringToLetterMap(firstInputMod)
					.equals(StringMethods.stringToLetterMap(secondInputMod));
		}
	}
}
