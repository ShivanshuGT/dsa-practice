import java.util.Scanner;

public class BasicMaths {

    private static void printAllDigits(int n){

        while(n > 0){
            System.out.print(n%10 + ", ");
            n = n/10;
        }
    }

    private static void printNumberOfDigits(int n){
        // int counter = 0;
        // while(n > 0){
        //     counter += 1;
        //     n = n / 10;
        // }
        // System.out.print("Number of digits  = " + counter);
        // here the time complexity is O(log(n)) where base of the log is 10
        // this is because the n is getting divided by 10 every time
        // In general the complexity of such a loop will be O(log(n)) where base of the log is the number by whom
        // n is getting divided

        // another approach to calculate the number of digits
        // number of digits in a number  = floor(log(number) + 1)
        // here the base of the log is 10
        System.out.print("Number of digits = " + (int)(Math.log10(n) + 1));

    }

    private static void printReverseOfADigit(int n){
        int result = 0;
        while(n > 0){
            result = result*10 + n%10;
            n = n /10;
        }
        System.out.print("reverse of the number is " + result);
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // printAllDigits(n);
        // printNumberOfDigits(n);
        printReverseOfADigit(n);
        sc.close();
    }
}
