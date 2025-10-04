import java.util.Scanner;
import java.util.Random;

public class Hangman {
    private static final String[] WORDS = {
        "java", "python", "computer", "program", "hangman", "challenge", "keyboard", "mouse", "screen", "internet"
    };
    private static final int MAX_TRIES = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String word = WORDS[random.nextInt(WORDS.length)];
        char[] guessed = new char[word.length()];
        for (int i = 0; i < guessed.length; i++) {
            guessed[i] = '_';
        }
        int tries = 0;
        boolean[] used = new boolean[26];
        boolean won = false;

        System.out.println("Welcome to Hangman!");
        while (tries < MAX_TRIES) {
            System.out.print("Word: ");
            for (char c : guessed) {
                System.out.print(c + " ");
            }
            System.out.println("\nTries left: " + (MAX_TRIES - tries));
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
                continue;
            }
            char guess = input.charAt(0);
            if (used[guess - 'a']) {
                System.out.println("You already guessed that letter.");
                continue;
            }
            used[guess - 'a'] = true;
            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessed[i] = guess;
                    found = true;
                }
            }
            if (!found) {
                tries++;
                System.out.println("Wrong guess!");
            } else {
                System.out.println("Good guess!");
            }
            if (String.valueOf(guessed).equals(word)) {
                won = true;
                break;
            }
        }
        if (won) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Game over! The word was: " + word);
        }
        scanner.close();
    }
}
