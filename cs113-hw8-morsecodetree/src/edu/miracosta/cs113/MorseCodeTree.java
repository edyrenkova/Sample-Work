package edu.miracosta.cs113;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    public MorseCodeTree(){
        super(new Node<Character>('1'));
        try{
            readFile("morsecode.txt");
        }
        catch(Exception e){
            System.out.println("Exception reading file");
        }

    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws IllegalArgumentException {
        Node<Character>current=root;
        Scanner scan=new Scanner(morseCode);
        scan.useDelimiter(" ");
        String code;
        StringBuilder sb=new StringBuilder();
        while(scan.hasNext()){
            current=root;
            code=scan.next();
            for(int i=0;i<code.length();i++){
                if(code.charAt(i)=='*'){
                    if(current.left!=null) {
                        current = current.left;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                else if(code.charAt(i)=='-'){
                    if(current.right!=null) {
                        current = current.right;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
           sb.append(current.data);
        }
        return sb.toString();
    }

    private void readFile(String filename) throws FileNotFoundException, IllegalArgumentException {
            Scanner scan = new Scanner(new FileInputStream(filename));
            String line, code;
            char data;
            while(scan.hasNextLine()){
                line=scan.nextLine();
                data=line.charAt(0);
                code=line.substring(2);
                addNode(data,code);
            }
    }

    /**
     * adds a single node to the tree in the correct position according to the code
     * @param data
     * @param code
     * @throws IllegalArgumentException when encounters character that is not * or - in the code
     */
    private void addNode(char data, String code) throws IllegalArgumentException {
        Node<Character> current=root;
        for(int i=0;i<code.length();i++){
            if(code.charAt(i)=='-'){
                if(current.right!=null) {
                    current = current.right;
                }else{
                    current.right=new Node<Character>(data);
                }
            }
            else if(code.charAt(i)=='*'){
                if(current.left!=null) {
                    current = current.left;
                }else{
                    current.left=new Node<Character>(data);
                }
            }
            else {
                throw new IllegalArgumentException();
            }
            }
    }
    public void printMorseTable() throws FileNotFoundException{
        System.out.println("International Morse Code");
        Scanner scan = new Scanner(new FileInputStream("morsecode.txt"));
        String line, code;
        char data;
        while(scan.hasNextLine()){
            line=scan.nextLine();
            data=line.charAt(0);
            code=line.substring(2);
            System.out.printf("%-4c %-5s\n",data,code);
        }
    }
} // End of class MorseCodeTree