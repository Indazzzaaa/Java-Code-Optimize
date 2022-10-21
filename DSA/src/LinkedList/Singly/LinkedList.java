package LinkedList.Singly;

public class LinkedList {
    public static void traverse(Node head) {

        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ---> ");
            curr = curr.next;
        }
        System.out.println("null");

    }

    public static Node insertBeginning(Node head,int data) {
        Node temp = new Node(data);
        temp.next = head;
        return temp;
    }

    public static Node insertEnd(Node head, int data) {

        Node temp = new Node(data);
        if (head == null) return temp;
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = temp;
        return head;

    }

    public static Node deleteFirst(Node head) {
        if (head == null) return null;
        return head.next;
    }

    public static Node deleteEnd(Node head) {
        if (head == null || head.next == null) return null;
        Node curr = head;
        while (curr.next != null && curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        return head;
    }

    public static Node insertAtPos(Node head, int pos, int data) {
        Node temp = new Node(data);
        if (pos == 1) {
            temp.next = head;
            return temp;

        }
        Node curr = head;
        for (int i = 1; i <= pos - 2 && curr != null; i++) {
            curr = curr.next;
        }
        if (curr == null) return head;

        temp.next = curr.next;
        curr.next = temp;
        return head;


    }

    public static int searchKeyPos(Node head, int key) {
        int pos = 1;
        Node curr = head;
        while (curr != null) {
            if (curr.data == key) return pos;
            curr = curr.next;
            pos++;
        }
        return -1;


    }

    // -------------------Reverse LL____________________________
    public static Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node prevPtr = null, currPtr = head;
        while (currPtr != null) {
            Node temp = currPtr.next;
            currPtr.next = prevPtr;
            prevPtr = currPtr;
            currPtr = temp;
        }
        return prevPtr;

    }

    public static Node reverseRecursive1(Node head) {
        if (head == null || head.next == null) return head;

        Node rest_head = reverseRecursive1(head.next);
        Node rest_tail = head.next;
        rest_tail.next = head;
        head.next = null;
        return rest_head;


    }


    // more optimized
    public static Node reverseRecursive2(Node curr, Node prev) {
        if (curr == null) return prev;
        Node next = curr.next;
        curr.next = prev;
        return reverseRecursive2(next, curr);


    }


    // ________ ___________________________________________

    public static Node reverseInGroups(Node head, int k) {
        Node curr = head, prev = null;
        int count = 0;
        while (curr != null && count < k) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            count++;
        }
        if (curr != null) {
            head.next = reverseInGroups(curr, k);
        }
        return prev;
    }

    public static Node reverseInGroupsOptimized(Node head, int k) {
        Node curr = head, prevFirst = null;
        boolean isFirstPass = true;
        while (curr != null) {
            Node first = curr, prev = null;
            int count = 0;
            while (curr != null && count < k) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;

            }

            if (isFirstPass) {
                head = prev;
                isFirstPass = false;
            } else {
                prevFirst.next = prev;
            }
            prevFirst = first;

        }
        return head;
    }

    public static void removeDuplicatesInSortedLL(Node head) {
        if (head == null || head.next == null) return;
        Node curr = head;


        while (curr != null && curr.next != null) {
            if (curr.data == curr.next.data) {
                curr.next = curr.next.next;
            } else {

                curr = curr.next;
            }
        }

    }


    // * Loop in Linked List
    public static boolean isLoop(Node head) {

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;


    }

    // floyd's loop detection algorithm
    public static void removeLoop(Node head) {
        boolean isLoop = false;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isLoop = true;
                break;
            }
        }

        if (!isLoop) return;

        // move both at same speed
        slow = head;
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = null;

    }

    public static int lengthOfLoop(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }


        if (slow != fast) return 0;

        int length = 1;
        Node curr = fast.next;
        while (curr != fast) {
            curr = curr.next;
            length++;
        }
        return length;
    }

    public static Node loopFirstNode(Node head) {
        boolean isLoop = false;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isLoop = true;
                break;
            }
        }

        if (!isLoop) return null;

        // move both at same speed
        slow = head;
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast.next;
    }

    //------------------------------------------------------------

    public static Node segEvenOdd(Node head) {
        Node eS = null, eE = null;
        Node oS = null, oE = null;
        Node curr = head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                if (eS == null) {
                    eS = curr;
                    eE = curr;
                } else {
                    eE.next = curr;
                    eE = eE.next;
                }
            } else {
                if (oS == null) {
                    oS = curr;
                    oE = curr;
                } else {
                    oE.next = curr;
                    oE = oE.next;
                }
            }
            curr = curr.next;
        }
        if (oS == null || eS == null) return head;

        eE.next = oS;
        oE.next = null;
        return eS;


    }

    public static void interSectionPoint(Node h1, Node h2) {
        int c1 = countNodes(h1), c2 = countNodes(h2);
        int diff = Math.abs(c1 - c2);
        Node curr1 = h1, curr2 = h2;
        if (c1 > c2) {
            curr1 = moveKNodes(curr1, diff);
        } else {
            curr2 = moveKNodes(curr2, diff);
        }
        // now move both together
        while (curr1 != null && curr2 != null) {
            if (curr1 == curr2) {
                System.out.println("data found : " + curr1.data);
                return;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;

        }

        System.out.println("No intersection found ");


    }

    private static int countNodes(Node head) {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            curr = curr.next;
            count++;
        }

        return count;
    }

    private static Node moveKNodes(Node head, int k) {
        Node curr = head;
        for (int i = 0; i < k; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public static Node pairWiseSwap(Node head) {
        if (head == null || head.next == null) return head;

        Node curr = head.next.next;
        Node prev = head;
        head = head.next;
        head.next = prev;
        while (curr != null && curr.next != null) {
            prev.next = curr.next;
            prev = curr;
            Node next = curr.next.next;
            curr.next.next = curr;
            curr = next;

        }
        prev.next = curr;
        return head;
    }

    public static Node mergeSortedLL(Node h1, Node h2) {

        Node result = null, tail = null;
        while (h1 != null && h2 != null) {
            if (h1.data > h2.data) {
                if (result == null) {
                    result = h2;
                    tail = result;
                } else {
                    tail.next = h2;
                    tail = tail.next;
                }
                h2 = h2.next;
            } else {
                if (result == null) {
                    result = h1;
                    tail = result;
                } else {
                    tail.next = h1;
                    tail = tail.next;
                }
                h1 = h1.next;
            }
        }

        if (h1 == null) {
            if (result == null) {
                result = h2;
            } else tail.next = h2;
        } else {
            if (result == null) {
                result = h1;
            } else tail.next = h1;
        }

        return result;

    }
    public static boolean isPalindrome(Node h1){
        // Move to half of the List
        Node slow = h1, fast = h1;
        System.out.print("Original : ");
        LinkedList.traverse(h1);
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node reversedHalf = reverseTempLL(slow.next,null);
        System.out.print("After half reverse : " );
        LinkedList.traverse(h1);
        slow.next = reversedHalf;

        // now start comparing for checking the pallindrome;
        Node curr = h1;
        slow = slow.next;
        while(slow!=null){
             if(curr.data!=slow.data) return false;
             curr = curr.next;
             slow = slow.next;
        }
        return true;
    }

    private static Node reverseTempLL(Node curr,Node prev){
        if(curr==null) return prev;

        Node next = curr.next;
        curr.next = prev;
        return reverseTempLL(next,curr);
    }


}

