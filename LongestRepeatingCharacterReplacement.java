import java.util.Scanner;

public class LongestRepeatingCharacterReplacement {

    private static int findMaxLengthBrute(int k, String input){
        int n = input.length();

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            int[] hash = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                char c = input.charAt(j);
                hash[c-'A'] = hash[c-'A'] + 1;
                maxFreq = Math.max(maxFreq, hash[c-'A']);
                if(((j-i+1) - maxFreq) <= k){
                    maxLength = Math.max(maxLength, j-i+1);
                }else{
                    break;
                }
            }
        }
        return maxLength;
        // TC -> O(n x n)
        // SC -> O(26)
    }

    private static int findMaxLengthBetter(int k, String input){
        int n = input.length();
        int maxLength = 0;
        int maxFreq = 0;
        int left = 0;
        int right = 0;
        int[] hash = new int[26];

        while (right < n) {
            char c = input.charAt(right);
            hash[c-'A'] = hash[c-'A'] + 1;
            maxFreq = Math.max(maxFreq, hash[c-'A']);

            while (((right-left-1) - maxFreq) > k) {
                // reduce the window
                hash[input.charAt(left) - 'A'] -= 1;
                // re-compute the max Frequency
                maxFreq = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq = Math.max(maxFreq, hash[i]);
                }
                left += 1;
            }

            if(((right-left+1) - maxFreq) <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(2 x n)
        // SC -> O(26)
    }

    private static int findMaxLengthOptimal(int k, String input){
        int n = input.length();
        int maxFreq = 0;
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int[] hash = new int[26];

        while (right < n) {
            char c = input.charAt(right);
            hash[c-'A'] += 1;
            maxFreq = Math.max(maxFreq, hash[c-'A']);

            if(((right-left+1) - maxFreq) > k){
                // reduce from back
                hash[input.charAt(left) - 'A'] -= 1;
                left += 1;
            }

            if(((right-left+1) - maxFreq) <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(n)
        // SC -> O(26)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String input = sc.next();
        System.out.println(findMaxLengthBrute(k, input));
        System.out.println(findMaxLengthBetter(k, input));
        System.out.println(findMaxLengthOptimal(k, input));
        sc.close();
    }
}
