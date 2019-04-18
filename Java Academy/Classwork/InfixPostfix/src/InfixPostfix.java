/*
 * Course ID: EYF-649
 * Submission type: Assignment DS02_InfixPostfix
 * Due Date: 2018/10/22
 * Author: Jeffrey McMullen II
 * Description: This program prompts the user for an expression and validates it using
 * the BalanceChecker class and then converts it from Infix to Postfix using
 * the ArrayStack class. It then utilizes the stack to evaluate the expression and
 * displays the result back to the user.
 */

import java.util.Scanner;

public class InfixPostfix 
{
    public static void main(String[] args) 
    {        
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter an infix expression: ");
        
        String expression = input.nextLine();
        
        if(BalanceChecker.checkBalance(expression))
        {            
            if (!(missingOperator(expression)))
            {
                String postfix = convertToPostfix((expression));
                System.out.println("Postfix form: " + postfix);
                System.out.println("The expression result is: " + evaluatePostfix(postfix));
            }
            else
            {
                System.out.println("An operator is missing.");
            }
        }
        else
        {
            System.out.println("Expression is not balanced");
        }
    }   
    
    /**
     * Check the entire infix expression for any missing mathematical operators.
     * @param expression A string containing an infix expression.
     * @return A boolean that is true when the operator is missing from the 
     * expression and false otherwise.
     */
    public static boolean missingOperator(String expression)
    {
        for(int i = 0; i < expression.length(); i++)
        {
            char numChar = expression.charAt(i);
            
            if (numChar >= '0' && numChar <= '9')
            {                
                for(int j = i + 1; j < expression.length(); j++)
                {
                    char curChar = expression.charAt(j);
                    
                    if (curChar == '(' || curChar == ')' || curChar == ' ')
                    {
                        //keep going
                    }
                    else if(curChar == '+' || curChar == '-' || curChar == '*' ||
                            curChar == '/' || curChar == '^')
                    {
                        break;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Algorithm to convert an infix expression to postfix form.
     * @param infix A string containing an infix expression.
     * @return A string containing the expression in postfix form.
     */
    public static String convertToPostfix(String infix)
    {
        ArrayStack<Character> operatorStack = new ArrayStack<Character>();
        
        String postfix = "";
        
        for(int i = 0; i < infix.length(); i++)
        {
            char nextCharacter = infix.charAt(i);
            
            switch (nextCharacter)
            {
                case '0': case '1': case '2': case '3': case '4': case '5': 
                case '6': case '7': case '8': case '9':
                    postfix += nextCharacter;
                    break;
                    
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                    
                case '+': case '-': case '*': case '/':                   
                    while (!operatorStack.isEmpty() && 
                            precedence(nextCharacter) <= precedence(operatorStack.peek()))
                    {                        
                        postfix += operatorStack.pop();               
                    }
                    operatorStack.push(nextCharacter);
                    break;
                    
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                    
                case ')': 
                    //Stack is not empty if infix expression isvalid                            
                    while (true)
                    {
                        char topOperator = operatorStack.pop();
                        
                        if (topOperator != '(')
                        {
                            postfix += topOperator;
                        }
                        else
                        {
                            break;
                        }
                    }
                    break;
                    
                default:
                    //Ignore unexpected characters
                    break; 
            }
        }

        while (!operatorStack.isEmpty())
        {
            char topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        
        return postfix;
    }
    
    /**
     * Ranks certain operands with an integer value based on its operator type.
     * @param operand A character containing an expression operator.
     * @return An integer notifying the precedence level of the operator.
     */
    public static int precedence(char operand)
    {
        int level = 0;
        
        switch (operand)
        {
            case '+': case '-':
                level = 1;
                break;
            case '*': case '/':
                level = 2;
                break;
            case '^':
                level = 3;
                break;
            default:
                //If it is none of these, then it is either a parenthese or
                //a numeric value.
                level = 0;
        }
        
        return level;
    }
    
    /**
     * Gets the result of the postfix expression passed to this method.
     * @param postfix A string containing an expression in postfix form.
     * @return A string containing the result of the postfix expression.
     */
    public static String evaluatePostfix(String postfix)
    {
        ArrayStack<String> valueStack = new ArrayStack<String>();
        
        for(int i = 0; i < postfix.length(); i++)
        {
            String nextCharacter = Character.toString(postfix.charAt(i));
            
            switch(nextCharacter)
            {
                case "0": case "1": case "2": case "3": case "4": case "5": 
                case "6": case "7": case "8": case "9":
                    valueStack.push(nextCharacter);
                    break;
                    
                case "+": case "-": case "*": case "/": case "^":
                    String operandTwo = valueStack.pop();
                    String operandOne = valueStack.pop();
                    String result = evaluateOperation(operandOne, operandTwo, nextCharacter);
                    valueStack.push(result);
                    break;
                    
                default: 
                    break;
            }
        }
        
        return valueStack.peek();
    }
    
    /**
     * Takes two operands and performs a calculation of the two based on the
     * operator provided.
     * @param operandOne A String containing a numeric value.
     * @param operandTwo A String containing a numeric value.
     * @param operator A String containing a an operator value.
     * @return A string containing the result of the operation.
     */
    public static String evaluateOperation(String operandOne, String operandTwo, String operator)
    {
        double operationResult = 0;
        double opOne = Double.parseDouble(operandOne);
        double opTwo = Double.parseDouble(operandTwo);
        
        switch(operator)
        {
            case "+":
                operationResult = (opOne + opTwo);
                break;
                
            case "-":
                operationResult = (opOne - opTwo);
                break;
                
            case "*":
                operationResult = (opOne * opTwo);
                break;
                
            case "/":
                operationResult = (opOne / opTwo);
                break;
                
            case "^":
                operationResult = Math.pow(opOne, opTwo);
                break;
                
            default:
                System.out.println("Operand not found!");
                break;
        }
        
        return Double.toString(operationResult);
    }
}