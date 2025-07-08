import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RearrangeElementsBySign {
    
    public static void rearrangeElementsBySign(int arr[]){
        int n = arr.length;
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(arr[i] >= 0){
                pos.add(arr[i]);
            }else{
                neg.add(arr[i]);
            }
        }

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < pos.size() && j < neg.size()) {
            arr[k] = pos.get(i);
            i+=1;
            k+=1;
            arr[k] = neg.get(j);
            j+=1;
            k+=1;
        }

        while (i < pos.size()) {
            arr[k] = pos.get(i);
            i+=1;
            k+=1;
        }

        while (j < neg.size()) {
            arr[k] = neg.get(j);
            j+=1;
            k+=1;
        }

        // TC -> O(n + n)
        // SC -> O(n)
    }

    public static int[] rearrangeElementsBySignIfNegEqualsPos(int arr[]){
        int n = arr.length;
        int pos_start = 0;
        int neg_start = 1;
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            if(arr[i] >= 0){
                ans[pos_start] = arr[i];
                pos_start += 2;
            }else{
                ans[neg_start] = arr[i];
                neg_start += 2;
            }
        }
        return ans;

        // TC -> O(n)
        // SC -> O(n)
    }
    public static void main(String[] args) {

        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // rearrangeElementsBySign(arr);
        // for (int i = 0; i < n; i++) {
        //     System.out.print(arr[i] + " ");
        // }

        int ans[] = rearrangeElementsBySignIfNegEqualsPos(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        sc.close();
    }
}
