import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DetectCycleInUndirectedGraphUsingDFS {

    static class Pair{
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }

    }

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }
        int e = edgeInfo.size();
        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // SC -> O(2xe)
        // TC -> O(n + e)
    }

    private static boolean dfs(int node, int parent, List<List<Pair>> graph, int[] visited){
        visited[node] = 1;
        // traversing and the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] != 1){
                // it has not been visited yet
                if(dfs(neighbour.nodeValue, node, graph, visited)){
                    return true;
                }
            }else{
                // it has been visited earlier
                if(neighbour.nodeValue == parent){
                    continue;
                }else{
                    // it is a cycle
                    return true;
                }
            }
        }   
        return false;
        // SC -> O(n + 2xe)
        // TC -> O(n + 2xe)
    }

    private static boolean detectCycle(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes+1];

        for (int i = 1; i <= nodes; i++) {
            if(visited[i] != 1){
                if(dfs(i, -1, graph, visited)){
                    return true;
                }
            }
        }
        return false;
        // TC -> O(n + 2xe)
        // SC -> O(n + 2xe)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> edgeInfo = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            edgeInfo.add(List.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        List<List<Pair>> graph = buildGraphFromInput(n, edgeInfo);
        System.out.println(detectCycle(n, graph));
        sc.close();
    }
}
