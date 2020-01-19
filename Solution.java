import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    private static final String FILE_PATH;

    static {
        FILE_PATH = "wordsforproblem.txt";
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

    public void solve(List<String> words) {
        Set<String> set = new HashSet<>();
        int minLength = Integer.MAX_VALUE;
        for(String word : words) {
            if(word.length() == 0) {
                continue;
            }
            set.add(word);
            if(word.length() < minLength) minLength = word.length();
        }
        TreeSet<String> concatenated = new TreeSet<>((first, second) -> {
        	return first.length() - second.length();
        });
        for (String word : words) {
            if (word.length() != 0 && isConcatenated(word, set, minLength, 0)) {
                concatenated.add(word);
            }
        }
        printResults(concatenated);
    }

    private static void printResults(TreeSet<String> results) {
        int size = results.size();
        System.out.println(results.pollLast());
        System.out.println(results.pollLast());
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
