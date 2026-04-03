import java.util.Arrays;
import java.util.Scanner;

public class NonOverlappingIntervals {

    private static int findMinRemovals(int[][] arr){
        int n = arr.length;
        // sorting the array according to the end time in ascending order
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        int count = 1;
        int freeTime = arr[0][1];

        for (int i = 1; i < n; i++) {
            if(freeTime <= arr[i][0]){
                count += 1;
                freeTime = arr[i][1];
            }
        }
        return (n-count);
        // TC -> O(n x log(n)) + O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        System.out.println(findMinRemovals(arr));
        sc.close();
    }
}
