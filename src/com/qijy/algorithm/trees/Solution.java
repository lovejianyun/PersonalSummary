package com.qijy.algorithm.trees;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/*
 * @ Description   :  给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/21 8:43
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int i = maxDepth(root.left);
        int j = maxDepth(root.right);
        return Math.max(i,j)+1;
    }

    public int maxDepth1(TreeNode root){
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode treeNode = new TreeNode(20);
        root.right = treeNode;
        treeNode.left = new TreeNode(15);
        treeNode.right = new TreeNode(7);
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(root));
    }


}
