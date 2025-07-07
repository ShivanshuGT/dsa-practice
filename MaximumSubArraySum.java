import java.util.Scanner;

public class MaximumSubArraySum {

    public static void findMaxSubArraySumBrute(int arr[]){
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int beg = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            
            for (int j = i; j < n; j++) {

                int sum  = 0;
                
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if(sum > max){
                    max = sum;
                    beg = i;
                    end = j;
                }
            }
        }

        System.out.println("Maximum sub array sum is "+ max +" with indices ["+beg+","+end + "]");
        // TC -> O(n x n x n)
        // SC -> O(1)
    }
    
    public static void findMaxSubArraySumBetter(int arr[]){
        int n = arr.length;
        int beg = -1;
        int end = -1;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if(sum > max){
                    max = sum;
                    beg = i;
                    end = j;
                }
            }
        }
        System.out.println("Maximum sub array sum is "+ max +" with indices ["+beg+","+end + "]");
        // TC -> O(n x n)
        // SC -> O(1)
    }
    
    public static void findMaxSubArraySumOptimal(int arr[]){
        int n = arr.length;
        int beg = -1;
        int end = -1;
        int start = 0;
        int i = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while (i < n) {
            if(sum == 0){
                start = i;
            }
            sum += arr[i];
            if(sum > max){
                max = sum;
                beg = start;
                end = i;
            }
            if(sum < 0){
                sum = 0;
            }
            i+=1;
        }
        
        System.out.println("Maximum sub array sum is "+ max +" with indices ["+beg+","+end + "]");
        // SC -> O(n)
        // TC -> O(1)
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // findMaxSubArraySumBrute(arr);
        // findMaxSubArraySumBetter(arr);
        findMaxSubArraySumOptimal(arr);
        sc.close();
    }
}
