import java.util.Arrays;
import java.util.Scanner;

public class LongestStringChain {

    private static boolean checkPossibility(String longerString, String shorterString){
        if((longerString.length() <= shorterString.length()) || (longerString.length() != (shorterString.length()+1))){
            return false;
        }

        int first = 0; // points to longer string
        int second = 0; // point to shorter string

        while (first < longerString.length()) {
            if((second < shorterString.length()) && (longerString.charAt(first) == shorterString.charAt(second))){
                first += 1;
                second += 1;
            }else{
                first += 1;
            }
        }

        if((first == longerString.length()) && (second == shorterString.length())){
            return true;
        }

        return false;

        // TC -> O(len(longerString))
        // SC -> O(1)
    }

    private static void findLongestStringChain(String[] arr){
        int n = arr.length;

        int[] dp = new int[n];
        int[] hash = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = i;
        }

        int lastIndex = 0;
        int length = 0;

        Arrays.sort(arr, (a,b) -> Integer.compare(a.length(), b.length()));

        for (int ind = 1; ind < n; ind++) {
            for (int prev = 0; prev <= ind-1; prev++) {
                if(checkPossibility(arr[ind], arr[prev]) && (dp[ind] < (1 + dp[prev])))   {
                    dp[ind] = 1 + dp[prev];
                    hash[ind] = prev;
                }
            }

            if(dp[ind] > length){
                length = dp[ind];
                lastIndex = ind;
            }
        }  

        
        String[] ans = new String[length];
        int x = length-1;
        ans[x] = arr[lastIndex];
        x -= 1;

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            ans[x] = arr[lastIndex];
            x -= 1;
        }

        for (int i = 0; i < length; i++) {
            System.out.println(ans[i] + " ");
        }

        // SC -> O(n)
        // TC -> O(n x log(n)) + O(n x n x len(string)) + O(n)
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        findLongestStringChain(arr);
        sc.close();
    }
}
