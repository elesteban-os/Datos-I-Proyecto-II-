package tree.list;

public class LinkedList {

    private Node head;

    /**
     * Constructor to create a LinkedList with its head as null
     */
    public LinkedList() {
        this.head = null;
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
    public void add(Object data) {
        Node newCharacter = new Node (data);
        newCharacter.setNext(this.head);
        this.head = newCharacter;
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
