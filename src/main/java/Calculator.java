import tree.BinaryNode;
import tree.StackList;
import tree.ExpressionTree;

public class Calculator {

    /**
     * method that changes a given operation to a postfix notation
     * @param operation operation to create a tree of
     * @return postfix notation of the operation
     */
    public String transform(String operation) {
        String[] characters = operation.split("");
        StackList stack = new StackList();
        StringBuilder output = new StringBuilder();
        for (String character : characters) {
            switch (character) {
                case "(":
                    stack.push("(");
                    break;
                case "+":
                    stack.push("+");
                    break;
                case "-":
                    stack.push("-");
                    break;
                case "*":
                    stack.push("*");
                    break;
                case "/":
                    stack.push("/");
                    break;
                case "%":
                    stack.push("%");
                    break;
                case ")":
                    output.append(stack.pop().getData());
                    if (stack.peek().getData().equals("(")) {
                        stack.pop();
                    }
                    break;
                default:
                    output.append(character);
                    break;
            }
        }
        if (stack.peek() != null) {
            output.append(stack.pop().getData());
        }
        return String.valueOf(output);
    }

    /**
     * method that creates a tree from a postfix notation of and operation
     * @param postfix postfix notation of an operation
     * @return expression tree for an operation
     */
    public ExpressionTree getTree(String postfix) {
        String[] characters = postfix.split("");
        StackList stack = new StackList();
        for (String character : characters) {
            switch (character) {
                case "+":
                    BinaryNode plus = new BinaryNode("+");
                    plus.setRight((BinaryNode) stack.pop().getData());
                    plus.setLeft((BinaryNode) stack.pop().getData());
                    stack.push(plus);
                    break;
                case "-":
                    BinaryNode minus = new BinaryNode("-");
                    minus.setRight((BinaryNode) stack.pop().getData());
                    minus.setLeft((BinaryNode) stack.pop().getData());
                    stack.push(minus);
                    break;
                case "*":
                    BinaryNode times = new BinaryNode("*");
                    times.setRight((BinaryNode) stack.pop().getData());
                    times.setLeft((BinaryNode) stack.pop().getData());
                    stack.push(times);
                    break;
                case "/":
                    BinaryNode divided = new BinaryNode("/");
                    divided.setRight((BinaryNode) stack.pop().getData());
                    divided.setLeft((BinaryNode) stack.pop().getData());
                    stack.push(divided);
                    break;
                case "%":
                    BinaryNode module = new BinaryNode("%");
                    module.setRight((BinaryNode) stack.pop().getData());
                    module.setLeft((BinaryNode) stack.pop().getData());
                    stack.push(module);
                    break;
                default:
                    stack.push(new BinaryNode(character));
                    break;
            }
        }
        return new ExpressionTree((BinaryNode) stack.pop().getData());
    }

    public static void main(String[] args) {
        String operation = "(a+(b*c))+(((d*e)+f)*g)";
        Calculator calc = new Calculator();
        String postfix = calc.transform(operation);
        ExpressionTree tree = calc.getTree(postfix);
    }
}
