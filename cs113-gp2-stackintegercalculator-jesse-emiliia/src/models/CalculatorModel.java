package models;

import edu.miracosta.cs113.BadAssStack;
import java.util.Scanner;

/**
 * CalculatorModel.java : Concrete class using the stack data structure to evaluate infix math expressions.
 *
 * @author Emiliia Dyrenkova, Jesse Wolf
 * @version 1.0
 */
public class CalculatorModel implements CalculatorInterface {
    private BadAssStack<Integer> stack1;
    private String postFixExpression;

    public CalculatorModel() {
        stack1 = new BadAssStack<Integer>();
        postFixExpression = null;
    }

    public String getPostFixExpression() {
        return postFixExpression;
    }

    /**
     * Takes an infix expression and, enforcing operator precedence, evaluates it using the stack data structure.
     *
     * @param expression unevaluated mathematical expression containing +, -, *, / and paren (all integer based)
     * @return a String representation of the expression evaluated, using operator precedence and enforcing parens.
     */
    @Override
    public String evaluate(String expression) {
            //Check if the expression is a Term. If so calculate derivative.
            if(expression.contains("x")) {
                expression=expression.replaceAll(" ","");
                Term t=new Term(expression);
                t.firstDerivative();
                return t.toString();
            }
            //Check if expression is inFix
            else if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '(') {
                postFixExpression = convertInfixToPostfix(expression);
                return "" + evaluatePostfix(postFixExpression);
            }
            //Check if expression is prefix
            else if (Character.isJavaIdentifierPart(expression.charAt(0))) {
                System.out.println("Convert Prefix here");
            }

            //Else expression is postFix
            else {
                postFixExpression = expression;
                return "" + evaluatePostfix(expression);
            }
            return convertInfixToPostfix(expression);
    }

    /**
     * Converts an infix expression to postfix.
     *
     * @param stringToConvert String
     * @return String
     */
    private String convertInfixToPostfix(String stringToConvert) {
        System.out.println("Within converter, toConvert is: " + stringToConvert);
        ConverterFromInfixToPostfix converter = new ConverterFromInfixToPostfix();
        String convertedExpression = "";
        try {
            convertedExpression = converter.convert(stringToConvert);
        } catch(SyntaxErrorException e) {
            e.getMessage();
        }
        System.out.println("Converted expression is: " + convertedExpression);
        return convertedExpression;
    }

    /**
     * Evaluates the postfix expression.
     *
     * @param expression unevaluated postfix mathematical expression containing +, -, *, / (all integers)
     * @return an integer that represents the result of the calculation
     */
    public int evaluatePostfix(String expression){
        String item;
        int num, lhs, rhs;
        Scanner sc=new Scanner(expression);
        while(sc.hasNext()){
            item=sc.next();
            switch (item.charAt(0)){
                case '-':
                    rhs = stack1.pop();
                    lhs = stack1.pop();
                    num = lhs - rhs;
                    stack1.push(num);
                    break;
                case '+':
                    num=stack1.pop()+stack1.pop();
                    stack1.push(num);
                    break;
                case '/':
                    rhs = stack1.pop();
                    lhs = stack1.pop();
                    num = lhs / rhs;
                    stack1.push(num);
                    break;
                case '*':
                    num=stack1.pop()*stack1.pop();
                    stack1.push(num);
                    break;
                    default:
                        stack1.push(Integer.valueOf(item));
            }
        }
        return stack1.pop();
    }
}

