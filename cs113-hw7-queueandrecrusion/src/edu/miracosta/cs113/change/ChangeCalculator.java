package edu.miracosta.cs113.change;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman and Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to verify
 * that all given coin combinations are unique.
 */
public class ChangeCalculator {
    public static LinkedList<Integer> combinations=new LinkedList<>();
    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
  * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        combinations.clear();
        addCombination(0,0,0,cents);
        return combinations.size()/4;
    }

    private static void addCombination(int quarters, int dimes, int nickels, int pennies){
        if(!checkDuplicate(quarters,dimes,nickels,pennies)) {
            combinations.add(quarters);
            combinations.add(dimes);
            combinations.add(nickels);
            combinations.add(pennies);
        }

        if(pennies>=5){
            addCombination(quarters,dimes,nickels+1, pennies-5);
        }
        if(pennies>=10){
            addCombination(quarters,dimes+1,nickels, pennies-10);
        }
        if(pennies>=25){
            addCombination(quarters+1,dimes,nickels, pennies-25);
        }
    }

    private static boolean checkDuplicate(int quarters, int dimes, int nickels, int pennies){
        for(int i=0;i<combinations.size();i+=4){
            if(combinations.get(i)==quarters && combinations.get(i+1)==dimes && combinations.get(i+2)==nickels && combinations.get(i+3)==pennies){
                return true;
            }
        }
        return false;
    }
    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination on their own separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents){
        String line;
        combinations.clear();
        calculateChange(cents);
        PrintWriter pw=null;
        try {
            pw=new PrintWriter(new FileOutputStream("CoinCombinations.txt"));
            for (int i = 0; i < combinations.size() - 3; i += 4) {
                line="[";
                line += combinations.get(i) + ", ";
                line += combinations.get(i + 1) + ", ";
                line += combinations.get(i + 2) + ", ";
                line += combinations.get(i + 3) + "]";
                pw.println(line);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        pw.close();
    }

    public static void main(String[]args){
        System.out.println(calculateChange(15));
        String line;
        for (int i = 0; i < combinations.size() - 3; i += 4) {
            line="[ ";
            line += combinations.get(i) + ", ";
            line += combinations.get(i + 1) + ", ";
            line += combinations.get(i + 2) + ", ";
            line += combinations.get(i + 3) + "]";
            System.out.println(line);
        }
    }
} // End of class ChangeCalculator