package march2018;

/**214. 最短回文 仅在最前添加字符，能构成的最短回文是，返回此回文
 * For example:

 Given "aacecaaa", return "aaacecaaa".

 Given "abcd", return "dcbabcd".

 TODO 不太民白*/
public class ShortestPalindrome {
    public static void main(String[] s) {
        ShortestPalindrome sp = new ShortestPalindrome();

    }

    public String shortestPalindrome1(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString(); //预处理镜像字符串
        int[] table = getTable(temp);

        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s){ //相当于kmp中找next数组？
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0; //table[0] = 0
        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = table[i-1] + 1;
                index ++;
            }else{
                //match failed, we try to match a shorter substring
                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char
                    index ++ ;
                }

                table[i] = index;
            }

        }

        return table;
    }

    /*The idea is to use two anchors j and i to compare the String from beginning and end.
    使用两个锚点分别从头和从尾开始比较
If j can reach the end, the String itself is Palindrome. Otherwise, we divide the String by j, and get mid = s.substring(0, j) and suffix.
如果j可以走到尾部，那字符串本身就是回文。否则，我们在j处分割字符串，并将j前半段的中间下标作为 结果的后一部分
We reverse suffix as beginning of result and recursively call shortestPalindrome to get result of mid then append suffix to get result.
我们反转这个“后一部分”将他作为结果的开头，然后递归地调用这个方法，得到这个方法返回结果的中间部分，并在一起作为结果返回*/
    public String shortestPalindrome(String s) { //aacecaaa
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; } //6
        }
        if (j == s.length()) { return s; } //no
        String suffix = s.substring(j); //aa
        return new StringBuffer(suffix).reverse().toString()
                + shortestPalindrome(s.substring(0, j)) + suffix; //aa + sp(aacecaa) + aa
    }
}
