import java.util.Scanner;

public class NoOfSubstringsHavingAllThreeChars {

    private static int countSubstringsBrute(String input){
        int n = input.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int[] hash = new int[3];
            for (int j = i; j < n; j++) {
                hash[input.charAt(j) - 'a'] = 1;
                if((hash[0] + hash[1] + hash[2]) == 3){
                    count += 1;
                }
            }
        }
        return count;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int countSubstringsBetter(String input){
        int n = input.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int[] hash = new int[3];
            for (int j = i; j < n; j++) {
                hash[input.charAt(j) - 'a'] = 1;
                if((hash[0] + hash[1] + hash[2]) == 3){
                    count += (n-j);
                    break;
                }
            }
        }
        return count;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int countSubstringsOptimal(String input){
        int n = input.length();
        int count = 0;
        int[] hash = new int[3];
        hash[0] = -1;
        hash[1] = -1;
        hash[2] = -1;

        for (int i = 0; i < n; i++) {
            hash[input.charAt(i) - 'a'] = i;
            count += (1 + Math.min(hash[0], Math.min(hash[1], hash[2])));
        }
        return count;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        sc.close();
        System.out.println(countSubstringsBrute(input));
        System.out.println(countSubstringsBetter(input));
        System.out.println(countSubstringsOptimal(input));
    }
}
