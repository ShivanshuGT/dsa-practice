import java.util.Scanner;

public class NthRootOfInteger {

    private static int nthRootBrute(int n, int m){
        for (int i = 1; i <= m; i++) {
            int result = comparePowerWithM(i, n, m);
            if(result == 0){
                return i;
            }else if(result == -1){
                break;
            }
        }
        return -1;

        // TC -> O(m x n)
        // SC -> O(1)
    }

    private static int nthRootOptimal(int n, int m){
        int beg = 1;
        int end = m;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int result = comparePowerWithM(mid, n, m);
            if(result == 0){
                // nth power of mid is equal to m
                return mid;
            }else if(result == -1){
                // nth power of mid is greater than m
                end = mid - 1;
            }else{
                // nth power of mid is lesser than m
                beg = mid + 1;
            }
        }

        return -1;
        // TC -> O(logm x n)
        // SC -> O(1)
    }

    private static int comparePowerWithM(int i, int n, int m){
        int resultant = 1;
        for (int j = 1; j <= n; j++) {
            resultant *= i;
            if(resultant > m){
                return -1;
            }
        }
        if(resultant == m){
            return 0;
        }
        return 1;

        // TC -> O(n)
        // SC -> O(1)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int number = sc.nextInt();
        // System.out.println(nthRootBrute(n, number));
        System.out.println(nthRootBrute(n, number));
        sc.close();
    }
}
