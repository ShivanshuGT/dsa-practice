import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RottenOranges {

    static class Pair {
        int row;
        int col;
        int time;

        Pair(int row, int col, int time){
            this.row = row;
            this.col = col;
            this.time = time;
        }
        
    }

    private static int findTimeToRotOranges(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] visited = new int[n][m];
        int initialNumberOfFreshOranges = 0;
        int answer = 0;

        Queue<Pair> queue = new LinkedList<>();
        // traversing the matrix to find which all oranges are rotten and pushing them in 
        // the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 2){
                    // these oranges are rotten initially
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 2;
                }
                if(matrix[i][j] == 1){
                    // these oranges are fresh initially
                    initialNumberOfFreshOranges += 1;
                }

            }
        }

        int[] delRow = {1, -1, 0, 0};
        int[] delCol = {0, 0, 1, -1};
        int numberOfOrangesRotten = 0;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int row = pair.row;
            int col = pair.col;
            int time = pair.time;
            answer = Math.max(answer, time);
            // checking all the neighbours and rotting them
            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                if(newRow >= 0 && newRow<n && newCol >= 0 && newCol<m){
                    // it is a valid neighbour
                    if(matrix[newRow][newCol] == 1 && visited[newRow][newCol] != 2){
                        // it is a fresh orange and has not been rotten earlier
                        queue.add(new Pair(newRow, newCol, time+1));
                        visited[newRow][newCol] = 2;
                        numberOfOrangesRotten += 1;
                    }
                }
            }
        }

        if(initialNumberOfFreshOranges != numberOfOrangesRotten){
            // all the fresh oranges have not been rotten
            return -1;
        }
        return answer;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        System.out.println(findTimeToRotOranges(matrix));
        sc.close();
    }
}
