import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NumberOfIslands {
    static class Pair{
        int row;
        int col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    private static void traverse(int row, int col, int[][] visited, int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        visited[row][col] = 1;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            row = pair.row;
            col = pair.col;
            // traverse the neighbours
            for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int neighbourRow = row + delRow;
                    int neighbourCol = col + delCol;
                    if(neighbourRow >=0 && neighbourRow < n && neighbourCol >=0 && neighbourCol < m){
                        // it is a valid neighbour in the matrix
                        if(matrix[neighbourRow][neighbourCol] == 1 && visited[neighbourRow][neighbourCol] !=1){
                            // it is a land and not visited
                            queue.add(new Pair(neighbourRow, neighbourCol));
                            visited[neighbourRow][neighbourCol] = 1;
                        }
                    }
                }
            }
        }
        // SC -> O(nxm)
    }

    private static int findNumberOfIslands(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int numberOfIslands = 0;

        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1 && visited[i][j]!=1){
                    numberOfIslands += 1;
                    traverse(i, j, visited, matrix);
                }
            }
        }
        return numberOfIslands;
        // SC -> O(nxm)
        // TC -> O(nxm)
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
        System.out.println(findNumberOfIslands(matrix));
        sc.close();
    }
}
