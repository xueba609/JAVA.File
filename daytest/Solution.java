package Test1.Test326;
/*

*两个栈实现一个队列
 */
import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        Integer  tmp= 0;
        if (stack2.empty()) //若栈2为空
        {
            while (!stack1.empty())
            {
                tmp = stack1.peek();
                stack2.push(tmp);
                stack1.pop();
            }
        }
        tmp = stack2.peek();
        stack2.pop();
        return tmp;

    }

    public static void main(String[] args) {
        Solution solution=new Solution();
        solution.push(1);
        System.out.println(solution.pop());
    }
}
