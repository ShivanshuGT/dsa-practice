import java.util.Scanner;

public class Candy {

    private static int calcTotalCandyBrute(int[] arr){
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n-1] = 1;

        // left pass
        for (int i = 1; i < n; i++) {
            if(arr[i] > arr[i-1]){
                left[i] = left[i-1] + 1;
            }else{
                left[i] = 1;
            }
        }

        // right pass
        for (int i = n-2; i >= 0; i--) {
            if(arr[i] > arr[i+1]){
                right[i] = right[i+1] + 1;
            }else{
                right[i] = 1;
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += Math.max(left[i], right[i]);
        }
        return answer;
        // TC -> O(3 x n)
        // SC -> O(2 x n)
    }

    private static int calcTotalCandyBetter(int[] arr){
        int n = arr.length;
        int[] left = new int[n];
        left[0] = 1;

        //left pass
        for (int i = 1; i < n; i++) {
            if(arr[i] > arr[i-1]){
                left[i] = left[i-1] + 1;
            }else{
                left[i] = 1;
            }
        }

        int right = 1;
        int cur = 1;
        int answer = Math.max(1, left[n-1]);

        for (int i = n-2; i >= 0; i--) {
            if(arr[i] > arr[i+1]){
                cur = right + 1;
                right = cur;
            }else{
                cur = 1;
            }
            answer += Math.max(cur, left[i]);
        }
        return answer;
        // TC -> O(2 x n)
        // SC -> O(n)
    }

    private static int calcTotalCandyOptimal(int[] arr){
        int n = arr.length;

        int answer = 1;
        int i = 1;

        while (i < n) {
            // if its a flat curve
            if(arr[i] == arr[i-1]){
                answer += 1;
                i += 1;
                continue;
            }

            // if its an increasing curve
            int peak = 1;
            while ((i < n) && (arr[i] > arr[i-1])) {
                peak += 1;
                answer += peak;
                i += 1;
            }

            // if its a decreasing curve
            int down = 1;
            while ((i < n) && (arr[i] < arr[i-1])) {
                answer += down;
                down += 1;
                i += 1;
            }

            if(down > peak){
                // adjust the peak
                answer += (down - peak);
            }
            
        }
        return answer;
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
        System.out.println(calcTotalCandyBrute(arr));
        System.out.println(calcTotalCandyBetter(arr));
        System.out.println(calcTotalCandyOptimal(arr));
        sc.close();
    }
}
