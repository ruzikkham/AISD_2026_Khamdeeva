class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    public boolean isEnd() {
        return left == null && right == null;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> node) {
        this.left = node;
        if (node != null) {
            node.parent = this;
        }
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> node) {
        this.right = node;
        if (node != null) {
            node.parent = this;
        }
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> node) {
        this.parent = node;
    }

    public void removeLeft() {
        this.left = null;
    }

    public void removeRight() {
        this.right = null;
    }

    public void disconnectFromParent() {
        if (parent != null) {
            if (parent.left == this) {
                parent.left = null;
            }
            if (parent.right == this) {
                parent.right = null;
            }
        }
        this.parent = null;
    }

    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }

    public boolean isRightChild() {
        return parent != null && parent.right == this;
    }

    public Node<T> getOnlyChild() {
        if (left != null && right == null) {
            return left;
        }
        if (right != null && left == null) {
            return right;
        }
        return null;
    }

    public void swapData(Node<T> other) {
        T temp = this.value;
        this.value = other.value;
        other.value = temp;
    }
}