import java.util.Scanner;

public class FloydWarshallAlgorithm {

    private static int[][] findShortestPath(int[][] graph){
        int n = graph.length;

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                    if((graph[i][via] != Integer.MAX_VALUE) && (graph[via][j] != Integer.MAX_VALUE) && (graph[i][j] > (graph[i][via] + graph[via][j]))){
                        graph[i][j] = graph[i][via] + graph[via][j];
                    }
               } 
            }
        }

        // step for detecting negative weight cycle
        for (int i = 0; i < n; i++) {
            if(graph[i][i] < 0){
                // there is a negative cycle present
                int[][] arr = new int[1][1];
                arr[0][0] = -1;
                return arr;
            }
        }
        return graph;
        // TC -> O(nxnxn)
        // SC -> O(nxn)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j){
                    graph[i][j] = 0;
                }else{
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph[u][v] = weight;
        }

        int[][] shortestPath = findShortestPath(graph);
        if(shortestPath.length == 1 && shortestPath[0][0] == -1){
            System.out.println("There is a negative cycle present in the graph.");
        }else{
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.println("The distance from node " + i + " to node " + j + " is " + shortestPath[i][j]  + " units.");
                }
            }
        }
        sc.close();
    }
}
