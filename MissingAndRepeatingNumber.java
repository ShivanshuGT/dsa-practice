import java.util.Scanner;

public class MissingAndRepeatingNumber {
    

    private static int[] findMissingAndRepeatingNumberBrute(int arr[]){
        int n = arr.length;
        int missing = -1;
        int repeating = -1;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if(arr[j] == i){
                    cnt += 1;
                }
            }
            if(cnt == 2){
                repeating = i;
            }
            if(cnt == 0){
                missing = i;
            }
            if(repeating != -1 && missing != -1){
                break;
            }
        }
        int result[] = new int[2];
        result[0] = missing;
        result[1] = repeating;
        return result;

        // TC -> O(n x n)
        // SC -> O(1)
    }

    private static int[] findMissingAndRepeatingNumberBetter(int arr[]){
        int n = arr.length;
        int hashArray[] = new int[n+1];
        for (int i = 0; i < n; i++) {
            hashArray[arr[i]] += 1;
        }
        int repeating = -1;
        int missing = -1;
        for (int i = 1; i <= n; i++) {
            if(hashArray[i] == 0){
                missing = i;
            }
            if(hashArray[i] == 2){
                repeating = i;
            }

            if(repeating != -1 && missing != -1){
                break;
            }
        }

        int result[] = new int[2];
        result[0] = missing;
        result[1] = repeating;
        return result;

    }

    private static int[] findMissingAndRepeatingNumberOptimalOne(int arr[]){
        int n = arr.length;
        int sum = 0;
        int sums = 0;
        int sn = n*(n+1)/2;
        int sn2 = n*(n+1)*(2*n+1)/6;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sums += arr[i] * arr[i];
        }
        int repeating = ((sum-sn) + (sums-sn2)/(sum-sn))/2;
        int missing = sn - sum + repeating;
        int result[] = new int[2];
        result[0] = missing;
        result[1] = repeating;
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int[] findMissingAndRepeatingNumberOptimalTwo(int arr[]){
        int n = arr.length;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor = xor ^ arr[i];
            xor = xor ^ (i+1);
        }

        int bitNo = 0;
        // find the bit number which is set
        while(true){
            if( (xor & (1<<bitNo)) != 0){
                break;
            }
            bitNo += 1;
        }
        int one = 0;
        int zero = 0;

        // segregate elements of array in zero and one club
        for (int i = 0; i < n; i++) {
            if((arr[i] & (1<<bitNo)) != 0){
                // element belongs to club 1
                one = one ^ arr[i];
            }else{
                // element belongs to club 0
                zero = zero ^ arr[i];
            }
        }

        // segregate numbers in zero and one club
        for (int i = 1; i <= n; i++) {
            if((i & (1<<bitNo)) !=0){
                // number belongs to club 1
                one = one ^ i;
            }else{
                // number belongs to club 0
                zero = zero ^ i;
            }
        }

        //verigying the result by manual checking
        int cnt1 = 0;
        int cnt0 = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == one){
                cnt1 += 1;
            }
            if(arr[i] == zero){
                cnt0 += 1;
            }
        }
        int repeating = -1;
        int missing = -1;
        if(cnt0 == 0){
            missing = zero;
            repeating = one;
        }else{
            missing = one;
            repeating = zero;
        }

        int result[] = new int[2];
        result[0] = missing;
        result[1] = repeating;
        return result;

        // TC -> O(n)
        // SC -> O(1)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // int result[] = findMissingAndRepeatingNumberBrute(arr);
        // int result[] = findMissingAndRepeatingNumberBetter(arr);
        // int result[] = findMissingAndRepeatingNumberOptimalOne(arr);
        int result[] = findMissingAndRepeatingNumberOptimalTwo(arr);
        System.out.println("Missing Number is " + result[0]); 
        System.out.println("Repeating Number is " + result[1]); 
        sc.close();
    }
}
