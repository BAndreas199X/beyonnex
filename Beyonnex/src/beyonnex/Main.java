package beyonnex;

import java.util.Scanner;
import java.util.Set;

public class Main {
	
	static final String DEMAND_NEXT_INPUT = "You have inserted '%s'. Please insert"
		+ " a second String to check whether it's an anagram of '%s'!";

	static final String ARE_ANAGRAMS = "\n'%s' and '%s' are anagrams: %b !%n";
	static final int CHARACTER_LIMIT = 10;
	
	
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

		try(Scanner inputScan = new Scanner(System.in)){
		
			while(true) {
			
				System.out.println("\nPlease enter a String. \nTo end the program, leave "
						+ "the input empty and press enter!");
				String firstInput = inputScan.nextLine();
			
				if(isEnter(firstInput)) {
				
					break;
				}
			
				String secondInput = "";
			
				while(isEnter(secondInput)) {
				
					System.out.printf(DEMAND_NEXT_INPUT, firstInput, firstInput);
					secondInput = inputScan.nextLine();
				
					if(isEnter(secondInput)) {
					
						System.out.println("The input at this point can not be blank. "
							+ "Please provide a non-blank input.");
					}
				}
			
				performEvaluation(firstInput, secondInput);
				System.out.println("\n---------new evaluation begins here----------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("The program's execution has been ended!");
		}
	}
	
	public static boolean isEnter(String fInput) {
		return "".equals(fInput);
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
		
		boolean areAnagrams = AnagramChecker.areAnagrams(firstInput, secondInput);
		
		System.out.printf(ARE_ANAGRAMS, firstInput, secondInput, areAnagrams);
		
		if(StringMethods.stripDownString(firstInput).length() <= CHARACTER_LIMIT) {
			
			System.out.println(allAnagramMessage(areAnagrams, firstInput, 
				secondInput));
			printAnagrams(StringMethods.stripDownString(firstInput));
		}else {
			
			System.out.printf("The anagrams will not be printed, since the Strings "
					+ "were longer than %d letters!", CHARACTER_LIMIT);
		}
		
		if(!areAnagrams && !firstInput.equals(secondInput) 
				&& StringMethods.stripDownString(secondInput).length() 
				<= CHARACTER_LIMIT) {
			
			System.out.printf("%nThe possible anagrams of '%s' are: %n", secondInput);
			printAnagrams(secondInput);
		}
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
	 * initiation class for anagram printing!
	 * 
	 * the global Set for anagrams is reset, after that anagram determination is
	 * 	initialised (all anagrams will be written to anagramSet)
	 * 
	 * Once it concludes, all anagrams (entries in anagramSet) are printed
	 */
	public static void printAnagrams(String fString) {
		
		fString = StringMethods.stripDownString(fString);
		
		AnagramPermutation anagramPerm = new AnagramPermutation();
		
		Set<String> anagramSet = anagramPerm.findAnagrams(fString);
		anagramSet.forEach(System.out::println);
	}
}