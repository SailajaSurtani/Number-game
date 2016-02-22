import java.util.Scanner;

/**
 * <h1>GameProgram:</h1>
 * <p>
 * This GameProgram is part of the coding exercise for CB. The end user can
 * assume the number. When the user is ready (enter R/r), the program will
 * prompt with the guessed number and expects the user response for the guess as
 * H/h as Higher, L/l as Lower and Y/y as YES. The program will calculate the
 * guess and continue prompting the guessed number until the user responded with
 * Y/y. (YES).
 * </p>
 * <p>
 * I have assumed the default MIN and MAX values for the number.The program has default MIN = 0  and MAX = 100 boundaries for the Guessed Number. While
 * calculating the guessed number, these boundaries can increase or decrease
 * dynamically depends on the user's guessed number.
 * </p>
 * 
 * @author Sailaja 
 * 
 */
public class GuessingGame {
    // Declaring the constants for the user input data.
    private static final String READY = "R";
    private static final String LOWER = "L";
    private static final String HIGHER = "H";
    private static final String YES = "Y";
     
    /**
     * This is the main method which makes use of playGame method
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        try {
            GuessingGame guessuingGame = new GuessingGame();
            guessuingGame.playGame();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

 /**
  * This method  accepts user input for Guessing Game.
  * When user enters "R"/'r' game starts. 
  * When other character entered user will prompted to enter again.
  */
    public void playGame() {
        // scanner object for command line input reading
        java.util.Scanner cmdInputReader = new Scanner(System.in);
        String userResponse = "";
        
        // Initial prompt to notify the user to assume the number which should be +ve integer
        System.out.println("Please Assume the Number which should be a positive Integer. ");
        System.out.println("Are you ready (enter as R/r) ?");
        userResponse = cmdInputReader.nextLine();
        System.out.println("The Response You Entered : " + userResponse);

        if (userResponse.equalsIgnoreCase(READY)) {
            guessTheNumber(cmdInputReader);
        } else {
            System.out.println("You have entered Wrong Input. Please restart the Game Again!..");
        }

        // closing the command input reader
        cmdInputReader.close();
        // safe exit from the program/game
        System.exit(0);
    }

    /**
     * This is the method where actual logic built.
     * @param cmdInputReader
     * @return Nothing
     */
    private void guessTheNumber(java.util.Scanner cmdInputReader) {
        String userResponse;
        // declaring the initial range for the assumed number and the attempts count
        int min = 0;
        int mid;
        int max = 100;
        int count = 0;

        // initial mid calculation
        mid = min + (max - min) / 2;
        // display mid as initial guess
        System.out.println("The Number you assumed :" + mid + " ? ");

        do {
            System.out.println("Is this HIGHER (enter as H/h)  or LOWER (eneter as L/l) or YES (enter as Y/y) : ");
            userResponse = cmdInputReader.nextLine();
            System.out.println("The Response You Entered : " + userResponse);
            count++;

            if (userResponse.equalsIgnoreCase(HIGHER)) {
                min = mid + 1;
                // if the guessed number is higher than the current Min and Max
                // range, then increase the Min to above Mid and Max to 2 times
                // Max.
                if (min >= max) {
                    max = 2 * max;
                }
                // the below console stmt has to be a logger entry
                // System.out.println("Inside HIGHER: Max as : "+max+", Min as :"+min+" , Mid as : "+
                // mid);
            } else if (userResponse.equalsIgnoreCase(LOWER)) {
                max = mid - 1;
                // if the guessed number is lower than the current Min and Max
                // range, then decrease the Max to the below Mid and Min to the
                // below half of the Mid.
                if (min >= max) {
                    min = max / 2;
                }
                // the below console stmt has to be a logger entry
                // System.out.println("Inside LOWER: Max as : "+max+", Min as :"+min+" , Mid as : "+mid);
            } 
            mid = min + (max - min) / 2;
            // display mid as guessed value. if the userResponse as Y, confirm
            // guessed number otherwise prompt the guessed number with
            // expectation of confirmation from the user.
            if (userResponse.equalsIgnoreCase(HIGHER)|| userResponse.equalsIgnoreCase(LOWER)) {
                System.out.println("The Number you assumed :" + mid + " ? ");
            } else if(userResponse.equalsIgnoreCase(YES)) {
                System.out.println("The assumed Number :" + mid);
            }else {
                System.out.println("You have entered Wrong Input. Please restart the Game Again!..");
            }
            
        } while (!userResponse.equalsIgnoreCase(YES));

        System.out.println("The System guessed the Number you assumed in "
                + count + " attempts");
    }
}
