import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
    private static final String FILE_PATH;

    static {
        FILE_PATH = "wordsforproblem.txt";
    }

    public List<String> solve(List<String> words) {
        Set<String> set = new HashSet<>();
        int minLength = Integer.MAX_VALUE;
        for(String word : words) {
            if(word.length() == 0) {
                continue;
            }
            set.add(word);
            if(word.length() < minLength) minLength = word.length();
        }
        List<String> concatenated = new ArrayList<>();
        for (String word : words) {
            if (word.length() != 0 && isConcatenated(word, set, minLength, 0)) {
                concatenated.add(word);
            }
        }
        concatenated.sort((arg1, arg2) -> {
            return arg2.length() - arg1.length();
        });
        printResults(concatenated);
        return concatenated;
    }

    private boolean isConcatenated(String word, Set<String> set, int minLength, int numberUnits) {
        if (numberUnits > 0
                && set.contains(word)) {
            return true;
        }
        int stringLength = word.length();
        for (int i = minLength; i < stringLength; i++) {
            if (set.contains(word.substring(0, i))
                    && isConcatenated(word.substring(i), set, minLength, numberUnits + 1)) {
                return true;
            }
        }
        return false;
    }

    private static void printResults(List<String> results) {
        int size = results.size();
        System.out.println(results.get(0));
        System.out.println(results.get(1));
        System.out.println(size);
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while(reader.ready()) {
                words.add(reader.readLine());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Solution solution = new Solution();
        solution.solve(words);
    }
}
