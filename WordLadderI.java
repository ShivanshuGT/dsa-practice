import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class WordLadderI {

    static class QueueEntry{
        String word;
        int sequenceLength;
        QueueEntry(String word, int sequenceLength){
            this.word = word;
            this.sequenceLength = sequenceLength;
        }
    }

    private static int findShortestSequenceLength(String startWord, String targetWord, List<String> words){
        int numberOfWords = words.size();
        Set<String> set = new HashSet<>();
        
        // putting all the words in a set to reduce the time for searching 
        for (int i = 0; i < numberOfWords; i++) {
            set.add(words.get(i));
        }
        int wordLength = words.get(0).length();

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(startWord, 1));
        set.remove(startWord);

        while (!queue.isEmpty()) {
            QueueEntry entry = queue.poll();
            String currentWord = entry.word;
            int currentSequenceLength = entry.sequenceLength;
            if(currentWord.equals(targetWord)){
                return currentSequenceLength;
            }

            // trying different combinations for each character in the word
            for (int i = 0; i < wordLength; i++) {
                // trying all alphabets from 'a' to 'z'
                for (char j = 'a'; j <= 'z'; j++) {
                    String word = currentWord;
                    char[] wordArray = word.toCharArray();
                    wordArray[i] = j;
                    String formedWord = new String(wordArray);
                    if(set.contains(formedWord)){
                        // the set contains the formed word - this means it is a valid transformation
                        queue.add(new QueueEntry(formedWord, currentSequenceLength + 1));
                        set.remove(formedWord);
                    }
                }
            }

        }

        // returns zero if we are not find to make the targetWord
        return 0;
        // Let 'n' be the number of words
        // and 'm' be the word length
        // TC -> O(n x m x 26 x log(n))
        // SC -> O(n)
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

        System.out.println(findShortestSequenceLength(startWord, targetWord, words));
        sc.close();
    }
}
