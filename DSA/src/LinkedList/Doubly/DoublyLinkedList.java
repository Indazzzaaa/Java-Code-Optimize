package LinkedList.Doubly;

public class DoublyLinkedList {

    public static void traverse(DoublyNode head) {
        DoublyNode curr = head;
        while(curr!=null){
            System.out.print(curr.data+" ----> " );
            curr = curr.next;
        }
        System.out.println("null");

    }
    public static DoublyNode insertBeginning(DoublyNode head,int data){
        DoublyNode temp = new DoublyNode(data);
        temp.next = head;
        if(head!=null) head.prev = temp;
        return temp;
    }
    public static DoublyNode insertEnd(DoublyNode head, int data){
        DoublyNode temp = new DoublyNode(data);
        if(head == null) return temp;
        DoublyNode curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = temp;
        temp.prev = curr;
        return head;
    }
    public static DoublyNode deleteBeginning(DoublyNode head){

        if(head==null || head.next == null) return null;
        head=head.next;
        head.prev=null;
        return head;


    }
    public static DoublyNode deleteEnd(DoublyNode head){
        if(head==null || head.next == null) return null;
        DoublyNode curr = head;
        while(curr.next!=null) curr = curr.next;
        curr.prev.next = null;
        return head;



    }

    public static DoublyNode reverse(DoublyNode head){
        if(head==null || head.next==null) return head;
        DoublyNode prev =null,curr = head;
        while(curr!=null){
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;
            curr = curr.prev;

        }

        return prev.prev;

    }
}
