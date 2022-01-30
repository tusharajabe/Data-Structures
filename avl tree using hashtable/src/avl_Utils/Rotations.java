package avl_Utils;

import BST.BTNode;
import BST.BinarySerachTree;

public class Rotations {
    /**
     * LL ROTATION
     * @param n
     * @param p
     * @param g
     */

    public static void clockWiseRotation(BTNode n, BTNode p, BTNode g) {
        if (p.rChild != null) {
            BTNode temp = p.rChild;
            g.lChild = temp;
        } else {
            g.lChild = null;
        }
        p.rChild = g;
    }

    /**
     * RR ROTATION
     * @param n
     * @param p
     * @param g
     */

    public static void antiClockWiseRotation(BTNode n, BTNode p, BTNode g) {
        if (p.lChild != null) {
            BTNode temp = p.lChild;
            g.rChild = temp;
        } else {
            g.rChild = null;
        }
        p.lChild = g;
    }
}
