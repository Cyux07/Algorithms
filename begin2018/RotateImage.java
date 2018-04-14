import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/31.
 * 48.Rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place,
 */
public class RotateImage {
    public static void main(String[] s) {
        int[][] result = new int[][]{{1,2,3}, {4, 5, 6}, {7, 8, 9}};
        new RotateImage().rotate(result);
        for (int[] re : result)
            System.out.println(Arrays.toString(re));
    }

    //1.main corner line
    //2.left right
    /**10.31%*/
    public void rotate(int[][] matrix) {
        int i = 0, j = 0, n = matrix.length, swap = 0;
        for (;i < n; i++) {
            for (j = i + 1; j < n; j++) {
                swap = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = swap;
            }
        }

        for (i = 0;i < n; i++) {
            for (j = 0; j < n/2; j++) {
                swap = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = swap;
            }
        }
    }

    /**prime Solution*/
    public void rotate2(int[][] matrix) {
        int l = matrix.length;
        if (l == 1) return;
        for (int i = 0; i < l/2; i++) {
            int first = i;
            int last = l-1-i;
            for (int j=first; j<last; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[l-1-j][i];
                matrix[l-1-j][i] = matrix[l-1-i][l-1-j];
                matrix[l-1-i][l-1-j] = matrix[j][l-1-i];
                matrix[j][l-1-i] = temp;
            }
        }
    }
}
