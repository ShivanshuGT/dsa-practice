import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsertIntervals {

    private static List<List<Integer>> insertInterval(int[][] arr, int[] newInterval){
        int n = arr.length;
        int i = 0;
        List<List<Integer>> answer = new ArrayList<>();

        // left portion
        while ((i < n) && (arr[i][1] < newInterval[0])) {
            answer.add(List.of(arr[i][0], arr[i][1]));
            i += 1;
        }

        // middle portion (overlapping portion)
        while ((i < n) && (arr[i][0] < newInterval[1])) {
            newInterval[0] = Math.min(newInterval[0], arr[i][0]);
            newInterval[1] = Math.max(newInterval[1], arr[i][1]);
            i += 1;
        }
        // insert the merged interval
        answer.add(List.of(newInterval[0], newInterval[1]));

        // right portion
        while (i < n) {
            answer.add(List.of(arr[i][0], arr[i][1]));
            i += 1;
        }
        return answer;
        // TC -> O(n)
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int[] newInterval = new int[2];
        newInterval[0] = sc.nextInt();
        newInterval[1] = sc.nextInt();
        List<List<Integer>> answer  =insertInterval(arr, newInterval);
        System.out.println(answer.toString());
        sc.close();
    }
}
