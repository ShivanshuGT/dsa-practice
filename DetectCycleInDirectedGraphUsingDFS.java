import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DetectCycleInDirectedGraphUsingDFS {

    static class Pair {
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

        int edges = edgeInfo.size();
        for (int i = 0; i < edges; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
        }
        return graph;
        // TC -> O(n+e)
        // SC -> O(e)

    }

    private static boolean dfs(int node, int[] visited, int[] pathVisited, List<List<Pair>> graph){
        visited[node] = 1;
        pathVisited[node] = 1;

        // traversing the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] == 0){
                // the neighbour is not visited yet
                dfs(neighbour.nodeValue, visited, pathVisited, graph);
            }else{
                // the neighbour is already visited
                if(pathVisited[neighbour.nodeValue] == 1){
                    // the neighbour was visited on the current path
                    // this is a cycle
                    return true;
                }

            }
        }

        pathVisited[node] = 0;
        return false;
    }

    private static boolean detectCycle(int n, List<List<Pair>> graph){
        int[] visited = new int[n+1];
        int[] pathVisited = new int[n+1];
        for (int i = 1; i <= n; i++) {
            if(dfs(i, visited, pathVisited, graph) == true){
                return true;
            }
        }
        return false;
        // TC -> O(n + 2xe)
        // SC -> O(n)
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
