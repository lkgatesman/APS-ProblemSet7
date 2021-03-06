import java.io.*;

public class RuthlessWar{

    public static void main(String args[]) throws IOException{

        //BufferedReader to read input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        //Array that holds 1 line of parsed input
        String[] input = in.readLine().split(" ");

        int numSoldiers = Integer.parseInt(input[0]);    //Read in the initial number of soldiers
        int numReports = Integer.parseInt(input[1]);    //Read in the number of reports

        //If there is only 1 soldier initially, then there's only 1 possible line of output
        if (numSoldiers == 1){
            System.out.println("* *");  //Print output for 1 initial soldier
            return; //End the function
        }

        //Array of soldiers, where the i'th soldier is stored at soldiers[i]
        //If i'th soldier is dead, the soldiers[i] = null
        Soldier[] soldiers = new Soldier[numSoldiers+2];

        //Populate the attackLine with all of the soldiers (all soldiers are initially alive)
        soldiers[1] = new Soldier(1, -1, 2);    //Manually input soldier #1
        soldiers[numSoldiers] = new Soldier(numSoldiers, numSoldiers-1, -1);    //Manually input the last soldier

        //For soldiers [2...numSoldiers-1], create a new soldier object with the soldier's pals
        //And input the soldier into the array of soldiers
        for (int i = 2; i < numSoldiers; i++){
            soldiers[i] = new Soldier(i, i-1, i+1);
        }

        int L;  //Left-most soldier that will die in the loss report
        int R;  //Right-most soldier that will die in the loss report

        //Note: For each loss report, all soldiers numbered [L...R] will die

        //Read in each loss report
        for (int j = 0; j < numReports; j++){

            input = in.readLine().split(" ");

            L = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);

            //Execute the loss report, i.e. remove all soldiers [L...R] and report the left surviving soldier and the right surviving soldier
            executeReport(soldiers, L, R);

        }

        //Close the BufferedReader
        in.close();

    }

    //Method to execute a loss report
    public static void executeReport(Soldier[] soldiers, int L, int R){

        //The soldier directly to the left of L will survive
        int leftSurviving = soldiers[L].left;  

        //The soldier directly to the right of R will survive
        int rightSurviving = soldiers[R].right;

        //"Kill" (remove) all soldiers numbered [L...R], i.e. set them to null
        for (int i = L; i <= R; i++){
            soldiers[i] = null;
        }

        //String builder to build the output
        StringBuilder sb = new StringBuilder();

        //If there is no left surviving soldier, print *
        if (leftSurviving == -1){
            sb.append("* ");
        }
        //Else, print the left-surviving soldier's number and update its right pal to be the right-surviving soldier
        else{
            soldiers[leftSurviving].right = rightSurviving;
            sb.append(leftSurviving + " ");
        }

        //If there is no right-surviving soldier, print *
        if (rightSurviving == -1){
            sb.append("*");
        }
        //Else, print the right-surviving soldier's number and update its left pal to be the left-surviving solder
        else{
            soldiers[rightSurviving].left = leftSurviving;
            sb.append(rightSurviving);
        }

        //Print out the left and right surviving pals/soldiers
        System.out.println(sb);

    }

}

class Soldier{

    int num;
    int left;
    int right;

    Soldier(int num, int left, int right){
        this.num = num;
        this.left = left;
        this.right = right;
    }

}

