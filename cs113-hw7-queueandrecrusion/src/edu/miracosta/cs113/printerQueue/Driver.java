package edu.miracosta.cs113.printerQueue;

/**
 * Driver.java
 * Simulates printing the same set of documents using 1,2 and three printers. Prints out the time that it took,
 * order of printing and which printer printed which print job.
 * @author Emiliia Dyrenkova
 */
public class Driver {
    public static void main(String [] args) {
        PrintJob[] printJobs = new PrintJob[100];
        for (int i = 0; i < printJobs.length; i++) {
            PrintJob pj = new PrintJob();
            printJobs[i] = pj;
        }
        //Experiment 1:
        int counter1 = 0;
        Printer pr1 = new Printer(1, 50);
        System.out.println("Experiment 1:");
        int i = 0;
        do {
            if (i < printJobs.length) {
                pr1.addPrintJob(printJobs[i]);
                i++;
            }
            pr1.print();
            counter1++;
        } while (!pr1.isEmpty() || pr1.isPrinting());
        System.out.println("Minutes took to print: " + counter1);
        counter1=0;
        pr1.setBounds(1,19);
        Printer pr2=new Printer(20,50);
        System.out.println("Experiment 2:");
        i = 0;
        do {
            if (i < printJobs.length) {
                if(!pr1.addPrintJob(printJobs[i])){
                    pr2.addPrintJob(printJobs[i]);
                }
                i++;
            }
            pr1.print();
            pr2.print();
            counter1++;
        } while (!pr1.isEmpty() || pr1.isPrinting() || pr2.isPrinting() || !pr2.isEmpty());

        System.out.println("Minutes took to print: " + counter1);
        counter1=0;
        pr1.setBounds(1,9);
        pr2.setBounds(10,19);
        Printer pr3=new Printer(20,50);
        System.out.println("Experiment 3:");
        i = 0;
        do {
            if (i < printJobs.length) {
                if(!pr1.addPrintJob(printJobs[i])){
                    if(!pr2.addPrintJob(printJobs[i])){
                        pr3.addPrintJob(printJobs[i]);
                    }
                }
            }
            i++;
            pr1.print();
            pr2.print();
            pr3.print();
            counter1++;
        } while (!pr1.isEmpty() || pr1.isPrinting() || pr2.isPrinting() || !pr2.isEmpty() || !pr3.isEmpty() || pr3.isPrinting());

        System.out.println("Minutes took to print: " + counter1);
    }

}
