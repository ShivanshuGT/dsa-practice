import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BridgesInGraph {

    static class Pair {
        int nodeValue;
        int weight;

        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }

    static int timer = 1;

    private static List<List<Pair>> buildGraphFromInput(int nodes, List<List<Integer>> edgeInfo){
        int e = edgeInfo.size();
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            List<Integer> edge = edgeInfo.get(i);
            graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + 2xe)
        // SC -> O(2xe)
    }

    private static void dfs(int node, int parent, int[] visited, List<List<Pair>> graph, int[] timeOfInsertion, int[] lowestTimeOfInsertion, List<List<Integer>> bridges){
        visited[node] = 1;

        timeOfInsertion[node] = timer;
        lowestTimeOfInsertion[node] = timer;
        timer += 1;

        // traversing over the adjacent nodes except the parent
        List<Pair> neighbours = graph.get(node);

        for (Pair neighbour : neighbours) {
            if (neighbour.nodeValue == parent){
                // skip if its the parent
                continue;
            }
            if(visited[neighbour.nodeValue] == 0){
                // the neighbour is not visited yet
                dfs(neighbour.nodeValue, node, visited, graph, timeOfInsertion, lowestTimeOfInsertion, bridges);
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[neighbour.nodeValue]);
                // check if this can be bridge
                if(lowestTimeOfInsertion[neighbour.nodeValue] > timeOfInsertion[node]){
                    // this is a bridge
                    bridges.add(List.of(node, neighbour.nodeValue));
                }
            }else{
                // the neighbour is already visited
                // this can never be a bridge
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node], lowestTimeOfInsertion[neighbour.nodeValue]);


            }
        }

        // TC -> O(n + 2xe)
    }

    private static List<List<Integer>> findBridges(int nodes, List<List<Pair>> graph){
        int[] visited = new int[nodes + 1];
        int[] timeOfInsertion = new int[nodes + 1];
        int[] lowestTimeOfInsertion = new int[nodes + 1];

        List<List<Integer>> bridges = new ArrayList<>();

        for (int i = 1; i <= nodes; i++) {
            if(visited[i] != 1){
                dfs(i, -1, visited, graph, timeOfInsertion, lowestTimeOfInsertion, bridges);
            }
        }
        return bridges;
        // TC -> O(n + 2xe)
        // SC -> O(n + e)

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
        List<List<Integer>> bridges = findBridges(n, graph);
        for (List<Integer> bridge : bridges) {
            System.out.println(bridge.get(0) + " -> " + bridge.get(1));
        }
        sc.close();
    }
}
