import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	// I decided to write code, as i solve problems on HackerRank, CodeWars, LeetCode : thinking about ckecking my problem-solving skills;
	// I don't know, how you check my work : manually ( with help of terminal "javac ...") or automated.
    // So, if it is needed : i can write it is with Maven + SOLID + more flexibility

    private static final String FILE_PATH = "wordsforproblem.txt";

    public List<String> findConcatenatedWords(List<String> words) {
        Set<String> set = new HashSet<>();
        int minLength = Integer.MAX_VALUE; // e.g. we have minLength word = 15, hence checking isConcatenatedWord should started from minLength
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            if (word.length() < minLength) {
                minLength = word.length();
            }
        }
        List<String> concatenatedWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() != 0 && isConcatenatedWord(word, set, minLength, 0)) {
                concatenatedWords.add(word);
            }
        }
        concatenatedWords.sort((arg1, arg2) -> {
            return arg2.length() - arg1.length();
        });
        return concatenatedWords;
    }

    private boolean isConcatenatedWord(String word, Set<String> set, int minLength, int numberUnits) {
        if (numberUnits > 0 && set.contains(word)) { // if numberUnits == 0, hence we just start out checking.
            return true;
        }
        int stringLength = word.length();
        for (int i = minLength; i < stringLength; i++) {
            if (set.contains(word.substring(0, i))
                    && isConcatenatedWord(word.substring(i), set, minLength, numberUnits + 1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while (reader.ready()) {
                words.add(reader.readLine());
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read file from " + FILE_PATH);
        }
        Solution solution = new Solution();
        List<String> allSortedConcatenatedWords = solution.findConcatenatedWords(words);
        printResults(allSortedConcatenatedWords);
    }

    private static void printResults(List<String> results) {
        int size = results.size();
        System.out.println(results.get(0));
        System.out.println(results.get(1));
        System.out.println(size);
    }
}
