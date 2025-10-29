import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MinimumMultiplicationsToReachEnd {

    static class QueueEntry{
        int numberOfMultiplications;
        int number;

        QueueEntry(int numberOfMultiplications, int number){
            this.numberOfMultiplications = numberOfMultiplications;
            this.number = number;
        }
    }

    private static int findMinimumNumberOfMultiplications(int[] arr, int start, int end){
        int[] multiplications = new int[100000];
        for (int i = 0; i < 100000; i++) {
            multiplications[i] = Integer.MAX_VALUE;
        }

        multiplications[start] = 0;

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(0, start)); 

        int n = arr.length;
        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int currentNumberOfMultiplications = entry.numberOfMultiplications;
            int currentNumber = entry.number;
            if(currentNumber == end){
                return currentNumberOfMultiplications;
            }

            // form all the numbers that are possible
            for (int i = 0; i < n; i++) {
                int newNumber = (currentNumber * arr[i]) % 100000;
                if(currentNumberOfMultiplications + 1 < multiplications[newNumber]){
                    multiplications[newNumber] = currentNumberOfMultiplications + 1;
                    queue.add(new QueueEntry(currentNumberOfMultiplications+1, newNumber));
                }
            }

        }
        return -1;
        // TC -> O(n x 100000)
        // SC -> O(100000)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        System.out.println(findMinimumNumberOfMultiplications(arr, start, end));
        sc.close();
    } 
}
