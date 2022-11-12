package Stacks;

import java.util.Stack;

public class Parser {
    // converting infix expression to postfix expression


    public static void infixToPostfix(String str) {
        StringBuffer buffer = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push('(');
                continue;
            }
            if (c == ')') {
                // empty stack until we found the opening one
                while (stack.peek() != '(') buffer.append(stack.pop());

                stack.pop();
                continue;

            }


            // if this is  operator
            if (isOperator(c)) {
                 if(!stack.isEmpty() && stack.peek()=='(') {
                     stack.push(c);
                     continue;
                 }
                 // push operator if stack is empty
                if (stack.isEmpty()) stack.push(str.charAt(i));
                else {

                    while (!stack.isEmpty() && shouldPopLast(stack.peek(), c)) {
                        buffer.append(stack.pop());
                    }
                    stack.push(c);

                }

            }
            // if not the operator then directly append to the buffer
            else {
                buffer.append(str.charAt(i));
            }


        }
        while (!stack.isEmpty()) buffer.append(stack.pop());

        System.out.println("Infix : " + str + "\tPostfix :  " + buffer.toString());


    }


    private static boolean isOperator(char c) {
        return c == '^' || c == '*' || c == '/' || c == '+' || c == '-' || c=='(' || c==')';
    }



    private static boolean shouldPopLast(char last, char curr) {
        return getPriority(last) >= getPriority(curr);
    }

    private static char getPriority(char c) {
        if (c == '^') return 4;
        if (c == '*' || c == '/') return 3;
        if (c == '+' || c == '-') return 2;
        return 0; // this for the braces if we encounter , but now just dummy never encounter this stage
    }

    public static int evaluation(String exp){
        Stack<Integer> stack = new Stack<>();
        int ans =0;
        for(int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
             if(!isOperator(c)) stack.push(c-48);
             else{
                 int op1 = stack.pop();
                 int op2 = stack.pop();
                 if(c=='+') stack.push(op2 + op1);
                 else if(c=='-') stack.push(op2-op1);
                 else if(c=='*') stack.push(op2*op1);
                 else if(c=='/') stack.push(op2/op1);
             }
        }
        return stack.pop();
    }



}
