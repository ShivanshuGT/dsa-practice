import java.util.Scanner;

public class BestTimeToBuyAndSellAStock {

    public static void bestTimeToBuyAndSellAStockOptimal(int arr[]){
        int n =  arr.length;
        int result = 0;
        int buy = 0;
        int sell = 0;
        int min = arr[0];
        int min_index = 0;
        for (int i = 1; i < n; i++) {
            if(arr[i] - min > result){
                result = arr[i] - min;
                sell = i;
                buy = min_index;
            }
            if(arr[i] < min){
                min = arr[i];
                min_index = i;
            }
        }

        System.out.println("You should buy the stock at index "+buy+" and sell at index "+sell + " . You will make the maximum profit of " + result+ " units.");
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
        bestTimeToBuyAndSellAStockOptimal(arr);
        sc.close();
    }
}
