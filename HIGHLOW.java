import java.util.*;

/**
 * This class represents a number guessing game within 10 tries.
 */
public class HIGHLOW {
    // Message Declaration
    static final String WELCOME = "Hello, you have 10 tries to guess the unknown number between 1 and 100 included.";
    static final String ENTER = "\nPlease enter a number: ";
    static final String HIGHER = "Higher!";
    static final String LOWER = "Lower!";
    static final String REMINDER = "The number should be between 1 and 100 included.";
    static final String WIN = "You win!";
    static final String LOSS = "You lose. The correct number is ";
    static final String INVALID_NUMBER = "You did not enter a valid number.";
    static final String REPLAY = "\nDo you want to play again (yes/no)?";
    static final String NO = "no";
    static final String YES = "yes";
    static final String PERIOD = ".";
    static final String WIN_RATE = "Your win rate is ";
    static final String PERCENTAGE_CHARACTER = "%.";

    // Variable declaration
    static int numberToGuess;
    static int enteredNumber = 0;
    static int nbGamesWon = 0;
    static int nbGamesPlayed = 0;
    static int nbTries = 0;
    static double winPercentage;
    static boolean play = true;
    static boolean validAnswer;
    static String choice;
    static Random random = new Random();
    static Scanner entry = new Scanner(System.in);

    /**
     * This method begins the game by generating a random number to guess and runs
     * until the user guesses the right number or is out of tries. Imput errors are
     * caught.
     */
    public static void beginGame() {
        numberToGuess = random.nextInt(100) + 1;
        while (enteredNumber != numberToGuess && nbTries < 10) {
            try {
                getNumber();
                dealNumberCase();
                winOrLose();
            } catch (Exception e) {
                displayNumberError();
            }
        }
    }

    /**
     * This method displays an invalid number message and clears the previous user
     * entry when an error is triggered.
     */
    public static void displayNumberError() {
        System.out.println(INVALID_NUMBER);
        entry.nextLine();
    }

    /**
     * This method saves the users number input try.
     */
    public static void getNumber() {
        System.out.print(ENTER);
        enteredNumber = entry.nextInt();
    }

    /**
     * This method deals with an entered number when it does not match the number to
     * guess.
     */
    public static void dealNumberCase() {
        if (enteredNumber < 1 || enteredNumber > 100) {
            System.out.println(REMINDER);
        } else if (enteredNumber < numberToGuess) {
            System.out.println(HIGHER);
            nbTries++;
        } else if (enteredNumber > numberToGuess) {
            System.out.println(LOWER);
            nbTries++;
        }
    }

    /**
     * This method decides wether the user won or lost the game.
     */
    public static void winOrLose() {
        if (enteredNumber == numberToGuess) {
            System.out.println(WIN);
            nbGamesWon++;
        } else if (nbTries == 10) {
            System.out.println(LOSS + numberToGuess + PERIOD);
        }
    }

    /**
     * This method displays options at the end of game for the user to respond.
     * 
     * @return play The decision to play again or not.
     */
    public static boolean decideEndGame() {
        while (validAnswer) {
            getEndChoice();
            if (choice.equals(NO)) {
                play = false;
                validAnswer = false;
            } else if (choice.equals(YES)) {
                validAnswer = false;
                nbTries = 0;
                enteredNumber = 0;
            }
        }
        return play;
    }

    /**
     * This method reads the users choice regarding the continuity of the game.
     */
    public static void getEndChoice() {
        System.out.println(REPLAY);
        choice = entry.nextLine();
    }

    /**
     * This method displays the statistics of the user.
     */
    public static void showSummary() {
        System.out.println("\nYou have won " + nbGamesWon + " out of " + nbGamesPlayed + PERIOD);
        calculateWinPercentage(nbGamesWon, nbGamesPlayed);
        System.out.print(WIN_RATE + winPercentage + PERCENTAGE_CHARACTER);
    }

    /**
     * This method calculates the win percentage of the user.
     * 
     * @param nbGamesWon    The number of games won by the user.
     * @param nbGamesPlayed The number of games played by the user.
     * @return winPercentage the win percentage by the user.
     */
    public static double calculateWinPercentage(int nbGamesWon, int nbGamesPlayed) {
        winPercentage = 100.00 * nbGamesWon / nbGamesPlayed;
        return winPercentage;
    }

    /**
     * This method runs the game until the user quits.
     */
    public static void startGuessingGame() {
        System.out.println(WELCOME);
        while (play) {
            beginGame();
            nbGamesPlayed++;
            validAnswer = true;
            entry.nextLine();
            decideEndGame();
            
        }
        entry.close();
        showSummary();
    }

    /**
     * This is the main method of the program.
     * 
     * @param args Arguments.
     */
    public static void main(String[] args) {
        startGuessingGame();
    }
}
