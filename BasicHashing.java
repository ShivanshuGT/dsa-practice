import java.util.Scanner;

public class BasicHashing {

    


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // int arr[] = new int[n]; // n size array is initialized with 0 value
        // for(int i = 0; i < n; i++)
        // arr[i] = sc.nextInt();

        // // calculate the frequency of each number in the array

        // int hashArray[] = new int[13]; // assuming that maz number that can come in the input array is 12
        
        // for(int i = 0; i < n ; i++){
        //     hashArray[arr[i]] += 1;
        // }
        // int querySize = sc.nextInt();
        // for(int i = 0; i< querySize; i++)
        // System.out.println(hashArray[sc.nextInt()]);

        String input = sc.nextLine();
        char arr[] = input.toCharArray();

        int charHashArr[] = new int[256];
        for(int i = 0; i< input.length(); i++)
        charHashArr[arr[i]] += 1;
        int querySize = sc.nextInt();
        for(int i = 0; i< querySize; i++)
        System.out.println(charHashArr[sc.next().charAt(0)]);


    }
}
