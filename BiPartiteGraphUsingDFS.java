import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BiPartiteGraphUsingDFS {

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
            graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
        }
        return graph;
        // TC -> O(n + e)
        // SC -> O(2 x e)
    }

    private static boolean dfs(int node, int nodeColor, List<List<Pair>> graph, int[] color){
        color[node] = nodeColor;

        // traversing the neighbours
        List<Pair> neighbours = graph.get(node);
        for (Pair neighbour : neighbours) {
            if(color[neighbour.nodeValue] == -1){
                // the neighbour is not colored yet
                if(dfs(neighbour.nodeValue, 1-nodeColor, graph, color) == false){
                    return false;
                }
            }else{
                // the neighbour is colored
                if(color[neighbour.nodeValue] == nodeColor){
                    // the neighbour has the same color as the current node color
                    // bipartite rule is violated
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkIfTheGraphIsBipartite(int nodes, List<List<Pair>> graph){
        int[] color = new int[nodes+1];
        for (int i = 0; i <= nodes; i++) {
            color[i] = -1;
        }
        for (int i = 1; i <= nodes; i++) {
            if(color[i] == -1){
                // the node is not colored yet
                if(dfs(i, 0, graph, color) == false){
                    return false;
                }
            }
        }
        return true;
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
        System.out.println(checkIfTheGraphIsBipartite(n, graph));
        sc.close();
    }
}
