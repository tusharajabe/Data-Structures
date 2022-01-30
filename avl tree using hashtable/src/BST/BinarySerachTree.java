package BST;

import avl_Utils.AvlImplementationClass;
import stack.utils.StackUsingArrayUtils;
import stack.utils.*;
import java.util.HashMap;
import static avl_Utils.AvlImplementationClass.*;

public class BinarySerachTree {

    /** ---------------- FIELDS -------------- **/

    public BTNode root;
    int count = 0;
    public int unbalancedNodeBalanceFactor = 0;
    public int balanceFactorOfRequiredNode = 0;
    public static boolean isElementPresentInBst = false;

    public BinarySerachTree() {
        root = null;
    }

    /**
     * ADDITION IN BST
     * @param node
     * @param element
     * @param insertionStack
     */

    private void addInBST(BTNode node, int element, StackUsingArrayUtils insertionStack) {
        //IF TREE IS EMPTY
        if (node == null) {
            root = new BTNode(element);
            ++count;
            return;
        }
        //IF root is the only node in the BST
        if (node.lChild == null && node.rChild == null) {
            if (node.data < element) {
                node.rChild = new BTNode(element);
            } else if (node.data > element) {
                node.lChild = new BTNode(element);
            } else
                System.out.println("DUPLICATE ELEMENT CANNOT BE ADDED TO BST");
            return;
        }

        BTNode current = root;
        BTNode previous = null;
        while (current != null) {
            if (current.data > element) {
                previous = current;
                //INSERT THE CURRENT IN INSERTION STACK TO MAINTAIN THE INSERTION ORDER
                insertionStack.push(current);
                current = current.lChild;
            } else if (current.data < element) {
                previous = current;
                //INSERT THE CURRENT IN INSERTION STACK TO MAINTAIN THE INSERTION ORDER
                insertionStack.push(current);
                current = current.rChild;
            } else {
                System.out.println("DUPLICATE ELEMENT FOUND, CANNOT BE ADDED TO BST: " + current.data);
                return;
            }
        }
        current = previous;
        if (current.data > element) {
            current.lChild = new BTNode(element);
            //INSERT THE CURRENT IN INSERTION STACK TO MAINTAIN THE INSERTION ORDER
            insertionStack.push(current.lChild);
        } else {
            current.rChild = new BTNode(element);
            insertionStack.push(current.rChild);
            //INSERT THE CURRENT IN INSERTION STACK TO MAINTAIN THE INSERTION ORDER
        }
    }

    /**
     * @param element
     * @param bst
     */

    public void addInBST(int element, BinarySerachTree bst) {
        AvlImplementationClass avl = new AvlImplementationClass();
        //CREATE AN INSERTION STACK TO MAINTAIN THE INSERTION ORDER FOR newNode
        StackUsingArrayUtils insertionStack = new StackUsingArrayUtils(100);
        //ADD IN BST
        addInBST(root, element, insertionStack);
        //AFTER ADDITION IN BST, BALANCE FACTOR OF NODES IN THE INSERTION ORDER CAN BE VIOLATED
        //GET THE STACK ELEMENTS IN AN ARRAY
        BTNode[] insertionStackArr = insertionStack.getStack();
        int topInitialPos = insertionStack.getTop();
        //ITERATE OVER ARRAY TO CHECK THE BALANCE FACTOR FOR EVERY NODE IN INSERTION ORDER
        for (int i = topInitialPos; i >= 0; i--) {
            BTNode temp = insertionStackArr[i];
            //CALCULATE THE BALANCE FACTOR
            balanceFactorCal(temp, null);
            //FLAG FOR CHECKING WHETHER TREE IS BALANCED OR NOT
            boolean isTreeUnbalanced = false;
            //CHECK IF THE TREE IS BALANCED AT CURRENT NODE
            isTreeUnbalanced = avl.isTreeUnbalanced();
            //IF TREE IS ALREADY BALANCED AT CURRENT NODE, CONTINUE CHECKING NEXT NODES FOR BALANCE FACTOR VIOLATION
            if (!isTreeUnbalanced) {
                continue;
            }
            //IF TREE IS NOT BALANCED AT CURRENT NODE, BALANCE THE TREE AT CURRENT NODE
            balanceTreeAfterInsertion(insertionStack, bst, topInitialPos);
            //AFTER BALANCING CURRENT NODE SET unbalancedNode TO null
            unbalancedNode = null;
        }
    }

    /**
     * DELETION FROM BST
     *
     * @param element
     * @param deletionStack
     */

