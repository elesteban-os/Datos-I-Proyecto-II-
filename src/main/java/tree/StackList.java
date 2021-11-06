package tree;

import tree.list.LinkedList;
import tree.list.Node;

public class StackList {

    private LinkedList list = new LinkedList();

    /**
     * method to add an element to the stack
     * @param data element to add
     */
    public void push(Object data) {
        this.list.add(data);
    }

    /**
     * method to get and delete the first element of the stack
     * @return first element of the stack
     */
    public Node pop() {
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
