package models;

import edu.miracosta.cs113.BadAssStack;
import java.util.EmptyStackException;

/**
 * Converter Class used to convert from infix to postfix notation.
 * For use within Calculator Model.
 *
 * @author Jesse Wolf , Emiliia Dyrenkova
 * @version 1.0
 */
public class ConverterFromInfixToPostfix {
    private BadAssStack<Character> operatorStack;
    private StringBuilder postfix;

    private static final String OPERATORS = "+-*/()";
    private static final int[] PRECEDENCE = {1,1,2,2,-1,-1};

    /**
     * Converts infix to postfix.
     * @param infix String
     * @return String
     * @throws SyntaxErrorException
     */
    public String convert(String infix) throws SyntaxErrorException {
        operatorStack = new BadAssStack<Character>();
        //String builder used to simply build the string. More efficient then concatenation to one String within a for loop.
        postfix = new StringBuilder();

        //Split the infix expression on white space
        String[] tokens = infix.split("\\s+");
        try {
            //Process each token in the infix string.
            for(String nextToken : tokens) {
                char firstChar = nextToken.charAt(0);

                //Check if firstChar is a java identifier(Letter, underscore or dollar sign) , or a digit.
                if(Character.isJavaIdentifierPart(firstChar) || Character.isDigit(firstChar)){
                    postfix.append(nextToken);
                    postfix.append(' ');
                }
                //Else if firstChar is one of our pre-defined operators
                else if(isOperator(firstChar)) {
                    processOperator(firstChar);
                }
                else {
                    throw new SyntaxErrorException("Unexpected Character Encountered: " + firstChar);
                }
            }
            while(!operatorStack.empty()) {
                //Pop the operator off
                char op = operatorStack.pop();
                //Any '(' on the stack is not matched.
                if(op == '(') {
                    throw new SyntaxErrorException("Unmatched opening parenthesis.");
                }
                postfix.append(op);
                postfix.append(' ');
            }
        } catch(EmptyStackException e) {
            System.out.println("Empty Stack Error.");
        }
        return postfix.toString();
    }

    /**
     * Process Operator.
     * @param op char
     */
    private void processOperator(char op) {
        //If first operator or is opening parentheses push onto stack.
        if(operatorStack.empty() || op == '(') {
            operatorStack.push(op);
        }
        else {
            char topOp = operatorStack.peek();
            if(precedence(op) > precedence(topOp)) {
                operatorStack.push(op);
            }
            else {
                while(!operatorStack.empty() && precedence(op) <= precedence(topOp)) {
                    operatorStack.pop();
                    if(topOp == '(') {
                        //Matching '(' popped. Exit loop.
                        break;
                    }
                    postfix.append(topOp);
                    postfix.append(' ');
                    if(!operatorStack.empty()) {
                        topOp = operatorStack.peek();
                    }
                }
                if(op != ')') {
                    operatorStack.push(op);
                }
            }
        }
    }

    /**
     * Simple check if operator is found in our predefined list of operators we can handle.
     * @param ch char
     * @return boolean
     */
    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Precedence checker. Predefined precedence stored in an array that is constant. Will return the precedence for this
     * particular char.
     * @param op char
     * @return int
     */
    private int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }
}