    public void deleteFromBst(int element, StackUsingArrayUtils deletionStack) {
        //IF TREE IS EMPTY
        if (root == null) {
            System.out.println("TREE IS EMPTY");
            return;
        }
        //FIND THE NODE TO BE DELETED
        BTNode current = root;
        BTNode previous = null;
        while (current != null) {
            //INSERT THE CURRENT IN DELETION STACK TO MAINTAIN THE DELETION ORDER
            deletionStack.push(current);
            if (current.data == element)
                break;
            previous = current;
            if (current.data > element)
                current = current.lChild;
            else
                current = current.rChild;
        }
        //ELEMENT IS NOT FOUND
        if (current == null) {
            System.out.println("ELEMENT NOT FOUND");
            return;
        }
        //DELETING A LEAF NODE
        if (current.lChild == null && current.rChild == null) {
            if (root == current) {
                root = null;
                return;
            }
            if (previous.lChild == current) {
                previous.lChild = null;
            } else {
                previous.rChild = null;
            }
            //POP ONE ELEMENT FROM DELETION STACK TO MAKE top POINT TO THE ELEMENT BEFORE THE ELEMENT TO BE DELETED IN DELETION ORDER
            deletionStack.pop();
            return;
        }
        //DELETING A NODE WHICH HAS ONE CHILD
        if (current.lChild == null) {//CHILD OF CURRENT IS PRESENT IN RIGHT BRANCH OF CURRENT
            if (current == root) { // CHECK IF ROOT HAS ONLY ONE CHILD (RIGHT CHILD)
                root = current.rChild;
                //POP ONE ELEMENT FROM DELETION STACK TO MAKE top POINT TO THE ELEMENT BEFORE THE ELEMENT TO BE DELETED IN DELETION ORDER
                deletionStack.pop();
                return;
            }
            if (previous.lChild == current) // FINDING IN WHICH BRANCH OF PREVIOUS NODE CURRENT IS PRESENT
                previous.lChild = current.rChild;
            else
                previous.rChild = current.rChild;
            //POP ONE ELEMENT FROM DELETION STACK TO MAKE top POINT TO THE ELEMENT BEFORE THE ELEMENT TO BE DELETED IN DELETION ORDER
            deletionStack.pop();
            return;
        }
        if (current.rChild == null) { // CHILD OF CURRENT IS PRESENT IN LEFT BRANCH OF CURRENT

            if (current == root) {  // CHECK IF ROOT HAS ONLY ONE CHILD (LEFT CHILD)
                root = current.lChild;
                //POP ONE ELEMENT FROM DELETION STACK TO MAKE top POINT TO THE ELEMENT BEFORE THE ELEMENT TO BE DELETED IN DELETION ORDER
                deletionStack.pop();
                return;
            }
            if (previous.lChild == current)
                previous.lChild = current.lChild;
            else
                previous.rChild = current.lChild;
            //POP ONE ELEMENT FROM DELETION STACK TO MAKE top POINT TO THE ELEMENT BEFORE THE ELEMENT TO BE DELETED IN DELETION ORDER
            deletionStack.pop();
            return;
        }
        //DELETING A NODE WHICH HAS TWO CHILDREN
        //REPLACE THE CURRENT NODE DATA WITH THE SMALLEST ELEMENT IN THE RIGHT TREE OF THE CURRENT
        // (LEFTMOST NODE IN THE CURRENTS RIGHT SUBTREE)
        //INORDER SUCCESSOR OF CURRENT
        //THIS ENSURES THAT THE PROPERTY OF BST IS NOT VIOLATED
        // NOTE: ioSuccessor WILL ALWAYS BE A NODE WHICH HAS EITHER 0 CHILDREN (i.e. LEAF NODE) OR IT COULD HAVE ONLY RIGHT CHILD.
        BTNode ioSuccessor = current.rChild; //75
        BTNode isParent = current;
        while (ioSuccessor.lChild != null) {
            //INSERT THE CURRENT IN DELETION STACK TO MAINTAIN THE DELETION ORDER
            deletionStack.push(ioSuccessor);
            isParent = ioSuccessor;
            ioSuccessor = ioSuccessor.lChild;
        }
        //SWAP THE DATA IN CURRENT WITH DATA IN ioSuccessor
        //TREE IS REARRANGED.
        int temp = ioSuccessor.data;
        ioSuccessor.data = current.data;
        current.data = temp;
        //DELETE THE ioSuccessor NODE WHICH NOW CONTAINS THE ELEMENT TO BE DELETED
        //ioSuccessor IS LEAF NODE.
        if (ioSuccessor.lChild == null && ioSuccessor.rChild == null) {
            if (isParent.lChild == ioSuccessor)
                isParent.lChild = null;
            else
                isParent.rChild = null;
            return;
        }
        //ioSuccessor HAS ONE CHILD NODE
        // THIS CHILD WILL ALWAYS BE IN RIGHT BRANCH OF ioSuccessor
        if (isParent.rChild == ioSuccessor)
            isParent.rChild = ioSuccessor.rChild;
        else
            isParent.lChild = ioSuccessor.rChild;
    }

