import java.util.Scanner;

public class KokoEatingBananas {

    private static int calculateTime(int heaps[], int speed){
        int result = 0;
        for (int i : heaps) {
            result += Math.ceilDiv(i, speed);
        }
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMaxHeapSize(int heaps[]){
        int result = -1;
        for (int i : heaps) {
            result = Math.max(result, i);
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findKBrute(int heaps[], int hours){
        int maxHeapSize = findMaxHeapSize(heaps);

        for (int i = 1; i <= maxHeapSize; i++) {
            int totalHours = calculateTime(heaps, i);
            if(totalHours <= hours){
                return i;
            }
        }
        return -1;

        // TC -> O(maxElement x n)
        // SC -> O(1)
    }

    private static int findKOptimal(int heaps[], int allowedHours){
        int beg = 1;
        int end = findMaxHeapSize(heaps);
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int totalHours = calculateTime(heaps, mid);

            if(totalHours <= allowedHours){
                result = mid;
                end = mid - 1;
            }else{
                beg = mid + 1;
            }
        }
        return result;

        // TC -> O(log(maxElement) x n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int hours = sc.nextInt();
        int heaps[] = new int[n];
        for (int i = 0; i < n; i++) {
            heaps[i] = sc.nextInt();
        }
        // System.out.println("Koko should eat at least " + findKBrute(heaps, hours) + " bananas per hour.");
        System.out.println("Koko should eat at least " + findKOptimal(heaps, hours) + " bananas per hour.");
        sc.close();
    }
}
