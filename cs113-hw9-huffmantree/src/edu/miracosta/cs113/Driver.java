package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Driver : class that is used to demo Huffman Tree class. Takes in website to scrape and text file name from the console,
 * builds Huffman Tree and, using the tree, encodes and decodes text. Outputs number of bits for each file and percent of
 * compression.
 *
 * @author Dyrenkova
 * @version 1.0
 */
public class Driver {
    public static void main(String[] args){
        String url, filenameOriginal, filenameEncoded, filenameDecoded;
        int originalBits, encodedBits, decodedBits;
        StringBuilder sb=new StringBuilder();
        HuffmanTree ht=new HuffmanTree();
        Scanner kb=new Scanner(System.in);
        Scanner file;
        PrintWriter pw;
        System.out.println("Please, enter url:");
        url=kb.nextLine();
        System.out.println("Please, enter filename, where you want to save clean text (including extension):");
        filenameOriginal=kb.nextLine();
        filenameEncoded="encoded_"+filenameOriginal;
        filenameDecoded="decoded_"+filenameOriginal;
        try {
            TextFileGenerator.makeCleanFile(url, filenameOriginal);
            file=new Scanner(new FileInputStream(filenameOriginal));
            while(file.hasNextLine()){
                sb.append(file.nextLine()+"\n");
            }
            file.close();
            originalBits=sb.length()*16;
            String encoded=ht.encode(sb.toString());
            encodedBits=encoded.length();
            String decoded=ht.decode(encoded);
            decodedBits=decoded.length()*16;
            pw=new PrintWriter(new FileOutputStream(filenameEncoded));
            pw.write(encoded);
            pw.close();
            pw=new PrintWriter(new FileOutputStream(filenameDecoded));
            pw.write(decoded);
            pw.close();
            double percentCompressed=(double)encodedBits/(double)originalBits*100;
            System.out.println("Original size - "+originalBits+" bits");
            System.out.println("Encoded size - "+encodedBits+" bits");
            System.out.println("Decoded size - "+decodedBits+" bits");
            System.out.println("Percent compression - "+percentCompressed+" %");
        }
        catch (IOException e){
            System.out.println("File error");
        }



    }
}
