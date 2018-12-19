package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * HuffmanTree : class that is used for building Huffman tree and encoding the text.
 *
 * @author Dyrenkova
 * @version 1.0
 */
public class HuffmanTree extends BinaryTree implements HuffmanInterface{

    private PriorityQueue<HuffmanNode> queue;

    private ArrayList<String> encodingTable;

    /**
     * private inner class that represents each Huffman node
     */
    private static class HuffmanNode extends Node<Character> implements Comparable<HuffmanNode> {

        public int freq;

        /**
         * Constructs Huffman node with a specified character and frequency of occurrence of that character
         * @param c Character object represents a character stored in the node
         * @param frequency int that represents frequency
         */
        public HuffmanNode(Character c,int frequency){
            super(c);
            this.freq=frequency;
        }

        /**
         * Compares two Huffman nodes based on frequency
         * @param n the object to be compared.
         * @return a negative integer, zero, or a positive integer as frequency of this Huffman node
         * is less than, equal to, or greater than the frequency in specified Huffman node.
         */
        @Override
        public int compareTo(HuffmanNode n) {
            return freq-n.freq;
        }
    }

    /**
     * Constructs empty Huffman tree object
     */
    public HuffmanTree(){
        queue=new PriorityQueue<HuffmanNode>();
        encodingTable=new ArrayList<String>();
    }

    /**
     * builds a Huffman tree based on the text specified.
     * @param text represents text to encode
     */
    private void buildTree(String text){
        ArrayList<Character> characters=new ArrayList<>();
        ArrayList<Integer> frequencies=new ArrayList<>();
        int index;
        for(int i=0;i<text.length();i++){
            index=characters.indexOf(text.charAt(i));
            if(index==-1) {
                characters.add(text.charAt(i));
                frequencies.add(1);
            }
            else{
                frequencies.set(index,(frequencies.get(index)+1));
            }
        }
        for(int i=0;i<characters.size();i++){
            HuffmanNode n=new HuffmanNode(characters.get(i),frequencies.get(i));
            queue.offer(n);
        }
        while(queue.size()>1){
            HuffmanNode n1=queue.poll();
            HuffmanNode n2=queue.poll();
            HuffmanNode root=new HuffmanNode(null, n1.freq+n2.freq);
            root.right=n2;
            root.left=n1;
            queue.add(root);
        }
        StringBuilder code=new StringBuilder();
        fillEncodingTable(queue.peek(),code,1);

    }
    /**
     * Decodes a message using the generated Huffman tree, where each character in the given message ('1's and '0's)
     * corresponds to traversals through said tree.
     *
     * @param codedMessage The compressed message based on this Huffman tree's encoding
     * @return The given message in its decoded form
     */
    @Override
    public String decode(String codedMessage) {
        StringBuilder sb=new StringBuilder();
        HuffmanNode current=queue.peek();
        for(int i=0;i<codedMessage.length();i++){

            if(codedMessage.charAt(i)=='0'){
                current=(HuffmanNode) current.left;
            }
            else{
                current=(HuffmanNode)current.right;
            }
            if(current.isLeaf()){
                sb.append(current.data);
                current=queue.peek();
            }
        }
        return sb.toString();
    }

    /**
     * Outputs the message encoded from the generated Huffman tree.
     * pre: the Huffman tree has been built using characters by which the message is only comprised.
     *
     * @param message The message to be decoded
     * @return The given message in its specific Huffman encoding of '1's and '0's
     */
    @Override
    public String encode(String message) {
        buildTree(message);
        StringBuilder sb=new StringBuilder();
        int index;
        for(int i=0;i<message.length();i++){
            index=encodingTable.indexOf(Character.toString(message.charAt(i)));
            sb.append(encodingTable.get(index-1));
        }
        return sb.toString();
    }

    /**
     * returns an ArrayList that represents encoding table
     * @return ArrayList that represents encoding table (with each first element being the code and every second
     * being a character encoded)
     */
    public ArrayList<String> getEncodingTable(){
        return new ArrayList<String>(encodingTable);
    }

    /**
     * Recursively fills encoding table
     * @param hn current huffman node
     * @param code String builder with code
     * @param level current level of the tree
     */
    private void fillEncodingTable(HuffmanNode hn, StringBuilder code, int level){

        if(hn.data!=null){
            encodingTable.add(code.toString());
            encodingTable.add(hn.data.toString());
        }
        else{
            if(hn.left!=null){
                if(level<=(code.length())){
                    code.delete(level-1,code.length());
                }
                code.append('0');
                fillEncodingTable((HuffmanNode) hn.left,code,level+1);
            }
            if(hn.right!=null){
                if(level<=(code.length())){
                    code.delete(level-1,code.length());
                }
                code.append('1');
                fillEncodingTable((HuffmanNode) hn.right,code,level+1);
            }
        }
    }
}
