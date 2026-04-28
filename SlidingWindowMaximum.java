import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class SlidingWindowMaximum {

    private static List<Integer> getSlidingWindowMaximum(int[] arr, int k){
        int n = arr.length;
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // maintaining the window size of 'k' elements
            if((!deque.isEmpty()) && ((i-k) >= deque.peekFirst())){
                deque.pollFirst();
            }

            while ((!deque.isEmpty()) && (arr[deque.peekLast()] <= arr[i])) {
                deque.pollLast();
            }

            deque.add(i);

            if(i >= (k-1)){
                ans.add(arr[deque.peekFirst()]);
            }
        }
        return ans;
        // TC -> O(2 x n)
        // SC -> O(k) + O(n-k) = O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> ans = getSlidingWindowMaximum(arr, k);
        for (Integer x : ans) {
            System.out.println(x);
        }
        sc.close();
    }
}
