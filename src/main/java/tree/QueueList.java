package tree;

import tree.list.LinkedList;
import tree.list.Node;

public class QueueList {

    private LinkedList list = new LinkedList();

    /**
     * method to add an element to the queue
     * @param data element to add
     */
    public void enqueue(Object data) {
        this.list.addAtEnd(data);
    }

    /**
     * method to get and delete the first element of the queue
     * @return first element of the stack
     */
    public Node dequeue() {
        return this.list.delete();
    }

    /**
     * method to see the first element of the queue
     * @return first element of the stack
     */
    public Node peek() {
        return this.list.getHead();
    }
}
