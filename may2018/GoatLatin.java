package may2018;

import java.util.Collections;
import java.util.StringJoiner;

/**Contest 82: 1-easy
 * 824
 * 给出一个仅由大小写字母组成的单词组成的句子S，将他转化成一种类似拉丁语的胡诌的山羊(Goat)拉丁语(Latin)
 * 规则如下：
 * 一个单词由元音（vowel: a, e, i, o, u）, 在单词后追加"ma", 如'apple' -> 'applema'
 * 如果以辅音(consonant, 非元音), 将第一个元素添加到末尾，再添加ma, 如'goat' -> 'oatgma'
 * 每个单词末尾添加a, a的数量按单词序数递增, 从1开始*/
public class GoatLatin {
    public String toGoatLatin(String S) {
        String[] words = S.split(" ");

        for (int i = 0; i < words.length; i ++) {
            StringBuilder sb = new StringBuilder();
            char c = words[i].charAt(0);
            int ca = i + 1;
            if (c == 'a' || c == 'e' || c== 'i' || c=='o' || c == 'u'
                    || c == 'A' || c == 'E' || c== 'I' || c=='O' || c == 'U') {
                sb.append(words[i]);
            }else {
                sb.append(words[i], 1, words[i].length());
                sb.append(words[i].charAt(0));
            }
            sb.append("ma");
            while (ca -- > 0)
                sb.append('a');
            words[i] = sb.toString();
        }

        return String.join(" ", words);
    }
}
