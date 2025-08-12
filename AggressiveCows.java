import java.util.Arrays;
import java.util.Scanner;

public class AggressiveCows {

    private static boolean possibleToTieCowsWithGivenMinimumDistance(int arr[], int cows, int d){
        int n = arr.length;
        
        int lastCowTiedAt = arr[0];
        int numberOfCowsTied = 1;
        // it means we tied the first cow at arr[0]

        for (int i = 1; i < n; i++) {
            if(numberOfCowsTied >= cows){
                return true;
            }
            if((arr[i] - lastCowTiedAt)>= d){
                // we can tie the cow at stall i
                lastCowTiedAt = arr[i];
                numberOfCowsTied += 1;
            }

        }

        if(numberOfCowsTied >= cows){
            return true;
        }
        return false;

        // TC -> O(n)
        // SC -> O(1)

    }

    private static int findMaxofMinDistanceBrute(int arr[], int cows){
        int n = arr.length;
        Arrays.sort(arr);
        int minSearchSpaceValue = 1;
        int maxSearchSpaceValue = arr[n-1] - arr[0];
        int ans = -1;
        for (int i = minSearchSpaceValue; i <= maxSearchSpaceValue; i++) {
            if(possibleToTieCowsWithGivenMinimumDistance(arr, cows, i)){
                ans = i;
            }else{
                break;
            }
        }
        return ans;

        // TC -> O(nlogn) + O(maxElement - minElement) x O(n)
        // SC -> O(1)
    }

    private static int findMaxofMinDistanceOptimal(int arr[], int cows){
        int n = arr.length;
        Arrays.sort(arr);

        int beg = 1;
        int end = arr[n-1] - arr[0];
        int ans = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(possibleToTieCowsWithGivenMinimumDistance(arr, cows, mid)){
                ans = mid;
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;

        // TC -> O(nlogn) + O(log(maxElement - minElement)) x O(n)
        // SC -> O(1)
    }


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cows = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // System.out.println("The maximum value of minimum possible distance between the cows is "+findMaxofMinDistanceBrute(arr, cows));
        System.out.println("The maximum value of minimum possible distance between the cows is "+findMaxofMinDistanceOptimal(arr, cows));
        sc.close();
    }
}
