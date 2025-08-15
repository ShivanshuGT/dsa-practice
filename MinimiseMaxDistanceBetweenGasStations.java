import java.util.AbstractMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimiseMaxDistanceBetweenGasStations {

    private static double findMinimumValueOfMaximumDistanceBetweenStationsBrute(int arr[], int gasStations){
        int n = arr.length;
        double hasNew[] = new double[n-1];
        for (int i = 1; i <= gasStations; i++) {

            double maxValue = Integer.MIN_VALUE;
            int maxIndex = -1;
            for (int j = 0; j < n-1; j++) {
                double distance = arr[j+1] - arr[j];
                double length = distance/(hasNew[j] + 1);

                if(maxValue < length){
                    maxValue = length;
                    maxIndex = j;
                }
            }
            hasNew[maxIndex] = hasNew[maxIndex] + 1;
            
        }

        // again iterating to find the max value after all the new stations has been placed
        double ans = -1;
        for (int i = 0; i < n-1; i++) {
            double length = (arr[i+1] - arr[i])/(hasNew[i] + 1);
            if(ans < length){
                ans = length;
            }
        }
        return ans;

        // TC -> O(K x n) + O(n) where K = no of gas stations
        // SC -> O(n)
    }

    private static double findMinimumValueOfMaximumDistanceBetweenStationsBetter(int arr[], int gasStations){
        int n = arr.length;
        double hasNew[] = new double[n-1];
        PriorityQueue<Map.Entry<Double, Integer>> pq =
            new PriorityQueue<>((a, b) -> Double.compare(b.getKey(), a.getKey()));

        for (int i = 0; i < n-1; i++) {
            pq.add(new AbstractMap.SimpleEntry<>((double)(arr[i+1] - arr[i]), i));
        }
        
        for (int i = 1; i <= gasStations; i++) {
            Map.Entry<Double, Integer> entry = pq.poll();
            int index = entry.getValue();
            hasNew[index] = hasNew[index] + 1;
            double length = (arr[index+1] - arr[index]) / (hasNew[index] + 1);
            pq.add(new AbstractMap.SimpleEntry<>(length, index));
        }

        return pq.poll().getKey();

        // TC -> O(n x log(n)) + O(K x log(n))
        // SC -> 2 x O(n)
        // where K is the number of gas Stations
    }

    private static boolean count(int arr[], double maxDistance, int stations){
        int result = 0;
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            if(result > stations){
                return false;
            }
            int div =  (int) ((arr[i+1] - arr[i]) / maxDistance);
            if((arr[i+1] - arr[i]) == div * maxDistance){
                // it was an exact divison
                div = div - 1;
            }
            result += div;
        }
        if(result > stations){
            return false;
        }else{
            return true;
        }

        // TC -> O(n)
        // SC -> O(1)
    }

    private static double findMaxOfConsecutiveDistances(int arr[]){
        int n = arr.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n-1; i++) {
            if(result < (arr[i+1] - arr[i])){
                result = arr[i+1] - arr[i];
            }
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static double findMinimumValueOfMaximumDistanceBetweenStationsOptimal(int arr[], int gasStations){
        double beg = 0;
        double end = findMaxOfConsecutiveDistances(arr);
        double significantDifference = Math.pow(10, -6);
        double ans = -1;
        while ((end - beg) > significantDifference) {
            double mid = (beg + end) / 2;
            boolean countAtMid = count(arr, mid, gasStations);
            if(countAtMid){
                ans = mid;
                end = mid;
            }else{
                beg = mid;
            }
        }

        return ans;

        // TC -> O(log(range)) x O(n) where range = max(consecutive difference between given gas stations)
        // SC -> O(1)
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int gasStations = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findMinimumValueOfMaximumDistanceBetweenStationsBrute(arr, gasStations));
        System.out.println(findMinimumValueOfMaximumDistanceBetweenStationsBetter(arr, gasStations));
        System.out.println(findMinimumValueOfMaximumDistanceBetweenStationsOptimal(arr, gasStations));
        sc.close();
    }
}
