/*
 *  BigNumber.java
 *
 *  This interface has the backbone methods to add and subtract numbers with each other
 *
 *  Author:  Victor Vazquez / Emilia Dyrenkova
 *  Version: 1.0
 */
package edu.miracosta.cs113;

public interface BigNumber {

    /*TODO:
     * - make sure to document interface methods below!!!
     * - these are specific to Sprint 2, so for Sprint 1 you can simply have the methods throw an UnsupportedOperationException
     * - do not forget to consult your UML for each sprint so you know ALL methods to build (interface
     * does not contain constructors and toString methods! don't forget!!)
     */

    /**
     * This method will add an integer n to a BigInteger Object
     * @param n         Number to be added to a BigInteger
     */
    public void add(int n);

    /**
     *  This method will add a string representation of a number and add it to a BigInteger object
     * @param n         The string representation of a number
     */
    public void add(String n);

    /**
     * This method will allow the programmer to subtract from a BigInterger object by an integer 'n'
     * @param n         this parameter will be used to subtract from the BigInteger object
     */
    public void subtract(int n);

    /**
     * This method is used to subtract from a BigInteger object with a string representation of a number
     * @param n     String representation of a number used to subtract from a BigInteger number
     */
    public void subtract(String n);
}
