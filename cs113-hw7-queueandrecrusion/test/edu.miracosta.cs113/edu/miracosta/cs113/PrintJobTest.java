package edu.miracosta.cs113;

import edu.miracosta.cs113.printerQueue.PrintJob;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * PrintJobTest : Test class for the PrintJob class.
 */
public class PrintJobTest {
    private final static int []PAGES={5,10,15,20,25,30,40,50};

    @Test
    public void testConstructorID(){
        PrintJob.resetIDCount();
        for(int i=0;i<5;i++){
            PrintJob pj=new PrintJob();
            assertEquals("ID is not correct",i,pj.getID());
        }
    }
    @Test
    public void testGetSetNumPages(){
        for (int PAGE : PAGES) {
            PrintJob pj = new PrintJob();
            pj.setNumPages(PAGE);
            assertEquals("Expected number of pages is not the same as actual", PAGE, pj.getNumPages());
        }
    }

    @Test
    public void testToString(){
        for (int PAGE : PAGES) {
            PrintJob pj = new PrintJob();
            pj.setNumPages(PAGE);
            assertEquals("Expected toString result is not the same as actual", "PrintJob #"+pj.getID()+": "+pj.getNumPages()+" pages",pj.toString());
        }
    }
}
