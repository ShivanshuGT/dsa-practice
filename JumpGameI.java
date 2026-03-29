import java.util.Scanner;

public class JumpGameI {

    private static boolean canReachEnd(int[] arr){
        int n = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if(i > maxIndex){
                return false;
            }
            maxIndex = Math.max(maxIndex, i + arr[i]);
        }
        return true;
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
        System.out.println(canReachEnd(arr));
        sc.close();
    }
}
