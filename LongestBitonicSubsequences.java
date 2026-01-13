import java.util.Scanner;

public class LongestBitonicSubsequences {
    private static int findLongestBitonicSubsequence(int[] arr){
        int n = arr.length;

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        for (int i = 0; i < n; i++) {
            dp1[i] = 1;
            dp2[i] = 1;
        }

        // computing for dp1

        for (int ind = 1; ind < n; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                if((arr[ind] > arr[prev]) && (dp1[ind] < (1 + dp1[prev]))){
                    dp1[ind] = 1 + dp1[prev];
                }
            }
        }

        // computing for dp2

        for (int ind = n-2; ind >= 0; ind--) {
            for (int prev = n-1; prev > ind; prev--) {
                if((arr[ind] > arr[prev]) && (dp2[ind] < (1 + dp2[prev]))){
                    dp2[ind] = 1 + dp2[prev];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp1[i] + dp2[i] -1);
        }

        return ans;
        // TC -> O(n x n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findLongestBitonicSubsequence(arr));
        sc.close();
    }
}


