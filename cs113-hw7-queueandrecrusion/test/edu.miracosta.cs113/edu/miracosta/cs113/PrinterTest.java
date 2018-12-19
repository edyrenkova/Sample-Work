package edu.miracosta.cs113;

import edu.miracosta.cs113.printerQueue.PrintJob;
import edu.miracosta.cs113.printerQueue.Printer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * PrinterTest : Test class for the Printer class.
 */
public class PrinterTest {
    private static final int[] lowerBounds = {1,11,21,31,41};
    private static final int[] upperBounds= {10,20,30,40,50};
    private static final PrintJob[] printJobs=new PrintJob[5];
    static {
        for(int i=0;i<printJobs.length;i++){
            printJobs[i]=new PrintJob();
        }
        printJobs[0].setNumPages(5);
        printJobs[1].setNumPages(10);
        printJobs[2].setNumPages(15);
        printJobs[3].setNumPages(25);
        printJobs[4].setNumPages(40);
    }

    @Test
    public void testFullConstructor() {
        Printer test;
        int lowerBound, upperBound;

        for(int i = 0; i < lowerBounds.length; i++) {
            lowerBound=lowerBounds[i];
            upperBound=upperBounds[i];

            test = new Printer(lowerBound, upperBound);

            assertEquals("Expected and actual lower bounds DON'T match", lowerBound, test.getLowerBound());
            assertEquals("Expected and actual upper bound DON'T match", upperBound, test.getHigherBound());
        }
    }

    @Test
    public void testGetSetBounds() {
        Printer test = new Printer();
        for(int i = 0; i < lowerBounds.length; i++) {
            test.setBounds(lowerBounds[i],upperBounds[i]);
            assertEquals("Expected and actual lower bounds DON'T match", lowerBounds[i], test.getLowerBound());
            assertEquals("Expected and actual upper bounds DON'T match", upperBounds[i], test.getHigherBound());
        }
    }

    @Test
    public void testToString() {
        Printer test = new Printer();
        String expected, actual;
        int l, u;

        for(int i = 0; i < lowerBounds.length; i++) {
            l = lowerBounds[i];
            u = upperBounds[i];
            expected = "Printer #"+0+": "+l+"-"+u+" pages.";

            test.setBounds(l, u);
            actual = test.toString();

            assertEquals("Expected and actual toStrings DON'T match", expected, actual);
        }
    }

    @Test
    public void testAddPrintJob(){
        for(int i=0;i<lowerBounds.length;i++){
            Printer pr=new Printer();
            pr.setBounds(lowerBounds[i],upperBounds[i]);
            for (PrintJob pj:printJobs) {
                if(pj.getNumPages()>=pr.getLowerBound() && pj.getNumPages()<=pr.getHigherBound())
                    assertTrue("addPrintJob does not return true when print job in bounds", pr.addPrintJob(pj));
                else{
                    assertFalse("addPrintJob does returns true when print job out of bounds", pr.addPrintJob(pj));
                }
            }
        }
    }

    @Test
    public void testPrintEmpty(){
        try{
            Printer pr=new Printer();
            pr.print();
        }
        catch(Exception e){
            fail("Exception is thrown when printer is empty");

        }
    }

    @Test
    public void testPrintSmall(){
        Printer pr=new Printer();
        PrintJob pj=new PrintJob();
        pj.setNumPages(5);
        pr.addPrintJob(pj);
        try {
            pr.print();
        }
        catch(Exception e){
            fail("Exception is thrown when printJob is less than 10");
        }
        assertNull("Current job is not null when job is printer", pr.getCurrentJob());
    }
}
