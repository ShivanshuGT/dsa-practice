import java.util.Scanner;
import java.util.Stack;

class Pair{
    int value;
    int ind;

    Pair(int value, int ind){
        this.value = value;
        this.ind = ind;
    }
}

public class StockSpanner {

    

    private Stack<Pair> stack;
    private int ind;

    StockSpanner(){
        this.stack = new Stack<>();
        this.ind = -1;
    }

    public int next(int x){
        this.ind += 1;

        while ((!this.stack.isEmpty()) && (this.stack.peek().value <= x)) {
            this.stack.pop();
        }

        int ans = this.ind - (stack.isEmpty() ? -1 : stack.peek().ind);
        this.stack.push(new Pair(x, this.ind));
        return ans;
    }
    // TC -> O(2 x n)
    // SC -> O(n)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StockSpanner stockSpanner = new StockSpanner();
        int queries = sc.nextInt();
        for (int i = 0; i < queries; i++) {
            System.out.println(stockSpanner.next(sc.nextInt()));
        }
        sc.close();
    }
}
