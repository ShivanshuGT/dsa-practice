import java.util.HashMap;
import java.util.Scanner;

public class HashingUnorderedMap {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            int number = sc.nextInt();
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        int query = sc.nextInt();
        for (int i = 0; i < query; i++) {
            System.out.println(map.getOrDefault(sc.nextInt(), 0));
        }

        sc.close();
    }

}

