package march2018;

import sun.reflect.generics.tree.Tree;

public class KthSmallestElementinBST {
    public static void main(String[] s) {
        KthSmallestElementinBST kbst = new KthSmallestElementinBST();
        TreeNode root = new TreeNode(1);
        System.out.println(kbst.kthSmallest(root, 1)); //[1] 1

        //[1,null,2]
        //2
        TreeNode root2 = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        root2.right = node21;
        System.out.println(kbst.kthSmallest(root2, 2));

        TreeNode root3 = new TreeNode(4);
        TreeNode node31 = new TreeNode(2);
        TreeNode node32 = new TreeNode(1);
        TreeNode node33 = new TreeNode(3);
        node31.left = node32;
        node31.right = node33;
        TreeNode node34 = new TreeNode(7);
        TreeNode node35 = new TreeNode(6);
        TreeNode node36 = new TreeNode(8);
        node35.left = new TreeNode(5);
        node34.left = node35;
        node34.right = node36;
        root3.left = node31;
        root3.right = node34;
        System.out.println(kbst.kthSmallest(root3, 1));
        System.out.println(kbst.kthSmallest(root3, 3));
        System.out.println(kbst.kthSmallest(root3, 4));
        System.out.println(kbst.kthSmallest(root3, 7));
        System.out.println(kbst.kthSmallest(root3, 8));

    }

     //Definition for a binary tree node.
    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    int ans, count; boolean found = false;
    public int kthSmallest1(TreeNode root, int k) {
        ans=0; count=1; found = false;
        kthCount(root, k);

        return ans;
    }

    private int kthCount(TreeNode node, int k) {
        if (found) return 0;

        int save = 0;
        if (node.left != null)
            save += kthCount(node.left, k);

        if (count + save == k) {
            ans = node.val;
            found = true;
            return save + 1;
        }else {
            save++; //+ self
        }

        if (node.right != null) {
            count += 1;// +node
            save += kthCount(node.right, k);
        }
        return save;
    }

    /**93.68%*/
    public int kthSmallest(TreeNode root, int k) {
        count = 0; ans = 0;found = false;
        kCount(root, k);
        return ans;
    }//中序遍历
    private void kCount(TreeNode root, int k) {
        if (found) return;//找到后跳过后续节点
        if (root.left != null)
            kCount(root.left, k);

        if (++count == k) {
            ans = root.val;
            found = true;
            return;
        }

        if (root.right != null)
            kCount(root.right, k);
    }
}
