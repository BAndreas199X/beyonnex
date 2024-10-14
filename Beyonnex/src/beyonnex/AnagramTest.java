package beyonnex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnagramTest {
	
	static final String RESULT_STRING = "Test-function '%s' passes: %b %n";
	private static Set<String> ringAnagrams;
	private static Set<String> peekAnagrams;

	public static void main(String[] args) {
		
		fillAnagramSets();
		
		test_failsSameLetter();
		test_failsOneLetter();
		test_worksTwoLetters();
		test_failsTwo();
		
		test_worksGeneral();
		test_failsGeneral();
		test_worksDoubleLetters();
		
		test_failsDifferentLength();
		
		test_worksWithSpaces();
		test_worksWithCapitalization();
		
		test_generalAnagramSetCreation();
		test_doubleLetterAnagramSetCreation();
	}
	
	public static void test_failsSameLetter() {
		String string1 = "A";
		String string2 = "A";
		
		printResult("failsSameLetter", !Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_failsOneLetter() {
		String string1 = "A";
		String string2 = "B";
		
		printResult("failsOneLetter", !Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_worksTwoLetters() {
		String string1 = "no";
		String string2 = "on";
		
		printResult("worksTwoLetters", Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_failsTwo() {
		String string1 = "on";
		String string2 = "it";
		
		printResult("failsTwoLetters", !Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_worksGeneral() {
		String string1 = "dial";
		String string2 = "laid";
		
		printResult("worksGeneral", Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_worksDoubleLetters() {
		String string1 = "peek";
		String string2 = "keep";
		
		printResult("worksDoubleLetters", Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_failsGeneral() {
		String string1 = "dial";
		String string2 = "lady";
		
		printResult("failsGeneral", !Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_failsDifferentLength() {
		String string1 = "nothing";
		String string2 = "noting";
		
		printResult("failsDifferentLength", !Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_worksWithSpaces() {
		
		String string1 = "anagram";
		String string2 = "nag a ram";
		
		printResult("worksWithSpaces", Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_worksWithCapitalization() {
		
		String string1 = "Tom Marvolo Riddle";
		String string2 = "I am Lord Voldemort";
		
		printResult("worksWithSpaces", Anagram.areAnagrams(string1, string2));
	}
	
	public static void test_generalAnagramSetCreation() {
		
		Anagram.setAnagramSet(new HashSet<>());
		
		String stringBasis = "ring";
		
		Anagram.findAnagrams(new StringBuilder(), Arrays.asList(stringBasis.split("")));
		
		printResult("generalAnagramSetCreation - exists", 
			Anagram.getAnagramSet() != null);
		printResult("generalAnagramSetCreation - length", 
			Anagram.getAnagramSet().size() == 24);
		printResult("generalAnagramSetCreation - Set", 
			Anagram.getAnagramSet().equals(ringAnagrams));
	}
	
	public static void test_doubleLetterAnagramSetCreation() {
		
		Anagram.setAnagramSet(new HashSet<>());
		
		String stringBasis = "keep";
		
		Anagram.findAnagrams(new StringBuilder(), Arrays.asList(stringBasis.split("")));
		
		printResult("doubleLetterAnagramSetCreation - exists", 
			Anagram.getAnagramSet() != null);
		printResult("doubleLetterAnagramSetCreation - length", 
			Anagram.getAnagramSet().size() == 12);
		printResult("doubleLetterAnagramSetCreation - Set", 
			Anagram.getAnagramSet().equals(peekAnagrams));
	}
	
	/**
	 * can be used to print the anagrams of any String
	 * @param input
	 */
	public static void anagramTest(String input) {
		
		Anagram.setAnagramSet(new HashSet<>());
		System.out.println("Anagrams of "+input+":");
		Anagram.findAnagrams(input);
		System.out.println(Anagram.getAnagramSet());
		System.out.println(Anagram.getAnagramSet().size());
	}

	public static void printResult(String function, boolean passes) {
		
		
		System.out.printf(RESULT_STRING, function, passes);
	};
	
	private static void fillAnagramSets() {
		
		ringAnagrams = new HashSet<>(Arrays.asList("grin", "gnir", "ingr", "nirg", 
			"ngri", "ring", "rgni", "rign", "rngi", "inrg", "igrn", "girn", "gnri", 
			"rnig", "irgn", "rgin", "nrig", "ngir", "irng", "ignr", "nrgi", "nigr", 
			"grni", "ginr"));
		
		peekAnagrams = new HashSet<>(Arrays.asList("eepk", "ekpe", "kpee", "keep", 
			"epke", "eekp", "peke", "kepe", "epek", "ekep", "peek", "pkee"));
	}
}
