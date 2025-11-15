import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberOfIslandsII {

    static class DisjointSet{
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        DisjointSet(int nodes){
            for (int i = 0; i <= nodes; i++) {
                parent.add(i);
                size.add(1);
                rank.add(0);
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

    private static boolean isValidCell(int n, int m, int row, int col){
        return row >=0 && row < n && col >=0 && col < m;
    }

    private static List<Integer> answerQueries(int n, int m,List<List<Integer>> queries){
        int[][] visited = new int[n][m];

        DisjointSet disjointSet = new DisjointSet(n * m);

        int count = 0;
        List<Integer> answers = new ArrayList<>();
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (List<Integer> query : queries) {
            int row = query.get(0);
            int col = query.get(1);
            if(visited[row][col] == 1){
                // the cell is already marked
                // then this would have no impact
                answers.add(count);
                continue;
            }
            visited[row][col] = 1;
            count += 1;

            // checking the neighbours
            for (int i = 0; i < 4; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if(isValidCell(n, m, newRow, newCol)){
                    // it is a valid cell
                    if(visited[newRow][newCol] == 1){
                        // neighbour is an island
                        // translate row and column number to nodes
                        int node = row * m + col;
                        int neighbourNode = newRow * m + newCol;
                        if(disjointSet.findParent(node) != disjointSet.findParent(neighbourNode)){
                            // node and neighbourNode are not connected yet
                            disjointSet.unionByRank(node, neighbourNode);
                            count -= 1;
                        }
                    }
                }
            }
            answers.add(count);

        }
        return answers;
        // TC -> O(n x m) + O(x) 
        // SC -> O(n x m) + O(x) where 'x' is the number of queries


    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int numberOfQueries = sc.nextInt();
        List<List<Integer>> queries = new ArrayList<>();
        for (int i = 0; i < numberOfQueries; i++) {
            queries.add(List.of(sc.nextInt(), sc.nextInt()));
        }
        List<Integer> answers = answerQueries(n, m, queries);
        for (Integer answer : answers) {
            System.out.println(answer + " ");
        }
        sc.close();
    }
}
