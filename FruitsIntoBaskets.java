import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FruitsIntoBaskets {

    private static int findMaxFruitsBrute(int[] arr){
        int n = arr.length;
        int maxFruits = 0;

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
                if(map.keySet().size() > 2){
                    break;
                }else{
                    maxFruits = Math.max(maxFruits, j-i+1);
                }
            }

        }
        return maxFruits;
        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int findMaxFruitsBetter(int[] arr){
        int n = arr.length;
        int maxFruits = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while (map.keySet().size() > 2) {
                map.put(arr[left], map.getOrDefault(arr[left], 0) -1);
                if(map.get(arr[left]) == 0){
                    // in case the frequency becomes zero
                    // erase that key from the map
                    map.remove(arr[left]);
                }
                left += 1;
            }

            if(map.keySet().size() <= 2){
                maxFruits = Math.max(maxFruits, right-left+1);
            }
            right += 1;
        }
        return maxFruits;
        // TC -> O(2 x n)
        // SC -> O(1)
    }

    private static int findMaxFruitsOptimal(int[] arr){
        int n = arr.length;
        int maxFruits = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right < n) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            if(map.keySet().size() > 2){
                map.put(arr[left], map.getOrDefault(arr[left], 0) - 1);
                if(map.get(arr[left]) == 0){
                    map.remove(arr[left]);
                }
                left += 1;
            }

            if(map.keySet().size() <= 2){
                maxFruits = Math.max(maxFruits, right-left+1);
            }
            right += 1;
        }
        return maxFruits;
        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMaxFruitsBrute(arr));
        System.out.println(findMaxFruitsBetter(arr));
        System.out.println(findMaxFruitsOptimal(arr));
        sc.close();
    }
}
