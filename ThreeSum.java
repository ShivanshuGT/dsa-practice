import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ThreeSum {


    private static HashSet<List<Integer>> findThreeSumBrute(int arr[]){
        int n = arr.length;
        HashSet<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    
                    if(arr[i] + arr[j] + arr[k] == 0){
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(arr[i]);
                        triplet.add(arr[j]);
                        triplet.add(arr[k]);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                }
            }
        }

        return result;

        // TC -> can vary from O(nxnxn x 1) to O(nxnxn x m)
        // SC -> O(m)
        // where m is the number of triplets
    }

    private static HashSet<List<Integer>> findThreeSumBetter(int arr[]){
        HashSet<List<Integer>> result = new HashSet<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (int j = i+2; j < n; j++) {
                temp.add(arr[j-1]);
                int target = -(arr[i] + arr[j]);
                if(temp.contains(target)){
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(arr[i]);
                    triplet.add(arr[j]);
                    triplet.add(target);
                    Collections.sort(triplet);
                    result.add(triplet);
                }
            }
        }
        return result;

        // TC -> can vary from O(nxn x 1 x 1) to  O(nxn x n x m)
        // SC -> O(n + m) where m is the number of triplets
    }

    private static List<List<Integer>> findThreeSumOptimal(int arr[]){
        int n = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if( i > 0 && arr[i-1] == arr[i])
                continue;
            int j = i+1;
            int k = n-1;

            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if(sum > 0){
                    k -= 1;
                }else if(sum < 0){
                    j += 1;
                }else{
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(arr[i]);
                    triplet.add(arr[j]);
                    triplet.add(arr[k]);
                    result.add(triplet);
                    j += 1;
                    k -= 1;
                    while (arr[j-1] == arr[j] && j < k) {
                        j += 1;
                    }
    
                    while (k<n-1 && arr[k+1] == arr[k] && j < k) {
                        k -= 1;
                    }
                }

                
            }
        }

        return result;

        // TC -> O(n x n)
        // SC -> O(m) where m is the number of triplets
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // HashSet<List<Integer>> result = findThreeSumBrute(arr);
        // HashSet<List<Integer>> result = findThreeSumBetter(arr);
        // for (List<Integer> list : result) {
        //     System.out.println(list);
        // }
        List<List<Integer>> result = findThreeSumOptimal(arr);
        for (List<Integer> list : result) {
            System.out.println(list);   
        }
        sc.close();
    }
}
