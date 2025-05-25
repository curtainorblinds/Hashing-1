import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Same as isomorphic string problem, only difference is second string is a word instead
 * of a character match.
 *
 * Time: O(n) Space: O(n) due to set space could increase as different words increases
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> mappings = new HashMap<>();
        Set<String> used = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            String word = words[i];

            if (mappings.containsKey(c)) {
                if (!mappings.get(c).equals(word)) {
                    return false;
                }
            } else {
                if (used.contains(word)) {
                    return false;
                }
                mappings.put(c, word);
                used.add(word);
            }
        }
        return true;
    }
}
