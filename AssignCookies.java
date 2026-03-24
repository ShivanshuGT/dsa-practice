import java.util.Arrays;
import java.util.Scanner;

public class AssignCookies {


    private static int assignCookies(int[] greedFactor, int[] cookies){
        int n = greedFactor.length;
        int m = cookies.length;

        Arrays.sort(greedFactor);
        Arrays.sort(cookies);

        int greed = 0;
        int cookie = 0;

        while ((greed < n) && (cookie < m)) {
            if(greedFactor[greed] <= cookies[cookie]){
                // this can be assigned
                greed += 1;
            }
            cookie += 1;
        }
        return greed;
        // TC -> O(nlogn) + O(mlogm) + O(min(n, m))
        // SC -> O(1)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] greedFactor = new int[n];
        int[] cookies = new int[m];

        for (int i = 0; i < n; i++) {
            greedFactor[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            cookies[i] = sc.nextInt();
        }
        System.out.println(assignCookies(greedFactor, cookies));
        sc.close();
    }
}
