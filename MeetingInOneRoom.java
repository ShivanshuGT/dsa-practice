import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingInOneRoom {

    static class Meeting{
        int id;
        int start;
        int end;

        Meeting(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }

    private static void scheduleMeetings(int[] start, int[] end){
        int n = start.length;

        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(i+1, start[i], end[i]);
        }

        // sort the meetings according to their end time
        Arrays.sort(meetings, (a, b) -> a.end - b.end);

        int count = 1;
        int freeTime = meetings[0].end;
        List<Integer> schedule = new ArrayList<>();
        schedule.add(meetings[0].id);

        for (int i = 1; i < n; i++) {
            if(freeTime < meetings[i].start){
                // we can take this meeting
                count += 1;
                freeTime = meetings[i].end;
                schedule.add(meetings[i].id);
            }
        }

        System.out.println(count + " meetings can be performed with the following schedule: " + schedule.toString() );
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            end[i] = sc.nextInt();
        }
        scheduleMeetings(start, end);
        sc.close();
    }
}
