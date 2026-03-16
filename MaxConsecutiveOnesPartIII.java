import java.util.Scanner;

public class MaxConsecutiveOnesPartIII {
    // this problem can be rephrased as find the longest subarray with
    // atmost 'k' zeroes

    private static int findMaxConsecutiveOnesBrute(int[] arr, int k){
        int n = arr.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int zeroes = 0;
            for (int j = i; j < n; j++) {
                if(arr[j] == 0){
                    zeroes += 1;
                }
                if(zeroes <= k){
                    maxLength = Math.max(maxLength, j-i+1);
                }else{
                    break;
                }
            }
        }
        return maxLength;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int findMaxConsecutiveOnesBetter(int[] arr, int k){
        int n = arr.length;

        int left = 0;
        int right = 0;
        int maxLength = 0;
        int zeroes = 0;

        while (right < n) {
            if(arr[right] == 0){
                zeroes += 1;
            }

            while (zeroes > k) {
                if(arr[left] == 0){
                    zeroes -= 1;
                }
                left +=1;
            }
            if(zeroes <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(2 x n)
        // SC -> O(1)
    }

    private static int findMaxConsecutiveOnesOptimal(int[] arr, int k){
        int n = arr.length;
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int zeroes = 0;

        while (right < n) {
            if(arr[right] == 0){
                zeroes += 1;
            }

            if(zeroes > k){
                if(arr[left] == 0){
                    zeroes -= 1;
                }
                left += 1;
            }

            if(zeroes <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMaxConsecutiveOnesBrute(arr, k));
        System.out.println(findMaxConsecutiveOnesBetter(arr, k));
        System.out.println(findMaxConsecutiveOnesOptimal(arr, k));
        sc.close();
    }
}
