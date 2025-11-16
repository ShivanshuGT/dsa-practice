import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MakingALargeIsland{

    static class DisjointSet {
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int nodes){
            for (int i = 0; i <= nodes; i++) {
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node){
            if(node == parent.get(node)){
                return node;
            }
            int val = findParent(parent.get(node));
            parent.set(node, val);
            return val;
            // TC -> O(1)
        }

        public void unionByRank(int u, int v){
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) == rank.get(parentV)){
                parent.set(parentV, parentU);
                rank.set(parentU, rank.get(parentU) + 1);
            }else if(rank.get(parentU) > rank.get(parentV)){
                parent.set(parentV, parentU);
            }else{
                parent.set(parentU, parentV);
            }
            // TC -> O(1)
        }

        public void unionBySize(int u, int v){
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(size.get(parentU) > size.get(parentV)){
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            }else{
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
            // TC -> O(1)
        }
        // SC -> O(n)
        
    }

    private static boolean isValidCell(int row, int col, int n, int m){
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    private static int findSizeOfLargestIsland(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        DisjointSet disjointSet = new DisjointSet(n * m);

        // itertaing over the matrix and connecting the islands with each other
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 1){
                    // the cell is an island
                    // checking the neighbours
                    for (int k = 0; k < 4; k++) {
                        int newRow = i + dr[k];
                        int newCol = j + dc[k];
                        if(isValidCell(newRow, newCol, n, m)){
                            // it is a valid neighbour
                            if(matrix[newRow][newCol] == 1){
                                // and it is an island as well
                                int node = i * m + j;
                                int neighbourNode = newRow * m + newCol;
                                if(disjointSet.findParent(node) != disjointSet.findParent(neighbourNode)){
                                    // the two nodes are not connected yet
                                    // so connect them
                                    disjointSet.unionBySize(node, neighbourNode);
                                }
                            }
                        }
                    }
                }
            }
        }
        int answer = -1;
        // checking for the all zeroes in the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 0){
                    Set<Integer> set = new HashSet<>();
                    // the cell has zero value
                    // checking its neighbours
                    for (int k = 0; k < 4; k++) {
                        int newRow = i + dr[k];
                        int newCol = j + dc[k];
                        if(isValidCell(newRow, newCol, n, m))  {
                            // it is a valid neighbour
                            if(matrix[newRow][newCol] == 1){
                                // and the neighbour cell is an island as well
                                // using a set so that no values can be repeated
                                set.add(disjointSet.findParent(newRow * m + newCol));
                            }
                        } 
                    }
                    int count = 0;
                    // incrementing the size for the current cell which was zero
                    count = 1;
                    for (Integer element : set) {
                        count += disjointSet.size.get(element);
                    }
                    answer = Math.max(answer, count);


                }
            }
        }

        // handling the case where all the cell has 1 value
        for (int i = 0; i < n * m; i++) {
            answer = Math.max(answer, disjointSet.size.get(disjointSet.findParent(i)));
        }
        return answer;
        // TC -> O(n x m)
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
        int sizeOfLargeIsland = findSizeOfLargestIsland(matrix);
        System.out.println(sizeOfLargeIsland);
        sc.close();
    }
}