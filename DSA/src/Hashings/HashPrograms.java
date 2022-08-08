package Hashings;

import java.util.Arrays;
import java.util.HashSet;

public class HashPrograms {

    //  paris with given sum in  unsorted array
    public static boolean isPairThere(int[] arr , int sum){

        HashSet<Integer> hash = new HashSet<>();
        for (int j : arr) {
            if (hash.contains(sum - j)) return true;
            else {
                hash.add(j);
            }
        }
        return false;



    }




}
