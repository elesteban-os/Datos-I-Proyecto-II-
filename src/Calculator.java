package src;

import src.tree.QueueList;
import src.tree.StackList;
import src.tree.ExpressionTree;

public class Calculator {

    /**
     * method that adds parenthesis to a given operation depending on the order of operations
     * @param operation operation to create a tree of
     * @return operation with parenthesis
     */
    public String addParenthesis(String operation) {
        String[] characters = operation.split("");
        StackList stack = new StackList();
        String lastOperator = "";
        for (int i = 0; i < characters.length; i++) {
            switch (characters[i]) {
                case "+", "-", "*", "/", "%" -> {
                    lastOperator = characters[i];
                }
                case "(" -> {
                    boolean enclosed = true;
                    StringBuilder expression = new StringBuilder();
                    while (enclosed) {
                        expression.append(characters[i]);
                        if (characters[i].equals(")")) {
                            enclosed = false;
                        }
                        i++;
                    }
                    i--;
                    stack.push(expression.toString());
                }
                default -> {
                    boolean isANumber = true;
                    StringBuilder number = new StringBuilder();
                    while (isANumber && i < characters.length) {
                        if (!this.isNaN(characters[i])) {
                            number.append(characters[i]);
                            i++;
                        } else {
                            isANumber = false;
                            i--;
                        }
                    }
                    stack.push(number.toString());
                    if (!lastOperator.equals("")) {
                        String operand2 = (String) stack.pop().getData();
                        String operand1 = (String) stack.pop().getData();
                        switch (lastOperator) {
                            case "*", "/", "%" -> {
                                stack.push("(" + operand1 + lastOperator + operand2 + ")");
                            }
                            default -> {
                                stack.push(operand1);
                                stack.push(lastOperator);
                                stack.push(operand2);
                            }
                        }
                    }
                }
            }
        }
        QueueList queue = new QueueList();
        while (stack.peek() != null) {
            queue.enqueue(stack.pop().getData());
        }
        while (queue.peek() != null) {
            stack.push(queue.dequeue().getData());
        }
        StringBuilder string = new StringBuilder();
        string.append("(");
        while (stack.peek() != null) {
            string.append(stack.pop().getData());
        }
        string.append(")");
        return string.toString();
    }

    /**
     * method that changes a given operation to a postfix notation
     * @param operation operation to create a tree of
     * @return postfix notation of the operation
     */
    public String getPostfix(String operation) {
        String parenthesised = this.addParenthesis(operation);
        String[] characters = parenthesised.split("");
        StackList stack = new StackList();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            switch (characters[i]) {
                case "(", "+", "-", "*", "/", "%" -> stack.push(characters[i]);
                case ")" -> {
                    output.append(stack.pop().getData());
                    stack.pop();
                    output.append(",");
                }
                default -> {
                    boolean isANumber = true;
                    while (isANumber && i < characters.length) {
                        if (!this.isNaN(characters[i])) {
                            output.append(characters[i]);
                            i++;
                        } else {
                            isANumber = false;
                            i--;
                        }
                    }
                    output.append(",");
                }
            }
        }
        output.deleteCharAt(output.length() - 1);
        if (String.valueOf(output.charAt(output.length() - 1)).equals("(")) {
            output.deleteCharAt(output.length() - 1);
            output.deleteCharAt(output.length() - 1);
        }
        return output.toString();
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

    /**
     * method to check if a String contains only numbers and accepted operators
     * @param s string to check
     * @return true if characters are accepted, false otherwise
     */
    public boolean validate(String s) {
        String[] characters = s.split("");
        boolean valid = true;
        for (String character : characters) {
            if (valid) {
                valid = switch (character) {
                    case "(", ")", "+", "-", "*", "/", "%" -> true;
                    default -> !this.isNaN(character);
                };
            }
        }
        return valid;
    }
}
