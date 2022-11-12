package Stacks;

import java.util.Stack;

public class Problems {

    public static String removeConsecutiveDuplicates(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        char c = stringBuffer.charAt(0);
        int count = 1;
        while (count < stringBuffer.length()) {
            if (stringBuffer.charAt(count) == c) {
                stringBuffer.deleteCharAt(count);
                continue;
            }
            c = stringBuffer.charAt(count);
            count++;
        }


        return stringBuffer.toString();

    }

    //Function to remove pair of duplicates from given string using Stack.
    public static String removePair(String str) {
        StringBuilder stringBuffer = new StringBuilder();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (st.isEmpty()) st.push(str.charAt(i));
            else if (st.peek() == str.charAt(i)) {
                st.pop();
            } else {
//                stringBuffer.append(st.pop());
                st.push(str.charAt(i));
            }

        }

        while (!st.empty()) {
            stringBuffer.append(st.pop());
        }


        return stringBuffer.reverse().toString();
    }

    // Maximum area in histogram
    public static int getMaxHistogramArea(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        /*stack.push(0);*/
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int itemIndex = stack.pop();
                int tempRes = arr[itemIndex] * (stack.isEmpty() ? i : (i - stack.peek()) - 1);
                res = Math.max(res, tempRes);
            }
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            int tp = stack.pop();
            int tempRes = arr[tp] * (stack.isEmpty() ? n : (n - stack.peek() - 1));
            res = Math.max(res, tempRes);
        }

        return res;


    }

    // celebrity problem
    public static int celebrity(int M[][], int n) {
        Stack<Integer> stack = new Stack<>();

        // put all the celebrity in the stack
        for (int i = n - 1; i > -1; i--) stack.push(i);

        // check whether they know each other
        while (!stack.isEmpty() && stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (aKnowsb(M, a, b)) stack.push(b);
            else stack.push(a);
        }

        if (stack.isEmpty()) return -1;
        else {
            int zeros = 0;
            int ones = 0;
            int candidate = stack.pop();
            for (int i = 0; i < n; i++) {
                if (M[candidate][i] == 0)
                    zeros++;
                if (M[i][candidate] == 1)
                    ones++;
            }
            if (zeros != n || ones != n - 1)
                return -1;

            return candidate;

        }


    }

    private static boolean aKnowsb(int M[][], int a, int b) {
        return M[a][b] == 1;
    }

    // maximum of minimum  of every window size in the array


}
