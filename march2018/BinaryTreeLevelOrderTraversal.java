package march2018;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 * Binary Tree Level Order Traversal 二叉树层次顺序遍历
 */
public class BinaryTreeLevelOrderTraversal {/**83.14%,已最优*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        traversal(result, root, 0);
        return result;
    }

    void traversal(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null)
            return;
        if (level == list.size())
            list.add(new ArrayList<>());
        list.get(level).add(node.val);
        traversal(list, node.left, level + 1);
        traversal(list, node.right, level + 1);
    }
}
