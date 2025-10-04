import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        char choice;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("I have chosen a number between 1 and 100.");

        do {
            int number = rand.nextInt(100) + 1;
            int guess, attempts = 0;

            while (true) {
                System.out.print("Enter your guess: ");
                guess = sc.nextInt();
                attempts++;

                if (guess < number) {
                    System.out.println("Too low! Try again.");
                } else if (guess > number) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("🎉 Congratulations! You guessed the number " + number + " in " + attempts + " attempts.");
                    break;
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            choice = sc.next().charAt(0);

        } while (choice == 'y' || choice == 'Y');

        System.out.println("Thanks for playing! Goodbye 👋");
        sc.close();
    }
}
