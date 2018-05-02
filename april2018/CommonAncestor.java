package april2018;

public class CommonAncestor {
    class TreeNode {
        TreeNode left, right;
        int value;
    }

    //使用“树节点”存储的二叉树
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //使用数组顺序存储的二叉树
    /**@return value of ancestor*/
    public int lca(int[] tree, int i, int j) {
        if (tree[i] != -1 && tree[j] != -1) { //节点存在
            while (i != j) {
                if (i > j)
                    i /= 2;
                else
                    j /= 2;
            }
            return tree[i];
        }

        return -1;
    }
}
