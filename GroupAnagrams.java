import java.util.*;

public class GroupAnagrams {
    //------------------------------Solution 1------------------------------
    /*
    For each element prepare a ASCII char frequency array and use hashmap to compare this array
    using it a key and original value of element will be added against that key.
    NOTE: hashmap can take int[] as its key but we will get wrong answer, bcz hashmap uses hashcode()
    and equals() of an object for comparison and Arrays class (here for int[]) will give array reference/memory address
    not its content as hashcode(), equals() output.

    Time: O(nk) Space: O(n)
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs == null || strs.length == 0){
            return new ArrayList<List<String>>();
        }

        Map<String,List<String>> resultMap = new HashMap<>();

        for (String str: strs) { //Time: O(n)
            int[] freq = new int[26];
            for (char c: str.toCharArray()) {//Time: O(k)
                freq[c - 'a']++;
            }
            String freqStr = Arrays.toString(freq);

            if(!resultMap.containsKey(freqStr)) {//hash functions get, put will have Time: O(k)
                resultMap.put(freqStr, new ArrayList<String>());
            }
            resultMap.get(freqStr).add(str);
        }
        return new ArrayList<>(resultMap.values());
    }

    //------------------------------Solution 2------------------------------
    /*
    Assign a prime number to each lowercase alphabet and calculate products of these primes for a given string. For anagram, this product
    will be same. Use this as key in hashmap and store original elements as list as value for elements sharing same key.

    Time: O(nk) Space: O(n)
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0){
            return new ArrayList<List<String>>();
        }
        int[] primes = getPrimes();
        Map<Double,List<String>> resultMap = new HashMap<>();

        for (String str: strs) { //Time: O(n)
            double product = 1;
            for (char c: str.toCharArray()) { //Time: O(k)
                product *= primes[c - 'a'];
            }

            if(!resultMap.containsKey(product)) { //Time: O(1) as it is happening on primitive double+
                resultMap.put(product, new ArrayList<String>());
            }
            resultMap.get(product).add(str);
        }
        return new ArrayList<>(resultMap.values());
    }

    private int[] getPrimes() {// Time is O(1) as only 26 primes needs to be found
        int[] primes = new int[26];
        primes[0] = 2;
        primes[1] = 3;

        int num = 4;
        for (int i = 2; i < primes.length; i++) {
            while(primes[i] == 0) {
                boolean isPrime = true;
                for (int j = 0; j < i; j++) {
                    if (num % primes[j] == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime) {
                    primes[i] = num;
                }
                num++;
            }
        }
        return primes;
    }
}
