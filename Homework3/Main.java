public class Main {
    public static void main(String[] args) {

        Tree<Double> tree = new Tree<>();

        tree.add(25.5);
        tree.add(15.3);
        tree.add(35.7);
        tree.add(10.1);
        tree.add(20.8);
        tree.add(30.2);
        tree.add(40.9);

        System.out.println("=======================");
        System.out.println("Размер дерева: " + tree.getSize()); // 7
        System.out.println("=======================");

        System.out.println("Корень: " + tree.getRoot().getValue()); // 25.5
        System.out.println("Левый от корня: " + tree.getRoot().getLeft().getValue()); // 15.3
        System.out.println("Правый от корня: " + tree.getRoot().getRight().getValue()); // 35.7
        System.out.println("Левый от 15.3: " + tree.getRoot().getLeft().getLeft().getValue()); // 10.1
        System.out.println("Правый от 15.3: " + tree.getRoot().getLeft().getRight().getValue()); // 20.8
        System.out.println("Левый от 35.7: " + tree.getRoot().getRight().getLeft().getValue()); // 30.2
        System.out.println("Правый от 35.7: " + tree.getRoot().getRight().getRight().getValue()); // 40.9

        System.out.println("=======================");

        System.out.println("Найти 20.8: " + tree.get(20.8));
        System.out.println("Найти 30.2: " + tree.get(30.2));
        System.out.println("Найти 50.0: " + tree.get(50.0));

        System.out.println("=======================");

        System.out.print("PreOrder: ");
        tree.printPreOrder(); // 25.5 15.3 10.1 20.8 35.7 30.2 40.9

        System.out.print("InOrder: ");
        tree.printInOrder(); // 10.1 15.3 20.8 25.5 30.2 35.7 40.9

        System.out.print("PostOrder: ");
        tree.printPostOrder(); // 10.1 20.8 15.3 30.2 40.9 35.7 25.5

        System.out.println("=======================");

        tree.remove(10.1);
        System.out.println("После удаления 10.1 (лист):");
        System.out.print("InOrder: ");
        tree.printInOrder(); // 15.3 20.8 25.5 30.2 35.7 40.9

        System.out.println("=======================");

        tree.remove(35.7);
        System.out.println("После удаления 35.7 (узел с одним ребенком):");
        System.out.print("InOrder: ");
        tree.printInOrder(); // 15.3 20.8 25.5 30.2 40.9

        System.out.println("=======================");

        tree.remove(25.5);
        System.out.println("После удаления 25.5 (корень с двумя детьми):");
        System.out.print("InOrder: ");
        tree.printInOrder(); // 15.3 20.8 30.2 40.9

        System.out.println("=======================");

        System.out.println("Новый корень: " + tree.getRoot().getValue());
        if (tree.getRoot().getLeft() != null) {
            System.out.println("Левый от корня: " + tree.getRoot().getLeft().getValue());
        }
        if (tree.getRoot().getRight() != null) {
            System.out.println("Правый от корня: " + tree.getRoot().getRight().getValue());
        }

        System.out.println("=======================");
        System.out.println("Размер дерева в конце: " + tree.getSize()); // 4
        System.out.println("=======================");
    }
}