package edu.miracosta.cs113;

import java.util.Scanner;
/**
 *
 * Driver.java: Provides a driver program for creating, editing and adding two polynomials.
 *
 * @author Emiliia Dyrenkova
 * @version 1.0
 *
 */
public class Driver {
    static Polynomial p1,p2;
    /*
     * ALGORITHM:
     *
     * INSTANTIATE: two Polynomial objects
     * DISPLAY main menu
     * MENU OPTIONS:
     * 1. Edit first Polynomial
     *      DISPLAY menu:
     *          1. Set Polynomial
     *              PROMPT to enter the polynomial
     *              READ using String constructor from Polynomial class and assign to p1
     *          2. Add term
     *              PROMPT to enter the term
     *              READ and add to the p1 by using addTerm method and Term String constructor
     *          3. Clear
     *              CALL clear method on p1
     *          4. Go back
     *              DISPLAY main menu
     * 2. Edit second Polynomial
     *      DISPLAY menu:
     *          1. Set Polynomial
     *              PROMPT to enter the polynomial
     *              READ using String constructor from Polynomial class and assign to p2
     *          2. Add term
     *              PROMPT to enter the term
     *              READ and add to the p1 by using addTerm method and Term String constructor
     *          3. Clear
     *              CALL clear method on p2
     *          4. Go back
     *              DISPLAY main menu
     * 3. Add polynomials
     *      DISPLAY p1 and p2
     *      INSTANTIATE p3 using copy constructor with p1 to perform addition on due to the nature of add method for polynomial
     *      CALL and PRINT the result of p3.add(p2)
     * 4. Exit
     *      EXIT the program
     */

    /**
     * Driver method creates polynomials and opens main menu.
     *
     * @param args not used for driver
     */
    public static void main(String args[])
    {
        p1=new Polynomial();
        p2=new Polynomial();

       mainMenu();
    }
    /**
     * Main menu method displays main menu and handles user's choice
     *
     */
    public static void mainMenu()
    {
        boolean cont;
        do
        {
            cont=true;
            System.out.println("Choose a menu item (1,2,3 or 4):");
            System.out.println("1. Edit first polynomial");
            System.out.println("2. Edit second polynomial");
            System.out.println("3. Add polynomials");
            System.out.println("4. Exit");
            Scanner kb=new Scanner(System.in);
            int choice=kb.nextInt();
            switch (choice)
            {
                case 1:
                    editFirst();
                    break;
                case 2:
                    editSecond();
                    break;
                case 3:
                    addPoly();
                    break;
                case 4:
                    cont=false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(cont);
    }
    /**
     * Edit first polynomial menu method displays first polynomial and handles user's choices
     *
     */
    public static void editFirst()
    {
        boolean cont;
        do {
            cont=false;
            System.out.println("First polynomial: " + p1);
            System.out.println("Choose a menu item (1,2,3 or 4):");
            System.out.println("1. Set polynomial");
            System.out.println("2. Add term");
            System.out.println("3. Clear");
            System.out.println("4. Go back");
            Scanner kb = new Scanner(System.in);
            int choice = kb.nextInt();
            String poly, term;
            int exp;
            switch (choice) {
                case 1:
                    System.out.println("Type the polynomial (e.g. 9x^2 + 2x^3)");
                    kb.nextLine();
                    poly = kb.nextLine();
                    p1 = new Polynomial(poly);
                    break;
                case 2:
                    System.out.println("Type the term (e.g. 9x^2 or -6x^5)");
                    kb.nextLine();
                    term = kb.nextLine();
                    p1.addTerm(new Term(term));
                    break;
                case 3:
                    p1.clear();
                    break;
                case 4:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice");
                    cont = true;

            }
        }while(cont);
    }
    /**
     * Edit second polynomial menu method displays first polynomial and handles user's choices
     *
     */
    public static void editSecond()
    {
        boolean cont;
        do {
            cont=false;
            System.out.println("Second polynomial: " + p2);
            System.out.println("Choose a menu item (1,2,3 or 4):");
            System.out.println("1. Set polynomial");
            System.out.println("2. Add term");
            System.out.println("3. Clear");
            System.out.println("4. Go back");
            Scanner kb = new Scanner(System.in);
            int choice = kb.nextInt();
            String poly, term;
            switch (choice) {
                case 1:
                    System.out.println("Type the polynomial (e.g. 9x^2 + 2x^3)");
                    kb.nextLine();
                    poly = kb.nextLine();
                    p2 = new Polynomial(poly);
                    break;
                case 2:
                    System.out.println("Type the term (e.g. 9x^2 or -6x^5)");
                    kb.nextLine();
                    term = kb.nextLine();
                    p2.addTerm(new Term(term));
                    break;
                case 3:
                    p2.clear();
                    break;
                case 4:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice");
                    cont = true;

            }
        }while(cont);
    }
    /**
     * Adds two polynomials and displays result
     *
     */
    public static void addPoly()
    {
        System.out.println(p1);
        System.out.println("+");
        System.out.println(p2);
        System.out.println("=");
        Polynomial p3=new Polynomial(p1);
        System.out.println(p3.add(p2));
    }
}
