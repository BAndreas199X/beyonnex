package beyonnex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Anagram {
	
	static final String SECOND_STRING_INPUT = "You have inserted '%s'. Please insert"
		+ " a second String to check whether it's an anagram of '%s'!";

	static final String ARE_ANAGRAMS = "\n'%s' and '%s' are anagrams: %b !%n";
	
	private static Set<String> anagramSet;
	
	
	/**
	 * Main-class:
	 * 
	 * the user is asked to subsequently enter two Strings.
	 * 
	 * Afterwards, it instantiates the analysis of the two Strings
	 * 
	 * If the user is prompted to input the first String, but makes an empty enter, 
	 * 	the program is terminated
	 * 
	 * He is not allowed to enter an empty String while being prompted to enter the
	 * 	second String. He will be re-prompted until he enters a String that is not 
	 * 	empty
	 */
	public static void main(String[] args) {

		Scanner inputScan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("\nPlease enter a String. \nTo end the program, leave "
				+ "the input empty and press enter!");
			String firstInput = inputScan.nextLine();
			
			if(firstInput.equals("")) {
				
				break;
			}
			
			String secondInput = "";
			
			while(secondInput.equals("")) {
				
				System.out.printf(SECOND_STRING_INPUT, firstInput, firstInput);
				secondInput = inputScan.nextLine();
				
				if(secondInput.equals("")) {
					
					System.out.println("The input at this point can not be blank. "
							+ "Please provide a non-blank input.");
				}
			}
			
			performEvaluation(firstInput, secondInput);
			System.out.println("\n---------new evaluation begins here----------");
		}

		System.out.println("The program's execution has been ended!");
		inputScan.close();
	}
	

	/**
	 * 
	 * The evaluation is called from here.
	 * 
	 * 1) The function is called that determines whether the two string provided 
	 * 	are anagrams
	 * 2) It is checked whether the Strings provided have less than ten letters. If
	 * 	so, all anagrams of the Strings are printed
	 * 	(the "less than ten letters" restriction is due to the time complexity of the
	 * 	function. The program can't execute it with  more than ten letters within a 
	 * 	reasonable time)
	 * 
	 * The anagrams for String 2 are not printed a second time if it's already an 
	 * 	anagram to String 1, since they were already printed as anagrams of String 1
	 */
	private static void performEvaluation(String firstInput, String secondInput) {
		
		boolean areAnagrams = areAnagrams(firstInput, secondInput);
		
		System.out.printf(ARE_ANAGRAMS, firstInput, secondInput, areAnagrams);
		
		if(firstInput.replace(" ","").length() <= 10) {
			
			System.out.println(allAnagramMessage(areAnagrams, firstInput, 
				secondInput));
			printAnagrams(firstInput.toLowerCase().replace(" ",""));
		}else {
			
			System.out.println("The anagrams will not be printed, since the Strings "
					+ "were longer than ten letters!");
		}
		
		if(!areAnagrams && !firstInput.equals(secondInput) 
				&& secondInput.replace(" ","").length() <= 10) {
			
			System.out.printf("%nThe possible anagrams of '%s' are: %n", secondInput);
			printAnagrams(secondInput);
		}
	}
	
	
	/**
	 * initiation class for anagram printing!
	 * 
	 * the global Set for anagrams is reset, after that anagram determination is
	 * 	initialised (all anagrams will be written to anagramSet)
	 * 
	 * Once it concludes, all anagrams (entries in anagramSet) are printed
	 */
	private static void printAnagrams(String fString) {
		
		fString = stripDownString(fString);
		
		anagramSet = new HashSet<>();
		findAnagrams(fString);
		anagramSet.forEach(System.out::println);
	}
	
	
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
		
		String firstInputMod = stripDownString(firstInput);
		String secondInputMod = stripDownString(secondInput);
		
		if(firstInputMod.length() != secondInputMod.length()) {
			
			return false;
		}else if(firstInputMod.equals(secondInputMod)) {
			
			return false;
		}else{
			
			return stringToLetterMap(firstInputMod)
					.equals(stringToLetterMap(secondInputMod));
		}
	}
	

	/**
	 * It is counted how many of each letters are included in a word
	 * This count is persisted in a Map collection which gets returned
	 */
	private static Map<String,Integer> stringToLetterMap(String fString){
		
		Map<String,Integer> resultMap = new HashMap<>();
		
		stringToLetterList(fString)
			.forEach(lttr -> resultMap.put(lttr, resultMap.getOrDefault(lttr, 0)));
		
		return resultMap;
	}
	
	
	/**
	 * The program only prints the anagrams once if the input-Strings are anagrams
	 * 	The adequate information is created here.
	 * 
	 * Depending on the result of 'are anagrams', the message informs the user that
	 * 	the subsequent Strings are anagrams of only the first of both input Strings
	 */
	private static String allAnagramMessage(boolean areAnagrams, String firstInput,
			String secondInput) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%nThe possible anagrams of '%s' ", firstInput));
		
		if(areAnagrams) {
			sb.append(String.format("and '%s' ", secondInput));
		}
		
		sb.append("are:");
		
		return sb.toString();
	}

	
	/**
	 * a string is broken down into its letters, these are returned in a List
	 */
	private static List<String> stringToLetterList(String fString){
		
		return Arrays.asList(fString.split(""));
	}
	
	
	/**
	 * The "initiation" class for finding and printing all anagrams
	 * First, all spaces and capitalisation is removed
	 * 
	 * the function is skipped, if the String only consists of one letter (only
	 * 	possible anagram, in that case)
	 * 
	 * After that: 
	 * 	The program takes each letter within the String, and positions it as the 
	 * 	'first letter' of a potential anagram (StringBuilder consisting only of 
	 * 	this first letter). 
	 * After that, the remaining letters (List "restList") will by systematically 
	 * 	added as the next letter within a potential anagram. This is done through 
	 * 	the function 'findAnagrams(StringBuilder currString, List<String> restList)'
	 */
	public static void findAnagrams(String fString) {
		
		String wordStripped = stripDownString(fString);
		
		if(wordStripped.length() == 1) {
			
			anagramSet.add(wordStripped);
			return;
		}
		
		stringToLetterList(wordStripped).forEach(lttr -> {
			
			List<String> restList = new ArrayList<>(stringToLetterList(wordStripped));
			restList.remove(lttr);
			
			findAnagrams(new StringBuilder(lttr), restList);
		});
	}
	
	
	/**
	 * Input are: The already positioned Letters, and the letters that yet need to be 
	 * 	positioned
	 * 
	 * If only one letter remains to be positioned, we've reached an end of this 
	 * 	procedure. The last letter gets positioned, and the result is added as an 
	 * 	anagram to anagramSet
	 * 
	 * If more than one letter still needs to be positioned: A new branch for each of
	 * 	these is instantiated. The following procedure happens once for each remaining
	 * 	letter:
	 * 		A letter is taken and attached to the presently-established anagram. After 
	 * 		that, a new branch is started, that repeats this procedure for this newly 
	 * 		established anagram-stem and all letters that are now still remaining
	 * 
	 * @param currString - StringBuilder with Letters of the potential anagram that 
	 * 	have already been positioned 
	 * @param restList - List if letters the still need to be positioned
	 */
	private static void findAnagrams(StringBuilder currString, List<String> restList) {
		
		if(restList.size() == 1) {
			
			StringBuilder currStringCopy = new StringBuilder(currString);
			anagramSet.add(currStringCopy.append(restList.get(0)).toString());
		}else {
			
			restList.forEach(lttr -> {
				
				StringBuilder currStringCopy = new StringBuilder(currString);
				currStringCopy.append(lttr);
				
				List<String> restListCopy = new ArrayList<>(restList);
				restListCopy.remove(lttr);
				
				findAnagrams(currStringCopy, restListCopy);
			});
		}
	}
	
	
	/**
	 * removes all capitalisation and spaces from a string and returns it
	 */
	private static String stripDownString(String fString) {
		
		return fString.replace(" ","").toLowerCase();
	}
	
	
	/**
	 * getter-method for the anagramSet variable (necessary for testing)
	 */
	public static Set<String> getAnagramSet(){
		
		return anagramSet;
	}
	
	
	/**
	 * setter-method for the anagramSet variable (necessary for testing)
	 */
	public static void setAnagramSet(Set<String> fAnagramSet){
		
		anagramSet = fAnagramSet;
	}
}
