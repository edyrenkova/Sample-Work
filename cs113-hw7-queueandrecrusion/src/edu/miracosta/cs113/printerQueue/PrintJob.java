package edu.miracosta.cs113.printerQueue;

import java.util.Random;

/**
 * PrintJob : model class that represents one print job of a specific page number.
 */
public class PrintJob {

    private static int IDCounter=0;
    private static Random rand=new Random();
    private int numPages;
    private int ID;

    /**
     * Default constructor, creates a new print job and increments ID counter. Chooses the number of pages randomly
     * from 1 to 50.
     */
    public PrintJob(){
        ID=IDCounter;
        IDCounter++;
        numPages=rand.nextInt(50)+1;
    }
    /**
     * getID returns ID of the print job
     *
     * @return integer representing ID of the job
     */
    public int getID(){
        return ID;
    }

    /**
     * getNumPages returns number of pages of the print job
     *
     * @return integer representing number of pages
     */
    public int getNumPages(){
        return numPages;
    }

    /**
     * setNumPages sets number of pages to the passed value
     *
     * @param numPages integer representing number of pages
     */

    public void setNumPages(int numPages){
        this.numPages=numPages;
    }
    /**
     * toString creates a string describing print job
     *
     * @return String representing print job
     * @see Object#toString()
     */
    public String toString(){
        return ("PrintJob #"+ID+": "+numPages+" pages");
    }

    /**
     * resetIDCount sets IDCount to 0
     */
    public static void resetIDCount(){
        IDCounter=0;
    }
}
