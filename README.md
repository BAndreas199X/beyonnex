My interpretation of the assignment:
1) I should write a CLI through which two Strings can be read in. These two Strings should be checked for whether they are anagrams of each other
2) For both Strings, I should give out all their possible anagrams. To avoid repetition, I only give out the anagrams for both of them if they are not already anagrams of each other. Otherwise, I only give out the anagrams for String 1 (since they already include the anagrams for String 2)

General remarks: 
1) I choose to prioritize readability, comprehensibility, modularity, flexibility, test-coverage, and error-resistance
2) Although I attached some importance to this, I did not try to OPTIMIZE performance. Potential solutions would have been to use concurrency functionalities
3) Since, considering the state of my code, the program cannot determine all anagrams for very long Strings within a reasonable time, I limited this functionality to Strings which are 10 letters or shorter
4) Since the assignment was a "quick" and easy" solution, I refrained from using Maven or Gradle. Also because I feel this would be like taking a sledgehammer to crack a nut for a program with one class
5) Since I am not using Maven, I refrained from using JUnit for testing. In spite of this, I gave my tests a unit-test like structure

Definition of anagram:
1) The definition of anagram used for this program is the definition provided by Wikipedia: "An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once"
2) Words shorter or longer than the analysed word are not considered an anagram, even if you could achieve it by repeating or omitting a letter. This is because they wouldn't meat the "exactly once" part of the provided in the definition under 1).
3) The check is case insensitive. "Santa" and "satan" would evaluate to true, although the first word has a capital letter String 2 does not.
4) Spaces are also ignored when making the comparison, because, according to Wikipedia, e.g. "New York Times" and "monkeys write" are valid anagrams, although they contain a different number of spaces at different positions.
5) If two provided Strings are identical (after being purified according to rules 3) and 4), the function evaluates to "false", because identical strings don't meet the "rearranging the letters" part of the definition in 1)
6) It is non evaluated whether a String has a meaning when printing or checking it as an anagram, e.g. "gnir" is not a real meaning, but would be considered as an anagram to "ring" through my program 
