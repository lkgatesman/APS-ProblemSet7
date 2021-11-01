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

/*
-----------

Problem Statement:

Daru is worried about a very time-consuming APS homework. As a super hacker, he develops an AI called Amadeus to help
him solve his homework problems automatically.

There are N problems for this week. It takes ai seconds for Amadeus to solve the i-th problem. Since Daru is a super
hacker, he has broken into Gradescope to figure out how long it takes Gradescope to evaluate each submission. He knows
that it takes bi seconds for Gradescope autograder to evaluate and accept the correct solution for the i-th problem. (Daru is
not interested in cheating by also downloading all the test cases from Gradescope since it’s not cool and he knows that this
would violate the academic integrity policies).

Amadeus can not work on multiple problems simultaneously. It will follow an order given by Daru to solve problems. Once
Amadeus solves a problem, it submits the solution to Gradescope and continues to the next problem.
It takes no time to submit a solution and Gradescope is able to evaluate multiple solutions at the same time. The solution for
the i-th problem will be accepted in exactly bi seconds after it is submitted to Gradescope. Amadeus always produces a
correct solution, so Gradescope always accepts after the first submission.

Daru wants to find an order of problems to minimize the time necessary for all problems to be accepted on Gradescope.
Can you help him to figure out the optimal order?

Input
The first line contains one integer N. N is the number of problems in homework, 1 ≤ N ≤ 1000.
The i-th line of the following N lines contains a pair of integers ai and bi
(1 ≤ ai, bi ≤10000), as described above.

Output
Output one number followed by a newline. The total number of seconds that it takes Amadeus to solve, submit and get
accept verdict for all the problems when the order of the problems is optimal. (Note, that there may be multiple orders that
result in the same time. Since you only report on the time, the actual order is irrelevant.) The output ends with a newline.

Example 1
Input:
3
2 1
2 5
3 2
Output:
8

-----------
*/
