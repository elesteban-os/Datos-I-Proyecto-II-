package tree.list;

public class LinkedList {

    private Node head;
    private Node tail;

    /**
     * Constructor to create a LinkedList with its head as null
     */
    public LinkedList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * method to get the LinkedList's head
     * @return LinkedList's head
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * method to add an element at the front of the LinkedList
     * @param data element to add
     */
    public void addAtFront(Object data) {
        Node newData = new Node (data);
        if (this.isEmpty()) {
            this.head = newData;
            this.tail = newData;
        } else {
            newData.setNext(this.head);
            this.head = newData;
        }
    }

    /**
     * method to add an element at the end of the LinkedList
     * @param data element to add
     */
    public void addAtEnd(Object data) {
        Node newData = new Node (data);
        if (this.isEmpty()) {
            this.head = newData;
            this.tail = newData;
        } else {
            this.tail.setNext(newData);
            this.tail = newData;
        }
    }

    /**
     * method to delete and return the first element of the LinkedList
     * @return LinkedList's head, which is deleted
     */
    public Node delete() {
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.getNext();
            return temp;
        } else {
            return null;
        }
    }
}
