import java.util.HashMap;
import java.util.Scanner;

public class FindMajorityElement {
    
    public static void findMajorityBrute(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int counter = 0;
            for (int j = 0; j < n; j++) {
                if(arr[j] == arr[i]){
                    counter += 1;
                }
            }
            if(counter > n/2){
                System.out.println("Majority Element is " + arr[i]);
                return;
            }
        }
        System.out.println("There is no majority element");
        
        // TC -> O(n x n)
        // SC -> O(1)
    }
    
    public static void findMajorityBetter(int arr[]){
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i],map.getOrDefault(arr[i], 0) +1);
        }
        
        for (int key : map.keySet()) {
            if(map.get(key) > n/2){
                System.out.println("Majority Element is " + key);
                return;
            }
        }
        System.out.println("There is no majority element");
        
        // TC -> can vary from O(n x 1)(in best and avg. case) to O(n x n)(in worst case)
        // SC -> O(n)
    }
    
    public static void findMajorityOptimal(int arr[]){
        int n = arr.length;
        int count = 1;
        int element = arr[0];
        int i = 1;
        while (i < n) {
            if(count == 0){
                element = arr[i];
                count = 1;
            }
            if(element == arr[i]){
                count += 1;
            }else{
                count -= 1;
            }
            i += 1;
        }
        
        int counter = 0;
        for (int j = 0; j < n; j++) {
            if(arr[j] == element){
                counter += 1;
            }
        }
        if(counter > n/2){
            System.out.println("Majority Element is " + element);
        }else{
            System.out.println("There is no majority element");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // findMajorityBrute(arr);
        // findMajorityBetter(arr);
        findMajorityOptimal(arr);
        sc.close();
    }
}
