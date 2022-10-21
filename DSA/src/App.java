import LinkedList.Doubly.DoublyLinkedList;
import LinkedList.Doubly.DoublyNode;
import LinkedList.Singly.LinkedList;
import LinkedList.Singly.Node;
import  LinkedList.*;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int[] arr1 =  new int[5];
        int[] arr2 = {2,4,6,8,10};
        Random random = new Random();
        for(int i=0;i< arr1.length;i++){
            arr1[i]=random.nextInt(0,100);
        }
        DoublyNode head1=null;
        DoublyNode head2=null;
        for(var i: arr1){
            head1 = DoublyLinkedList.insertEnd(head1, i);
        }
        for(var j: arr2){
            head2 = DoublyLinkedList.insertEnd(head2, j);
        }



        System.out.println("Before sorting : \n");
        DoublyLinkedList.traverse(head1);
//        DoublyLinkedList.traverse(head2);

        System.out.println("After merging : \n");
        DoublyNode head = Problems.sortDoubly(head1);
        DoublyLinkedList.traverse(head);











//        populateLL(head);

    }

    private static void populateLL(Node head) {
        int data = 0;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            data = random.nextInt(0, 10);
            head = LinkedList.insertEnd(head, data);
        }
    }




}


