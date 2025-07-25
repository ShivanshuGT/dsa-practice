import java.util.ArrayList;
import java.util.List;
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

    private static void isTheNumberAPalindrome(int n){
        int number = n;
        int reverse = 0;
        while(n > 0){
            reverse = reverse * 10 + n % 10;
            n = n / 10;
        }
        if(number == reverse){
            System.out.print("Given Number is a Palindrome");
        }else{
            System.out.print("Given Number is not a Palindrome");

        }
    }

    private static void isArmstrong(int n){
        int number = n;
        int sum = 0;
        int numberOfDigits = String.valueOf(n).length();
        while (n > 0) {
            int digit = n % 10;
            sum = (int) (sum + Math.pow(digit, numberOfDigits));
            n = n / 10;
        }
        if(sum == number){
            System.out.print("Given Number is an Armstrong Number");
        }else{
            System.out.print("Given Number is not an Armstrong Number");

        }
    }

    private static void printAllDivisors(int n){
        List<Integer> list = new ArrayList<>();

        // TC -> O(sqrt(n))
        for(int i=1; i*i <= n; i++) {
            if(n % i ==0){
                list.add(i);
                if(i != (n/i))
                list.add(n/i);
            }
        }

        // TC -> O(x.log(x)) where x is the number of factors of n
        list.sort(null);

        // TC -> O(x) where x is the number of factors of n
        list.forEach(x -> System.out.print(x + ", "));

    }

    private static void isPrime(int n){
        // a number is prime only if it has exactly two factors ie., 1 and itself
        // by this rule 1 is not a prime number because there is only one factor of 1
        int counter = 0;
        for(int i = 1 ; i*i <= n ; i++){
            if(n % i == 0){
                counter +=1;
                if((i) != (n/i)){
                    counter += 1;
                }
            }
        }

        if(counter == 2){
            System.out.print("It is a prime number");
        }else{
            System.out.print("It is not a prime number");
        }
    }

    private static void findHCF(int n1, int n2){
        int min = Math.min(n1, n2);
        int hcf = 1; // by default hcf of any two numbers is 1
        for(int i =1; i<= min; i++){
            if((n1 % i ==0) && (n2 % i == 0)){
                hcf = i;
            }
        }

        System.out.print("HCF / GCD of "+ n1+ " and "+ n2 +" is : "+ hcf);
    }

    private static void findHCFByEuclideanAlgo(int n1, int n2){
        while( n1 > 0 && n2 > 0){
            if(n1 > n2){
                n1 = n1 % n2;
            }else{
                n2 = n2 % n1;
            }
        }
        if(n1 == 0){
            System.out.print("GCD is " + n2);
        }
        else{
            System.out.print("GCD is " + n1);

        }
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        // printAllDigits(n);
        // printNumberOfDigits(n);
        // printReverseOfADigit(n);
        // isTheNumberAPalindrome(n);
        // isArmstrong(n);
        // printAllDivisors(n);
        // isPrime(n);
        // findHCF(n1, n2);
        findHCFByEuclideanAlgo(n1, n2);
        sc.close();
    }
}
