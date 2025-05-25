import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * for 2 strings to be isomorphic to each other, we can write solution with 2 hashmaps maintaining
 * key values of replacement pairs for each string. Further optimization is to use HashSet for other
 * string as reverse mapping already exists in the hashmap.
 *
 * Time: O(n) Space: O(1) as keys are ascii chars which is fixed/constant
 */
public class IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> used = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (mapping.containsKey(sChar)) {
                if (mapping.get(sChar) != tChar)
                    return false;
            } else {
                if (used.contains(tChar)) {
                    return false;
                } else {
                    mapping.put(sChar, tChar);
                    used.add(tChar);
                }
            }
        }
        return true;
    }
}
