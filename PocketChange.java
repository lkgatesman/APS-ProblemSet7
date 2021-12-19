//Source used for logic in memoization of top-up approach: https://www.educative.io/edpresso/coin-change-problem-2-finding-the-number-of-ways-to-make-a-sum
package PS7;
import java.util.*;

public class PocketChange {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        //All denominations (each multiplied by 100 to not use decimal points)
        int denoms[] = { 5, 10 , 20 , 50 , 100 , 200 , 500 , 1000 , 2000 , 5000 , 10000 } ;

        //Read in the input number as a float since it will have 2 decimal points
        double input = in.nextDouble();

        //System.out.println("input: " + input);

        //Multiply the original input value by 100 so we avoid decimal points messing up the sums
		int sum = (int) Math.round((input*100+0.001));
        //System.out.println("sum: " + sum);

        //Variable that will store the number of distinct sums for the input number
        long numDistinctSums; //Using long since it may be larger than Integer.MAX_VALUE

        //If the sum is less than 5 (i.e. less than .05 cents), there are only 0 ways to make it
        //Since our minimum denomination is .05 cents
        if (sum < 5){
            //Print 0 and exit the program
            numDistinctSums = 0;
        }
        else{
             //Call the function that finds the number of distinct sums that can be made
            numDistinctSums = findDistinctNumSums(denoms, sum);        
        }

        //Print out the original input value and the number of ways to sum it using the denominations
        System.out.printf("%6.2f", input);
        System.out.printf("%17d\n", numDistinctSums);

        //Close the scanner
        in.close();

    }

    //Dyamic programming & memoization top-down approach to the problem
    static long findDistinctNumSums(int[] denoms, int sum) {
        
        //2D array for memoizing how many sums there are for each number
        long[][] memo = new long[12][sum+1];
        
        // Set all possible sums to their initial states
        // Each row in the 2D array holds the number of coins that can be used
        for(int i = 0; i < 12; i++){
            memo[i][0] = 1;
        }
        // Each column in the 2D array holds the number of ways other sums can be made
        for(int j = 0; j < sum + 1; j++){
            memo[0][j] = 0;
        }

        //Calculate the number of ways each number can be summed to using the given denominations
        //For each coin value i...
        for(int coin = 1; coin < 12; coin++){
            for(int target = 1; target < sum+1; target++){

                /*
                If the current coin is larger than the target value, 
                then the number of distinct ways of summing to that target
                value must be the same for the previous number of coins 
                used to sum to the target
                */
                if(denoms[coin-1] > target){
                    memo[coin][target] = memo[coin-1][target];
                }

                /*
                ELSE 
                If the current coin is NOT larger than the target value, 
                then the number of distinct ways of summing to the target value
                is equal to the previous number of coins plus the number of ways
                we can use the current coin
                */
                else{
                    memo[coin][target] = memo[coin-1][target] + memo[coin][target-denoms[coin-1]];
                }
            }
        }

        //Then return the numberof ways the sum can be made with the number of denominations we have (which is 11)
        return memo[11][sum];

    }
    
}
