package detaileddifferences;

import java.util.Scanner;

/**
 *
 * @author Dan Harrison
 */
public class DetailedDifferences {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create new scanner on system input
        Scanner input = new Scanner(System.in);
        //Save integer denoting number of sets for later looping
        int sets = input.nextInt(); 
        //Move scanner to next line
        input.nextLine();
        //Loop until counter variable is equal to the integer saved in sets
        for(int count = 0; count < sets; count++){
            //Save next two lines to String variables
            String inputOne = input.nextLine();
            String inputTwo = input.nextLine();
            //Create/reset empty string variable for results
            String compareResult = "";
            //Print input lines
            System.out.println(inputOne);
            System.out.println(inputTwo);
            //Loop through characters in inputOne, comparing with same 
            //character in inputTwo. If same, append "." to results String.
            //If different, append "*"
            for(int i = 0; i < inputOne.length(); i++) {
                if(inputOne.charAt(i) == inputTwo.charAt(i)) {
                    compareResult += ".";
                } else {
                    compareResult += "*";
                }                    
            }
            //Print results followed by a blank line
            System.out.println(compareResult);
            System.out.println("");
        }
        //Close scanner
        input.close();
        System.exit(0);
    }
}
