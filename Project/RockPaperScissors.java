import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] choices = {"rock", "paper", "scissors"};
        char playAgain;

        System.out.println("🎮 Welcome to Rock, Paper, Scissors Game!");

        do {
            System.out.println("Enter your choice (rock, paper, scissors): ");
            String userChoice = sc.next().toLowerCase();

            // Computer choice
            String computerChoice = choices[rand.nextInt(3)];
            System.out.println("🤖 Computer chose: " + computerChoice);

            // Result logic
            if (userChoice.equals(computerChoice)) {
                System.out.println("😐 It's a tie!");
            } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("🎉 You win!");
            } else {
                System.out.println("💻 Computer wins!");
            }

            System.out.print("Do you want to play again? (y/n): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'y' || playAgain == 'Y');

        System.out.println("👋 Thanks for playing!");
        sc.close();
    }
}
