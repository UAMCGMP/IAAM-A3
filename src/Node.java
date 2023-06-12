public class Node {
    public String featureName;
    public Node leftChild;
    public Node rightChild;
    public Object label;

    public Node(String featureName, Node leftChild, Node rightChild, Object label) {
        this.featureName = featureName;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.label = label;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}

