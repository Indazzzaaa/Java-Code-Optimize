package LinkedList;

import LinkedList.Doubly.DoublyNode;
import LinkedList.Singly.Node;

import java.util.HashSet;

public class Problems {
    public static Node removeDupInUnsortedLL(Node head) {
        if (head == null || head.next == null) return head;
        HashSet<Integer> set = new HashSet<>();
        Node curr = head;
        set.add(curr.data);
        while (curr != null && curr.next != null) {
            if (set.contains(curr.next.data)) {
                curr.next = curr.next.next;
            } else {
                set.add(curr.next.data);
                curr = curr.next;
            }
        }

        return head;
    }

    //Function to swap Kth node from beginning and end in a linked list.
    public static Node swapkthnode(Node head, int num, int k) {
        if (k > num) return head;
        Node sKth = head;
        for (int i = 1; i < k - 1 && sKth != null; i++) {
            sKth = sKth.next;
        }

        if (sKth == null) return head;

        Node fast = head;
        for (int i = 1; i <= k && fast != null; i++) {
            fast = fast.next;
        }
        if (fast == null) return head;

        Node slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // swapping

        if (k == 1) {

            Node temp1 = head;
            head = head.next;
            Node temp2 = slow.next;
            slow = temp2.next;
            temp2.next = head;
            head = temp2;
            temp1.next = null;
            slow.next = temp1;
        } else {

            Node temp1 = sKth.next;
            Node temp2 = slow.next;
            sKth.next = temp1.next;
            slow.next = temp2.next;
            temp2.next = sKth.next;
            sKth.next = temp2;
            temp1.next = slow.next;
            slow.next = temp1;
        }

        return head;


    }

    // segregate zeros , ones and twos nodes
    public static Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        Node zStart = null, zEnd = null;
        Node oStart = null, oEnd = null;
        Node tStart = null, tEnd = null;

        while (head != null) {
            Node temp = head;

            head = head.next;
            temp.next = null;

            if (temp.data == 0) {
                if (zStart == null) {
                    zStart = temp;
                    zEnd = temp;
                } else {
                    zEnd.next = temp;
                    zEnd = temp;
                }
            } else if (temp.data == 1) {
                if (oStart == null) {
                    oStart = temp;
                    oEnd = temp;
                } else {
                    oEnd.next = temp;
                    oEnd = temp;
                }

            } else if (temp.data == 2) {
                if (tStart == null) {
                    tStart = temp;
                    tEnd = temp;
                } else {
                    tEnd.next = temp;
                    tEnd = temp;
                }
            }

        }

        if (zStart != null) {
            zEnd.next = (oStart != null) ? oStart : tStart;
        }

        if (oStart != null) {
            oEnd.next = tStart;
        }


        return zStart;


    }

    // merge sort
    public static Node mergeSort(Node head) {
        Node curr = head;
        if (head == null || head.next == null) return head;


        // find the mid-node
        Node slow = head, fast = head;
        Node start = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node next = slow.next;
        slow.next = null;
        Node head1 = mergeSort(start);
        Node head2 = mergeSort(next);
        Node result = merge(head1, head2);
        return result;

    }

    public static Node merge(Node head1, Node head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;


        Node mergeStart = null, mergeEnd = null;
        Node curr1 = head1, curr2 = head2;
        while (curr1 != null && curr2 != null) {
            if (curr1.data < curr2.data) {
                Node temp = curr1.next;
                curr1.next = null;
                if (mergeStart == null) {
                    mergeStart = curr1;
                    mergeEnd = curr1;
                } else {

                    mergeEnd.next = curr1;

                }
                mergeEnd = curr1;
                curr1 = temp;
            } else {
                Node temp = curr2.next;
                curr2.next = null;
                if (mergeStart == null) {
                    mergeStart = curr2;
                    mergeEnd = curr2;
                } else {

                    mergeEnd.next = curr2;
                }
                mergeEnd = curr2;
                curr2 = temp;
            }

        }

        if (curr1 == null) {
            mergeEnd.next = curr2;

        }
        if (curr2 == null) {
            mergeEnd.next = curr1;
        }

        return mergeStart;


    }

    // On doubly
    //Function to sort the given doubly linked list using Merge Sort.
    public static DoublyNode sortDoubly(DoublyNode head) {
        if (head == null || head.next == null) return head;
        DoublyNode slowPos = split(head);
        DoublyNode next = slowPos.next;
//        slowPos.next.prev =null;
        slowPos.next = null;
        DoublyNode head1 = sortDoubly(head);
        DoublyNode head2 = sortDoubly(next);
        return merge(head1, head2);

    }

    public static DoublyNode merge(DoublyNode head1, DoublyNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        DoublyNode curr1 = head1, curr2 = head2;
        DoublyNode start = null, end = null;
        while (curr1 != null && curr2 != null) {

            if (curr1.data < curr2.data) {
                if (start == null) {
                    DoublyNode temp = curr1;
                    curr1 = curr1.next;
                    temp.next = null;
                    temp.prev = null;
                    start = temp;
                    end = start;
                } else {
                    DoublyNode temp = curr1;
                    curr1 = curr1.next;
                    temp.next = null;
                    temp.prev = end;
                    end.next = temp;
                    end=end.next;
                }
            } else {
                if (start == null) {
                    DoublyNode temp = curr2;
                    curr2 = curr2.next;
                    temp.next = null;
                    temp.prev = null;
                    start = temp;
                    end = start;
                } else {
                    DoublyNode temp = curr2;
                    curr2 = curr2.next;
                    temp.next = null;
                    temp.prev = end;
                    end.next = temp;
                    end = end.next;
                }


            }

        }

        if (curr1 == null ) {
            curr2.prev = end;
            end.next = curr2;
        }

        if (curr2 == null) {
            curr1.prev = end;
            end.next = curr1;
        }


        return start;
    }

    private static DoublyNode split(DoublyNode head) {
        if (head == null || head.next == null) return head;
        DoublyNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;

    }

    //Function to find intersection point in Y shaped Linked Lists.
   public static int intersectPoint(Node head1, Node head2)
    {
        if(head1==null || head2==null) return -1;
        Node c1 =head1,c2=head2;
        // counting the number of nodes
        int count1=0,count2=0;
        while(c1!=null){
            count1++;
            c1=c1.next;
        }
        while(c2!=null){
            count2++;
            c2=c2.next;
        }

        if(count1>count2) return matchForInterseciton(head1,head2,count1-count2);
        else return matchForInterseciton(head2,head1,count2-count1);



    }

    private static int matchForInterseciton(Node h1,Node h2, int diff){
        for(int i=0;i<diff  && h1!=null;i++){
            h1=h1.next;
        }

        while(h1!=null && h2!=null){
            if(h1==h2) return h1.data;
            h1=h1.next;
            h2=h2.next;
        }

        return -1;


    }


}



