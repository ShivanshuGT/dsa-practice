import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {

    static class Item{
        int weight;
        int value;

        Item(int value, int weight){
            this.value = value;
            this.weight = weight;    
        }
    }

    private static double findMaxValue(Item[] items, int w){

        // sort on the basis of val/weight in descending order

        Arrays.sort(items, (a, b) -> (b.value/b.weight) - (a.value/a.weight));

        int answer = 0;

        for (Item item : items) {
            if(item.weight <= w){
                answer += item.value;
                w -= item.weight;
            }else{
                answer += (item.value / item.weight) * w;
                break;
            }
        }
        return answer;
        // TC -> O(n x log(n)) + O(n)
        // SC -> O(1)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(sc.nextInt(), sc.nextInt());
        }
        System.out.println(findMaxValue(items, w));
        sc.close();
    }
}
