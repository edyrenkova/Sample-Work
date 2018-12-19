package edu.miracosta.cs113;
/**
 * Term.java : represents a single term in the polynomial
 *
 * @author Emiliia Dyrenkova
 * @version 1.0
 *
 */
public class Term implements Comparable<Term>, Cloneable{
    private int coefficient;
    private int exponent;

    /**
     * Default constructor, sets the term to x
     */
    public Term()
    {
        setAll(1,1);
    }
    /**
     * Full constructor, specifying coefficient and exponent
     *
     * @param coefficient
     *            integer representing coefficient
     * @param exponent
     *            integer representing exponent
     */
    public Term(int coefficient, int exponent)
    {
        setAll(coefficient,exponent);
    }
    /**
     * Copy constructor, creating a deep copy of Term object provided
     *
     * @param term
     *            Term object that is copied
     */

    public Term(Term term)
    {
        this.exponent=term.getExponent();
        this.coefficient=term.getCoefficient();
    }
    /**
     * String constructor, creating a Term object from the String
     *
     * @param term
     *            String representing the term (e.g. -5x^2)
     */
    public Term(String term)
    {
        int c;
        int e;
        if(term.isEmpty())
        {
            c=0;
            e=0;
        }
        else {
            if (term.contains("^")) {
                e = Integer.valueOf(term.substring(term.indexOf("^") + 1));
            } else {
                if (term.contains("x")) {
                    e = 1;
                } else {
                    e = 0;
                }
            }
            if (term.contains("x")) {
                if (term.startsWith("+x") || term.startsWith("x")) {
                    c = 1;
                } else if (term.startsWith("-x")) {
                    c = -1;
                } else {
                    c = Integer.valueOf(term.substring(0, term.indexOf("x")));
                }

            } else {
                c = Integer.valueOf(term);
            }
        }
        this.setCoefficient(c);
        this.setExponent(e);
    }
    /**
     * Mutator for coefficient value
     *
     * @param coefficient
     *            integer value representing coefficient
     */
    public void setCoefficient(int coefficient)
    {
        this.coefficient=coefficient;
    }
    /**
     * Mutator for exponent value
     *
     * @param exponent
     *            integer value representing exponent
     */
    public void setExponent(int exponent)
    {
        this.exponent=exponent;
    }
    /**
     * Accessor for coefficient value
     *
     * @return coefficient integer value
     */
    public int getCoefficient() {
        return coefficient;
    }
    /**
     * Accessor for exponent value
     *
     * @return exponent integer value
     */
    public int getExponent() {
        return exponent;
    }
    /**
     * Mutator for both coefficient and exponent values
     *
     * @param c
     *          integer value representing coefficient
     * @param e
     *          integer value representing exponent
     */
    public void setAll(int c, int e)
    {
        setCoefficient(c);
        setExponent(e);
    }

    /**
     * Equals method checks ALL instance variables are equal
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Term)
        {
            if(exponent==((Term) obj).getExponent() && coefficient==((Term)obj).coefficient)
            {
                return true;
            }
            else return false;
        }
        else return false;
    }
    /**
     * toString representing objects values
     *
     * @return formatted string of a term
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String c, e,x="x^";
        if(coefficient==0)
        {
            return "";
        }
        else
            if(exponent==0)
            {
                e="";
                x="";
            }
            else if(exponent==1)
            {
                e="";
                x="x";
            }
            else{
                e=""+exponent;
            }
            if(coefficient==1)
            {
                c="+";
            }
            else if(coefficient==-1)
            {
                c="-";
            }
            else if(coefficient>1)
            {
                c="+"+coefficient;
            }
            else{
                c=""+coefficient;
            }
            return c+x+e;
    }
    /**
     * compareTo compares the terms by their exponents
     *
     * @param o term to compare to
     * @return negative number if object's exponent is less than parameter's exponent, 0 if equal, positive number if greater
     * @see java.lang.Comparable#compareTo(Object o)
     */
    @Override
    public int compareTo(Term o) {
        return this.exponent-o.exponent;
    }
    /**
     * clone returns a deep copy of the object
     *
     * @return Term object that is a deep copy
     * @see java.lang.Object#clone()
     */
    @Override
    public Term clone()
    {
        Term copy=new Term(this.getCoefficient(), this.getExponent());
        return copy;
    }
    /**
     * add method adds term passed to the method
     *
     * @param t
     *          Term object that is added to the term
     * @return new Term object
     */
    public Term add(Term t)throws IllegalArgumentException
    {
        if (t.getExponent()==this.getExponent())
        {
            this.setCoefficient(t.getCoefficient()+getCoefficient());
            return this;
        }
        else throw new IllegalArgumentException();
    }
}
