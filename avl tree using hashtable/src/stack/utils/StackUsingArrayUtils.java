package stack.utils;

import stack.custom_exception.StackUsingArrayHandlingException;
import stack.intf.StackUsingArrayInterface;
import BST.*;

public class StackUsingArrayUtils implements StackUsingArrayInterface {

    /** ---------------- FIELDS -------------- **/

    public int top = -1;
    BTNode[] stack;
    int size;

    public StackUsingArrayUtils(int size) {
        stack = new BTNode[size];
        this.size = size;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public BTNode[] getStack() {
        return stack;
    }

    /**
     * PUSH IN STACK
     * @param newNode
     */

    @Override
    public void push(BTNode newNode) {
        stack[++top] = newNode;
    }

    /**
     * POP FROM STACK
     * @return
     */

    @Override
    public BTNode pop() {
        BTNode topNode = stack[top];
        top--;
        return topNode;
    }

    /**
     * STACK FULL CHECK
     * @return
     */

    @Override
    public boolean isFull() {
        if (top == size - 1)
            return true;
        return false;
    }

    /**
     * STACK EMPTY CHECK
     * @return
     */

    @Override
    public boolean isEmpty() {
        if (top == -1)
            return true;
        return false;
    }
}
