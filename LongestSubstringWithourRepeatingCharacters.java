import java.util.Scanner;

public class LongestSubstringWithourRepeatingCharacters {

    private static int findLongestSubstringBrute(String s){

        int n = s.length();
        int maxLength = 0;
        int[] hash = new int[256]; // hash array for remembering the appearance of a character
        for (int i = 0; i < n; i++) {
            String str = "";
            for (int j = 0; j < n; j++) {
                if(hash[s.charAt(j)] == 1){
                    // this character appeared previously
                    // so ignore this substring
                    break;
                }
                str = str + s.charAt(j);
                maxLength = Math.max(maxLength, j-i+1);
                hash[s.charAt(j)] = 1;
            }

        }
        return maxLength;
        // TC -> O(n x n)
        // SC -> O(256)
    }

    private static int findLongestSubstringOptimal(String s){
        int n = s.length();
        int maxLength = 0;
        int[] hash = new int[256]; // hash array for remembering the index at which a character appeared lastly
        
        for (int i = 0; i < 256; i++) {
            hash[i] = -1;
        }
        int l = 0;
        int r = 0;

        while (r < n) {
            if(hash[s.charAt(r)] != -1){
                // the character appeared before as well
                if(hash[s.charAt(r)] >= l){
                    // the character is a part of the current window
                    l = hash[s.charAt(r)] + 1;
                }
            }
            maxLength = Math.max(maxLength, r-l+1);
            hash[s.charAt(r)] = r;
            r += 1;
        }
        return maxLength;
        // TC -> O(n)
        // SC -> O(256)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(findLongestSubstringBrute(s));
        System.out.println(findLongestSubstringOptimal(s));
        sc.close();
    }
}
