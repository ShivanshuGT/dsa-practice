import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {

    static class Pair{
        int nodeValue;
        int weight;
        Pair(int nodeValue, int weight){
            this.nodeValue = nodeValue;
            this.weight = weight;
        }
    }

    private static List<List<Pair>> buildgraphFromWords(int nodes, String[] words){
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        // traversing each word to check
        int numberOfWords = words.length;
        for (int i = 0; i < numberOfWords-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];

            // compare each character of the two words
            int n1 = word1.length();
            int n2 = word2.length();
            int j = 0; 
            while (j < n1 && j < n2) {
                if(word1.charAt(j) != word2.charAt(j)){
                    // the characters are not same
                    // there is an edge from word1.charAt(j) -> word2.charAt(j)
                    int source = word1.charAt(j) - 'a';
                    int destination = word2.charAt(j) - 'a'; // maps a to 0 , b to 1 and so on...
                    graph.get(source).add(new Pair(destination, 1));
                    break;
                }
                j += 1;
            }
        }
        return graph;
        // TC -> O(n + sum of length of each word)
        // SC -> O(e) where 'e' is the number of edges formed
    }

    private static List<Character> findPossibleSequence(int numberOfAlphabets, String[] words){
        // find the dependency in letters and map them into a 0-based indexing graph
        List<List<Pair>> graph = buildgraphFromWords(numberOfAlphabets, words);
  
        // use topological sort now
        int[] indegree = new int[numberOfAlphabets];
        // calculate the indegree for each node
        for (int i = 0; i < numberOfAlphabets; i++) {
            List<Pair> edges = graph.get(i);
            for (Pair edge : edges) {
                indegree[edge.nodeValue] += 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // push all the nodes with 0 indegree
        for (int i = 0; i < numberOfAlphabets; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        List<Character> possibleSequence = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            char letter = (char) ('a' + node); // mapping 0 to 'a', 1 to 'b' and so on ....
            possibleSequence.add(letter);

            // traversing all the neighbours
            List<Pair> neighbours = graph.get(node);
            for (Pair neighbour : neighbours) {
                indegree[neighbour.nodeValue] -= 1;
                if(indegree[neighbour.nodeValue] == 0){
                    queue.add(neighbour.nodeValue);
                }
            }
        }

        return possibleSequence;
        // SC -> O(n + e)
        // TC -> O(sum of length of each word + n + e)
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfAlphabets = sc.nextInt();
        int numberOfWords = sc.nextInt();
        String[] words = new String[numberOfWords];
        for (int i = 0; i < numberOfWords; i++) {
            words[i] = sc.next();
        }
        List<Character> possibleSequence = findPossibleSequence(numberOfAlphabets, words);
        for (Character letter : possibleSequence) {
            System.out.print(letter + " ");
        }
        // this sequence is not possible in two cases : 
        // 1. When the words are given like this : 
        // abcd
        // abc
        // 2. When there is a cycle formed in the graph, for example : 
        // abc
        // bca
        // acd
        sc.close();
    }
}
