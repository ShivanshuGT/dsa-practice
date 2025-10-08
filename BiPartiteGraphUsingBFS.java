import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BiPartiteGraphUsingBFS {

    static class Pair {
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
        
    }



    private static List<List<Pair>> buildgraphFromInput(int nodes, List<List<Integer>> edgeInfo){
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

    private static boolean bfs(int node, int[] color, List<List<Pair>> graph){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        color[node] = 0; // coloring the node with color '0'

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            int currentNodeColor = color[currentNode];
            // traversing all the neighbours of the current node
            List<Pair> neighbours = graph.get(currentNode);
            for (Pair neighbour : neighbours) {
                if(color[neighbour.nodeValue] == -1){
                    // the neighbour is not colored yet
                    queue.add(neighbour.nodeValue);
                    color[neighbour.nodeValue] = 1 - currentNodeColor; 
                }else{
                    // the neighbour is already colored
                    if(color[neighbour.nodeValue] == color[currentNode]){
                        // the color of the current node and its neighbour is same
                        // rule if bipartite graph is violated
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private static boolean checkIfTheGraphIsBipartite(int nodes, List<List<Pair>> graph){
        int[] color = new int[nodes+1];
        for (int i = 0; i <= nodes; i++) {
            // marking all the nodes as not colored (-1)
            color[i] = -1;
        }
        for (int i = 1; i <= nodes; i++) {
            if(color[i] == -1){
                // the node is not colored yet
                if(bfs(i, color, graph) == false){
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
        List<List<Pair>> graph = buildgraphFromInput(n, edgeInfo);
        System.out.println(checkIfTheGraphIsBipartite(n, graph));
        sc.close();
    }
}
