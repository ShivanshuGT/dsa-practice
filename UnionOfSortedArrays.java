import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class UnionOfSortedArrays {

    public static List<Integer> unionOfSortedArraysBrute(int arr1[], int arr2[]){
        
        int n1 = arr1.length;
        int n2 = arr2.length;

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            set.add(arr1[i]);
        }

        for (int i = 0; i < n2; i++) {
            set.add(arr2[i]);
        }

        for(int element : set){
            result.add(element);
        }

        // TC -> O(n1log(n1)) + O(n2log(n2)) + O(x)
        // SC -> O(x) where max value of x will be max(n1,n2)

        return result;
    }

    public static List<Integer> unionOfSortedArraysOptimal(int arr1[], int arr2[]){
        int n1 = arr1.length;
        int n2 = arr2.length;

        int i = 0;
        int j = 0;

        List<Integer> result = new ArrayList<>();

        while(i < n1 && j < n2){

            if(arr1[i] < arr2[j]){
                if(result.size() == 0 || result.get(result.size() - 1)!= arr1[i]){
                    result.add(arr1[i]);
                }
                i+=1;
            }else{
                if(result.size() == 0 || result.get(result.size() -1) != arr2[j]){
                    result.add(arr2[j]);
                }
                j+=1;
            }
        }

        while (i< n1) {
            if(result.size() == 0 || result.get(result.size() -1) != arr1[i]){
                result.add(arr1[i]);
            }
            i+=1;

        }
        while (j < n2) {
            if(result.size() == 0 || result.get(result.size() -1) != arr2[j]){
                result.add(arr2[j]);
            }
            j+=1;
        }

        // TC -> O(n)
        // SC -> O(1)

        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        // List<Integer> result = unionOfSortedArraysBrute(arr1, arr2);
        List<Integer> result = unionOfSortedArraysOptimal(arr1, arr2);

        for (Integer element : result) {
            System.out.print(element + " ");
        }

        sc.close();
    }
}
