import java.util.Scanner;

public class SqrtUsingBinarySearch {

    private static int findSqrtBrute(int n){
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            if(i*i <= n){
                ans = i;
            }else{
                break;
            }  
        }
        return ans;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findSqrtOptimal(int n){
        int ans = 1;
        int beg = 1;
        int end = n;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if(mid*mid <= n){
                ans = mid;
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;

        // TC -> O(logn)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // System.out.println(findSqrtBrute(n));
        System.out.println(findSqrtOptimal(n));
        sc.close();
    }
}
