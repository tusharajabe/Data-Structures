package avl_Utils;

import BST.BTNode;
import BST.BinarySerachTree;
import stack.utils.StackUsingArrayUtils;
import BST.BinarySerachTree.*;
import java.util.Arrays;
import static avl_Utils.Rotations.*;
import static stack.utils.StackUsingArrayUtils.*;

public class AvlImplementationClass {

    /** ---------------- FIELDS -------------- **/

    public static BTNode unbalancedNode;
    public static int htOfLeftOfUnbalanced;
    public static int htOfRightOfUnbalanced;
    static int indexOfUnbalancedNodeInDeletionStack;

    static {
        unbalancedNode = null;
        htOfLeftOfUnbalanced = 0;
        htOfRightOfUnbalanced = 0;
    }

    /**
     * IS TREE BALANCED
     * @return
     */

    public boolean isTreeUnbalanced() {
        if (unbalancedNode == null)
            return false;
        else
            return true;
    }

    /**
     * @param unbalancedNode
     * @param insertionStack
     * @return
     */

    public static int getPosOfUnbalancedNodeInInsertionStack(BTNode unbalancedNode, StackUsingArrayUtils insertionStack) {
        BTNode[] stackArr = insertionStack.getStack();
        int x;
        for (x = 0; x <= insertionStack.getTop(); x++) {
            if (unbalancedNode.getData() == stackArr[x].getData()) {
                break;
            }
        }
        return x;
    }

    /**
     * LOGIC FOR BALANCING TREE AFTER INSERTION
     * @param insertionStack
     * @param bst
     * @param topInitialPos
     */

    public static void balanceTreeAfterInsertion(StackUsingArrayUtils insertionStack, BinarySerachTree bst, int topInitialPos) {
        BTNode[] stackArr = insertionStack.getStack();
        //GET THE LAST ADDED NODE
        BTNode newNode = insertionStack.pop();
        //CHECK IN WHICH DIRECTION OF UNBALANCED NODE THIS newNode is ADDED
        //LL (clockWise Rotation)
        if (newNode.getData() < unbalancedNode.getData() && newNode.getData() < unbalancedNode.getlChild().getData()) {
            int unbalancedNodePos = getPosOfUnbalancedNodeInInsertionStack(unbalancedNode, insertionStack);
            insertionStack.setTop(unbalancedNodePos + 2);
            BTNode n = insertionStack.pop();
            BTNode p = insertionStack.pop();
            BTNode g = insertionStack.pop();
            clockWiseRotation(n, p, g);
            //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
            if (!insertionStack.isEmpty()) {
                BTNode gg = insertionStack.pop();
                if (p.getData() < gg.getData()) {
                    gg.lChild = p;
                } else {
                    gg.rChild = p;
                }
            } else {
                bst.root = p;
            }
            return;
        }//END OF LL ROTATION

        //RR (antiClockWiseRotation)
        if (newNode.getData() > unbalancedNode.getData() && newNode.getData() > unbalancedNode.getrChild().getData()) {
            int unbalancedNodePos = getPosOfUnbalancedNodeInInsertionStack(unbalancedNode, insertionStack);
            insertionStack.setTop(unbalancedNodePos + 2);
            BTNode n = insertionStack.pop();
            BTNode p = insertionStack.pop();
            BTNode g = insertionStack.pop();
            antiClockWiseRotation(n, p, g);
            //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
            if (!insertionStack.isEmpty()) {
                BTNode gg = insertionStack.pop();
                if (p.getData() < gg.getData()) {
                    gg.lChild = p;
                } else {
                    gg.rChild = p;
                }
            } else {
                bst.root = p;
            }
            return;
        }//END OF RR ROTATION

        //LR Rotation (DO RR (antiClockWiseRotation) --> SWAP REFERENCES --> DO LL (clockWiseRotation))
        if (newNode.getData() < unbalancedNode.getData() && newNode.getData() > unbalancedNode.getlChild().getData()) {
            int unbalancedNodePos = getPosOfUnbalancedNodeInInsertionStack(unbalancedNode, insertionStack);
            insertionStack.setTop(unbalancedNodePos + 2);
            BTNode n = insertionStack.pop();
            BTNode p = insertionStack.pop();
            BTNode g = insertionStack.pop();
            //MAKE SUB TREE OF UNBALANCED NODE LEFT HEAVY
            BTNode temp1 = p.rChild;
            p.rChild = n.lChild;
            BTNode temp2 = g.lChild;
            g.lChild = temp1;
            temp1.lChild = temp2;
            //SWAPPING THE REFERENCES
            BTNode buffer;
            buffer = p;
            p = n;
            n = buffer;
            //AFTER MAKING IT LEFT HEAVY PERFORM LL ROTATION
            clockWiseRotation(n, p, g);
            //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
            if (!insertionStack.isEmpty()) {
                BTNode gg = insertionStack.pop();
                if (p.getData() < gg.getData()) {
                    gg.lChild = p;
                } else {
                    gg.rChild = p;
                }
            } else {
                bst.root = p;
            }
        }

        //RL Rotation (DO LL (clockWiseRotation)  --> SWAP REFERENCES --> DO RR (antiClockWiseRotation))
        if (newNode.getData() > unbalancedNode.getData() && newNode.getData() < unbalancedNode.getrChild().getData()) {
            int unbalancedNodePos = getPosOfUnbalancedNodeInInsertionStack(unbalancedNode, insertionStack);
            insertionStack.setTop(unbalancedNodePos + 2);
            BTNode n = insertionStack.pop();
            BTNode p = insertionStack.pop();
            BTNode g = insertionStack.pop();
            //MAKE SUB TREE OF UNBALANCED NODE RIGHT HEAVY
            BTNode temp1 = p.lChild;
            p.lChild = n.rChild;
            BTNode temp2 = g.rChild;
            g.rChild = temp1;
            temp1.rChild = temp2;
            //SWAPPING THE REFERENCES
            BTNode buffer;
            buffer = p;
            p = n;
            n = buffer;
            //AFTER MAKING IT LEFT HEAVY PERFORM RR ROTATION
            antiClockWiseRotation(n, p, g);
            //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
            if (!insertionStack.isEmpty()) {
                BTNode gg = insertionStack.pop();
                if (p.getData() < gg.getData()) {
                    gg.lChild = p;
                } else {
                    gg.rChild = p;
                }
            } else {
                bst.root = p;
            }
        }
    }

