import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LISUsingBinarySearch {

    private static int lowerBound(List<Integer> arr, int target){
        int n = arr.size();
        int low = 0;
        int high = n-1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if(arr.get(mid) >= target){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findLISLength(int[] arr){
        int n = arr.length;
        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);
        int length = 1;
        
        for (int i = 1; i < n; i++) {
            if(temp.get(length-1) < arr[i]){
                // condition of adding 
                temp.add(arr[i]);
                length += 1;
            }else{
                // condition of replacing
                int lowerBound = lowerBound(temp, arr[i]);
                temp.set(lowerBound, arr[i]);
            }
        }

        return length;
        // TC -> O(n x log(n))
        // SC -> O(n)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findLISLength(arr));
        sc.close();

    }
}
