package april2018;

/**将一个字符串转换成锯齿形：
 * 如"PAYPALISHIRING"：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 给出字符串和，行数，按行输出 “转化成n行锯齿状”的字符串
 * 有点难理解，再给出一个理解，还是这个字符串，四行是这样：
 * P      I     N
 * A    L S   I G
 * Y  A   H  R
 * P      I
 * 那么输出为："PINALSIGYAHRPI"*/

/**
 * 测试用例检查！*/
public class ZigZagConversion {
    /**27.97%...时间O(n), 空间O(kn), k?...(row=4时，k=2*/
    public String convert1(String s, int numRows) {
        if (numRows == 1 || s.length() <= 1)
            return s;

        int len = s.length(), maxCols = (len / ((numRows - 1) * 2) + 1) * (numRows-1); //多一个zigzag
        char[][] zigzag = new char[numRows][maxCols];

        int row = 0, col = 0;
        boolean rowTurn = true;
        for (char c : s.toCharArray()) {
            zigzag[row][col] = c;
            if (rowTurn) {
                row++;
                if (row == numRows) {
                    rowTurn = false;
                    row = numRows - 2; //move back
                    col++; //move to next location
                }
            }else {
                if (row > 0) {
                    col++;
                    row--;
                }else {
                    rowTurn = true;
                    row++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< zigzag.length; i++)
            for (int j = 0; j < zigzag[i].length; j++)
                if (zigzag[i][j] != 0)
                    sb.append(zigzag[i][j]);
        return sb.toString();
    }

    /**68.44% 59ms 时间O(n), 空间O(1)*/
    public String convert(String s, int numRows) {
        if (s.length() < 2 || numRows <2 || numRows >= s.length())
            return s;

        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        int len = s.length();
        int interval =(numRows - 1) * 2, period = len / ((numRows - 1) * 2) + 1;//;每一轮拉链的元素数量,总轮数 maxCols = (len / ((numRows - 1) * 2) + 1) * (numRows-1); // one more
        int row = 0, count = 0; //行数行间上下连续，使用元素计数
        int i = 0;
        while (count < len) {
            while (i < period) {
                if (i * interval + row < len) { //每行相隔一轮地添加元素
                    sb.append(cs[i * interval + row]);
                    count++;
                }
                i++; //中间斜着的拉链元素，最顶行和最低行没有
                if (row != 0 && row != numRows - 1 && i*interval - row < len) {
                    sb.append(cs[i*interval - row]);
                    count++;
                }
            }
            i = 0;
            row++;
        }

        return sb.toString();
    }

    /**55ms*/
    public String convertGood(String s, int numRows) {
        if (s.length() < 2 || numRows <2 || numRows >= s.length())
            return s;

        // the difference of indices of two consecutive char at first row
        int interval  = 2*(numRows -1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<numRows; i++) {
            appendOneRow(numRows, interval, i, s, sb);
        }
        return sb.toString();
    }

    public void appendOneRow(int numRows, int interval, int rowIndex, String s, StringBuilder sb) {
        int currCharIndex = rowIndex;
        // first or last row
        if (rowIndex == 0 || rowIndex == numRows -1){
            while(currCharIndex < s.length()){
                sb.append(s.charAt(currCharIndex));
                currCharIndex +=interval;
            }
        } else {
            while(currCharIndex < s.length() && currCharIndex + 2*(numRows - 1 -rowIndex) < s.length()){
                sb.append(s.charAt(currCharIndex));
                sb.append(s.charAt(currCharIndex + 2*(numRows - 1 -rowIndex)));
                currCharIndex +=interval;
            }
            if (currCharIndex < s.length() && currCharIndex + 2*(numRows - 1 -rowIndex) >= s.length())
                sb.append(s.charAt(currCharIndex));
        }
    }

    /**最速 43ms*/
    public String convertPrime(String s, int numRows) {

        if (s == null || s.length() == 0 || numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        final int length = s.length(), step = (2 * numRows) - 2;
        final char[] chars = s.toCharArray();
        final StringBuilder result = new StringBuilder(length);

        //First row
        for (int i = 0; i < length; i += step) {
            result.append(chars[i]);
        }

        //Rows between
        for (int rowIndex = 1; rowIndex < (numRows - 1); rowIndex++) {
            int baseCharIndex = rowIndex;
            int neighbourCharIndex = step - rowIndex;

            while (baseCharIndex < length) {
                result.append(chars[baseCharIndex]);
                baseCharIndex += step;

                if (neighbourCharIndex < length) {
                    result.append(chars[neighbourCharIndex]);
                    neighbourCharIndex += step;
                }
            }
        }

        //Last row
        for (int i = (numRows - 1); i < length; i += step) {
            result.append(chars[i]);
        }

        return result.toString();
    }

    public static void main(String[] s) {
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 3)); //PAHNAPLSIIGYIR
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 4)); //PINALSIGYAHRPI
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 5)); //PHASIYIRPLIGAN

        System.out.println(new ZigZagConversion().convert("ABCDE", 4)); //ABCED
    }
}
