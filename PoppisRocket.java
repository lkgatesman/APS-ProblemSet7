import java.util.*;
import java.io.*;

/*
This is a bottom-up dynamic programming approach.
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


