import java.util.Arrays;
import java.util.Scanner;

public class MinimumPlatforms {

    private static int findMinPlatforms(int[] arrival, int[] departure){
        int n = arrival.length;
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int i = 0;
        int j = 0;
        int count = 0;
        int maxCount = 0;

        while (i < n) {
            if(arrival[i] <= departure[j])   {
                // its an arrival
                count += 1;
                i += 1;
            }else{
                // its a departure
                count -= 1;
                j += 1;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
        // TC -> O(2 x nlogn) + O(2 x n)
        // SC -> O(1) 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arrival = new int[n];
        int[] departure = new int[n];
        for (int i = 0; i < n; i++) {
            arrival[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            departure[i] = sc.nextInt();
        }
        System.out.println(findMinPlatforms(arrival, departure));
        sc.close();
    }
}
