import java.util.Scanner;

public class MissingNumberInArray {

    public static int findMissingNumberBrute(int arr[], int n){

        for (int i = 1; i <= n; i++) {
            boolean found = false;
            for (int j = 0; j < n-1; j++) {
                if(arr[j] == i){
                    found = true;
                    break;
                }
            }
            if(!found){
                return i;
            }
        }
        return -1;

        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static long findMissingNumberOptimalOne(int arr[], int n){
        long sum = 0;
        for (int i = 0; i < n-1; i++) {
            sum += arr[i];
        }
        return (n*(n+1)/2) - sum;

        // TC -> O(n)
        // SC -> O(1)
    }

    public static long findMissingNumberOptimalTwo(int arr[], int n){
        // ^ -> XOR operator
        // a^a = 0
        // 0^a = a
        long xor1 = 0;
        long xor2 = 0;
        for (int i = 0; i < n-1; i++) {
           xor1 = xor1 ^ arr[i];
           xor2 = xor2 ^ (i+1); 
        }
        xor2 = xor2 ^ n;
        return xor1 ^ xor2;

        // TC -> O(n)
        // SC -> O(1)
    }

    public static int findMissingNumberBetter(int arr[], int n){
        int hash[] = new int[n+1]; // all values will be zero by default

        for (int i = 0; i < n-1; i++) {
            hash[arr[i]] = 1;
        }

        for (int i = 1; i < n+1; i++) {
            if(hash[i] == 0){
                return i;
            }
        }

        return -1;
        // TC -> O(2n)
        // SC -> O(n)

    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n-1];

        for (int i = 0; i < n-1; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print(findMissingNumberOptimalTwo(arr, n));

        sc.close();
    }
}
