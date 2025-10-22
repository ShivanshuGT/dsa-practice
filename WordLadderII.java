import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class WordLadderII {

    private static List<List<String>> findShortestSequence(String startWord, String targetWord, List<String> words){
        List<List<String>> shortestSequences = new ArrayList<>();
        Queue<List<String>> queue = new LinkedList<>();
        List<String> usedOnLevelWords = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int numberOfWords = words.size();
        int wordLength = words.get(0).length();
        // putting all the words in the set to improve the search time
        for (int i = 0; i < numberOfWords; i++) {
            set.add(words.get(i));
        }

        int level = 0; // variable to keep track of the current level
        usedOnLevelWords.add(startWord);
        List<String> startList = new ArrayList<>();
        startList.add(startWord);
        queue.add(startList);

        while (!queue.isEmpty()) {
            List<String> currentSequence = queue.poll();
            
            if(currentSequence.size() > level){
                // we have completed the level
                // time to delete the usedOnLevelWords from the set
                for (String word : usedOnLevelWords) {
                    set.remove(word);
                }
                // increase the level
                level += 1;
                // clear the usedOnLevelWords list
                usedOnLevelWords.clear();
            }

            // get the last formed word from the currentSequence
            String lastFormedWord = currentSequence.get(currentSequence.size() - 1);

            // check if the lastFormedWord is the target word
            if(lastFormedWord.equals(targetWord)){
                // check if the shortestSequence list is empty
                if(shortestSequences.size() == 0){
                    // this is the first answer sequence to be inserted
                    shortestSequences.add(currentSequence);
                }else{
                    // allow only those sequences which have the size equal to the first inserted sequence
                    if(shortestSequences.get(0).size() == currentSequence.size()){
                        shortestSequences.add(currentSequence);
                    }
                }
            }

            // trying all the possible combinations
            for (int i = 0; i < wordLength; i++) {
                // trying all the possible characters from 'a' to 'z'
                for (char j = 'a'; j <= 'z'; j++) {
                    String wordToBeChanged = lastFormedWord;
                    char[] charArray = wordToBeChanged.toCharArray();
                    charArray[i] = j;
                    String newWord = new String(charArray);
                    // checking if the formed word is present in the set
                    if(set.contains(newWord)){
                        // this is a valid transformation
                        // push the new word in the current sequence
                        currentSequence.add(newWord);
                        // push this sequence in the queue
                        List<String> temp = new ArrayList<>();
                        for (String element : currentSequence) {
                            temp.add(element);
                        }
                        queue.add(temp);
                        // remove the formed word from the sequence
                        currentSequence.removeLast();
                        usedOnLevelWords.add(newWord); // add the new formed word in usedOnLevelWords
                    }
                }
            }
        }
        return shortestSequences;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String startWord = sc.next();
        String targetWord = sc.next();
        int numberOfWords = sc.nextInt();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            words.add(sc.next());
        } 
        List<List<String>> shortestSequences = findShortestSequence(startWord, targetWord, words);
        for (List<String> sequence : shortestSequences) {
            for (String word : sequence) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
