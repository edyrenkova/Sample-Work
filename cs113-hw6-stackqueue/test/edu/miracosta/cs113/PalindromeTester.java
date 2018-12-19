package edu.miracosta.cs113;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * PalindromeTester : a test class for isPalindrome, a method intended to utilize stacks to evaluate if a given
 * string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backwards as it does
 * forwards. Such sequences include "madam," "top spot," or "no lemon, no melon".
 */
/*ALGORITHM for isPalindrome(STRING S)

      IF s is null
        THROW IllegalArgumentException
      Replace all whitespaces with empty string
      Make string lowercase
      INSTANTIATE ArrayListStack<Character> stack1 and stack2 with new objects
      FOR length of string
        PUSH character onto stack1
      FOR length of string/2
        PULL character from stack1
        PUSH that character onto stack2
      IF length of the string is odd number
        PULL last character on the stack1
      WHILE stack1 AND stack2 are not empty
        PULL character from stack1 and stack2
        IF they are not equal
            RETURN false
      RETURN true;
     */
public class PalindromeTester {

    /** True test cases which include spaces and symbols */
    private static final String[] SIMPLE_TRUE = { "", " ", "A", "7", "%", "  ", "BB", "33", "**" };
    /** False test cases which include spaces and symbols */
    private static final String[] SIMPLE_FALSE = { "AC", "71", "@+" };

    /** True test cases which include spaces */
    private static final String[] WHITE_SPACE_TRUE = { " x ", " t   t  ", " 5 5", " #      # " };
    /** False test cases which include spaces */
    private static final String[] WHITE_SPACE_FALSE = { "m   n  ", "   8  7 ", "  ^      &  "};

    /** Case-sensitive palindromes */
    private static final String[] CASE_SENSITIVE_TRUE = { "ABba", "roTOR", "rAceCaR" };

    /** Complex palindromes which include spaces, symbols, and varied capitalization */
    private static final String[] COMPLEX_TRUE = { "fOO race CAR oof", "AbBa ZaBba", "1 3 3 7  331",
                                                "N0 LEm0n, n0 Mel0n",
                                                "sT RJKLEeE R#@ $A$ @# REeEL K  JRT s" };

    /**
     * Utilizes stacks to determine if the given string is a palindrome. This method ignores whitespace and case
     * sensitivity, but does not ignore digits or symbols.
     *
     * @param s a string comprised of any character
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     */

    private boolean isPalindrome(String s) {

        // TODO: Implement this method body using your ArrayListStack. Be mindful of your algorithm!
        if(s==null) throw new IllegalArgumentException();
        s=s.replaceAll(" ","");
        s=s.toLowerCase();
        ArrayListStack<Character> stack1=new ArrayListStack<Character>();
        ArrayListStack<Character> stack2=new ArrayListStack<Character>();
        for(int i=0;i<s.length();i++){
            stack1.push(s.charAt(i));
        }
        for(int i=0;i<s.length()/2;i++){
            Character temp=stack1.pop();
            stack2.push(temp);
        }
        if(s.length()%2!=0){
            stack1.pop();
        }
        while(!stack1.empty()&&!stack2.empty()){
            if(stack1.pop()!=stack2.pop()) return false;
        }
        return true;

    } // End of method isPalindrome

    // region isPalindrome tests =======================================================================================

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        for (int i = 0; i < SIMPLE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(SIMPLE_TRUE[i]));
        }
    }

    @Test
    public void testSimpleFalseCases() {
        for (int i = 0; i < SIMPLE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(SIMPLE_FALSE[i]));
        }
    }

    @Test
    public void testWhitespaceCases() {
        for (int i = 0; i < WHITE_SPACE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(WHITE_SPACE_TRUE[i]));
        }

        for (int i = 0; i < WHITE_SPACE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(WHITE_SPACE_FALSE[i]));
        }
    }

    @Test
    public void testCaseSensitivityCases() {
        for (int i = 0; i < CASE_SENSITIVE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(CASE_SENSITIVE_TRUE[i]));
        }
    }

    @Test
    public void testComplexCases() {
        for (int i = 0; i < COMPLEX_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(COMPLEX_TRUE[i]));
        }
    }

    // endregion isPalindrome tests ====================================================================================

} // End of class PalindromeTester