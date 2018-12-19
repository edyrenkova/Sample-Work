/*
 *  BigInteger.java
 *
 *  This class is used to create a BigInteger object that can represent integers that can't normally be represented
  *  by the primitive type "int". BigIntegers can be subtracted ad added together like int types
 *
 *  Author:  Victor Vazquez / Emilia Dyrenkova
 *  Version: 1.0
 */
package edu.miracosta.cs113;

import java.util.LinkedList;//Used to store Integer values in a BigNumber object

public class BigInteger implements Comparable<BigInteger>, BigNumber {
    private LinkedList<Integer> number;
    private boolean negative;

    /**
     * String constructor creates an object using String
     *
     * @param number String representing the number
     */
    public BigInteger(String number) {
        this.convertStringToInteger(number);
    }

    /**
     * Default constructor creates an empty object
     */
    public BigInteger() {
        number = new LinkedList<Integer>();
    }

    /**
     * Copy constructor creates a copy of passed object
     *
     * @param original BigInteger object copied
     */
    public BigInteger(BigInteger original) {
        this.number = new LinkedList<Integer>(original.number);
        this.negative = original.negative;
    }

    /**
     * This method Converts A String into a Integer values and
     * Stores Them into a Linked list with each node Integer value being 9 digits long
     *
     * @param number Is used to obtain the String value needed to be converted to a Integer Value
     */
    public void convertStringToInteger(String number) {
        int lengthOfBigInt, numberOfWholeNodesNeeded, remaindingNumbers;
        String term;
        this.number = new LinkedList<Integer>();
        if (number.startsWith("-")) {
            negative = true;
            number = number.substring(1);
        } else {
            negative = false;
        }

        //Gets the String and removes all the commas
        number = number.replaceAll(",", "");
        lengthOfBigInt = number.length();

        numberOfWholeNodesNeeded = lengthOfBigInt / 9;//This calculates how many full nodes are needed(each node has 9 Characters)
        remaindingNumbers = lengthOfBigInt % 9;//This is used to create the head node that is full(doesnt have 9 characters)

        //This creates the head of the linked list if the head will not have 9 characters in it
        if (remaindingNumbers != 0) {
            this.number.addFirst(new Integer(Integer.parseInt(number.substring(0, (remaindingNumbers)))));
            if (numberOfWholeNodesNeeded > 0) {
                term = number.substring(remaindingNumbers);
            } else return;
        } else
            term = number;

        //This adds the remainding characters to nodes
        for (int i = 0; i < numberOfWholeNodesNeeded; i++) {
            //Will Parse A String to a Integer Object by breaking the String up in separate sections
            // that each hold 9 indexes (Ex. 123456789 is one Integer Object)
            this.number.addLast(new Integer(Integer.parseInt(term.substring(i * 9, i * 9 + 9))));
        }

        //This will get the remainding digits at the end of the String that
        //Couldn't of been added inside the for loop
    }

    /**
     * toString returns the String that represents the big integer number
     *
     * @return string that represents the number
     * @see Object#toString()
     */
    @Override
    public String toString() {
        String result = "", numberText = "";
        int remainder;
        for (Integer part : number) {
            if (number.indexOf(part) != 0 && part.toString().length() < 9) {
                for (int i = 0; i < 9 - part.toString().length(); i++)
                    numberText += "0";
            }
            numberText += part.toString();
        }
        if (numberText.length() > 3) {
            remainder = numberText.length() % 3;
            if (remainder != 0) {
                result += numberText.substring(0, remainder) + ",";
            }
            for (int i = remainder; i < numberText.length(); i += 3) {
                result += numberText.substring(i, i + 3) + ",";
            }
            result = result.substring(0, result.length() - 1);

        } else result = numberText;
        if (negative) {
            result = "-" + result;
        }
        return result;

    }

    /**
     * equals determines if the object passed is equal to the instance of the class
     *
     * @param obj represents the object compared
     * @return boolean that is true if object passed is equal to the instance.
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BigInteger) {
            if (((BigInteger) obj).getNumberNodes() == number.size()) {
                for (int i = 0; i < number.size(); i++) {
                    if (!number.get(i).equals(((BigInteger) obj).getNode(i))) {
                        return false;
                    }

                }
                return true;
            } else return false;
        } else return false;
    }

    /**
     * private helper method returns number of nodes in the linked list
     *
     * @return integer represents number of nodes
     */
    private int getNumberNodes() {
        return number.size();
    }

    /**
     * private helper method returns the node by index
     *
     * @return Integer represents the node at specified index
     */
    private Integer getNode(int index) {
        return number.get(index);
    }
    /**
     * private helper method sets corresponding node
     *
     * @param index represents the index at which the node will be set
     * @param num represents the data the node to be set to
     */
    private void setNode(int index,Integer num) {
        number.set(index,num);
    }

    /**
     * returns a deep copy of the instance
     *
     * @return BigInteger object that is a deep copy of instance
     * @see Object#clone()
     */
    public BigInteger clone() {
        BigInteger num = new BigInteger(this);
        return num;
    }

    /**
     * This allows an integer value to be added to a BigInteger object
     * @param n         Number to be added to a BigInteger
     */
    @Override
    public void add(int n)
    {
        add(n+"");
    }

