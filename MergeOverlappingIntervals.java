import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MergeOverlappingIntervals {

    private static List<List<Integer>> mergeIntervalsOptimal(int arr[][]){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // compare first element
            } else {
                return Integer.compare(a[1], b[1]); // if first equal, compare second
            }
        });
        int n = arr.length;
        int start = arr[0][0];
        int end = arr[0][1];
        for (int i = 1; i < n; i++) {
            if(end >= arr[i][0]){
                end = Math.max(end, arr[i][1]);
            }else{
                result.add(List.of(start, end));
                start = arr[i][0];
                end = arr[i][1];
            }
        }
        result.add(List.of(start, end));
        return result;

        // TC -> O(nlogn) + O(n)
        // SC -> O(n)
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        List<List<Integer>> result = mergeIntervalsOptimal(arr);
        for (List<Integer> list : result) {
            System.out.println(list);
        } 
        sc.close();
    }
}
