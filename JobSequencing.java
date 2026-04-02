import java.util.Arrays;
import java.util.Scanner;

public class JobSequencing {

    static class Job{
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit){
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    private static int[] findMaxProfit(Job[] jobs){
        int n = jobs.length;
        int count = 0;
        int totalProfit = 0;

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // find the max of deadline to get the length of hash array
        int maxDeadline = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, jobs[i].deadline);
        }

        int[] hash = new int[maxDeadline + 1];

        // init the hash array with -1 value
        for (int j = 0; j <= maxDeadline; j++) {
            hash[j] = -1;
        }

        for (int i = 0; i < n; i++) {
            
            int deadlineOfJob = jobs[i].deadline;
            for (int j = deadlineOfJob; j >= 0; j--) {
                if(hash[j] == -1){
                    // its still vacant
                    // we can do this job
                    hash[j] = jobs[i].id;
                    System.out.println(jobs[i].id);
                    totalProfit += jobs[i].profit;
                    count += 1;
                    break;
                }
            }

        }

        int[] result = {count, totalProfit};
        return result;
        // TC -> O(n x log(n)) + O(n x max(deadline))
        // SC -> O(max(deadline))
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] deadline = new int[n];
        int[] profit = new int[n];

        for (int i = 0; i < n; i++) {
            deadline[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            profit[i] = sc.nextInt();
        }

        Job[] jobs= new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(i, deadline[i], profit[i]);
        }

        int[] ans = findMaxProfit(jobs);
        System.out.println(ans[0] + " jobs with total profit of " +  ans[1]);
        sc.close();
    }
}
