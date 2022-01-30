package testAVL;

import BST.BinarySerachTree;
import avl_Utils.AvlImplementationClass;
import hashTable.HashTable;
import stack.utils.StackUsingArrayUtils;
import java.util.ArrayList;
import java.util.HashMap;
import static avl_Utils.AvlImplementationClass.*;

/*********************************************************
 * @PROJECT_NAME: ADELSON-VELSKII AND LANDIS (AVL) TREE  *
 * @AUTHOR: ADITYA PATIL, TUSHAR AJABE                   *
 * @DATE: 30-01-2022                                     *
 *********************************************************/

public class Main {

    public static void main(String[] args) {
        BinarySerachTree bst = new BinarySerachTree();
        HashTable h1 = new HashTable();
        h1.addInHashTable(100);
        h1.addInHashTable(50);
        h1.addInHashTable(150);
        h1.addInHashTable(25);
        h1.addInHashTable(75);
        h1.addInHashTable(125);
        h1.addInHashTable(160);
        h1.addInHashTable(170);
        h1.addInHashTable(65);
        h1.addInHashTable(80);
        h1.addInHashTable(20);
        h1.addInHashTable(90);
        h1.deleteFromHashTable(65);
        h1.deleteFromHashTable(75);
        h1.printHashTable(h1, bst);
        h1.searchInHashTable(20);

        /**
         * TEST CASES:-
         */
//        bst.addInBST(25, bst);
//        bst.addInBST(15, bst);
//        bst.addInBST(30, bst);
//        bst.addInBST(10, bst);
//        bst.addInBST(9, bst);

//TREE FOR CHECKING DELETION (L rotation)
//        bst.addInBST(100,bst);
//        bst.addInBST(50,bst);
//        bst.addInBST(150,bst);
//        bst.addInBST(25,bst);
//        bst.addInBST(75,bst);
//        bst.addInBST(125,bst);
//        bst.addInBST(160,bst);
//        bst.addInBST(170,bst);
//        bst.addInBST(65,bst);
//        bst.addInBST(80,bst);
//        bst.addInBST(20,bst);
//        bst.addInBST(90,bst);

        //TREE FOR CHECKING DELETION (RL rotation)
//        bst.addInBST(100,bst);
//        bst.addInBST(50,bst);
//        bst.addInBST(150,bst);
//        bst.addInBST(25,bst);
//        bst.addInBST(75,bst);
//        bst.addInBST(125,bst);
//        bst.addInBST(160,bst);
//        bst.addInBST(20,bst);
//        bst.addInBST(65,bst);
//        bst.addInBST(80,bst);
//        bst.addInBST(170,bst);
//        bst.addInBST(55,bst);

        //TREE FOR CHECKING DELETION (L rotation)
//        bst.addInBST(33,bst);
//        bst.addInBST(13,bst);
//        bst.addInBST(53,bst);
//        bst.addInBST(9,bst);
//        bst.addInBST(21,bst);
//        bst.addInBST(61,bst);
//        bst.addInBST(8,bst);
//        bst.addInBST(11,bst);

//        System.out.println("calling delete");
//        bst.deleteFromBST(61, bst);
//        System.out.print("inorder after delete: ");
//        bst.inOrder();

        //        bst.addInBST(100, bst);
//        bst.addInBST(50, bst);
//        bst.addInBST(150, bst);
//        bst.addInBST(25, bst);
//        bst.addInBST(75, bst);
//        bst.addInBST(125, bst);
//        bst.addInBST(160, bst);
//        bst.addInBST(165, bst);
//        bst.addInBST(65, bst);
//        bst.addInBST(80, bst);
//        bst.addInBST(55, bst);


        //        bst.addInBST(15, bst);
//        bst.addInBST(17, bst);
//        bst.addInBST(20, bst);
//        bst.addInBST(25, bst);
//        bst.addInBST(30, bst);

//        bst.addInBST(100, bst);
//        bst.addInBST(50, bst);
//        bst.addInBST(150, bst);
//        bst.addInBST(25, bst);
//        bst.addInBST(75, bst);
//        bst.addInBST(160, bst);
//        bst.addInBST(15, bst);
//        bst.addInBST(65, bst);
//        bst.addInBST(80, bst);
//        bst.addInBST(90, bst);


        //        bst.addInBST(50, bst);
//        bst.addInBST(25, bst);
//        bst.addInBST(75, bst);
//        bst.addInBST(15, bst);
//        bst.addInBST(35, bst);
//        bst.addInBST(80, bst);
//        bst.addInBST(8, bst);
//        bst.addInBST(20, bst);
//        bst.addInBST(60, bst);
//        bst.addInBST(90, bst);
//        bst.addInBST(7, bst);

    }
}
