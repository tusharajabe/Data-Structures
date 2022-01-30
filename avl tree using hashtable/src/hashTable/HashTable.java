package hashTable;

import BST.BTNode;
import BST.BinarySerachTree;

import static BST.BinarySerachTree.isElementPresentInBst;

public class HashTable {

    /** ---------------- FIELDS -------------- **/

    public BinarySerachTree[] arr;

    public HashTable() {
        arr = new BinarySerachTree[10];
    }

    /**
     * CALCULATE BUCKET ID
     * @param key
     * @return
     */

    private int calculateBucketId(int key) {
        return key % 10;
    }

    /**
     * ADD IN HASHTABLE
     * @param key
     */

    public void addInHashTable(int key) {
        //GETTING BUCKET ID FOR THE KEY
        int bucketId = calculateBucketId(key);//getting bucketId for the key
        //ASSIGNING FRESH BUCKET ID
        if (arr[bucketId] == null) {
            //EACH BUCKET ID HOLDS AN OBJECT OF BINARY SEARCH TREE
            arr[bucketId] = new BinarySerachTree();
            //ADD NEW ELEMENT (NODE) IN BST OF THIS BUCKET ID
            arr[bucketId].addInBST(key, arr[bucketId]);
            return;
        }
        //ADD NEW ELEMENT (NODE) IN BST OF THIS BUCKET ID
        arr[bucketId].addInBST(key, arr[bucketId]);
    }

    /**
     * DELETE FROM HASHTABLE
     * @param key
     */

    public void deleteFromHashTable(int key) {
        //GETTING BUCKET ID FOR THE KEY
        int bucketId = calculateBucketId(key);
        //IF ROOT ELEMENT IS NOT PRESENT, STOP
        if (arr[bucketId] == null) {
            return;
        }
        //DELETE ELEMENT (NODE) IN BST OF THIS BUCKET ID
        arr[bucketId].deleteFromBST(key, arr[bucketId]);
    }

    /**
     * SEARCH IN HASHTABLE
     * @param key
     */

    public void searchInHashTable(int key) {
        //GETTING BUCKET ID FOR THE KEY
        int bucketId = calculateBucketId(key);
        if (arr[bucketId] == null) {
            System.out.println("ELEMENT " + key + " FOUND: false");
        }
        arr[bucketId].searchInBst(key, arr[bucketId].root);
        System.out.println("ELEMENT " + key + " FOUND: " + isElementPresentInBst);
        //SET isElementPresentInBst TO false
        isElementPresentInBst = false;
    }

    /**
     * PRINT HASHTABLE
     * @param h1
     * @param bst
     */

    public void printHashTable(HashTable h1, BinarySerachTree bst) {
        System.out.println("BALANCED TREES: ");
        for (int i = 0; i < 10; i++) {
            if (h1.arr[i] != null) {
                System.out.print("AVL TREE AT bucketId :" + i + " => [ ");
                bst.inOrder(h1.arr[i].root);
                System.out.print("]");
                System.out.println();
            }
        }
    }
}
