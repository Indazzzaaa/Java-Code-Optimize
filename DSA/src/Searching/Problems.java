package Searching;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Problems {
    // count majority elements
    public static int majorityElement(int arr[], int size) {
        // moore's voting algorithm
        int count = 1, element = arr[0];

        // finding the right candidate
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == element) count++;
            else if (element == -1) {
                element = arr[i];
                count++;
            } else {
                count--;
                if (count == 0) element = -1;
            }
        }

        // now we have found the candidate
        if (element == -1) return -1;
        else {
            count = 0;
            for (var v : arr) {
                if (v == element) count++;
            }
        }

        return count > (size / 2) ? element : -1;
    }

    // finding peak element
    public static int peakElement(int[] arr, int n) {
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == n - 1 || arr[mid] >= arr[mid + 1])) return mid;
            else if (mid != 0 && arr[mid - 1] >= arr[mid]) end = mid - 1;
            else {
                start = mid + 1;
            }


        }

        return -1;
    }

    //Function to find the minimum element in sorted and rotated array.
    // this is time and space O(long(n)) does not take into account that they are sorted
    public static int minNumber(int arr[], int low, int high) {
        int mid = (low + high) / 2;


        if (high > low) {
            return Math.min(minNumber(arr, low, mid), minNumber(arr, mid + 1, high));

        } else {
            return arr[low];
        }


    }

    public static int minNumberOptimized(int arr[], int low, int end) {
        int res = Integer.MAX_VALUE;
        while (low <= end) {
            int mid = (low + end) / 2;
            if (arr[mid] >= arr[low]) {
                res = Math.min(res, arr[low]);
                low = mid + 1;
            } else {
                res = Math.min(res, arr[mid]);
                end = mid - 1;
            }
        }

        return res;
    }

    //Function to find repeated element and its frequency.
    public static Point findRepeating(Integer arr[], int n) {
        int freq = n - (arr[n - 1] - arr[0]);
        if (freq == 1) return new Point(-1, -1);
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] == mid + arr[0]) {
                low = mid + 1;
            } else {
                high = mid-1;
            }

        }

        return new Point(arr[low], freq);
    }
    //Function to find all elements in array that appear more than n/k times.
    public static int countOccurence(int[] arr, int n, int k)
    {
        HashMap<Integer,Integer> map = new HashMap<>(k-1);
        for(var val : arr){
            if(map.containsKey(val)) map.put(val,map.get(val)+1);
            else if(map.size() < k-1)  map.put(val,map.getOrDefault(val,0)+1);
            else{
                   subByOne(map);
            }
        }


        int res =0;
        for( var val : map.entrySet()){
            int cur =0;
            for(var arVal : arr){
                if(arVal == val.getKey()) cur++;
            }
            if(cur> (n/k)) res++;
        }

        return res;
    }

    private static void subByOne(HashMap<Integer,Integer> map){

        var iter = map.entrySet().iterator();
        while(iter.hasNext()){
            var entry = iter.next()
;            int freq = entry.getValue();
            if(freq==1) iter.remove();
            else map.put(entry.getKey(),freq-1);
        }




    }

    // subarray with given sum
    //Function to find a continuous sub-array which adds up to a given number.
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int s)
    {
        int start =0,sum = arr[0];
        for(int i=1;i<=n;i++){
            while(sum>s && start<i-1){
                sum-= arr[start];
                start++;
            }
            if(sum==s){
                var result = new ArrayList<Integer>();
                result.add(start+1);
                result.add(i);
                return result;
            }
            sum+=arr[i];
        }

        if(sum==s){
            var result = new ArrayList<Integer>();
            result.add(start+1);
            result.add(n);
            return result;
        }

        var result = new ArrayList<Integer>();
        result.add(-1);
        return  result;

    }



    }
