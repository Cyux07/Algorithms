package march2018;

import java.util.HashSet;
import java.util.Set;

/**753. Cracking the Safe
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

 You can keep inputting the password, the password will automatically be matched against the last n digits entered.

 For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

 Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *Input: n = 1, k = 2
 Output: "01"
 Note: "10" will be accepted too.
 *Input: n = 2, k = 2
 Output: "00110"(包含了所有可能的数对00,11,10,01
 Note: "01100", "10011", "11001" will be accepted too.
 *
 * Note:
 n will be in the range [1, 4].
 k will be in the range [1, 10].
 k^n will be at most 4096.*/
public class CrackingtheSafe {
    public static void main(String[] s) {
        CrackingtheSafe cs = new CrackingtheSafe();
        System.out.println(cs.crackSafe(1, 2));
        System.out.println(cs.crackSafe(2, 2));//001001
        System.out.println(cs.crackSafe(3, 3));//"00222121112201202101102001000"
    }

    /**dfs, 20ms, no chart*/
    int len, n, k;
    public String crackSafe(int n, int k) { //n密码长度, k数字种类数
        StringBuilder sb = new StringBuilder();
        len = (int) Math.pow(k, n);
        this.n = n;this.k = k;

        Set<String> pwds = new HashSet<>();
        for (int i = 0; i < n; i++) sb.append('0'); //最前n-1位000
        pwds.add(sb.toString());
        dfs(sb, pwds);

        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, Set<String> pwds) {
        if (pwds.size() == len)
            return true;

        String pre = sb.substring(sb.length() - n + 1, sb.length()); //前(n-1)个数字
        for (int i = 0; i < k; i++) {
            String next = pre + i;
            if (!pwds.contains(next)) {
                pwds.add(next);
                sb.append(i);//自动to char
                if (dfs(sb, pwds)) return true;
                sb.deleteCharAt(sb.length() - 1);//回收清理
                pwds.remove(next);//回收清理
            }
        }
        return false;
    }


}
