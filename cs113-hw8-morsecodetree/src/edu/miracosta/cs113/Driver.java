package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver.java
 * Driver class provides menu for the user with 3 choices:
 * 1. Print Morse table
 * 2. Translate Morse to English from file
 * 3. Translate Morse to English from console
 *
 * @author Emiliia Dyrenkova
 */
public class Driver {
    public static void main(String []args) {
        try {
            MorseCodeTree mct = new MorseCodeTree();
            char response;
            Scanner scan = new Scanner(System.in);
            String filename;
            StringBuilder sb;
            do {
                sb=new StringBuilder();
                System.out.println("Please enter:");
                System.out.println("1 - Output English alphabet and corresponding Morse code");
                System.out.println("2 - Input the name of text file with Morse code to decode");
                System.out.println("3 - Input the Morse code to translate");
                System.out.println("Enter Q if want to quit");
                response = scan.next().charAt(0);
                switch (response) {
                    case '1':
                        mct.printMorseTable();
                        break;
                    case '2':
                        System.out.print("Enter file name: ");
                        filename = scan.next();
                        Scanner scanFile=new Scanner(new FileInputStream(filename));
                        while(scanFile.hasNext()){
                            sb.append(scanFile.next());
                        }
                        System.out.println(mct.translateFromMorseCode(sb.toString()));
                        break;
                    case '3':
                        scan.nextLine();
                        System.out.println("Enter Morse code (like *- ---)");
                        sb.append(scan.nextLine());
                        System.out.println(mct.translateFromMorseCode(sb.toString()));
                        break;
                    default:
                        if (response != 'q' && response != 'Q') {
                            System.out.println("unknown choice");
                        }
                        break;
                }
            } while (response != 'Q' && response != 'q');
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
