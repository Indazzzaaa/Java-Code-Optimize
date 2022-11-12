package Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Problems {

    public static int tour(int petrol[], int distance[]) {
        int totalPetrol = 0;
        int index = -1;
        for (int i = 0; i < petrol.length; i++) {
            totalPetrol += (petrol[i] - distance[i]);
            if (totalPetrol >= 0) {
                index = index != -1 ? index : i;
            } else {
                index = -1;
            }
        }

        return index;

    }

    //Function to find maximum of each subarray of size k.
    public static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        // processing first group separately
        for (int i = 0; i < k; i++) {
            if (dq.isEmpty()) dq.offer(i);
            else {
                while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) dq.pollLast();
            }
            dq.offerLast(i);
        }

        // now working with the remaining window
        for (int i = k; i < n; i++) {
            // first check whether first element is smaller the coming element
            if (!dq.isEmpty() && arr[dq.peekFirst()] <= arr[i]) {
                result.add(dq.pollFirst());
                dq.clear();
            } else if (!dq.isEmpty() && i >= dq.peekFirst()) {
                result.add(dq.pollFirst());
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) dq.pollLast();
            dq.offerLast(i);


        }

        return result;

    }
}
