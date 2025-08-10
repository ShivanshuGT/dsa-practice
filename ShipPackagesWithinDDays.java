import java.util.Scanner;

public class ShipPackagesWithinDDays {

    private static int calculateSum(int arr[]){
        int result = 0;
        for (int i : arr) {
            result += i;
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMaxElement(int arr[]){
        int result = Integer.MIN_VALUE;
        for (int i : arr) {
            if(result < i){
                result = i;
            }
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static boolean possibleToShipPackagesWithinDDaysWithGivenCapacity(int weights[], int days, int capacity){
        int n = weights.length;
        int daysTook = 0;
        int i = 0;
        while (i < n) {
            int availableCapacityPerDay = capacity;
            while ( i < n && weights[i] <= availableCapacityPerDay) {
                availableCapacityPerDay -= weights[i];
                i += 1;
            }
            daysTook += 1;
        }
        if(daysTook <= days){
            return true;
        }
        return false;

        // TC -> O(n)
        // SC -> O(1)

    }

    private static int findMinimumCapacityBrute(int weights[], int days){
        int minSearchSpaceValue = findMaxElement(weights);
        int maxSearchSpaceValue = calculateSum(weights);
        for (int i = minSearchSpaceValue; i <= maxSearchSpaceValue; i++) {
            if(possibleToShipPackagesWithinDDaysWithGivenCapacity(weights, days, i)){
                return i;
            }
        }

        return -1;
        // TC -> O(sum - maxElement + 1) x O(n)
        // SC -> O(1)
    }

    private static int findMinimumCapacityOptimal(int weights[], int days){
        int beg = findMaxElement(weights);
        int end = calculateSum(weights);
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(possibleToShipPackagesWithinDDaysWithGivenCapacity(weights, days, mid)){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(sum - maxElement + 1)) x O(n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int days = sc.nextInt();
        int weights[] = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }
        // System.out.println(findMinimumCapacityBrute(weights, days));
        System.out.println(findMinimumCapacityOptimal(weights, days));
        sc.close();
    }
}
