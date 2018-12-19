package edu.miracosta.cs113;

import models.CalculatorModel;
import org.junit.Before;
import org.junit.Test;
import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Calculator Model Tester. Various J Unit tests to test Calculator Model methods which in effect also tests
 * Converter class.
 *
 * @author Jesse Wolf, Emiliia Dyrenkova
 * @version 1.0
 */
public class CalculatorModelTest {

    /** An object of CalculatorModel class.*/
    private CalculatorModel calculatorModelTest;

    /** Simple Tests **/
    private static final String[] SIMPLE_INFIX_EXPRESSIONS = {"1 + 2", "2 - 1" , "4 / 2" , "2 * 4"};

    private static final String[] SIMPLE_INFIX_TO_POSTFIX = {"1 2 + ", "2 1 - ", "4 2 / ", "2 4 * "};

    private static final int[] SIMPLE_EVALUATION = {3, 1, 2, 8};

    /** Complicated Tests **/
    private static final String[] COMPLICATED_INFIX_EXPRESSIONS = {"( 1 + 2 )", "1 + 2 / 3", "( 1 + 2 ) - ( 4 - 3 )", "6 * 2 / 6 / 2 + 10 - 7"};

    private static final String[] COMPLICATED_INFIX_TO_POSTFIX = {"1 2 + ", "1 2 3 / + ", "1 2 + 4 3 - - ", "6 2 * 6 / 2 / 10 + 7 - "};

    private static final int[] COMPLICATED_EVALUATION = {3, 1, 2, 4};

    /** Exception Checks **/
    private static final String[] EXCEPTION_CHECKS = {"( ( 6 + 2 )", "1 0 /", "2 * X - 3"};

    private static final String[] TERMS={"2x", "5x^4", "-4x^-2", "-2x^0"};

    private static final String[] TERMS_DERIV={"2", "20x^3", "8x^-3", "0"};

    /** This function is executed every single time before each test runs. */
    @Before
    public void setup() {
        calculatorModelTest = new CalculatorModel();
    }

    /** Simple Expression Tests **/
    @Test
    public void testSimplePostfixConversion() {
        for (int i = 0; i < SIMPLE_INFIX_EXPRESSIONS.length; i++) {
            calculatorModelTest.evaluate(SIMPLE_INFIX_EXPRESSIONS[i]);
            assertEquals(SIMPLE_INFIX_TO_POSTFIX[i],calculatorModelTest.getPostFixExpression());
        }
    }

    @Test
    public void testDerivatives(){
        for(int i=0;i<TERMS.length;i++){
            assertEquals(TERMS_DERIV[i],calculatorModelTest.evaluate(TERMS[i]));
        }
    }

    @Test
    public void testSimplePostfixEvaluation() {
        int evaluation;

        for(int i = 0; i < SIMPLE_INFIX_TO_POSTFIX.length; i++) {
            evaluation = calculatorModelTest.evaluatePostfix(SIMPLE_INFIX_TO_POSTFIX[i]);
            assertEquals(SIMPLE_EVALUATION[i],evaluation);
        }
    }

    /** Complicated Expression Tests **/
    @Test
    public void testComplicatedPostfixConversion() {
        for (int i = 0; i < COMPLICATED_INFIX_EXPRESSIONS.length; i++) {
            calculatorModelTest.evaluate(COMPLICATED_INFIX_EXPRESSIONS[i]);
            assertEquals(COMPLICATED_INFIX_TO_POSTFIX[i],calculatorModelTest.getPostFixExpression());
        }
    }

    @Test
    public void testComplicatedPostfixEvaluation() {
        int evaluation;
        for (int i = 0; i < COMPLICATED_INFIX_EXPRESSIONS.length; i++) {
            evaluation = calculatorModelTest.evaluatePostfix(COMPLICATED_INFIX_TO_POSTFIX[i]);
            assertEquals(COMPLICATED_EVALUATION[i],evaluation);
        }
    }

    @Test
    public void testConversionError() {
        try {
            calculatorModelTest.evaluate(EXCEPTION_CHECKS[0]);
            calculatorModelTest.evaluate(EXCEPTION_CHECKS[1]);
            //If neither exception is not thrown then this test fails.
            fail();
        } catch (EmptyStackException | ArithmeticException e) {
            //If exception is caught then error is working correctly.
        }
    }
}
