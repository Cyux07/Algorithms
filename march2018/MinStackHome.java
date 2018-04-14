package march2018;

import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class MinStackHome {
    public static void main(String[] s) {
        //test operation
        /**["MinStack","push","push","push","getMin","pop","top","getMin"]
         [[],[-2],[0],[-3],[],[],[],[]]*/
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        stack.getMin();
        stack.pop();
        stack.top();
        stack.getMin();
    }
}

/**36.27%, 没有什么难度。程序员面试指南里的第一道题
 * 普通解法，用两个栈 */
class MinStack1 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack1() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x)
            minStack.push(x);
    }

    /**Removes the element on top of the stack.*/
    public void pop() {
        int removed = stack.pop();
        if (minStack.peek() == removed)
            minStack.pop();
    }

    /**Get the top element.*/
    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


/**one stack Solution, 实际上效率一样(39.79%*/
class MinStack2 {
    private class Element {
        int min;
        int value;

        Element(int min, int value) {
            this.min = min;
            this.value = value;
        }
    }

    final private Stack<Element> stack;//final

    /** initialize your data structure here. */
    public MinStack2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        final int min = stack.isEmpty() ? x : stack.peek().min;// final
        stack.push(new Element(Math.min(min, x), x));
    }

    /**Removes the element on top of the stack.*/
    public void pop() {
        stack.pop();
    }

    /**Get the top element.*/
    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().min;
    }
}

/**最速Solution*/
class MinStack {
    Stack<Integer> stack;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int pop = stack.pop();
        if (pop == min)
            min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
