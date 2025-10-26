import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShortestDistanceInBinaryMaze {

    static class QueueEntry {
        int distance;
        int row;
        int col;

        QueueEntry(int distance, int row, int col){
            this.distance = distance;
            this.row = row;
            this.col = col;
        }
        
    }

    private static int findShortestDistance(int[][] matrix, int sourceRow, int sourceCol, int targetRow, int targetCol){
        if(sourceRow == targetRow && sourceCol == targetCol){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] distance = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        distance[sourceRow][sourceCol] = 0;

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(0, sourceRow, sourceCol));

        int[] delRow = {0,0,1,-1};
        int[] delCol = {1,-1,0,0};

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int currentDistance = entry.distance;
            int currentRow = entry.row;
            int currentCol = entry.col;
            
            // traversing the neighbours
            for (int k = 0; k < 4; k++) {
                int newRow = currentRow + delRow[k];
                int newCol = currentCol + delCol[k];

                if(newRow >=0 && newRow < row && newCol >=0 && newCol < col ){
                    // it is a valid neighbour
                    if(matrix[newRow][newCol] == 1){
                        // we can take that cell in the path
                        if(currentDistance + 1 < distance[newRow][newCol]){
                            // we found the shorter path to that cell
                            if(newRow == targetRow && newCol == targetCol){
                                // this is the target cell
                                return currentDistance + 1;
                            }
                            distance[newRow][newCol] = currentDistance + 1;
                            queue.add(new QueueEntry(currentDistance + 1, newRow, newCol));
                        }
                    }
                }
            }

        }

        return -1;
        // TC -> O(n x m)
        // SC -> O(n x m)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int sourceRow = sc.nextInt();
        int sourceCol = sc.nextInt();
        int targetRow = sc.nextInt();
        int targetCol = sc.nextInt();

        int shortestDistance = findShortestDistance(matrix, sourceRow, sourceCol, targetRow, targetCol);
        System.out.println(shortestDistance);
        sc.close();
    }
}
