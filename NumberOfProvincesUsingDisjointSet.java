import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberOfProvincesUsingDisjointSet {

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
            if(parent.get(node) == node){
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
                parent.set(parentU, parentV);
                rank.set(parentV, rank.get(parentV) + 1);
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

        public int findDistinctParents(){
            int nodes = parent.size()-1;
            int answer = 0;
            for (int i = 1; i <= nodes; i++) {
                if(parent.get(i) == i){
                    answer += 1;
                }
            }
            return answer;
            // TC -> O(N)
        }
        // SC -> O(N)
    }

    private static int findNumberOfProvinces(int nodes, int[][] graph){
        DisjointSet disjointSet = new DisjointSet(nodes);

        for (int i = 1; i <= nodes; i++) {
            for (int j = 1; j <= nodes; j++) {
                if(graph[i][j] != 0){
                    int u = i;
                    int v = j;
                    disjointSet.unionByRank(u,v);
                }
            }
        }
        return disjointSet.findDistinctParents();
        // TC -> O(N x N)
        // SC -> O(N x N)

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] graph = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        System.out.println(findNumberOfProvinces(n, graph));
        sc.close();
    }   
}
