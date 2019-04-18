/**
 * Create a BalanceChecker class to determine if expressions are balanced.
 * @author Owner
 */
public class BalanceChecker 
{
    /** Decides whether the parentheses, brackets, and braces
     *  in a string occur in left/right pairs.     * 
     * @param expression A string to be checked.
     * @return True if the delimiters are paired correctly.
     */
    public static boolean checkBalance(String expression)
    {
        StackInterface<Character> openDelimiterStack = new ArrayStack<Character>();
        
        int characterCount = expression.length();
        boolean isBalanced = true;
        int index = 0;
        char nextCharacter = ' ';
        
        while (isBalanced && (index < characterCount))
        {
            nextCharacter = expression.charAt(index);
            switch (nextCharacter)
            {
                case '(': case '[': case '{':
                    openDelimiterStack.push(nextCharacter);
                    break;
                    
                case ')': case ']': case '}':
                    if (openDelimiterStack.isEmpty())
                    {
                        isBalanced = false;
                    }
                    else
                    {
                        char openDelimiter = openDelimiterStack.pop();
                        isBalanced = isPaired(openDelimiter, nextCharacter);
                    }
                    break;
                    
                default:
                    //Ignore unexpected characters
                    break;
            }
            index++;
        }
        
        if (!openDelimiterStack.isEmpty())
        {
            isBalanced = false;
        }
        
        return isBalanced;
    }
    
    /**
     * Checks to see that the opening char is an opening delimiter and that
     * the closing char is a closing delimiter.
     * @param open A char that should contain an opening delimiter.
     * @param close A char that should contain a closing delimiter.
     * @return A boolean that is true when the appropriate opening and closing
     * delimiters are paired.
     */
    private static boolean isPaired(char open, char close)
    {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
}
