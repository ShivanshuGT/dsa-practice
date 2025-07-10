import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class LongestConsecutiveSequence {

    public static int findLongestConsecutiveSequenceBrute(int arr[]){
        int n = arr.length;
        int result = 1;
        for (int i = 0; i < n; i++) {
            int j = 1;
            int counter = 1;
            while(linear_search(arr,arr[i]+j)){
                j += 1;
                counter += 1;
            }
            result = Math.max(result, counter);
        }
        return result;

        // TC -> O(n x n x n)
        // SC -> O(1)
    }

    public static int findLongestConsecutiveSequenceBetter(int arr[]){
        int n = arr.length;
        Arrays.sort(arr);
        int last_small = Integer.MIN_VALUE;
        int result = 1;
        int counter = 1;
        for (int i = 0; i < n; i++) {
            if(arr[i] -1 == last_small){
                counter +=1;
            }else if(arr[i] == last_small){
                continue;
            }
            else{
                counter = 1;   
            }
            last_small = arr[i];
            result = Math.max(result, counter);
        }
        return result;

        // TC -> O(n x log(n))
        // SC -> O(1)
    }

    public static int findLongestConsecutiveSequenceOptimal(int arr[]){
        int result = 1;
        HashSet<Integer> set = new HashSet<>();
        for (Integer element : arr) {
            set.add(element);
        }

        for (Integer element : set) {
            if(set.contains(element -1)){
                continue;
            }else{
                int j = 1;
                int counter = 1;
                while (set.contains(element + j)) {
                    counter += 1;
                    j += 1;
                }
                result = Math.max(counter, result);
            }
        }
        return result;

        // TC -> O(n) assuming that searching in the set takes O(1) time
        // SC -> O(n)

    }

    public static boolean linear_search(int arr[], int target){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if(arr[i] == target)
                return true;
        }
        return false;
    } 
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // System.out.print(findLongestConsecutiveSequenceBrute(arr));
        // System.out.print(findLongestConsecutiveSequenceBetter(arr));
        System.out.print(findLongestConsecutiveSequenceOptimal(arr));
        sc.close();
    }
}
