package march2018;

/**
 * Created by Administrator on 2018/3/1.
 * 101.判断一颗二叉树是否对称
 * easy题，要点：条件判断的语句优化
 */
//Definition for a binary tree node.
/*public*/ class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

public class SymmetricTree {
    public static void main(String[] s) {
        SymmetricTree st = new SymmetricTree();
    }

    /**34.44%*/
    public boolean isSymmetric1(TreeNode root) {
        if (root == null)
            return true;
        return compare1(root.left, root.right);
    }

    boolean compare1(TreeNode l, TreeNode r) {
        if ((l == null && r != null) || (l != null && r == null))
            return false;
        if (l == null && r == null)
            return true;
        if (l.val == r.val)//accept
            return compare1(l.left, r.right) && compare1(l.right, r.left);
        else
            return false;
    }


    /**20.49%???*/
    public boolean isSymmetric(TreeNode root) {
        return root == null || compare(root.left, root.right);
    }

    boolean compare(TreeNode l, TreeNode r) {
        if (l == null && r == null)
            return true;
        else
            return l != null && r != null && l.val == r.val
                    && compare(l.left, r.right) && compare(l.right, r.left);
    }
}

class PrimeSolution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return func(root.left, root.right);
    }

    public boolean func(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        else if(t1 != null && t2 != null && t1.val == t2.val) {
            return func(t1.left, t2.right) && func(t1.right, t2.left);
        }
        else
            return false;
    }
}
