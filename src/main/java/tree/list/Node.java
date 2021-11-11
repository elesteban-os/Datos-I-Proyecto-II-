package tree.list;

public class Node {
    private Object data;
    private Node next;

    /**
     * Constructor that creates a Node with an Object and next as null
     * @param data Object for this Node
     */
    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    /**
     * method that returns this Node's Object
     * @return Node's Object
     */
    public Object getData() {
        return this.data;
    }

    /**
     * method to set this Node's next Node
     * @param next Node that this one will point
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * returns the Node that this one is pointing to
     * @return next Node
     */
    public Node getNext() {
        return this.next;
    }
}
