import java.util.Scanner;

public class Recursion {

    // print name N times
    private static void printNameNTimes(int n){
        if(n ==0)
        return;
        printNameNTimes(n-1);
        System.out.print("TYAGI \n");
    }

    // print from 1 to N  - backtrack
    private static void printFrom1ToN(int n){
        if(n == 0)
        return;
        printFrom1ToN(n-1);
        System.out.print(n + "\n");
    }

    // print from N to 1  - backtrack
    private static void printFromNTo1(int current, int n){
        if(current > n)
        return;
        printFromNTo1(current + 1, n);
        System.out.print(current + "\n");
    }

    // sum of first n natural numbers
    private static int sumOfFirstNNaturalNumbers(int n){
        if(n == 0)
            return 0;
        return n + sumOfFirstNNaturalNumbers(n-1);
    }

    // factorial of n
    private static int factorial(int n){
        if(n == 0)
            return 1;
        return n * factorial(n-1);
    }

    // reverse an array
    private static void reverseArray(int[] arr, int n, int i){
        if(i >= n/2)
            return;
        swap(arr, i, n-i-1);
        reverseArray(arr, n, i+1);
    }

    // swap two values in an array
    private static void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp; 
    }

    // check whether a string is palindrome or not
    private static boolean isPalindrome(String str, int i){
        if(i >= str.length()/2){
            return true;
        }
        if(str.charAt(i) != str.charAt(str.length()-i-1)){
            return false;
        }  
        return isPalindrome(str, i+1);
    }

    // nth fibonacci number
    // TC -> 2^n
    private static int fibonacci(int n){
        if( n <= 1)
            return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // int arr[]  = new int[n];
        // printNameNTimes(n);
        // printFrom1ToN(n);
        // printFromNTo1(1, n);
        // System.out.print(sumOfFirstNNaturalNumbers(n));
        // System.out.print(factorial(n));
        // for(int i = 0; i < n ; i++)
        //     arr[i] = sc.nextInt();
        // reverseArray(arr, n, 0);
        // for(int i = 0; i < n ; i++)
        //     System.out.print(arr[i] + ", ");
        // String input = sc.nextLine();
        // if(isPalindrome(input,  0)){
        //     System.out.print("Given string is a Palindrome");
        // }else{
        //     System.out.print("Given string is not a Palindrome");
        // }
        System.out.print(fibonacci(n));
        sc.close();
    }
}
