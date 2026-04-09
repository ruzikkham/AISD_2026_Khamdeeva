package Homework6;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Task4 {

    static class TreeNode {
        int value;
        List<TreeNode> children;
        int sumOfChildren;

        TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
            this.sumOfChildren = 0;
        }
    }

    static int calculateSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int sumOfAllDescendants = 0;

        for (int i = 0; i < node.children.size(); i++) {
            TreeNode child = node.children.get(i);

            int childTreeSum = calculateSum(child);

            sumOfAllDescendants = sumOfAllDescendants + child.value + childTreeSum;
        }

        node.sumOfChildren = sumOfAllDescendants;

        return node.value + sumOfAllDescendants;
    }

    static void printTree(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println("Узел " + node.value + " → сумма потомков = " + node.sumOfChildren);

        // Печатаем всех детей
        for (int i = 0; i < node.children.size(); i++) {
            printTree(node.children.get(i), level + 1);
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);


        root.children.add(node5);
        root.children.add(node3);

        node5.children.add(node2);
        node5.children.add(node4);

        node3.children.add(node1);


        calculateSum(root);


        System.out.println("Результат:\n");
        printTree(root, 0);


    }
}