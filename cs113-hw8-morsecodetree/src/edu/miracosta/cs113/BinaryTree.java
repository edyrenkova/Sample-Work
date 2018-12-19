package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {

    /**
     * protected inner static class that represents the Node in a tree
     *
     * @param <E> represents type of data Node is storing
     */
    protected static class Node<E> implements Serializable {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toSting() {
            return data.toString();
        }
    }

    /**
     * Reference to the root of the tree
     */
    protected Node<E> root;

    /**
     * constructs empty binary tree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * constructs a binary tree with the given node as the root
     * @param root the node that will be the root of the tree
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs binary tree with the given data at the root and the two given subtrees
     *
     * @param data      data stored in the root
     * @param leftTree  left tree
     * @param rightTree right tree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if(leftTree!=null){
            root.left = leftTree.root;
        }else{
            root.left=null;
        }
        if(rightTree!=null){
            root.right = rightTree.root;
        }else{
            root.right=null;
        }
    }

    /**
     * Returns the left subtree
     *
     * @return BinaryTree object that is left subtree
     */
    public BinaryTree<E> getLeftSubtree() {
        if(root!=null && root.left!=null){
            return new BinaryTree<E>(root.left);
        }
        else return null;
    }

    /**
     * Returns right subtree
     *
     * @return BinaryTree object that is right subtree
     */
    public BinaryTree<E> getRightSubtree() {
        if(root!=null && root.right!=null){
            return new BinaryTree<E>(root.right);
        }
        else return null;
    }

    /**
     * Returns data in the root
     *
     * @return data in the root
     */
    public E getData() {
        return root.data;
    }

    /**
     * @return true if the tree is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return (root.left==null && root.right==null);
    }

    /**
     * @return a String representation of the tree
     * @see Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        preOrderTraverse(root,1,sb);
        return sb.toString();
    }

    /**
     * Performs a preorder traversal of the subtree whose root is node. Appends the representation to the StringBuilder.
     * Increments the value of depth (the current tree level).
     * @param node current node
     * @param depth current tree level
     * @param sb string storing tree representation
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb){
        for(int i=1;i<depth;i++){
            sb.append(" ");
        }
        if(node==null){
            sb.append("null\n");
        }
        else{
            sb.append(node.toSting() +"\n");
            preOrderTraverse(node.left, depth+1,sb);
            preOrderTraverse(node.right,depth+1,sb);
        }
    }

    /**
     * Constructs a binary tree by reading its data using Scanner scan
     * @param scan Scanner object where data is read from
     * @return Binary tree that is read
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan){

        String data=scan.next();
        if(data.equals("null")){
            return null;
        }
        else{
            BinaryTree<String> leftTree=readBinaryTree(scan);
            BinaryTree<String> rightTree=readBinaryTree(scan);
            return new BinaryTree<String>(data,leftTree,rightTree);
        }

    }
}
