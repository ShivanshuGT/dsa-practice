import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NumberOfEnclaves {

    static class Pair {
        int row;
        int col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
        
    }

    private static int countNumberOfEnclaves(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();

        // traversing first row
        for (int i = 0; i < m; i++) {
            if(matrix[0][i] == 1){
                queue.add(new Pair(0, i));
                visited[0][i] = 1;
            }
        }
        // traversing last row
        for (int i = 0; i < m; i++) {
            if(matrix[n-1][i] == 1){
                queue.add(new Pair(n-1, i));
                visited[n-1][i] = 1;
            }
        }
        // traversing first col
        for (int i = 0; i < n; i++) {
            if(matrix[i][0] == 1){
                queue.add(new Pair(i, 0));
                visited[i][0] = 1;
            }
        }
        // traversing last col
        for (int i = 0; i < n; i++) {
            if(matrix[i][m-1] == 1){
                queue.add(new Pair(i, m-1));
                visited[i][m-1] = 1;
            }
        }

        while (!queue.isEmpty()) {
            Pair entry = queue.poll();
            int row = entry.row;
            int col = entry.col;

            int[] delRow = {-1, 1, 0, 0};
            int[] delCol = {0, 0, 1, -1};
            // traversing in 4 directions from the current cell
            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                if(newRow>=0 && newRow<n && newCol>=0 && newCol<m){
                    // it is a valid neighbour
                    if(matrix[newRow][newCol] == 1 && visited[newRow][newCol] != 1){
                        // it is a land and not visited yet
                        queue.add(new Pair(newRow, newCol));
                        visited[newRow][newCol] = 1;
                    }
                }
            }
        }

        int numberOfEnclaves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1 && visited[i][j] != 1){
                    // it is a land and not visited yet
                    numberOfEnclaves += 1;
                }
            }
        }
        return numberOfEnclaves;
        // TC -> O(nxm)
        // SC -> O(nxm)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int martix[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                martix[i][j] = sc.nextInt();
            }
        }
        System.out.println(countNumberOfEnclaves(martix));
        sc.close();
    }
}
