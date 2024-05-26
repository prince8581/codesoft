import java.util.Scanner;
import java.util.Random;

public class task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int totalAttempts = 0;
        int roundsWon = 0;

        System.out.println("Enter number between 1 to 100");

        do {
           
            int targetNumber=scanner.nextInt();
            int attemptsLeft = maxAttempts;
            System.out.println("\nI have selected a number between " + minRange + " and " + maxRange + ". You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    roundsWon++;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            totalAttempts += maxAttempts;

            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }

        } while (true);

        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + roundsWon);
        System.out.println("Total attempts made: " + totalAttempts);
        scanner.close();
    }
}
