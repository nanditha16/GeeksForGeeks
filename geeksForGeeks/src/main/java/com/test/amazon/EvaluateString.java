package com.test.amazon;

import java.util.Stack;

import com.algo.tree.BinaryTree;
import com.algo.tree.NodeTree;

/*
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 1. While there are still tokens to be read in,
 *  1.1 Get the next token.
 *  1.2 If the token is:
 *      1.2.1 A number: push it onto the value stack.
 *      1.2.2 A variable: get its value, and push onto the value stack.
 *      1.2.3 A left parenthesis: push it onto the operator stack.
 *      1.2.4 A right parenthesis:
 *        1 While the thing on top of the operator stack is not a 
 *          left parenthesis,
 *            1 Pop the operator from the operator stack.
 *            2 Pop the value stack twice, getting two operands.
 *            3 Apply the operator to the operands, in the correct order.
 *            4 Push the result onto the value stack.
 *        2 Pop the left parenthesis from the operator stack, and discard it.
 *      1.2.5 An operator (call it thisOp):
 *        1 While the operator stack is not empty, and the top thing on the
 *          operator stack has the same or greater precedence as thisOp,
 *          1 Pop the operator from the operator stack.
 *          2 Pop the value stack twice, getting two operands.
 *          3 Apply the operator to the operands, in the correct order.
 *          4 Push the result onto the value stack.
 *        2 Push thisOp onto the operator stack.
 *	2. While the operator stack is not empty,
 *   1 Pop the operator from the operator stack.
 *   2 Pop the value stack twice, getting two operands.
 *   3 Apply the operator to the operands, in the correct order.
 *   4 Push the result onto the value stack.
 *	3. At this point the operator stack should be empty, and the value
 *  stack should have only one value in it, which is the final result.
 */

public class EvaluateString {

	public static int evaluateInfixExp(String expression) {

		char[] tokens = expression.toCharArray();

		// Stack for numbers: 'values'
		Stack<Integer> values = new Stack<Integer>();

		// Stack for Operators: 'ops'
		Stack<Character> ops = new Stack<Character>();

		for (int i = 0; i < tokens.length; i++) {
			// Current token is a whitespace, skip it
			if (tokens[i] == ' ')
				continue;

			// Current token is a number, push it to stack for numbers
			if (tokens[i] >= '0' && tokens[i] <= '9') {
				StringBuffer sbuf = new StringBuffer();
				// There may be more than one digits in number
				while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
					sbuf.append(tokens[i++]);
				values.push(Integer.parseInt(sbuf.toString()));
			} // Current token is an opening brace, push it to 'ops'
			else if (tokens[i] == '(') {
				ops.push(tokens[i]);
			} // Closing brace encountered, solve entire brace
			else if (tokens[i] == ')') {
				while (ops.peek() != '(')
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				ops.pop();
			} // Current token is an operator.
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
				// While top of 'ops' has same or greater precedence to current
				// token, which is an operator. Apply operator on top of 'ops'
				// to top two elements in values stack
				while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));

