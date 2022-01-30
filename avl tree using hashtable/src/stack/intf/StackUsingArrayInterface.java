package stack.intf;

import BST.*;
import stack.custom_exception.StackUsingArrayHandlingException;

public interface StackUsingArrayInterface {
	void push(BTNode newNode);
	BTNode pop();
	boolean isFull();
	boolean isEmpty();
}
