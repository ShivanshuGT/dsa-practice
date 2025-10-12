import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FindEventualSafeStatesUsingDFS {

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
        // TC -> O(n + e)
        // SC -> O(e)
    }

    private static boolean dfs(int node, int[] visited, int[] pathVisited, int[] safeChecker, List<List<Pair>> graph){
        visited[node] = 1;
        pathVisited[node] = 1;
        // traversing the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(visited[neighbour.nodeValue] == 0){
                // the neighbour is not visited yet
                if(dfs(neighbour.nodeValue, visited, pathVisited, safeChecker, graph)){
                    return true;
                }
            }else{
                // the neighbour has already been visited
                if(pathVisited[neighbour.nodeValue] == 1){
                    // the neighbour has been visited on the current path
                    // this is a cycle
                    return true;
                }
            }
        }
        pathVisited[node] = 0;
        safeChecker[node] = 1;
        return false;
    } 

    private static List<Integer> findSafeNodes(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes+1];
        int[] pathVisited = new int[nodes+1];
        int[] safeChecker = new int[nodes+1];

        for (int i = 1; i <= nodes; i++) {
            if(visited[i] != 1){
                dfs(i, visited, pathVisited, safeChecker, graph);
            } 
        }
        List<Integer> safeNodes = new ArrayList<>();

        for (int i = 1; i <= nodes; i++) {
            if(safeChecker[i] == 1){
                // this is a safe node
                safeNodes.add(i);
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
        // TC -> O(n + e)
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
        List<Integer> safeNodes = findSafeNodes(n, graph);
        for (Integer safeNode : safeNodes) {
            System.out.print(safeNode + " ");
        }
        sc.close();
    }
}
