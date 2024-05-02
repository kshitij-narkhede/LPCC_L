import java.util.*;


public class ThreeAddressCode {
    private List<String[]> code;
    private int tempCount;


    public ThreeAddressCode() {
        code = new ArrayList<>();
        tempCount = 0;
    }


    public String newTemp() {
        String tempName = "t" + tempCount;
        tempCount++;
        return tempName;
    }


    public void genCode(String op, String arg1, String arg2, String result) {
        String[] instruction = {op, arg1, arg2, result};
        code.add(instruction);
    }


    public String infixToPostfix(String expression) {
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);


        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();


        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            char ch = token.charAt(0);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                postfix.append(token).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Discard '('
            } else {
                while (!stack.isEmpty() && precedence.getOrDefault(stack.peek(), 0) >= precedence.getOrDefault(ch, 0)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString().trim();
    }


    public String generateExpressionCode(String expression) {
        String postfixExpression = infixToPostfix(expression);
        String[] tokens = postfixExpression.split("\\s+");
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            char ch = token.charAt(0);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                stack.push(token);
            } else {
                String op = token;
                String arg2 = stack.pop();
                String arg1 = stack.pop();
                String result = newTemp();
                genCode(op, arg1, arg2, result);
                stack.push(result);
            }
        }
        return stack.pop();
    }


    public String displayReadableCode() {
        StringBuilder readableCode = new StringBuilder();
        for (String[] instruction : code) {
            String readableInstruction = instruction[3] + " = " + instruction[1] + " " + instruction[0] + " " + instruction[2];
            readableCode.append(readableInstruction).append("\n");
        }
        return readableCode.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the expression: ");
        String expression = scanner.nextLine();
        ThreeAddressCode tac = new ThreeAddressCode();
        String result = tac.generateExpressionCode(expression);
        System.out.println("\nReadable code:");
        System.out.println(tac.displayReadableCode());
    }
}
