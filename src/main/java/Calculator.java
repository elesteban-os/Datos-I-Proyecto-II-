import tree.StackList;
import tree.ExpressionTree;

public class Calculator {

    /**
     * method that changes a given operation to a postfix notation
     * @param operation operation to create a tree of
     * @return postfix notation of the operation
     */
    public String getPostfix(String operation) {
        String[] characters = operation.split("");
        StackList stack = new StackList();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            switch (characters[i]) {
                case "(", "+", "-", "*", "/", "%" -> stack.push(characters[i]);
                case ")" -> {
                    output.append(stack.pop().getData());
                    if (stack.peek().getData().equals("(")) {
                        stack.pop();
                    }
                    output.append(",");
                }
                default -> {
                    boolean number = true;
                    while (number) {
                        if (!this.isNaN(characters[i])) {
                            output.append(characters[i]);
                            i++;
                        }
                        else {
                            number = false;
                        }
                    }
                    i--;
                    output.append(",");
                }
            }
        }
        if (stack.peek() != null) {
            output.append(stack.pop().getData());
        }
        return String.valueOf(output.deleteCharAt(output.length()-1));
    }

    /**
     * method to check if a String is an integer
     * @param s string to check
     * @return true if is not a number, false otherwise
     */
    public boolean isNaN(String s) {
        return switch (s) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> false;
            default -> true;
        };
    }


    public static void main(String[] args) {
        String operation = "((50*70)+(120/60))";
        Calculator calc = new Calculator();
        String postfix = calc.getPostfix(operation);
        System.out.println(postfix);
        ExpressionTree tree = new ExpressionTree();
        tree.create(postfix);
        System.out.println(tree.getResult());
    }
}
