package edu.miracosta.cs113;

/**
 * MyClue.java : This is a driver for the Clue program. Finds the answer by smart traversing: start with 1 for each
 * parameter and increment the one that is wrong
 *
 * @author Emiliia Dyrenkova
 * @version 1.0
 *
 */

import java.util.Random;
import java.util.Scanner;
import model.Theory;
import model.AssistantJack;

public class MyClue {

    /*
     * ALGORITHM:
     *
     * PROMPT "Which theory to test? (1, 2, 3[random]): "
     * READ answerSet
     * INSTANTIATE jack = new AssistantJack(answerSet)
     * weapon=1
     * location=1
     * murder=1
     * solution = jack.checkAnswer(weapon, location, murder)
     * WHILE solution != 0
     *      IF solution==1
     *          weapon+=1
     *      IF solution==2
     *          location+=1
     *      IF solution==3
     *          murder +=1
     *      solution = jack.checkAnswer(weapon, location, murder)
     *
     * OUTPUT "Total checks = " + jack.getTimesAsked()
     * IF jack.getTimesAsked() is greater than 20 THEN
     *      OUTPUT "FAILED"
     * ELSE
     *      OUTPUT "PASSED"
     * END IF
     */

    /**
     * Driver method for smart traversing
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder, weapon, location;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);
        weapon = 1;
        location = 1;
        murder = 1;
        solution=jack.checkAnswer(weapon, location, murder);
        while(solution != 0) {
            if(solution==1)
            {
                weapon += 1;
            }
            if(solution==2)
            {
                location += 1;
            }
            if(solution==3)
            {
                murder += 1;
            }
            solution = jack.checkAnswer(weapon, location, murder);
        }

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Sherlock!");
        }

    }

}