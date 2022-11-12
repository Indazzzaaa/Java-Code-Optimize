package Stacks;

public class MyStackLL {
     class StackNode {
         int data;
         StackNode next;
         StackNode(int a) {
             data = a;
             next = null;
         }
     }
    StackNode top;

    //Function to push an integer into the stack.
    void push(int a)
    {
        var node = new StackNode(a);
        node.next=top;
        top=node;

    }

    //Function to remove an item from top of the stack.
    int pop()
    {
        if(top==null) return -1;

        int data = top.data;
        top=top.next;
        return data;


    }
}
