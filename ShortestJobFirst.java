import java.util.Arrays;
import java.util.Scanner;

public class ShortestJobFirst {

    private static int findAvgWaitingTime(int[] arr){
        int n = arr.length;
        Arrays.sort(arr);
        int time = 0;
        int totalWaitingTime = 0;

        for (int i = 0; i < n; i++) {
            totalWaitingTime += time;
            time += arr[i];
        }
        return (totalWaitingTime/n);
        // TC -> O(nlogn) + O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findAvgWaitingTime(arr));
        sc.close();
    }
}
