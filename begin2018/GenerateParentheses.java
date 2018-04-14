import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */
public class GenerateParentheses {
    public static void main(String[] s) {
        System.out.print(Arrays.toString(new GenerateParentheses().generateParenthesis(3).toArray()));
    }


    /**10.61%*/
    List<String> list = new ArrayList<String>();
    int max;
    public List<String> generateParenthesis(int n) {
        max = n;
        backtrack("", 0, 0);
        return list;
    }

    /**num of left quart & num of right quart*/
    /**upgrade: with StringBuilder or char[]*/
    private void backtrack(String str, int left, int right) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (left < max) {
            backtrack(str + "(", left + 1, right);
        }

        if (left > right) {
            backtrack(str + ")", left, right + 1);
        }
    }
}
