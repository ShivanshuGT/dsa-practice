import java.util.Scanner;

public class ReplaceOWithX {

    private static void dfs(int row, int col, String[][] matrix, int[][] visited){
        visited[row][col] = 1;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] delRow = {-1, 1, 0, 0};
        int[] delCol = {0, 0, 1, -1};

        // traversing all the cells in the four directions
        for (int i = 0; i < 4; i++) {
            int newRow = row + delRow[i];
            int newCol = col + delCol[i];

            if(newRow>=0 && newRow<n && newCol>=0 && newCol<m){
                // it is a vlaid neighbour
                if("O".equals(matrix[newRow][newCol]) && visited[newRow][newCol] != 1){
                    // it is an O and not visited yet
                    dfs(newRow, newCol, matrix, visited);
                }
            }
        }
    }

    private static String[][] replaceOWithX(String[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] visited = new int[n][m];

        // iterating over the first row
        for (int i = 0; i < m; i++) {
            if("O".equals(matrix[0][i]) && visited[0][i] != 1){
                // it is an O and not visited yet
                dfs(0, i, matrix, visited);
            }
        }
        // iterating over the last row
        for (int i = 0; i < m; i++) {
            if("O".equals(matrix[n-1][i]) && visited[n-1][i] != 1){
                // it is an O and not visited yet
                dfs(n-1, i, matrix, visited);
            }
        }
        // iterating over the first col
        for (int i = 0; i < n; i++) {
            if("O".equals(matrix[i][0]) && visited[i][0] != 1){
                // it is an O and not visited yet
                dfs(i, 0, matrix, visited);
            }
        }
        // iterating over the last col
        for (int i = 0; i < n; i++) {
            if("O".equals(matrix[i][m-1]) && visited[i][m-1] != 1){
                // it is an O and not visited yet
                dfs(i, m-1, matrix, visited);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if("O".equals(matrix[i][j]) && visited[i][j] == 0){
                    // it is an O and not visited
                    // they can be replaced
                    matrix[i][j] = "X";
                }
            }
        }
        return matrix;
        // TC -> O(nxm)
        // SC -> O(nxm)
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[][] matrix = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.next();
            }
        }
        matrix = replaceOWithX(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