    /**
     * @param element
     * @param bst
     */
    public void deleteFromBST(int element, BinarySerachTree bst) {
        AvlImplementationClass avl = new AvlImplementationClass();
        //CREATE AN INSERTION STACK TO MAINTAIN THE INSERTION ORDER FOR newNode
        StackUsingArrayUtils deletionStack = new StackUsingArrayUtils(100);
        //DELETE FROM BST
        deleteFromBst(element, deletionStack);
        //AFTER DELETION FROM BST, BALANCE FACTOR OF NODES IN THE DELETION ORDER CAN BE VIOLATED
        //GET THE STACK ELEMENTS IN AN ARRAY
        BTNode[] deletionStackArr = deletionStack.getStack();
        int topInitialPos = deletionStack.getTop();
        //ITERATE OVER ARRAY TO CHECK THE BALANCE FACTOR FOR EVERY NODE IN INSERTION ORDER
        for (int i = topInitialPos; i >= 0; i--) {
            BTNode temp = deletionStackArr[i];
            //CALCULATE THE BALANCE FACTOR
            balanceFactorCal(temp, null);
            //FLAG FOR CHECKING WHETHER TREE IS BALANCED OR NOT
            boolean isTreeUnbalanced = false;
            //CHECK IF THE TREE IS BALANCED AT CURRENT NODE
            isTreeUnbalanced = avl.isTreeUnbalanced();
            //IF TREE IS ALREADY BALANCED AT CURRENT NODE, CONTINUE CHECKING NEXT NODES FOR BALANCE FACTOR VIOLATION
            if (!isTreeUnbalanced) {
                if (deletionStack.getTop() >= 0)
                    deletionStack.pop();
                continue;
            }
            //IF TREE IS NOT BALANCED AT CURRENT NODE, BALANCE THE TREE AT CURRENT NODE
            balanceTreeAfterDeletion(deletionStack, bst, topInitialPos);
            //AFTER BALANCING CURRENT NODE SET unbalancedNode TO null
            unbalancedNode = null;
            //POP FROM STACK IFF STACK IS NOT EMPTY
            if (deletionStack.getTop() >= 0) {
                deletionStack.pop();
            }
        }
    }// END OF DELETION LOGIC

    /**
     * BALANCE FACTOR CALCULATION
     * @param node
     * @param prev
     * @return
     */

    public int balanceFactorCal(BTNode node, BTNode prev) {
        /** ---------------- METHOD LOCAL VARIABLES -------------- **/
        BTNode previous = null;
        BTNode current = node;
        int htOfLeftSubTree = 0;
        int htOfRightSubTree = 0;

        if (current.lChild != null) {
            previous = current;
            current = current.lChild;
            ++htOfLeftSubTree;
            htOfLeftSubTree = htOfLeftSubTree + balanceFactorCal(current, previous);
            current = previous;
        }
        if (current.rChild != null) {
            previous = current;
            current = current.rChild;
            ++htOfRightSubTree;
            htOfRightSubTree = htOfRightSubTree + balanceFactorCal(current, previous);
            current = previous;
        }
        // BALANCE FACTOR FOR CURRENT NODE
        int bf = htOfLeftSubTree - htOfRightSubTree;
        balanceFactorOfRequiredNode = bf;
        //IF BALANCE FACTOR OF CURRENT NODE IS VIOLATED
        if (bf <= -2 || bf >= 2) {
            unbalancedNode = current;
            htOfLeftOfUnbalanced = htOfLeftSubTree;
            htOfRightOfUnbalanced = htOfRightSubTree;
            unbalancedNodeBalanceFactor = bf;
        }
        if (prev == null) {
            return bf;
        }
        int height = Math.max(htOfLeftSubTree, htOfRightSubTree);
        return height;
    }

    /**
     * BALANCE FACTOR CALCULATION
     */

    public void balanceFactorCal() {
        balanceFactorCal(root, null);
    }

    /**
     * INORDER TRAVERSAL
     * @param node
     */

    public void inOrder(BTNode node) {
        if (node == null) {
            System.out.println("TREE IS EMPTY");
            return;
        }
        if (node.lChild != null) {
            inOrder(node.lChild);
        }
        System.out.print(node.data + " ");
        if (node.rChild != null) {
            inOrder(node.rChild);
        }
    }

    /**
     * INORDER TRAVERSAL
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * SEARCH IN BST
     * @param element
     * @param node
     */

    public void searchInBst(int element, BTNode node) {
        if (node.getData() == element)
            isElementPresentInBst = true;
        if (node.lChild != null) {
            searchInBst(element, node.lChild);
        }
        if (node.rChild != null) {
            searchInBst(element, node.rChild);
        }
    }

}
