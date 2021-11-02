import java.util.*;

public class AmadeusGrader {

    public static void main(String args[]){

        //Scanner to read in standard input
        Scanner in = new Scanner(System.in);

        //The total number of problems to solve
        int numProblems = in.nextInt();

        //The total time that it will take amadeus to solve all the problems 
        //Aka the sum of all the a values from the problem statement
        int amadeusTotal = 0;

        //Variable to hold the longest time a single problem takes to be solved and evaluated
        int maxProblem = -1;

        int a;  //how long it takes a problem to be solved by amadeus
        int b;  //how long it takes a prolemt to be evaluated and approved by gradescope

        //The smallest amount of time that it takes gradescope to evaluate one of the problems
        int minB = Integer.MAX_VALUE;

        //Read in each problem
        for (int i = 0; i < numProblems; i++){

            a = in.nextInt();   //Amadeus length of time
            b = in.nextInt();   //Gradescope length of time

            amadeusTotal += a;  //Add the amadeus length of time to the total of amadeus

            //If the current problem is the longest problem we have encountered, 
            //update maxProblem
            if (a+b > maxProblem){
                maxProblem = a+b;
            }

            //If b is the smallest gradescope time we have seen, update minB
            if (b < minB){
                minB = b;
            }

        }

        //Print out the max of the amadeusTotal+minB or the longest problem
        System.out.println(Math.max(amadeusTotal+minB, maxProblem));

        //Close the scanner
        in.close();

    }
    
}
