package General;

import java.util.ArrayList;

public class OnArrays {
    //Function to reverse every sub-array group of size k.
    public static void reverseInGroups(ArrayList<Integer> arr, int n, int k) {

        for(int i=0;(i+k-1)<arr.size();i+=k){
            reverse(arr,i,i+k-1);
        }

        if(arr.size()%k!=0) {

            int left =  arr.size()%k;

            reverse(arr,arr.size()-left, arr.size()-1);
        }


    }
    private static void reverse(ArrayList<Integer> arr, int start ,int end){

        while(end>=start){
            int temp = arr.get(end);
            arr.remove(end);
            arr.add(end-1,arr.get(start));

            arr.remove(start);
            arr.add(start,temp);
            end--;
            start++;


        }


    }
}
