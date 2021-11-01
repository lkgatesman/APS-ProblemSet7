import java.util.*;
import java.io.*;

/*
This is a bottom-up dynamic programming approach to the problem
Poppi's Rocket from Algorithmic Problem Solving Problem Set #7.
This solution yields 20.0/20.0 on the GradeScope autograder.
*/

public class PoppisRocket {

    public static void main(String args[]) throws IOException{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());    //Read in the number of rungs
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] rung = new int[n+1];  //Array to store the height of the i'th rung at rung[i]

        rung[0] = 0;    //The first rung is height 0 (the ground)

        //Read in the height of each rung, where rung[i] is the height of the i'th rung
        for(int i = 1; i < n+1; i++){
            rung[i] = Integer.parseInt(st.nextToken());
        }

        //Stores the minimum value of rocket strength (k) that Poppi needs to travel
        //up to and including the previous 2 rungs (rungs j-1 and j-2)
        int oldK = rung[1] - rung[0];   //We do the first calculation manually

        //Stores the minimum value of rocket strength (k) that Poppi needs to travel
        //between the current 2 runs we are looking at (rungs j and j-1)
        int newK;

        //For each 2 consecutive rungs
        for (int j = 2; j < n+1; j++){

            //Calculate the distance between the two of them
            newK = rung[j] - rung[j-1];

            //If oldK is greater than newK, we keep oldK the same value
            //(i.e. the rocket doesn't need more strength to traverse rungs j and j-1)
            if (oldK > newK){
                continue;
            }
            //if newK is greater than oldK, we update oldK (i.e., the rocket needs more strength)
            else if (newK > oldK){
                oldK = newK;
                continue;
            }
            //BUT if oldK and newK are equivalent, then in order to prevent k becoming 0,
            //We need to add 1 more to oldK
            else if (newK == oldK){
                oldK++;
                continue;
            }

        }

        //Print out the final value of oldK
        //This is the minimum strength k that Poppi's rocket needs to have
        //in order to reach the top rung
        System.out.println(oldK);

    }
    
}

/*
------------
Problem Statement:

Poppi’s Rocket
The World Tree is a towering tree standing in the center of the endless Cloud Sea. The fabled paradise Elysium is said to
be located at the top of the World Tree. The exterior of the World Tree is covered in thick growths of branches, while the
interior is a high-tech ladder with n rungs.

Poppi can use rocket jumping to climb the ladder, but she cannot
skip any rungs. Initially Poppi is on the ground. Initial strength of
the rocket is set to k. As long as her jumps are strictly smaller
than k, her rocket’s strength remains at k. If her jump is ever
equal to k, the strength decreases by 1. The rocket cannot be
used to propel her to the height greater than k.

For example let the height of the rungs from the ground be 2, 7, 8,
12, 14 respectively and the initial strength of the rocket be k = 5.
Her jumps are:
1. Jumped 2 feet from the ground to the 1st rung (ground to 2),
k remains 5.
2. Jumped 5 feet to the next rung (2 to 7). So, k decreases to 4.
3. Jump 1 feet to the 3rd rung (7 to 8). So, k remains 4.
4. Jump 4 feet to the 4th rung (8 to 12). So, k decreases to 3.
5. Jump 2 feet to the 5th rung (12 to 14). So, k remains 3.

Since the rockets with the greater strength cost more, Poppi asks
you for help in figuring out the minimum initial strength k for the
rocket so that she can reach the top rung.

Input
The first line contains 1 integer: a positive n (0 < n ≤ 10^5) giving the number of rungs in the ladder.
The next line contains n space separated integers, r1, r2, … , rn (1 ≤ r1 < r2 < … < rn ≤ 10^7),
denoting the heights of the rungs from the ground.

Output
Print the minimum value of k as described above. The output ends with a newline.

Example 1
Input:
5
2 7 8 12 14
Output:
5

------------
*/
