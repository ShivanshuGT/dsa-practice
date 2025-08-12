import java.util.Scanner;

public class AllocateBooks {

    private static int numberOfStudentsRequired(int books[], int maxPages){
        int n = books.length;
        int numberOfStudents = 0;
        int i = 0;
        while (i < n) {
            int capacity = maxPages;
            while (i < n && books[i] <= capacity) {
                capacity -= books[i];
                i += 1;
            }
            numberOfStudents += 1;
        }
        return numberOfStudents;

        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMax(int arr[]){
        int result = Integer.MIN_VALUE;
        for (int i : arr) {
            if(result < i){
                result = i;
            }
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findSum(int arr[]){
        int result = 0;
        for (int i : arr) {
            result += i;
        }
        return result;
        // TC -> O(n)
        // SC -> O(1)
    }

    private static int findMinimumValueOfMaximumPagesBrute(int books[], int students){
        int n = books.length;
        if(n < students){
            // when the number of books are less than the number of students
            return -1;
        }
        int minSearchSpaceValue = findMax(books);
        int maxSearchSpaceValue = findSum(books);

        for (int i = minSearchSpaceValue; i <= maxSearchSpaceValue; i++) {
            if(numberOfStudentsRequired(books, i) == students){
                return i;
            }
        }
        return -1;

        // TC -> O(maxSearchSpaceValue - minSearchSpaceValue + 1) x O(n)
        // SC -> O(1)

    }

    private static int findMinimumValueOfMaximumPagesOptimal(int books[], int students){
        int n = books.length;
        if(n < students){
            return -1;
        }

        int beg = findMax(books);
        int end = findSum(books);
        int ans = -1;

        while (beg <= end) {
            int mid = (beg + end) / 2;
            // find number of students required at 'mid' number of pages
            int studentsRequiredAtMid = numberOfStudentsRequired(books, mid);

            if(studentsRequiredAtMid == students){
                ans = mid;
                end = mid - 1;
            }else if(studentsRequiredAtMid > students){
                beg = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;

        // TC -> O(log(maxSearchSpaceValue - minSearchSpaceValue + 1)) x O(n)
        // SC -> O(1)
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int students = sc.nextInt();
        int books[] = new int[n];
        for (int i = 0; i < n; i++) {
            books[i] = sc.nextInt();
        }
        // System.out.println(findMinimumValueOfMaximumPagesBrute(books, students));
        System.out.println(findMinimumValueOfMaximumPagesOptimal(books, students));
        sc.close();
    }
}
