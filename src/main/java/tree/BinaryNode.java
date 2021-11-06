package tree;

public class BinaryNode {
    private String character;
    private BinaryNode left;
    private BinaryNode right;

    /**
     * Constructor that creates a Node for the tree with its character and children as nulls
     * @param character character for this BinaryNode
     */
    public BinaryNode(String character) {
        this(character, null, null);
    }

    /**
     * Constructor that creates a Node for the tree with its character and given children
     * @param character character for this BinaryNode
     * @param left left child for this BinaryNode
     * @param right right child for this BinaryNode
     */
    public BinaryNode(String character, BinaryNode left, BinaryNode right) {
        this.character = character;
        this.left = left;
        this.right = right;
    }

    /**
     * method to get the character of this BinaryNode
     * @return this BinaryNode's character
     */
    public String getCharacter() {
        return this.character;
    }

    /**
     * method to set a left child for this BinaryNode
     * @param left left child for this BinaryNode
     */
    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    /**
     * method to get the left child of this BinaryNode
     * @return this BinaryNode's left child
     */
    public BinaryNode getLeft() {
        return left;
    }

    /**
     * method to set a right child for this BinaryNode
     * @param right right child for this BinaryNode
     */
    public void setRight(BinaryNode right) {
        this.right = right;
    }

    /**
     * method to get the right child of this BinaryNode
     * @return this BinaryNode's right child
     */
    public BinaryNode getRight() {
        return right;
    }
}
