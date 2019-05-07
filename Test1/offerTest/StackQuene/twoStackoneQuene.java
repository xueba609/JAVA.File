package offerTest.StackQuene;

import java.util.Stack;

/*
*用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class twoStackoneQuene {
    public static void main(String[] args) {
        twoStackoneQuene s = new twoStackoneQuene();
        s.push(1);
        s.push(2);
        System.out.println(s.pop());
    }
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {
        while(stack2.empty()){
            while(!stack1.empty()){
                stack2.add(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
