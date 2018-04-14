package march2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestWordinDictionarythroughDeleting {
    public static void main(String[] s) {
        //Input:
        //s = "abpcplea", d = ["ale","apple","monkey","plea"]
        //
        //Output:
        //"apple"
        List<String> d = new ArrayList<>();
        d.add("ale");
        d.add("ble");
        d.add("apple");
        d.add("monkey");
        d.add("plea");
        System.out.println(new LongestWordinDictionarythroughDeleting().findLongestWord("abpcplea", d));
    }


    /**19.3%
     * Space complexity : O(logn). Sorting takes O(logn) space in average case.
     * upgrade: 不排序，存最长字符串*/
    public String findLongestWord(String s, List<String> d) {
        d.sort((o1, o2) -> {
            if (o1.length() != o2.length())
                return o2.length() - o1.length(); //长的在前
            else
                return o1.compareTo(o2); //字典序小的在前
        });

        char[] chars = s.toCharArray(), adc = null;
        int len = 0, i = 0;
        for (String ad : d) {
            adc = ad.toCharArray();// 单词
            len = adc.length; i = 0;
            for (char as : s.toCharArray()) { //长串
                if (i == len)
                    return ad;
                if (as == adc[i])
                    i++;
            }
            if (i == len)
                return ad;
        }

        return ""; //no result, return empty string
    }
}
