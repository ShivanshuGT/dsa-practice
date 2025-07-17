import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MajorityElementII {


    private static List<Integer> findMajorityElementBrute(int arr[]){
        int n = arr.length;
        int threshold = Math.floorDiv(n, 3);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(result.size() == 2){
                break; // at max there can be ony 2 elements that can be majority in an array
            }
            if(result.contains(arr[i])){
                continue;
            }
            if(findNumberOfOccurrences(arr, arr[i]) > threshold){
                result.add(arr[i]);
            }

        }
        Collections.sort(result);
        return result;

        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int findNumberOfOccurrences(int arr[], int target){
        int n = arr.length;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if(target == arr[i]){
                result += 1;
            }
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    } 

    private static List<Integer> findMajorityElementBetter(int arr[]){
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int threshold = Math.floorDiv(n, 3);
        for (int i = 0; i < n; i++) {
            if(result.size() == 2){
                break;
            }
            if(result.contains(arr[i])){
                continue;
            }   
            int occurence = map.getOrDefault(arr[i], 0);
            if(occurence + 1 > threshold){
                result.add(arr[i]);
            }
            map.put(arr[i], occurence + 1);
        }

        Collections.sort(result);
        return result;

        // TC -> can vary from O(n x 1 ) to O(n x n)
        // SC -> O(n)
    }

    private static List<Integer> findMajorityElementOptimal(int arr[]){
        List<Integer> result = new ArrayList<>();
        int cnt1 = 0;
        int cnt2 = 0;
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if(cnt1 == 0 && el2 != arr[i]){
                cnt1 = 1;
                el1 = arr[i];
            }else if(cnt2 == 0 && el1 != arr[i]){
                cnt2 = 1;
                el2 = arr[i];
            }else if(arr[i] == el1){
                cnt1 += 1;
            }else if(arr[i] == el2){
                cnt2 += 1;
            }else{
                cnt1 -= 1;
                cnt2 -= 1;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        // mannual check for el1 and el2
        int threshold = Math.floorDiv(n, 3);
        for (int i = 0; i < n; i++) {
            if(arr[i] == el1){
                cnt1 += 1;
            }
            if(arr[i] == el2){
                cnt2 += 1;
            }
        }
        if(cnt1 > threshold){
            result.add(el1);
        }
        if(cnt2 > threshold){
            result.add(el2);
        }
        Collections.sort(result);
        return result;

    }
    

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> result = new ArrayList<>();
        // result = findMajorityElementBrute(arr);
        // result = findMajorityElementBetter(arr);
        result = findMajorityElementOptimal(arr);
        if(result.size() > 0){
            for (Integer element : result) {
                System.out.print(element +" ");
            }
        }else{
            System.out.print("There is no majority element.");
        }
        sc.close();


    }
}
