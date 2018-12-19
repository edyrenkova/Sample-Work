package edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.Scanner;
/**
 * Polynomial.java : represents a single term in the polynomial
 *
 * @author Emiliia Dyrenkova
 * @version 1.0
 *
 */
public class Polynomial {
private LinkedList<Term> polynomial;
    /**
     * Default constructor, creates a new empty list of Term objects
     */
public Polynomial()
{
    polynomial=new LinkedList<Term>();
}
    /**
     * String constructor, creating a Polynomial object from the String
     *
     * @param poly
     *            String representing the polynomial (e.g. -5x^2 + 3)
     */
public Polynomial(String poly)
{
    polynomial=new LinkedList<>();
    Scanner reader=new Scanner(poly);
    reader.useDelimiter(" ");
    String term;
    while(reader.hasNext())
    {
        term=reader.next();
        if(term.equals("+")||term.equals("-")) {
            term = term.concat(reader.next());
        }
        this.addTerm(term);
    }

}
    /**
     * Copy constructor, creating a deep copy of Polynomial object provided
     *
     * @param p
     *            Polynomial object that is copied
     */

public Polynomial(Polynomial p)
{
    polynomial=new LinkedList<Term>();
    for(int i=0;i<p.getNumTerms();i++)
    {
        polynomial.add(p.getTerm(i).clone());
    }
}
    /**
     * getNumberTerms returns the length of the list of the Terms
     *
     * @return integer representing the number of terms in polynomial
     */
public int getNumTerms()
{
    return polynomial.size();
}
    /**
     * adds the term to the correct place in polynomial or adds to the existing term with the same exponent
     *
     * @param term
     *            the term that is added to the polynomial
     */
public void addTerm(Term term)
{
    for (int i=0;i<this.getNumTerms();i++) {
        if(this.getTerm(i).getExponent()==term.getExponent())
        {
            polynomial.get(i).add(term);
            if(polynomial.get(i).getCoefficient()==0)
            {
                polynomial.remove(i);
            }
            return;
        }
        else if(this.getTerm(i).getExponent()<term.getExponent())
        {
            polynomial.add(i,term);
            return;
        }


    }
    polynomial.add(term);
}
    /**
     * overload of addTerm(Term term) that takes in String parameter
     *
     * @param term
     *            the String which represents the term that is added to the polynomial
     */
public void addTerm(String term)
{
    this.addTerm(new Term(term));
}
    /**
     * Accessor for a Term object by its index
     *
     * @param i
     *          integer value that represents index
     * @return coefficient integer value
     */
public Term getTerm(int i)
{
    return polynomial.get(i);
}
    /**
     * Mutator for the term in a specified position
     *
     * @param index
     *            integer value representing index
     * @param term
     *            term that the corresponding element is set to
     */
public void setTerm(int index, Term term)
{
    polynomial.set(index,term);
}
    /**
     * toString representing objects values
     *
     * @return formatted string of a polynomial
     * @see java.lang.Object#toString()
     */
public String toString()
{
    String result="";
    if(polynomial.isEmpty())
    {
        result="0";
        return result;
    }
    for(int i=0;i<polynomial.size();i++)
    {
        result=result.concat(polynomial.get(i).toString());
    }
    if(result.startsWith("+"))
    {
        result=result.substring(1);
    }
    return result;
}
    /**
     * sets the list representing polynomial to be empty
     */
public void clear()
{
    polynomial.clear();
}
    /**
     * add method adds polynomial passed to the method
     *
     * @param poly2
     *          Polynomial object that is added to the term
     * @return new Polynomial object
     */
public Polynomial add(Polynomial poly2)
{
    for (int i=0;i<poly2.getNumTerms();i++)
    {
        this.addTerm(poly2.getTerm(i).clone());
    }
    return this;
}
}
