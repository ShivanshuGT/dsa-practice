import java.util.Scanner;

public class MinimumDaysMBouquets {

    private static boolean isPossibleToMakeBouquets(int flowers[], int m, int k, int day){
        int counter = 0;
        int bouquetsMade = 0;
        int n = flowers.length;
        for (int i = 0; i < n; i++) {
            if(bouquetsMade >= m){
                return true;
            }
            if(flowers[i] <= day){
                // the flower is bloomed
                counter += 1;
            }else{
                // the flower is not bloomed
                bouquetsMade += counter/k;
                counter = 0;
            }
        }
        bouquetsMade += counter/k;
        if(bouquetsMade >= m){
            return true;
        }
        return false;

        // TC -> O(n)
        // Sc -> O(1)

    }  
    
    private static int findMinimumDaysBrute(int flowers[], int m, int k){
        int n = flowers.length;
        if(n < m*k){
            // when the given number of flowers are less than the required number of flowers
            // to make 'm' bouquets of 'k' size
            return -1;
        }

        int min = findMinimumBloomDay(flowers);
        int max = findMaximumBloomDay(flowers);

        for (int i = min; i <= max; i++) {
            if(isPossibleToMakeBouquets(flowers, m, k, i)){
                return i;
            }
        }
        return -1;

        // TC -> O(maxElement - minElement + 1) x O(n)
        // SC -> O(1)
    }

    private static int findMinimumDaysOptimal(int flowers[], int m, int k){
        int n = flowers.length;
        if( n < m*k){
            return -1;
        }

        int beg = findMinimumBloomDay(flowers);
        int end = findMaximumBloomDay(flowers);
        int result = -1;

        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(isPossibleToMakeBouquets(flowers, m, k, mid)){
                // it is possible to make m bouquets with
                // k consecutive flowers in 'mid' number of days
                result = mid;
                end = mid - 1;
            }else{
                // it is not possible to make m bouquets with
                // k consecutive flowers in 'mid' number of days
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(maxElement - minElement + 1)) x O(n)
        // SC -> O(1)
    }

    private static int findMinimumBloomDay(int flowers[]){
        int min = Integer.MAX_VALUE;
        for (int i : flowers) {
            if(min > i){
                min = i;
            }
        }
        return min;

        // TC -> O(n)
        // Sc -> O(1)
    }

    private static int findMaximumBloomDay(int flowers[]){
        int max = Integer.MIN_VALUE;
        for (int i : flowers) {
            if(max < i){
                max = i;
            }
        }
        return max;

        // TC -> O(n)
        // Sc -> O(1)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int flowers[] = new int[n];
        int m = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            flowers[i] = sc.nextInt();
        }
        // System.out.println("Minimum number of days required = " + findMinimumDaysBrute(flowers, m, k));
        System.out.println("Minimum number of days required = " + findMinimumDaysOptimal(flowers, m, k));
        sc.close(); 
    }
}