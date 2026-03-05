import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int n1 = kb.nextInt();


        List<Integer> divisors = getDivisors(n1);
        if (divisors.isEmpty()) { // check the factors if list is empty == a prime number
            System.out.println(n1 + " is a prime number.");
        } else {
            System.out.println(n1 + " is not a prime number.");
            System.out.println("The numbers that divide " + n1 + " are: " + divisors);
        }

        System.out.println("How many time want to check the UPC: ");
        int n = kb.nextInt();
        String[] upc = new String[n];
        kb.nextLine();

        for (int i = 0; i < n; i++) { // O(n)
            System.out.println("Enter your 12 digit UPC: ");upc[i] = kb.nextLine();
        }
        for (int i = 0; i < n; i++) {// O(n)
            if (isValidUPC(upc[i])) {
                System.out.println("UPC is Valid ");
            } else {
                System.out.println("UPC is Invalid because the total of digit after mod 10 are not equivalent zero.");
                // Fix with minimum changes
                String corrected = fixUPC(upc[i]);System.out.println("Corrected UPC (minimum change): " + corrected);
            }
        }
    }
    static List<Integer> getDivisors(int n1) {
        List<Integer> divisors = new ArrayList<>();
        if (n1 <= 1) {       // to handle the non-prime number
            return divisors;  // return empty list
        }
        for (int i = 2; i * i <= n1; i++) { // start from two because ts the possible prime number O(sqrt n)
            if (n1 % i == 0) { // to check if i is divisor
                divisors.add(i); // If the number is divisible, the factor i is added to the divisors list (add first factor)
                if (i * i != n1) { //This checks if i is not the square root of n1 we dont want to add it twice.
                    divisors.add(n1 / i); // if i is not square root
                }
            }
        }
        // Sort the divisors from low to high
        Collections.sort(divisors);
        return divisors; // Final Return
    }
    static boolean isValidUPC(String upc) {
        if (upc.length() != 12) {return false;
        }

        int sumOdd = 0, sumEven = 0;
        for (int i = 0; i < 11; i++) { // O(1)
            int digit = Character.getNumericValue(upc.charAt(i));
            if (i % 2 == 0)
                sumOdd += digit;
            else
                sumEven += digit;
        }
        int total = (sumOdd * 3) + sumEven;
        int checkDigit = (10 - (total % 10));
        int lastDigit = Character.getNumericValue(upc.charAt(11)); // to get the last digit
        return checkDigit == lastDigit;
    }
    static String fixUPC(String upc) {
        if (upc.length() != 12)
            return ("Invalid Length");

        int sumOdd = 0, sumEven = 0;

        for (int i = 0; i < 11; i++) {
            int digit = Character.getNumericValue(upc.charAt(i));
            if (i % 2 == 0)
                sumOdd += digit;

            else sumEven += digit;
        }
        int total = (sumOdd * 3) + sumEven;
        int correctCheckDigit = (10 - (total % 10));
        // Replace last digit with correct one
        return upc.substring(0, 11) + correctCheckDigit;
    }
}