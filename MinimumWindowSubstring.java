import java.util.Scanner;

public class MinimumWindowSubstring {

    private static String findMinimumWindowBrute(String input, String t){
        int n = input.length();
        int m = t.length();

        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int i = 0; i < n; i++) {
            int count = 0;
            int[] hash = new int[256];
            for (int j = 0; j < m; j++) {
                hash[t.charAt(j)] += 1;
            }

            for (int j = i; j < n; j++) {
                if(hash[input.charAt(j)] > 0){
                    count += 1;
                }
                hash[input.charAt(j)] -= 1;

                if(count == m){
                    // the substring has all the required chars
                    if((j-i+1) < minLength){
                        minLength = j-i+1;
                        startIndex = i;
                        break;
                    }
                }
            }
        }

        return (startIndex == -1) ? "" : input.substring(startIndex, startIndex+minLength);
        // TC -> O(n x n x m)
        // SC -> O(256)
    }

    private static String findMinimumWindowOptimal(String input, String t){
        int n = input.length();
        int m = t.length();
        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;
        int[] hash = new int[256];
        int count = 0;

        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)] += 1;
        }

        while (right < n) {
            if(hash[input.charAt(right)] > 0){
                count += 1;
            }
            hash[input.charAt(right)] -= 1;

            while (count == m) {
                // reduce the substring till the count matches with m
                // i.e, try to reduce the substring till it has all the required chars
                if((right-left+1) < minLength){
                    minLength = right-left+1;
                    startIndex = left;
                }
                hash[input.charAt(left)] += 1;
                if(hash[input.charAt(left)] > 0){
                    count -= 1;
                }
                left += 1;
            }
            right += 1;
        }
        return (startIndex == -1) ? "" : input.substring(startIndex, startIndex+minLength);
        // TC -> O(m) + O(2 x n)
        // SC -> O(256)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String t = sc.next();
        System.out.println(findMinimumWindowBrute(input, t));
        System.out.println(findMinimumWindowOptimal(input, t));
        sc.close();
    }
}
