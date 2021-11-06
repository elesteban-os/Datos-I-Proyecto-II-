package tree;

public class ExpressionTree {
    private BinaryNode root;

    /**
     * Constructor that creates an ExpressionTree with its root as null
     */
    public ExpressionTree() {
        this.root = null;
    }

    /**
     * method that creates the ExpressionTree from a postfix notation of an operation
     * @param postfix postfix notation of an operation
     */
    public void create(String postfix) {
        String[] characters = postfix.split(",");
        StackList stack = new StackList();
        for (String character : characters) {
            switch (character) {
                case "+", "-", "*", "/", "%" -> {
                    BinaryNode right = (BinaryNode) stack.pop().getData();
                    BinaryNode left = (BinaryNode) stack.pop().getData();
                    stack.push(new BinaryNode(character, left, right));
                }
                default -> stack.push(new BinaryNode(character));
            }
        }
        this.root = (BinaryNode) stack.pop().getData();
    }

    /**
     * method to get the result of the ExpressionTree's expression
     * @return result of the tree's expression
     */
    public int getResult() {
        return this.solve(this.root);
    }

    /**
     * recursive method that solves expressions of subtrees
     * @param node root of the subtree to solve
     * @return result of subtree's expression
     */
    private int solve(BinaryNode node) {
        int result;
        switch (node.getCharacter()) {
            case "+" -> {
                result = solve(node.getLeft());
                result += solve(node.getRight());
            }
            case "-" -> {
                result = solve(node.getLeft());
                result -= solve(node.getRight());
            }
            case "*" -> {
                result = solve(node.getLeft());
                result *= solve(node.getRight());
            }
            case "/" -> {
                result = solve(node.getLeft());
                result /= solve(node.getRight());
            }
            case "%" -> {
                result = solve(node.getLeft());
                result %= solve(node.getRight());
            }
            default -> result = Integer.parseInt(node.getCharacter());
        }
        return result;
    }
}