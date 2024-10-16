package beyonnex;

public class AnagramCheckerTest {
	
	static final String RESULT_STRING = "Test-function '%s' passes: %b %n";

	public static void main(String[] args) {
		
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
	}
	
	public static void test_failsSameLetter() {
		String string1 = "A";
		String string2 = "A";
		
		printResult("failsSameLetter", !AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_failsOneLetter() {
		String string1 = "A";
		String string2 = "B";
		
		printResult("failsOneLetter", !AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_worksTwoLetters() {
		String string1 = "no";
		String string2 = "on";
		
		printResult("worksTwoLetters", AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_failsTwo() {
		String string1 = "on";
		String string2 = "it";
		
		printResult("failsTwoLetters", !AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_worksGeneral() {
		String string1 = "dial";
		String string2 = "laid";
		
		printResult("worksGeneral", AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_worksDoubleLetters() {
		String string1 = "peek";
		String string2 = "keep";
		
		printResult("worksDoubleLetters", AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_failsGeneral() {
		String string1 = "dial";
		String string2 = "lady";
		
		printResult("failsGeneral", !AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_failsDifferentLength() {
		String string1 = "nothing";
		String string2 = "noting";
		
		printResult("failsDifferentLength", !AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_worksWithSpaces() {
		
		String string1 = "anagram";
		String string2 = "nag a ram";
		
		printResult("worksWithSpaces", AnagramChecker.areAnagrams(string1, string2));
	}
	
	public static void test_worksWithCapitalization() {
		
		String string1 = "Tom Marvolo Riddle";
		String string2 = "I am Lord Voldemort";
		
		printResult("worksWithSpaces", AnagramChecker.areAnagrams(string1, string2));
	}

	public static void printResult(String function, boolean passes) {
		
		System.out.printf(RESULT_STRING, function, passes);
	}
}
