import java.util.Scanner;

public class MaxConsecutiveOnes {

    public static int maxConsecutiveOnes(int arr[]){
        int n = arr.length;
        int result = 0;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i] == 1){
                counter +=1;
                if(counter > result){
                    result = counter;
                }
            }else{
                counter = 0;
            }
        }
        return result;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++) {
           arr[i] = sc.nextInt(); 
        }

        System.out.print(maxConsecutiveOnes(arr));

        sc.close();
    }
}
