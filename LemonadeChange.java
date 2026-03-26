import java.util.Scanner;

public class LemonadeChange {

    private static boolean canServe(int[] arr){
        int n = arr.length;
        int five = 0;
        int ten = 0;

        for (int i = 0; i < n; i++) {
            if(arr[i] == 5){
                five += 1;
            } else if(arr[i] == 10){
                if(five > 0){
                    ten += 1;
                    five -= 1;
                }else{
                    return false;
                }
            }else{
                if((ten > 0) && (five > 0)){
                    ten -= 1;
                    five -= 1;
                }else if((five >= 3)){
                    five -= 3;
                }else{
                    return false;
                }
            }
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
        System.out.println(canServe(arr));
        sc.close();
    }
}
