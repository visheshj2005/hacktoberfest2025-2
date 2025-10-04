 import java.util.Scanner;

public class NumberConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n🔢 Number Converter Tool");
            System.out.println("1. Decimal to Binary");
            System.out.println("2. Decimal to Octal");
            System.out.println("3. Decimal to Hexadecimal");
            System.out.println("4. Binary to Decimal");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a decimal number: ");
                    int dec1 = sc.nextInt();
                    System.out.println("Binary: " + Integer.toBinaryString(dec1));
                    break;
                case 2:
                    System.out.print("Enter a decimal number: ");
                    int dec2 = sc.nextInt();
                    System.out.println("Octal: " + Integer.toOctalString(dec2));
                    break;
                case 3:
                    System.out.print("Enter a decimal number: ");
                    int dec3 = sc.nextInt();
                    System.out.println("Hexadecimal: " + Integer.toHexString(dec3).toUpperCase());
                    break;
                case 4:
                    System.out.print("Enter a binary number: ");
                    String bin = sc.next();
                    int decimal = Integer.parseInt(bin, 2);
                    System.out.println("Decimal: " + decimal);
                    break;
                case 5:
                    System.out.println("👋 Exiting...");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
  