import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String allChars = upper + lower + digits + symbols;

        System.out.print("Enter desired password length: ");
        int length = sc.nextInt();

        if (length < 4) {
            System.out.println("Password length should be at least 4 for better security.");
            return;
        }

        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        // Ensure at least one char from each category
        password.append(upper.charAt(rand.nextInt(upper.length())));
        password.append(lower.charAt(rand.nextInt(lower.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));
        password.append(symbols.charAt(rand.nextInt(symbols.length())));

        // Fill remaining with random chars
        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        // Shuffle the password (optional but recommended)
        char[] pwdArray = password.toString().toCharArray();
        for (int i = 0; i < pwdArray.length; i++) {
            int j = rand.nextInt(pwdArray.length);
            char temp = pwdArray[i];
            pwdArray[i] = pwdArray[j];
            pwdArray[j] = temp;
        }

        System.out.println("✅ Your generated password: " + new String(pwdArray));
        sc.close();
    }
}
