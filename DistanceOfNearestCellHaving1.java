import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DistanceOfNearestCellHaving1 {

    private static class Pair{
        int row;
        int col;
        int step;
        Pair(int row, int col, int step){
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }

    private static int[][] findNearestCellHaving1(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] visited = new int[n][m];
        int[][] result = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1){
                    // these are sources to start
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 1;
                } 
            }
        }

        while (!queue.isEmpty()) {
            Pair entry = queue.poll();
            int row = entry.row;
            int col = entry.col;
            int step = entry.step;
            result[row][col] = step;

            int[] delRow = {-1, 1, 0, 0};
            int[] delCol = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if(newRow>=0 && newRow<n && newCol>=0 && newCol<m){
                    // it is a valid neighbour
                    if(visited[newRow][newCol] != 1){
                        queue.add(new Pair(newRow, newCol, step+1));
                        visited[newRow][newCol] = 1;
                    }
                }

            }

        }
        return result;
        // TC -> O(nxm)
        // SC -> O(nxm)
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
        int[][] result = findNearestCellHaving1(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