    /**
     * This is used to compare which BigInteger is bigger and which is smaller in terms of LinkedList number of nodes
     * @param obj       the other BigInteger object being compared to the calling BigInteger
     * @return          Return -1 , 1 or 0 depending on which BigIntger is shorter or if their equal lengths
     */
    @Override
    public int compareTo(BigInteger obj) {
        if (obj.getNumberNodes() > getNumberNodes()) {
            return -1;
        } else if (obj.getNumberNodes() < getNumberNodes()) {
            return 1;
        } else {
            for (int i = 0; i < getNumberNodes(); i++) {
                if (obj.getNode(i) > getNode(i)) {
                    return -1;
                } else if (obj.getNode(i) < getNode(i)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * This method allows a BigInteger to be subtracted by a integer value
     * @param n         this parameter will be used to subtract from the BigInteger object
     */
    @Override
    public void subtract(int n) {
        String num=""+n;
        subtract(num);
    }

    /**
     * This method allows a string representation of a number to subtract from a BigInteger value
     * @param n     String representation of a number used to subtract from a BigInteger number
     */
    @Override
    public void subtract(String n) {
        BigInteger n2=new BigInteger(n);
        if(n2.negative && !this.negative)
        {
            add(n.substring(1));

        }
        else if(!n2.negative && negative)
        {
            negative=false;
            add(n);
            negative=true;
        }
        else if(negative && n2.negative){
            n2.negative=false;
            negative=false;
            BigInteger temp= subtractPositive(n2,this);
            number=temp.number;
            negative=true;
        }
        else{
            BigInteger temp= subtractPositive(this, n2);
            number=temp.number;
            negative=temp.negative;
        }

    }

    /**
     * Helper method that allows subtraction of positive numbers
     * @param n1            First BigInteger object
     * @param n2            Second BigInteger Object
     * @return              Return the difference between the numbers
     */
    private BigInteger subtractPositive(BigInteger n1, BigInteger n2)
    {
        int num1, num2, index1, index2;
        if(n1.compareTo(n2)>=0)
        {
            for(int i=1;i<=n2.getNumberNodes();i++)
            {
                index1=n1.getNumberNodes()-i;
                index2=n2.getNumberNodes()-i;
                num1=n1.getNode(index1);
                num2=n2.getNode(index2);
                num1=num1-num2;
                if(num1<0)
                {
                    num1=(int)(Math.pow(10,9))+num1;
                    n1.setNode(index1-1,getNode(index1-1)-1);
                }
                n1.setNode(index1,num1);
            }
            return n1;
        }
        else{
            for(int i=1;i<=n1.getNumberNodes();i++)
            {
                index1=n2.getNumberNodes()-i;
                index2=n1.getNumberNodes()-i;
                num1=n2.getNode(index1);
                num2=n1.getNode(index2);
                num1=num1-num2;
                if(num1<0)
                {
                    num1=(int)Math.pow(10,9)-num1;
                    n2.setNode(index1-1,getNode(index1-1)-1);
                }
                n2.setNode(index1,num1);
            }
            n2.negative=true;
            return n2;
        }
    }

    /**
     * This method allows a String representation of a number to be added to a BigInteger
     * @param n         The string representation of a number
     */
    @Override
    public void add(String n){
        BigInteger num=new BigInteger(n);
        BigInteger result;
        if(negative && !num.negative){
            negative=false;
            result=subtractPositive(num,this);
        }
        else if(!negative && num.negative){
            num.negative=false;
            result=subtractPositive(this,num);
        }
        else if(negative && num.negative){
            negative=false;
            num.negative=false;
            result=addPositive(this,num);
            result.negative=true;
        }
        else{
            result=addPositive(this,num);
        }
        this.number=result.number;
        this.negative=result.negative;
    }

    /**
     * This method allows BigInteger objects that are both positive to be added together
     * @param int1      First BigInteger object that contains a LinkedList<Integer>
     * @param int2      Second BigInteger object that contains a LinkedList<Integer>
     * @return          return the sum of the int1 and int2
     */
    private BigInteger addPositive(BigInteger int1,BigInteger int2){
        BigInteger n2,n1;
        int index1,index2,num1,num2;
        final int LIMIT=(int)Math.pow(10,9);
        if(int1.compareTo(int2)>=0)
        {
            n2=new BigInteger(int2);
            n1=new BigInteger(int1);
        }
        else{
            n1=new BigInteger(int2);
            n2=new BigInteger(int1);
        }
        for(int i=1;i<=n2.getNumberNodes();i++)
        {
            index1=n1.getNumberNodes()-i;
            index2=n2.getNumberNodes()-i;
            num1=n1.getNode(index1);
            num2=n2.getNode(index2);
            num1=num1+num2;
            n1.setNode(index1,num1);
        }
        for(int i=n1.getNumberNodes()-1;i>0;i--) {
            n1.setNode(i-1,n1.getNode(i-1)+n1.getNode(i)/LIMIT);
            n1.setNode(i,n1.getNode(i)%LIMIT);

        }
        if(n1.getNode(0)>=LIMIT){
            n1.number.addFirst(1);
        }
        return n1;
    }
}