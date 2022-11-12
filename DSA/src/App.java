import java.util.ArrayDeque;
import java.util.Queue;
import  Queue.Problems;

public class App {
    public static void main(String[] args) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(10);
        dq.offer(20);
        dq.offer(30);
        dq.offer(40);
        var it = dq.iterator();
//        System.out.println(it.next());

        for(int i=0;i<2;i++){
            it.next();
            it.remove();
        }
//        it.remove();

        for(var x : dq) System.out.println(x);


    }


}


