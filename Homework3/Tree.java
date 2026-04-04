public class Tree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public Tree() {
        root = null;
        size = 0;
    }

    public Tree(Node<T> root) {
        this.root = root;
        size = countNodes(root);
    }

    public void add(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            addNode(root, value);
        }
        size = countNodes(root);
    }

    private void addNode(Node<T> current, T value) {
        if (current.getValue().compareTo(value) > 0) {
            if (current.getLeft() == null) {
                Node<T> newNode = new Node<>(value, current);
                current.setLeft(newNode);
            } else {
                addNode(current.getLeft(), value);
            }
        } else {
            if (current.getRight() == null) {
                Node<T> newNode = new Node<>(value, current);
                current.setRight(newNode);
            } else {
                addNode(current.getRight(), value);
            }
        }
    }

    public Node<T> get(T value) {
        if (root == null) {
            return null;
        }
        return getNode(root, value);
    }

    private Node<T> getNode(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (current.getValue().equals(value)) {
            return current;
        }

        if (current.getValue().compareTo(value) > 0) {
            return getNode(current.getLeft(), value);
        } else {
            return getNode(current.getRight(), value);
        }
    }

    public void remove(T value) {
        Node<T> nodeToDelete = get(value);

        if (nodeToDelete == null) {
            return;
        }

        deleteNode(nodeToDelete);
        size = countNodes(root);
    }

    private void deleteNode(Node<T> node) {
        if (node.isEnd()) {
            if (!node.hasParent()) {
                root = null;
            } else {
                Node<T> parent = node.getParent();
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                node.setParent(null);
            }
            return;
        }


        if (node.getLeft() == null || node.getRight() == null) {
            Node<T> child = node.getOnlyChild();

            if (!node.hasParent()) {
                root = child;
                child.setParent(null);
            } else {
                Node<T> parent = node.getParent();
                if (parent.getLeft() == node) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
                child.setParent(parent);
            }
            node.setParent(null);
            return;
        }

        Node<T> smallest = node.getRight();
        while (smallest.getLeft() != null) {
            smallest = smallest.getLeft();
        }

        T smallestValue = smallest.getValue();
        deleteNode(smallest);
        node.setValue(smallestValue);
    }

    private int countNodes(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    public void printPreOrder() {
        doPreOrder(root);
        System.out.println();
    }

    public void printInOrder() {
        doInOrder(root);
        System.out.println();
    }

    public void printPostOrder() {
        doPostOrder(root);
        System.out.println();
    }

    private void doPreOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        doPreOrder(node.getLeft());
        doPreOrder(node.getRight());
    }

    private void doInOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        doInOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        doInOrder(node.getRight());
    }

    private void doPostOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        doPostOrder(node.getLeft());
        doPostOrder(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    public Node<T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}