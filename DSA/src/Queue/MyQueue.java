package Queue;

public class MyQueue {

    int front, rear;
    int arr[] = {2,0,0};

    public MyQueue()
    {
        front=0;
        rear=0;
    }

    //Function to push an element x in a queue.
   public void push(int x)
    {
        if(front==rear && arr[front]==0) arr[front]=x;
        else if(arr[(1+rear)%arr.length]!=0 ) return;
        else arr[(++rear)%arr.length] = x;


    }

    //Function to pop an element from queue and return that element.
   public  int pop()
    {
        if(front==rear && arr[front]==0) return -1;
        else if(front==rear && arr[front]!=0) {
            int dataReturn = arr[front];
            arr[front]=0;
            front=0;
            rear=0;
            return dataReturn;
        }else{
            return arr[front++%arr.length];
        }
    }
}
