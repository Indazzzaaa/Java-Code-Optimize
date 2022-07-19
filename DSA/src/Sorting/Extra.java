package Sorting;

import java.util.Arrays;

public class Extra {
    //Given an unsorted array arr[] of n positive integers.
    // Find the number of triangles that can be formed with three different array elements as lengths of three sides of triangles.
    // in triangle sum of two sides always grater then third side.
    public static int findNumberOfTriangles(int arr[], int n) {

        Arrays.sort(arr);
        int triangleCount = 0;
        for (int i = arr.length - 1; i > 1; i--) {
            int c = arr[i]; // we always taking grates element as c
            int lIndex = 0, rIndex = i - 1;
            while (rIndex > lIndex) {
                int a = arr[lIndex];
                int b = arr[rIndex];
                if (a + b > c) {
                    triangleCount += (rIndex - lIndex);
                    rIndex--;
                } else {
                    lIndex++;
                }
            }
        }

        return triangleCount;

    }


}