    /**
     * LOGIC FOR BALANCING TREE AFTER DELETION
     * @param deletionStack
     * @param bst
     */

    public static void getPosOfUnbalancedNodeInDeletionStack(StackUsingArrayUtils deletionStack, BinarySerachTree bst) {
        BTNode[] newDeletionArray = deletionStack.getStack();
        for (int i = deletionStack.getTop(); i >= 0; i--) {
            System.out.println(newDeletionArray[i].getData());
            int BF = bst.balanceFactorCal(newDeletionArray[i], null);
            if (BF > 1 || BF < -1) {
                indexOfUnbalancedNodeInDeletionStack = i;
                break;
            }
        }
    }

    /**
     * @param deletionStack
     * @param bst
     * @param topInitialPos
     */

    public static void balanceTreeAfterDeletion(StackUsingArrayUtils deletionStack, BinarySerachTree bst, int topInitialPos) {
        int unbalancedNodeBalanceFactor = bst.unbalancedNodeBalanceFactor;
        if (unbalancedNodeBalanceFactor < -1) {
            //CALCULATE THE BAL FACTOR OF RIGHT CHILD
            if (bst.balanceFactorCal(unbalancedNode.rChild, null) <= 0) {
                BTNode g = unbalancedNode;
                BTNode p = g.rChild;
                AvlImplementationClass.getPosOfUnbalancedNodeInDeletionStack(deletionStack, bst);
                //DO ANTICLOCKWISE ROTATION (RR)
                antiClockWiseRotation(null, p, g);
                //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
                deletionStack.setTop(indexOfUnbalancedNodeInDeletionStack - 1);
                if (!deletionStack.isEmpty()) {
                    BTNode gg = deletionStack.pop();
                    if (p.getData() < gg.getData()) {
                        gg.lChild = p;
                    } else {
                        gg.rChild = p;
                    }
                } else {
                    bst.root = p;
                }
                return;
            } else {
                //RL Rotation (DO LL (clockWiseRotation)  --> REASSIGN REFERENCES --> DO RR (antiClockWiseRotation))
                BTNode g = unbalancedNode;
                BTNode p = g.rChild;
                BTNode n = p.lChild;
                //MAKE SUB TREE OF UNBALANCED NODE RIGHT HEAVY
                BTNode temp1 = p.lChild;
                p.lChild = n.rChild;
                BTNode temp2 = g.rChild;
                g.rChild = temp1;
                temp1.rChild = temp2;
                //REASSIGNING THE REFERENCES
                BTNode buffer;
                buffer = p;
                p = n;
                n = buffer;
                //AFTER MAKING IT RIGHT HEAVY PERFORM RR ROTATION
                antiClockWiseRotation(n, p, g);
                //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
                AvlImplementationClass.getPosOfUnbalancedNodeInDeletionStack(deletionStack, bst);
                deletionStack.setTop(indexOfUnbalancedNodeInDeletionStack);
                if (!deletionStack.isEmpty()) {
                    BTNode gg = deletionStack.pop();
                    deletionStack.setTop(deletionStack.getTop() + 1);
                    if (p.getData() < gg.getData()) {
                        gg.lChild = p;
                    } else {
                        gg.rChild = p;
                    }
                } else {

                    bst.root = p;
                }
            }
        }

        if (unbalancedNodeBalanceFactor > 1) {
            //CALCULATE THE BAL FACTOR OF RIGHT CHILD
            if (bst.balanceFactorCal(unbalancedNode.lChild, null) >= 0) {
                BTNode g = unbalancedNode;
                BTNode p = g.lChild;
                AvlImplementationClass.getPosOfUnbalancedNodeInDeletionStack(deletionStack, bst);
                // DO CLOCKWISE ROTATION
                clockWiseRotation(null, p, g);
                //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
                deletionStack.setTop(indexOfUnbalancedNodeInDeletionStack - 1);
                if (!deletionStack.isEmpty()) {
                    BTNode gg = deletionStack.pop();
                    if (p.getData() < gg.getData()) {
                        gg.lChild = p;
                    } else {
                        gg.rChild = p;
                    }
                } else {
                    bst.root = p;
                }
                return;
            } else {
                //LR Rotation (DO RR (antiClockWiseRotation) --> REASSIGN REFERENCES --> DO LL (clockWiseRotation))
                BTNode g = unbalancedNode;
                BTNode p = g.lChild;
                BTNode n = p.rChild;
                //MAKE SUB TREE OF UNBALANCED NODE LEFT HEAVY
                BTNode temp1 = p.rChild;
                p.rChild = n.lChild;
                BTNode temp2 = g.lChild;
                g.lChild = temp1;
                temp1.lChild = temp2;
                //REASSIGNING THE REFERENCES
                BTNode buffer;
                buffer = p;
                p = n;
                n = buffer;
                //AFTER MAKING IT LEFT HEAVY PERFORM RR ROTATION
                clockWiseRotation(n, p, g);
                //AFTER ROTATING THE UNBALANCED SUB-TREE, ATTACH IT TO PARENT NODE
                AvlImplementationClass.getPosOfUnbalancedNodeInDeletionStack(deletionStack, bst);
                deletionStack.setTop(indexOfUnbalancedNodeInDeletionStack);
                System.out.println("GF:" + indexOfUnbalancedNodeInDeletionStack);
                if (!deletionStack.isEmpty()) {
                    BTNode gg = deletionStack.pop();
                    deletionStack.setTop(deletionStack.getTop() + 1);
                    if (p.getData() < gg.getData()) {
                        gg.lChild = p;
                    } else {
                        gg.rChild = p;
                    }
                } else {
                    bst.root = p;
                }
            }
        }
    }
}
