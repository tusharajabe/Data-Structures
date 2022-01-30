package BST;

public class BTNode {

    /** ---------------- FIELDS -------------- **/

    int data;
    public BTNode lChild;
    public BTNode rChild;

    public BTNode(int element){
        data = element;
        lChild = rChild = null;
    }

    public int getData() {
        return data;
    }

    public BTNode getlChild() {
        return lChild;
    }

    public BTNode getrChild() {
        return rChild;
    }

}
