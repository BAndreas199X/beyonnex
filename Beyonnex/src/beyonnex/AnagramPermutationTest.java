package beyonnex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnagramPermutationTest {
	
	static final String RESULT_STRING = "Test-function '%s' passes: %b %n";
	private static Set<String> ringAnagrams;
	private static Set<String> peekAnagrams;
	
	public static void main(String[] args) {
		fillAnagramSets();
		
		test_generalAnagramSetCreation();
		test_doubleLetterAnagramSetCreation();
	}

	public static void test_generalAnagramSetCreation() {
		
		AnagramPermutation anagramPerm = new AnagramPermutation();
		
		String stringBasis = "ring";
		
		Set<String> resultSet = anagramPerm.findAnagrams(stringBasis);
		
		printResult("generalAnagramSetCreation - exists", resultSet != null);
		printResult("generalAnagramSetCreation - length", resultSet.size() == 24);
		printResult("generalAnagramSetCreation - Set", resultSet.equals(ringAnagrams));
	}
	
	public static void test_doubleLetterAnagramSetCreation() {
		
		AnagramPermutation anagramPerm = new AnagramPermutation();
		
		String stringBasis = "keep";
		
		Set<String> resultSet = anagramPerm.findAnagrams(stringBasis);
		
		printResult("doubleLetterAnagramSetCreation - exists", resultSet != null);
		printResult("doubleLetterAnagramSetCreation - length", resultSet.size() == 12);
		printResult("doubleLetterAnagramSetCreation - Set", resultSet.equals(peekAnagrams));
	}
	
	/**
	 * can be used to print the anagrams of any String
	 * @param input
	 */
	public static void anagramTest(String input) {
		
		AnagramPermutation anagramPerm = new AnagramPermutation();
		
		Set<String> resultSet = anagramPerm.findAnagrams(input);
		System.out.println(resultSet);
		System.out.println(resultSet.size());
	}
	
	public static void printResult(String function, boolean passes) {
		
		System.out.printf(RESULT_STRING, function, passes);
	}
	
	private static void fillAnagramSets() {
		
		ringAnagrams = new HashSet<>(Arrays.asList("grin", "gnir", "ingr", "nirg", 
			"ngri", "ring", "rgni", "rign", "rngi", "inrg", "igrn", "girn", "gnri", 
			"rnig", "irgn", "rgin", "nrig", "ngir", "irng", "ignr", "nrgi", "nigr", 
			"grni", "ginr"));
		
		peekAnagrams = new HashSet<>(Arrays.asList("eepk", "ekpe", "kpee", "keep", 
			"epke", "eekp", "peke", "kepe", "epek", "ekep", "peek", "pkee"));
	}
}
