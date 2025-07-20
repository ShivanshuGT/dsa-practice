import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FourSum {

    private static List<List<Integer>> findFourSumBrute(int arr[], int target){
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for(int k = j+1; k < n; k++){
                    for(int l = k+1; l < n; l++){
                        int sum = arr[i] + arr[j] + arr[k] + arr[l];
                        if(sum == target){
                            List<Integer> quad = new ArrayList<>();
                            quad.add(arr[i]);
                            quad.add(arr[j]);
                            quad.add(arr[k]);
                            quad.add(arr[l]);
                            Collections.sort(quad);
                            set.add(quad);
                        }
                    }
                }
            }
        }
        for (List<Integer> list : set) {
            result.add(list);
        }
        return result;

        // TC ->  can vary from O(nxnxnxn x 1) to O(nxnxnxn x m)
        // SC -> O(m) where 'm' is the number of quads
    }

    private static List<List<Integer>> findFourSumBetter(int arr[], int target){
        int n = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                HashSet<Integer> tempSet = new HashSet<>();
                for (int k = j+2; k < n; k++) {
                    tempSet.add(arr[k-1]);
                    int sum = arr[i] + arr[j] + arr[k];
                    if(tempSet.contains(target - sum)){
                        List<Integer> quad = new ArrayList<>();
                        quad.add(arr[i]);
                        quad.add(arr[j]);
                        quad.add(arr[k]);
                        quad.add(target - sum);
                        Collections.sort(quad);
                        set.add(quad);
                    }
                }
            }
        }

        for (List<Integer> list : set) {
           result.add(list); 
        }
        return result;

        // TC ->  can vary from O(nxnxn x 1 x 1) to O(nxnxn x n x m)
        // SC ->  O(n + m) where 'm' is the number of quads
    }

    private static List<List<Integer>> findFourSumOptimal(int arr[], int target){
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if( i > 0 && arr[i-1] == arr[i]){
                continue;
            }
            for (int j = i+1; j < n; j++) {
                if(j > i+1 && arr[j-1] == arr[j]){
                    continue;
                }
                int k = j+1;
                int l = n-1;
                while (k < l) {
                    int sum = arr[i] + arr[j] + arr[k] + arr[l];
                    if(sum > target){
                        l -= 1;
                    }else if(sum < target){
                        k += 1;
                    }else{
                        List<Integer> quad = List.of(arr[i], arr[j],arr[k],arr[l]);
                        result.add(quad);
                        k += 1;
                        l -= 1;
                        while (k < l && arr[k-1] == arr[k]) {
                            k += 1;
                        }
                        while (k < l && arr[l+1] == arr[l]) {
                            l -= 1;
                        }
                    }
                }
            }
        }
        return result;

        // TC -> O(nlogn) + O(nxnxn)
        // SC -> O(m) where 'm' is the number of quads
        // Here the space id used to just return the answer, not to solve the problem
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int target = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // List<List<Integer>> result = findFourSumBrute(arr, target);
        // List<List<Integer>> result = findFourSumBetter(arr, target);
        List<List<Integer>> result = findFourSumOptimal(arr, target);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
        sc.close();
    }
}