				// Push current token to 'ops'.
				ops.push(tokens[i]);
			}

		}

		// Entire expression has been parsed at this point, apply remaining
		// ops to remaining values
		while (!ops.empty()) {
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
		}

		// Top of 'values' contains result, return it
		return values.pop();
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return false;
		else
			return true;
	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static int applyOp(char op, int b, int a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			if (b == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return a / b;
		}
		return 0;
	}

	// A utility function to return precedence of a given operator
	// Higher returned value means higher precedence
	public static int Prec(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	// funtion to check if character
	// is operator or not
	static boolean isOperator(char x) {
		switch (x) {
		case '+':
		case '-':
		case '/':
		case '*':
			return true;
		}
		return false;
	}

	/*
	 * Postfix expression:The expression of the form a b op. Infix expression:The
	 * expression of the form a op b
	 * 
	 */
	private static String infixToPostfix(String exp) {
		// initializing empty String for result
		String result = new String("");

		// initializing empty stack
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			// If the scanned character is an operand, add it to output.
			if (Character.isLetterOrDigit(c)) {
				result += c;
			} // If the scanned character is an '(', push it to the stack.
			else if (c == '(') {
				stack.push(c);
			} // If the scanned character is an ')', pop and output from the stack
				// until an '(' is encountered.
			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					result += stack.pop();
				}

				if (!stack.isEmpty() && stack.peek() != '(') {
					return "Invalid Expression"; // invalid expression
				} else {
					stack.pop();
				}

			} // an operator is encountered
			else {
				while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
					if (stack.peek() == '(')
						return "Invalid Expression";
					result += stack.pop();
				}
				stack.push(c);
			}
		}

		// pop all the operators from the stack
		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				return "Invalid Expression";
			result += stack.pop();
		}
		return result;
	}

	
	// Convert prefix to Postfix expression
	static String preToPost(String pre_exp) {

		Stack<String> s = new Stack<String>();

		// length of expression
		int length = pre_exp.length();

		// reading from right to left
		for (int i = length - 1; i >= 0; i--) {

			// check if symbol is operator
			if (isOperator(pre_exp.charAt(i))) {

				// pop two operands from stack
				String op1 = s.peek();
				s.pop();
				String op2 = s.peek();
				s.pop();

				// concat the operands and operator
				String temp = op1 + op2 + pre_exp.charAt(i);

				// Push String temp back to stack
				s.push(temp);
			}

			// if symbol is an operand
			else {
				// push the operand to the stack
				s.push(pre_exp.charAt(i) + "");
			}
		}

		// stack contains only the Postfix expression
		return s.peek();
	}

	/*
	 * Method to evaluate value of a postfix expression : O(n)
	 */
	static int evaluatePostfix(String exp) {
		// create a stack
		Stack<Integer> stack = new Stack<>();

		// Scan all characters one by one
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);

			if (c == ' ') {
				continue;
			}
			// If the scanned character is an operand (number here),
			// push it to the stack.
			else if (Character.isDigit(c)) {
				int n = 0;

				// extract the characters and store it in num
				while (Character.isDigit(c)) {
					n = n * 10 + (int) (c - '0');
					i++;
					c = exp.charAt(i);
				}
				i--;

				// push the number in stack
				stack.push(n);

				// stack.push(c - '0');

			}

			// If the scanned character is an operator, pop two
			// elements from stack apply the operator
			else {
				int val1 = stack.pop();
				int val2 = stack.pop();

				switch (c) {
				case '+':
					stack.push(val2 + val1);
					break;

				case '-':
					stack.push(val2 - val1);
					break;

				case '/':
					stack.push(val2 / val1);
					break;

				case '*':
					stack.push(val2 * val1);
					break;
				}
			}
		}
		return stack.pop();
	}

	// Convert postfix to Prefix expression 
    static String postToPre(String post_exp) 
    { 
        Stack<String> s = new Stack<String>(); 
  
        // length of expression 
        int length = post_exp.length(); 
  
        // reading from right to left 
        for (int i = 0; i < length; i++) { 
  
            // check if symbol is operator 
            if (isOperator(post_exp.charAt(i))) { 
  
                // pop two operands from stack 
                String op1 = s.peek(); 
                s.pop(); 
                String op2 = s.peek(); 
                s.pop(); 
  
                // concat the operands and operator 
                String temp = post_exp.charAt(i) + op2 + op1; 
  
                // Push String temp back to stack 
                s.push(temp); 
            } 
  
            // if symbol is an operand 
            else { 
  
                // push the operand to the stack 
                s.push(post_exp.charAt(i) + ""); 
            } 
        } 
  
        // stack[0] contains the Prefix expression 
        return s.peek(); 
    } 
    
 // Convert prefix to Infix expression 
    static String  preToInfix(String pre_exp) { 
    	 Stack<String> stack = new Stack<>();
         for (int i = pre_exp.length()-1; i >=0 ; i--) {
             char c = pre_exp.charAt(i);

             if(isOperator(c)){
                 String s1 = stack.pop();
                 String s2 = stack.pop();
                 String temp = "("+s1+c+s2+")";
                 stack.push(temp);
             }else{
                 stack.push(c+"");
             }
         }

         String result=stack.pop();

         return result;
    } 
    
	public static void main(String[] args) {
		System.out.println("Infix evaluation: " + EvaluateString.evaluateInfixExp("10 + 2 * 6"));
		System.out.println("Infix evaluation: " + EvaluateString.evaluateInfixExp("100 * 2 + 12"));
		System.out.println("Infix evaluation: " + EvaluateString.evaluateInfixExp("100 * ( 2 + 12 )"));
		System.out.println("Infix evaluation: " + EvaluateString.evaluateInfixExp("100 * ( 2 + 12 ) / 14"));

		String exp = "a+b*(c^d-e)^(f+g*h)-i";
		System.out.println("Infix to Postfix : " +infixToPostfix(exp));

		
		exp = "100 200 + 2 / 5 * 7 +";
		System.out.println("postfix evaluation: " + evaluatePostfix(exp));

		String pre_exp = "*-A/BC-/AKL"; 
	    System.out.println("PreFix to Postfix : " + preToPost(pre_exp)); 
	    
	    pre_exp = "*-A/BC-/AKL";
        System.out.println("PreFix to Infix : " + preToInfix(pre_exp)); 
 	   
        
	    String post_exp = "ABC/-AK/L-*"; 
        System.out.println("Postfix to Prefix : " + postToPre(post_exp)); 
        
       
        
	}

}
