/**
 * Doubly-linked node for linear data structures.
 * @author Alex Taylor CS221-Fa23
 */
public class Node<T> {
    private T element;
    private Node<T> nextNode;
    private Node<T> previousNode;

    /**
     * Initialize node with given element.
     * @param element
     */
    public Node(T element) {
        this(element, null, null);
    }

    /**
     * Initialize node with given element and next node.
     * @param element
     * @param nextNode
     */
    public Node(T element, Node<T> nextNode) {
        this(element, nextNode, null);
    }

    /**
     * Initialize node with given element, next node, and previous node.
     * @param element
     * @param nextNode
     * @param previousNode
     */
    public Node(T element, Node<T> nextNode, Node<T> previousNode) {
        this.element = element;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    /**
     * Return element.
     * @return element
     */
    public T getElement() {
        return element;
    }

    /**
     * Update element.
     * @param element
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Return next node.
     * @return nextNode
     */
    public Node<T> getNextNode() {
        return nextNode;
    }

    /**
     * Update next node.
     * @param nextNode
     */
    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Return previous node.
     * @return previousNode
     */
    public Node<T> getPreviousNode() {
        return previousNode;
    }

    /**
     * Update previous node.
     * @param previousNode
     */
    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    

}
