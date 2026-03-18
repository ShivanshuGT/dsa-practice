import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LongestSubstringWithAtmostKChars {

    private static int findLongestSubstringBrute(String input, int k){
        int n = input.length();
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                map.put(input.charAt(j), map.getOrDefault(input.charAt(j), 0)+1);
                if(map.size() <= k){
                    maxLength = Math.max(maxLength, j-i+1);
                }else{
                    break;
                }
            }
        }
        return maxLength;
        // TC -> O(n x n x log(k))
        // SC -> O(k)
    }

    private static int findLongestSubstringBetter(String input, int k){
        int n = input.length();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(input.charAt(right), map.getOrDefault(input.charAt(right), 0)+1);

            while (map.size() > k) {
                map.put(input.charAt(left), map.get(input.charAt(left))-1);
                if(map.get(input.charAt(left)) == 0){
                    map.remove(input.charAt(left));
                }
                left += 1;
            }

            if(map.size() <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(2 x n x log(k))
        // SC -> O(k)
    }

    private static int findLongestSubstringOptimal(String input, int k){
        int n = input.length();
        int maxLength = 0;
        int right = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(input.charAt(right), map.getOrDefault(input.charAt(right), 0)+1);

            if(map.size() > k){
                map.put(input.charAt(left), map.get(input.charAt(left))-1);
                if(map.get(input.charAt(left)) == 0){
                    map.remove(input.charAt(left));
                }
                left += 1;    
            }

            if(map.size() <= k){
                maxLength = Math.max(maxLength, right-left+1);
            }
            right += 1;
        }
        return maxLength;
        // TC -> O(n x log(k))
        // SC -> O(k)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k =sc.nextInt();
        String input = sc.next();
        System.out.println(findLongestSubstringBrute(input, k));
        System.out.println(findLongestSubstringBetter(input, k));
        System.out.println(findLongestSubstringOptimal(input, k));
        sc.close();
    }
}
