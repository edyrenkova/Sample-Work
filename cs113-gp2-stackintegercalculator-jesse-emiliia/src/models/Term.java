package models;

/**
 * File Name: Term.java : Class used to to store coefficient and exponent making up a Term.
 * This class will be used within my Polynomial class. Has generic methods as well as the ability
 * to process String Terms into integers and back again without losing any mathematical syntax.
 *
 * Date Last Modified: 21 Sep 2018
 * @author  Jesse Wolf
 */

public class Term implements Comparable{

    private int coefficient, exponent;

    /**
     * Default Constructor
     * Set my instance variables to 1.
     */
    Term() {
        this.coefficient = 1;
        this.exponent = 1;
    }

    /**
     * Constructor taking in String term. Will call the processTerm method in order to process the String into its
     * respective integers for coefficient and exponent.
     * @param term String
     */
    Term(String term) {
        this.processTerm(term);
    }

    /**
     * Full Constructor, taking in both instance variables.
     * @param coefficient int
     * @param exponent int
     */
    Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Copy Constructor, sets my current instance variables to match the Term passed in.
     * @param originalTerm Term
     */
    Term(Term originalTerm) {
        coefficient = originalTerm.coefficient;
        exponent = originalTerm.exponent;
    }

    /**
     * Clone method.
     * @return Object
     */
    public Object clone() {
        return new Term(this.coefficient, this.exponent);
    }

    /**
     * setExponent
     * @param exponent int
     */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    /**
     * setCoefficient
     * @param coefficient int
     */
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * getCoefficient
     * @return int
     */
    public int getCoefficient() {
        return coefficient;
    }

    /**
     * getExponent
     * @return int
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * setAll Method. Set all my instance variables.
     * @param coefficient int
     * @param exponent int
     */
    public void setAll(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Uses regex to split the input term into coefficient and exponent respectively.
     * @param term String
     */
    private void processTerm(String term) {
        //Split off of '^' if it exists.
        String[] temp = term.split("\\^");

        //Set both of my tempCoefficient variable to ""
        String tempCoefficient = "";

        //Replace all '+' with an empty string.
        temp[0] = temp[0].replaceAll("\\+", "");

        //temp[0] will represent the coefficient and temp[1] will be exponent if it exists.
        //Capture the index of x. Then replace the x with an empty string.
        int indexOfX = temp[0].indexOf('x');
        temp[0] = temp[0].replaceAll("x", "");
        if(indexOfX == 0) {
            //Set tempCoefficient to be current temp plus 1.
            tempCoefficient = temp[0] + 1;
        }
        if(temp[0].equals("")) {
            tempCoefficient = "1";
        }
        else if(temp[0].charAt(0) == '-' && temp[0].length() == 1){
            tempCoefficient = temp[0] + 1;
        }
        else {
            tempCoefficient = temp[0];
        }
        this.coefficient = Integer.parseInt(tempCoefficient);

        if(indexOfX != -1) {
            this.exponent = 1;
        }
        if(coefficient == 1 || coefficient == -1) {
            this.exponent = 1;
        }
        if(temp.length > 1) {
            this.exponent = Integer.parseInt(temp[1]);
        }
    }

    /**
     * processCoefficient, used to process my integer coefficient back into a string. Helper method for my toString.
     * @return String
     */
    private String processCoefficient() {
        String temp = "";
        if(coefficient == 0) {
            return "0";
        }
        else if(coefficient == 1) {
            temp = "+";
        }
        //When coefficient is -1 set temp to only the -, don't need the 1.
        else if(coefficient == -1) {
            temp = "-";
        }
        //When coefficient is 1 set temp to only the +, don't need the 1.
        else if(coefficient > 1) {
            temp = "+" + coefficient;
        }
        //When coefficient is -1 set temp to only the -, don't need the 1.
        else {
            temp = "" + coefficient;
        }
        return temp;
    }

    /**
     * processExponent,  used to process my integer exponent back into a string. Helper method for my toString.
     * @return
     */
    private String processExponent() {
        String temp = "";
        //If either exponent or coefficient = 0 then return an empty string.
        if(exponent == 0 || coefficient == 0) {
            return temp;
        }
        //If exponent is equal to 1 return an x
        else if(exponent == 1) {
            temp = "x";
        }
        //If exponent is greater then 1 or less then 0 then return x^exponent
        else {
            temp = "x^" + exponent;
        }
        return temp;
    }

    /**
     * toString, used to generate a String from all instance variables.
     * @return String
     */
    @Override
    public String toString() {
        String tempCoefficient = processCoefficient();
        if(tempCoefficient.startsWith("+")){
            tempCoefficient=tempCoefficient.substring(1);
        }
        String tempExponent = processExponent();
        return "" + tempCoefficient + tempExponent;
    }

    /**
     * equals, used to test an object against our current class. Tests if it is an instance and if it then is an exact
     * match of our current classes instance variables or not.
     * @param otherObject Object
     * @return boolean
     */
    @Override
    public boolean equals(Object otherObject) {
        if(otherObject == null) return false; //Null Check
        else if(getClass() != otherObject.getClass()) return false; //Instance Check
        else {
            Term otherTerm = (Term) otherObject; //TypeCast testing object
            //Test specific instance variables for equality.
            if(otherTerm.getCoefficient() != this.getCoefficient()) return false;
            if(otherTerm.getExponent() != this.getExponent()) return false;
        }
        return true; //no mismatch found. New object is equal to current.
    }

    /**
     * compareTo, used to compare a Term object with the current Term. Only checking for matches of the instance
     * variable exponent value, as this will let us know when to sum up two terms.
     * @param o Object
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Term) {
            Term newTerm = (Term) o;//Typecast o to type Term
            return(this.getExponent() - newTerm.getExponent());
        }
        //If o is not an instance of Term is automatically negative and considered less than this.
        else return -1;
    }

    /**
     * firstDerivative, used to find first derivative of the term by the formula (ax^n)'=(a*n)x^(n-1).
     * Mutates the object to become a derivative
     *
     */
    public void firstDerivative() {
        this.setAll(getCoefficient()*getExponent(),getExponent()-1);
    }
}