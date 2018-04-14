package april2018;

import java.util.*;

/**17. 见Python reduce*/


public class LetterCombinations {
    /**76.01%*/
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0)
            return ans;
        /*Map<Character, String> kv = new HashMap<>();
        kv.put('2', "abc");
        kv.put('3', "def");
        kv.put('4', "ghi");
        kv.put('5', "jkl");
        kv.put('6', "mno");
        kv.put('7', "pqrs");
        kv.put('8', "tuv");
        kv.put('9', "wxyz");*/
        String[] kv = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        Queue<String> queue = new LinkedList<>();
        queue.add("");
        char[] digitc = digits.toCharArray();
        for (char dic : digitc) {
            Queue<String> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                String acc = queue.poll();
                for (char alpha : kv[dic - '0'].toCharArray())
                    temp.add(acc + alpha);
            }
            queue = temp;
        }

        return (List<String>) queue;
    }

    public static void main(String[] s) {
        List<String> ans =
                new LetterCombinations()
                        .letterCombinations("23");
        for (String an : ans) {
            System.out.print(an+", ");
        }
    }
}
