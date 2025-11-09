import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberOfOperationsToMakeNetworkConnected {

    static class DisjointSet {
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent  = new ArrayList<>();
        List<Integer> size  = new ArrayList<>();

        DisjointSet(int nodes){
            // supports both '0-based' and '1-based' indexing
            for (int i = 0; i <= nodes; i++) {
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node){
            // recursive approach is used to do path compression as well
            if(parent.get(node) == node){
                return node;
            }
            int val = findParent(parent.get(node));
            parent.set(node, val);
            return parent.get(node);
            // TC -> O(1) = O(4 x alpha)
        }

        public void unionByRank(int u, int v){
            // performs union of node 'u' and 'v'
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(rank.get(parentU) == rank.get(parentV)){
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
                rank.set(parentV, rank.get(parentV) + 1);
            }else if(rank.get(parentU) > rank.get(parentV)){
                // attaching 'v' below 'u'
                parent.set(parentV, parentU);
            }else{
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
            }
             // TC -> O(1) = O(4 x alpha)
        }

        public void unionBySize(int u, int v){
            int parentU = findParent(u);
            int parentV = findParent(v);

            if(size.get(parentU) > size.get(parentV)){
                // attaching 'v' below 'u'
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            }else{
                // attaching 'u' below 'v'
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
             // TC -> O(1) = O(4 x alpha)
        }

        public int findDistinctComponents(){
            int n = parent.size();
            int distinctComponents = 0;
            for (int i = 0; i < n-1; i++) {
                if(parent.get(i) == i){
                    distinctComponents += 1;
                }
            }
            return distinctComponents;
            // TC -> O(N)
        }
        // SC -> O(N)
        
    }

    private static int findNumberOfOperations(int n, List<List<Integer>> edges){
        int extraEdges = 0;
        DisjointSet disjointSet = new DisjointSet(n);

        // iterating over the edges of the graph
        int e = edges.size();
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edges.get(i);
            int u = edge.get(0);
            int v = edge.get(1);

            if(disjointSet.findParent(u) == disjointSet.findParent(v)){
                // 'u' and 'v' are already connected 
                // this means the current edge is an extra edge
                extraEdges += 1;
            }else{
                // 'u' and 'v' are not connected
                // we need to connect them
                disjointSet.unionByRank(u, v);
            }
        }

        int requiredNumberOfEdges = disjointSet.findDistinctComponents() - 1;
        
        if(extraEdges >= requiredNumberOfEdges){
            return requiredNumberOfEdges;
        }
        // it is not possible to make the network connected
        return -1;
        // TC -> O(N + E)
        // TC -> O(E + N)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edges.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int numberofOperations = findNumberOfOperations(n, edges);
        System.out.println(numberofOperations);
        sc.close();
    }
}
