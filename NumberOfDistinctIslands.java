import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NumberOfDistinctIslands {

    private static void dfs(int row, int col, int[][] visited, int[][] matrix, int baseRow, int baseCol, List<String> islandList){
        visited[row][col] = 1;
        int n = matrix.length;
        int m = matrix[0].length;

        int[] delRow = {1, -1, 0, 0};
        int[] delCol = {0, 0, 1, -1};
        islandList.add(Integer.toString(row-baseRow) + " " + Integer.toString(col-baseCol));

        // traversing in the four directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];
            if(newRow>=0 && newRow<n && newCol>=0 && newCol<m){
                // it is a valid neighbour
                if(matrix[newRow][newCol] == 1 && visited[newRow][newCol] !=1){
                    // it is a land and not visited yet
                    dfs(newRow, newCol, visited, matrix, baseRow, baseCol, islandList);
                }
            }
        }

    }

    private static int countNumberOfDistinctIslands(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] visited = new int[n][m];
        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1 && visited[i][j] != 1){
                    // it is a land and not visited yet
                    List<String> islandList = new ArrayList<>();
                    dfs(i, j, visited, matrix, i, j, islandList);
                    set.add(islandList);
                }
            }
        }
        return set.size();
        // TC -> O(n x m x log(n x m)) + O(n x m)
        // SC -> O(n x m)
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
        System.out.println(countNumberOfDistinctIslands(matrix));
        sc.close();
    }
}
