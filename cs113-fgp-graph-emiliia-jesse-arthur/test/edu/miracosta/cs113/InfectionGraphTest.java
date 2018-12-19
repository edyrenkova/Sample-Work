package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import static org.junit.Assert.assertEquals;

/**
 * Cities Graph Tester. Various J Unit tests to test Cities Graph Methods.
 */
public class InfectionGraphTest
{
    /**
     * An object of Cities Graph class.
     */
    private InfectionGraph infectionGraphTest;
    private String citiesInputFile = "./resources/citiesInput.txt";

    /**
     * Dijkstra's Outcomes on our default files.
     **/
    private static final int[][] PREDECESSORS = {
            {0,0,6,2,5,0,0,6,7,3},{1,0,1,2,5,0,0,8,2,3},{6,2,1,2,5,0,2,8,2,3},
            {6,2,3,2,5,0,2,8,3,3},{5,0,6,2,5,4,0,6,7,3},{5,0,6,2,5,4,0,6,7,3},
            {6,0,6,2,5,0,0,6,7,3},{6,2,8,8,5,0,7,6,7,3},{6,2,8,8,5,0,7,8,7,3}};


    private static final double[][] DISTANCES = {
            {0.00,1.50,2.75,3.75,1.00,0.50,1.00,2.00,3.00,5.55}, {1.50,1.50,1.33,2.33,2.50,2.00,2.50,3.33,2.33,4.13},
            {2.75,1.33,1.33,1.00,3.75,3.25,1.75,2.00,1.00,2.80}, {3.75,2.33,1.00,1.00,4.75,4.25,2.75,2.00,1.00,1.80},
            {1.00,2.50,3.75,4.75,4.75,0.50,2.00,3.00,4.00,6.55}, {0.50,2.00,3.25,4.25,0.50,0.50,1.50,2.50,3.50,6.05},
            {1.00,2.50,1.75,2.75,2.00,1.50,1.50,1.00,2.00,4.55}, {2.00,3.33,2.00,2.00,3.00,2.50,1.00,1.00,1.00,3.80},
            {3.00,2.33,1.00,1.00,4.00,3.50,2.00,1.00,1.00,2.80}, {1.70,1.50,1.10,3.33,1.90,1.80,1.50,1.30,1.10,1.10}};

    /**
     * This function is executed every single time before each test runs.
     */
    @Before
    public void setup()
    {
        Weight.setInfectionRate(100);
        infectionGraphTest = new InfectionGraph(citiesInputFile);
    }

    /**
     * Test to see if dijsktras is working on our predefined file names.
     * Note: Had to format the doubles in order to use assertEquals.
     */
    @Test
    public void testDijsktrasOutcomes()
    {
        NumberFormat formatter = new DecimalFormat("#0.00");
        int[] testPredecessors;
        double[] testDistances;
        for(int row = 0; row < PREDECESSORS.length;row++)
        {
            infectionGraphTest.runDijkstras(row);
            testPredecessors = infectionGraphTest.getPredecessors();
            testDistances = infectionGraphTest.getDistances();
            int[] expectedPredecessors = PREDECESSORS[row];
            double[] expectedDistances = DISTANCES[row];
            for(int p = 0; p < testPredecessors.length; p++)
            {
                System.out.println("Test: " + testPredecessors[p]);
                System.out.println("Expe: " + expectedPredecessors[p]);
                assertEquals("Expected Predecessors do not match.", testPredecessors[p], expectedPredecessors[p]);
            }
            //Had to get fancy with assertEquals for a double. Checking two doubles is not supported so formatted it before checking equality.
            for(int d = 0; d < testDistances.length; d++)
            {
                System.out.println("Test: " + testDistances[d]);
                System.out.println("Expe: " + expectedDistances[d]);
                assertEquals("Expected Destination does not match.", formatter.format(testDistances[d]),formatter.format(expectedDistances[d]));
            }
        }
    }
}
