import java.util.Scanner;
import java.util.TreeMap;

public class HashingOrderedMap {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        String inp = sc.nextLine();
        int length = inp.length();
        for(int i = 0; i < length; i++){
            char input = inp.charAt(i);
            map.put(input, map.getOrDefault(input, 0) + 1);
        }

        Character highestKey  = null;
        Character lowestKey = null;
        int highestFreq = 0;
        int lowestFreq  = Integer.MAX_VALUE;
        for(Character key : map.keySet()){
            
            if(map.get(key) > highestFreq){
                highestKey = key;
                highestFreq = map.get(key);
            }
            if(map.get(key) < lowestFreq){
                lowestKey = key;
                lowestFreq = map.get(key);
            }
            System.out.println(key + " -> " + map.getOrDefault(key, 0));
        }
        System.out.println("Highest frequency pair : " +highestKey + " -> " + map.get(highestKey));
        System.out.println("Lowest frequency pair : " +lowestKey + " -> " + lowestFreq);

        int query = sc.nextInt();
        for (int i = 0; i < query; i++) {
            System.out.println(map.getOrDefault(sc.next().charAt(0), 0));
        }

        sc.close();
    }
}
