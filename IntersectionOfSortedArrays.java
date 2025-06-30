import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntersectionOfSortedArrays {

    public static List<Integer> intersectionOfSortedArraysBrute(int arr1[], int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;

        int visited[] = new int[n2];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if((arr1[i]== arr2[j]) && (visited[j] ==0)) {
                    result.add(arr1[i]);
                    visited[j] = 1;
                    break;
                }

                if(arr2[j] > arr1[i]){
                    break; //since the array is sorted
                }
            }
        }
        return result;
    }

    public static List<Integer> intersectionOfSortedArraysOptimal(int arr1[], int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;
        int i =0;
        int j = 0;

        List<Integer> result = new ArrayList<>();

        while (i< n1 && j < n2) {
            if(arr1[i] == arr2[j]){
                result.add(arr1[i]);
                i+=1;
                j+=1;
            }
            else if( arr1[i] < arr2[j]){
                i+=1;
            }else{
                j+=1;
            }
        }

        // TC -> O(n1  + n2)
        // SC -> O(1)
        return result;
    }
    

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int arr1[] = new int[n1];
        int arr2[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }

        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }

        // List<Integer> result = intersectionOfSortedArraysBrute(arr1, arr2);
        List<Integer> result = intersectionOfSortedArraysOptimal(arr1, arr2);

        for (Integer element : result) {
            System.out.print(element + " ");
        }
        // TC -> O(n1 x n2)
        // SC -> O(n2) -> for maintaining the visited array

        sc.close();
    }
}
