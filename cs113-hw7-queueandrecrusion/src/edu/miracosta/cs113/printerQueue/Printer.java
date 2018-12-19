package edu.miracosta.cs113.printerQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Printer : model class that represents printer that can be bounded by the number of pages the document has
 * to have to be printed.
 */
public class Printer {
    private Queue<PrintJob> queue=new LinkedList<PrintJob>();
    private int lowerBound;
    private int higherBound;
    private PrintJob currentJob;
    private int pagesLeft;
    private int ID;
    private static int lastID=0;

    /**
     * Default constructor, creates a new Printer and increments ID counter. Sets bounds to 0 which means printer can print any document.
     */
    public Printer(){
        this.ID=lastID++;
        currentJob=null;
        lowerBound=0;
        higherBound=0;
    }
    /**
     * Bounds constructor, creates a new Printer and increments ID counter, sets bounds
     * @param lowerBound integer represents lower bound of pages
     * @param higherBound integer represents upper bound of pages
     */
    public Printer(int lowerBound, int higherBound){
        this.ID=lastID++;
        currentJob=null;
        this.lowerBound=lowerBound;
        this.higherBound=higherBound;
    }
    /**
     * setBounds sets number of pages the document to be bounded by
     *
     * @param lowerBound integer representing lower bound
     * @param higherBound integer representing upper bound
     */
    public void setBounds(int lowerBound, int higherBound){
        this.lowerBound=lowerBound;
        this.higherBound=higherBound;
    }
    /**
     * getLowerBound returns number of pages the print job should be greater than to be printed
     *
     * @return integer representing number of pages
     */
    public int getLowerBound(){
        return lowerBound;
    }
    /**
     * getLowerBound returns number of pages the print job should be smaller than to be printed
     *
     * @return integer representing number of pages
     */
    public int getHigherBound(){
        return higherBound;
    }

    /**
     * addPrintJob adds the printJob to the Queue if it is in bounds
     *
     * @param pj PrintJob passed
     * @return boolean that is true if print job was added and false otherwise
     */
    public boolean addPrintJob(PrintJob pj){
        if((pj.getNumPages()<=higherBound && pj.getNumPages()>=lowerBound) || (higherBound==0 && lowerBound==0)){
            queue.offer(pj);
            System.out.println(this.toString()+" received "+pj.toString());
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * prints 10 pages of the current document or the document next in the queue. if queue is empty and no document
     * is printing nothing happens.
     *
     */
    public void print(){
        if(currentJob==null) {
            if (!queue.isEmpty()) {
                currentJob = queue.poll();
                pagesLeft = currentJob.getNumPages() - 10;
                if (pagesLeft <= 0) {
                    System.out.println(currentJob.toString() + " is finished");
                    currentJob = null;
                }
            }
        }
        else{
            pagesLeft-=10;
            if(pagesLeft<=0){
                System.out.println(currentJob.toString()+" is finished");
                currentJob=null;
            }
        }
    }
    /**
     * toString creates a string describing Printer
     *
     * @return String representing Printer
     * @see Object#toString()
     */
    public String toString(){
        return ("Printer #"+ID+": "+lowerBound+"-"+higherBound+" pages.");
    }

    /**
     * isEmpty checks if the queue of the printer is empty
     *
     * @return boolean true if empty, false if there are print job(s) in the queue
     */
    public boolean isEmpty(){
        return(queue.isEmpty());
    }
    /**
     * isPrinting checks if the Printer is printing a job
     *
     * @return boolean true if current job is not null, false otherwise
     */
    public boolean isPrinting(){
        return(currentJob!=null);
    }

    /**
     * equals compares to the passed object to the Printer by bounds. IDs are not checked
     *
     * @return boolean true if equal, false otherwise
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj){
        if(obj instanceof Printer){
            return (((Printer)obj).getHigherBound()==higherBound && ((Printer)obj).getLowerBound()==lowerBound);
        }
        return false;
    }
    /**
     * getCurrentJob returns current Job that is in the process of printing
     *
     * @return PrintJob that is printing
     */
    public PrintJob getCurrentJob(){
        return currentJob;
    }
    /**
     * clone returns a copy of the printer with the same bounds
     *
     * @return new Printer with the same bounds.
     * @see Object#clone()
     */
    public Printer clone(){
        return new Printer(lowerBound,higherBound);
    }
}
