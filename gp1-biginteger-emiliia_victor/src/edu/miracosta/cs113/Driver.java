/*
AUTHOR      Victor Vazquez / Emilia Dyrenkova
COURSE:     CS 113 Basic Data Structures and Algorithms
SECTION:    MW 1:30pm-3:20pm
HOMEWORK    Group Project #1
LAST MODIFIED   October 8,2018
*******************************************************************************************************
* Driver.java
* *****************************************************************************************************
* This program solves Euler's problem 13. Which is summing 100
* 50 digit numbers and outputting the first ten digits of the sum.
* *****************************************************************************************************
* ALGORITHM
* CREATE a File object that is linked to "number.txt" which has 100 50 digit numbers
* CREATE a Scanner object by passing in the File object into the Scanner constructor
* CREATE a BigInteger Object by passing in the first line of numbers inside the file "number.txt"
* WHILE the scanner object has a next line from the "numbers.txt" file
*       CALL the add method with the BigInteger Object created previously
*       This will add all 100 numbers and store it inside the BigInteger Objects Linked List
* OUTPUT the first ten numbers of the BigInteger Object starting from the left
* CLOSE the File Object
*
 */
package edu.miracosta.cs113;

import java.io.File;//Used to create File Objects
import java.io.FileNotFoundException;//Used to catch exceptions when creating File Object's
import java.util.Scanner;//Used to read from a File

public class Driver
{
    public static void main(String[]args)
    {
        BigInteger result=new BigInteger("0");
        try
        {
            File file= new File("numbers.txt");
            Scanner sc=new Scanner(file);
            result.add(sc.nextLine());
            String number = "";
            while(sc.hasNextLine())
            {

                number = sc.nextLine();
                result.add(number);

            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //This will print out the first ten numbers of the sum of the 100 numbers
        System.out.println(result.toString().replaceAll(",","").substring(0,10));

    }
}
