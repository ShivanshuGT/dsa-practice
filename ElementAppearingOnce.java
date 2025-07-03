import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ElementAppearingOnce {

    public static int findElementAppearingOnceBrute(int arr[]){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int counter = 0;
            int num = arr[i];
            for (int j = 0; j < n; j++) {
                if(arr[j] == num){
                    counter += 1;
                }
            }
            if(counter == 1){
                return num;
            }
        }
        return -1;

        // TC -> O(n x n)
        // SC -> O(1)
    }

    public static long findElementAppearingOnceBetter(int arr[]){
        int n = arr.length;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put((long)arr[i], map.getOrDefault((long)arr[i], 0L) + 1);
        }

        for (long key : map.keySet()) {
            if(map.get(key) == 1){
                return key;
            }
        }
        return -1;
        // TC -> O(n.m) + O(m) where m is the size of the map
        // SC -> O(m) where m is the size of the map
    }

    public static long findElementAppearingOnceOptimal(int arr[]){
        int n = arr.length;
        long xor = 0;
        for (int i = 0; i < n; i++) {
            xor = xor ^ arr[i];
        }   
        return xor;

        // TC -> O(n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print(findElementAppearingOnceOptimal(arr));

        sc.close();
    }
}
