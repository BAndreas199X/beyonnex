package beyonnex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnagramPermutation {
	
	private final Set<String> anagramSet = new HashSet<>();
	
	
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
	 * 
	 * returns a set of all possible Variables
	 */
	public Set<String> findAnagrams(String fString) {
		
		String wordStripped = StringMethods.stripDownString(fString);
		
		if(wordStripped.length() == 1) {
			
			anagramSet.add(wordStripped);
			return this.anagramSet;
		}
		
		StringMethods.stringToLetterList(wordStripped).forEach(lttr -> {
			
			List<String> restList = new ArrayList<>(
				StringMethods.stringToLetterList(wordStripped)
			);
			
			restList.remove(lttr);
			
			findAnagrams(new StringBuilder(lttr), restList);
		});
		
		return this.anagramSet;
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
	private void findAnagrams(StringBuilder currString, List<String> restList) {
		
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
}
