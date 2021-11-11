package src.tree;

import src.tree.list.LinkedList;
import src.tree.list.Node;

public class QueueList {

    private LinkedList list = new LinkedList();

    /**
     * method to add an element to the stack
     * @param data element to add
     */
    public void enqueue(Object data) {
        this.list.addAtEnd(data);
    }

    /**
     * method to get and delete the first element of the stack
     * @return first element of the stack
     */
    public Node dequeue() {
        return this.list.delete();
    }

    /**
     * method to see the first element of the stack
     * @return first element of the stack
     */
    public Node peek() {
        return this.list.getHead();
    }
}
