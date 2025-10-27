import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PathWithMinimumEffort {

    static class QueueEntry {
        int effort;
        int row;
        int col;

        QueueEntry(int effort, int row, int col){
            this.effort = effort;
            this.row = row;
            this.col = col;
        }
        
    }

    private static int findMinimumEffort(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;

        Comparator<QueueEntry> comparator = Comparator.comparingInt((QueueEntry entry) -> entry.effort).thenComparing(entry -> entry.row).thenComparing(entry -> entry.col);
        Queue<QueueEntry> queue = new PriorityQueue<>(comparator);

        queue.add(new QueueEntry(0, 0, 0)); // starting with the source (0,0)

        int[] delRow = {1,-1,0,0};
        int[] delCol = {0,0,1,-1};

        int[][] distance = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        distance[0][0] = 0;

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            int currentEffort = entry.effort;
            int currentRow = entry.row;
            int currentCol = entry.col;

            if((currentRow == row-1) && (currentCol == col-1)){
                // we found the target point 
                return currentEffort;
            }

            // traversing the neighbours
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + delRow[i];
                int newCol = currentCol + delCol[i];
                if(newRow >= 0 && newRow < row && newCol >= 0 && newCol < col){

                    int newEffort = Math.max(currentEffort, Math.abs(matrix[currentRow][currentCol] - matrix[newRow][newCol]));
    
                    if(distance[newRow][newCol] > newEffort){
                        distance[newRow][newCol] = newEffort;
                        queue.add(new QueueEntry(newEffort, newRow, newCol));
                    }
                }
            }
        }
        return -1;

        // TC -> O(4 x n x m x log(n x m))
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
        System.out.println(findMinimumEffort(matrix));
        sc.close();
    }
}
