package Sorting;

import java.lang.reflect.Array;
import java.util.*;

public class Problems {

    private static void swap(int[] a, int first, int second) {
        a[second] = a[second] ^ a[first] ^ (a[first] = a[second]);
    }

    public static int lomatoPartition(int[] arr, int low, int high) {

        Random rd = new Random();
        int randomIndex = rd.nextInt(low, high);
        swap(arr, randomIndex, high);

        int smallIndex = low - 1;
        for (int i = low; i < high; i++) {
            if (arr[i] < arr[high]) {
                swap(arr, ++smallIndex, i);
            }
        }
        swap(arr, ++smallIndex, high);
        return smallIndex;

    }

    public static int hoarsPartition(int[] arr, int low, int high) {
        int lIndex = low - 1;
        int hIndex = high + 1;

        while (true) {
            do {
                lIndex++;
            } while (arr[lIndex] < arr[low]);

            do {
                hIndex--;

            } while (arr[hIndex] > arr[low]);
            if (lIndex >= hIndex) return hIndex;
            swap(arr, lIndex, hIndex);

        }


    }

    // Kth smallest
    public static int kthSmallestElement(int[] arr, int k) {

        if (k > arr.length) return -1;
        int partition = lomatoPartition(arr, 0, arr.length - 1);
        int l = 0, h = arr.length - 1;

        while (l <= h) {
            if (partition == (k - 1)) return arr[partition];
            else if (partition > (k - 1)) {
                h = partition - 1;
            } else {
                l = partition + 1;
            }
            partition = lomatoPartition(arr, l, h);

        }


        return -1;

    }

    // kth largest
    public static int kthLargestElement(int[] arr, int k) {
        // that will be arr.length -k smallest element
        return kthSmallestElement(arr, arr.length - k + 1);

    }

    // segregate array of two types
    public static void segPosNeg(int[] arr) {
        int lIndex = -1;
        int rIndex = arr.length;
        while (true) {
            do {
                lIndex++;
            } while (arr[lIndex] < 0);
            do {
                rIndex--;

            } while (arr[rIndex] >= 0);

            if (lIndex >= rIndex) {

                return;
            }
            swap(arr, lIndex, rIndex);
        }


    }

    public static void segEvenOdd(int[] arr) {
        int lIndex = -1;
        int rIndex = arr.length;
        while (true) {
            do {
                lIndex++;
            } while ((arr[lIndex] & 1) == 1);
            do {
                rIndex--;

            } while ((arr[rIndex] & 1) == 0);

            if (lIndex >= rIndex) {

                return;
            }
            swap(arr, lIndex, rIndex);
        }


    }

    public static void binSort(int arr[]) {
        int start = -1, end = arr.length;
        while (true) {
            do {
                start++;
            } while (start < arr.length && arr[start] == 0);

            do {
                end--;
            } while (end > -1 && arr[end] == 1);
            if (start >= end) {
                return;
            }
            arr[start] = arr[end] ^ arr[start] ^ (arr[end] = arr[start]);

        }


    }

    // dutch flag algo
    // same concept can be for range partitioning
    public static void threeWayPartitioning(int[] arr) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 1) {
                mid++;
            } else if (arr[mid] == 0) {
                swap(arr, mid++, low++);
            } else {
                swap(arr, mid, high--);
            }


        }


    }

    // count inversion

    public static int merge(int[] arr, int low, int high) {
        int inversions = 0;
        if (high > low) {
            int mid = low + (high - low) / 2;
            inversions += merge(arr, low, mid);
            inversions += merge(arr, mid + 1, high);
            inversions += mergeSortedArray(arr, low, mid, high);
        }
        return inversions;


    }

    public static int mergeSortedArray(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int inversions = 0;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        for (int i = 0; i < n1; i++) leftArr[i] = arr[l + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[m + j + 1];
        int i = 0, j = 0;
        int oIndex = l;
        while (i < n1 && j < n2) {
            if (leftArr[i] > rightArr[j]) {
                arr[oIndex++] = rightArr[j++];
                inversions += n1 - i;
            } else {
                arr[oIndex++] = leftArr[i++];
            }
        }
        while (i < n1) {
            arr[oIndex++] = leftArr[i++];
        }
        while (j < n2) {
            arr[oIndex++] = rightArr[j++];
        }

        return inversions;


    }

    // Meet maximum people
    public static int maxMeet(int ari[], int dep[]) {
        Sorting.quickSort(ari, 0, ari.length - 1);
        System.out.println(Arrays.toString(ari));
        Sorting.quickSort(dep, 0, dep.length - 1);
        System.out.println(Arrays.toString(dep));

        int i = 0, j = 0, res = 0, currCount = 0;
        while (i < ari.length && j < dep.length) {
            if (ari[i] < dep[j]) {
                i++;

                res = Math.max(res, ++currCount);
            } else if (ari[i] == dep[j]) {
                i++;
                j++;
                // net result will be 0
            } else {
                currCount--;
                j++;


            }
        }
        return res;


    }


    public static int nCr(int n, int r) {
        if (n - r == 0) return 1;
        return factorial(n) / (factorial(n - r) * factorial(r));

    }

    public static int factorial(int n) {
        if (n <= 1) return n;
        int result = 1;
        do {
            result *= n;
        } while (--n > 0);
        return result;
    }

    //Given an array arr[](0-based indexing) of N integers which is closer sorted (defined below) and an element x. The task is to find the index of element x if it is present. If not present, then print -1.
    //Closer Sorted: The first array is sorted, but after sorting some elements are moved to either of the adjacent positions, i.e, maybe to the arr[i+1] or arr[i-1].
    public static long closer(int arr[], int n, long x) {
        int lIndex = 0, rIndex = arr.length - 1;
        while (lIndex <= rIndex) {
            int mid = (lIndex + rIndex) / 2;
            if (x == arr[mid]) return mid;
            else if (x > arr[mid]) {
                if (mid - 1 >= 0 && arr[mid - 1] == x) return mid - 1;
                else {
                    lIndex = mid + 1;
                }
            } else {
                if (mid + 1 < arr.length && arr[mid + 1] == x) return mid + 1;
                else {
                    rIndex = mid - 1;
                }
            }

        }


        return -1;


    }

    // [21]
    public static int findPlatform(int arr[], int dep[], int n) {

        if (arr.length <= 1) return arr.length;

        Arrays.sort(arr);
        Arrays.sort(dep);
        int result = 0;
        int platforms = 0;
        int i = 0, j = 0;
        while (i < n && j < n) {
            // this line also deals with two trans can't arrive and dep at same time from same platform
            if (dep[j] >= arr[i]) {
                i++;
                platforms++;

            } else {

                j++;
                platforms--;
            }

            result = Math.max(result, platforms);
        }
        return result;


    }

    // merging three sorted arrays
    public static ArrayList<Integer> merge3sorted(int A[], int B[], int C[]) {
        int aIndex = 0, bIndex = 0, cIndex = 0;
        ArrayList<Integer> sortedList = new ArrayList<>(A.length + B.length + C.length);
        while (aIndex < A.length && bIndex < B.length && cIndex < C.length) {
            int val = A[aIndex] > B[bIndex] ? (B[bIndex] > C[cIndex] ? C[cIndex++] : B[bIndex++]) : (A[aIndex] > C[cIndex] ? C[cIndex++] : A[aIndex++]);
            sortedList.add(val);
        }
        decideWhich2ToSort(sortedList, A, B, C, aIndex, bIndex, cIndex);
        System.out.println(sortedList.toString());
        System.out.println("Size: " + sortedList.size());
        return sortedList;
    }

    private static void decideWhich2ToSort(ArrayList<Integer> sortedList, int a[], int[] b, int[] c, int aIndex, int bIndex, int cIndex) {
        if (aIndex == a.length) merge2sorted(sortedList, b, c, bIndex, cIndex);
        else if (bIndex == b.length) merge2sorted(sortedList, a, c, aIndex, cIndex);
        else merge2sorted(sortedList, a, b, aIndex, bIndex);

    }

    private static void merge2sorted(ArrayList<Integer> sortedList, int[] a, int[] b, int aIndex, int bIndex) {
        while (aIndex < a.length && bIndex < b.length) {
            int val = a[aIndex] > b[bIndex] ? b[bIndex++] : a[aIndex++];
            sortedList.add(val);
        }
        while (aIndex < a.length) sortedList.add(a[aIndex++]);
        while (bIndex < b.length) sortedList.add(b[bIndex++]);

    }

    // gap method of merging 2 sorted array
    //Function to merge the arrays.
    public static void merge2sorted(long arr1[], long arr2[], int n, int m) {
        int gap = ((arr1.length + arr2.length) & 1) == 0 ? (arr1.length + arr2.length) / 2 : (arr1.length + arr2.length) / 2 + 1;
        while (gap > 0) {

        }

    }

    // finding triplets
    public static boolean findTriplets(int arr[], int n) {
        if (arr.length <= 2) return false;

        Arrays.sort(arr);
        int b = 1, c = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            b = i + 1;
            c = arr.length - 1;
            while (c > b) {
                if (arr[b] + arr[c] == -arr[i]) {
                    System.out.printf("values are : %d , %d ,%d \n", arr[i], arr[b], arr[c]);
                    return true;
                } else if (arr[b] + arr[c] > -arr[i]) c--;
                else b++;

            }
        }
        return false;
    }

    // counting sort for arranging string
    public static String countSort(String arr) {
        int[] characterFreq = new int[26];
        System.out.println("values : " + Arrays.toString(characterFreq));
        char[] output = new char[arr.length()];
        System.out.println("output : " + Arrays.toString(output));
        for (int i = 0; i < arr.length(); i++) {
            characterFreq[arr.charAt(i) % 97]++;
        }
        System.out.println("After counting freq : " + Arrays.toString(characterFreq));

        int currentIndex = 0;
        for (int i = 0; i < characterFreq.length; i++) {
            int j = characterFreq[i];
            while (j > 0) {
                output[currentIndex++] = (char) (97 + i);
                j--;
            }
        }

        return new String(output);
    }

    // segragate012
    public static void segragate012(int arr[], int N) {
        int low = -1;
        int mid = 0;
        int high = arr.length;
        while (high > mid) {
            if (arr[mid] == 0) {
                low++;
                arr[mid] = arr[low] ^ arr[mid] ^ (arr[low] = arr[mid]);
                mid++;
            } else if (arr[mid] == 2) {
                high--;
                arr[mid] = arr[high] ^ arr[mid] ^ (arr[high] = arr[mid]);
            } else {
                mid++;
            }
        }
    }

    public static void segragate012Optimized(int arr[], int N) {
        int low = -1, mid = 0, high = arr.length;
        while (true) {
            do {
                if (arr[mid] == 2) break;
                if (arr[mid] == 0) {
                    low++;
                    arr[low] = arr[mid] ^ arr[low] ^ (arr[mid] = arr[low]);
                    mid++;
                    continue;
                }
                mid++;
            } while (mid < arr.length && arr[mid] != 2);
            do {
                high--;
            } while (arr[high] == 2);
            if (mid > high) return;
            arr[mid] = arr[high] ^ arr[mid] ^ (arr[high] = arr[mid]);
        }
    }

    // sort by absolute difference
    public static void sortABS(int[] arr, int n, int k) {
        mergeSort(arr, 0, arr.length - 1, k);
    }

    public static void mergeSort(int[] arr, int l, int r, int k) {

        // base case : at-least two elements should be there
        if (r > l) {
            int mid = l + (r - l) / 2; // to avoid overflowing (l+r)/2
            mergeSort(arr, l, mid, k);
            mergeSort(arr, mid + 1, r, k);
            merge(arr, l, mid, r, k);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, int k) {
        int leftLength = m - l + 1;
        int rightLength = r - m;
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];
        // copying the elements
        for (int i = 0; i < leftLength; i++) leftArray[i] = arr[l + i];
        for (int i = 0; i < rightLength; i++) rightArray[i] = arr[m + 1 + i];
        // copying the element to main array
        int mainArrayIndex = l;
        int left = 0, right = 0;
        while (left < leftLength && right < rightLength) {
            if (Math.abs(leftArray[left] - k) > Math.abs(rightArray[right] - k)) {
                arr[mainArrayIndex] = rightArray[right];
                right++;
                mainArrayIndex++;
            } else if (Math.abs(leftArray[left] - k) == Math.abs(rightArray[right] - k)) {
                // check who comes first in main array and put that.
                if (isThisFirst(arr, leftArray[left], rightArray[right])) {
                    arr[mainArrayIndex++] = leftArray[left++];
                } else {
                    arr[mainArrayIndex++] = rightArray[right++];
                }

            } else {
                arr[mainArrayIndex] = leftArray[left];
                left++;
                mainArrayIndex++;
            }
        }
        while (left < leftLength) {
            arr[mainArrayIndex] = leftArray[left];
            left++;
            mainArrayIndex++;
        }
        while (right < rightLength) {
            arr[mainArrayIndex] = rightArray[right];
            right++;
            mainArrayIndex++;
        }

    }

    private static boolean isThisFirst(int[] arr, int main, int second) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == main) return true;
            if (arr[i] == second) break;
        }
        return false;
    }

    //! count pairs such that x^y > y^x;
    public static long countPairs(int x[], int y[], int M, int N) {

        Arrays.sort(y);
        int[] counts = new int[5];
        // count number of 0,1,2,3,4
        for (var v : y) {
            if (v >= 5) break;
            counts[v]++;
        }
        int pairs = 0;

        for (var v : x) {
            if (v == 0) {
            }// do nothing
            else if (v == 1) {
                pairs += counts[0];
            } else if (v == 2) {
                // find it's correct position subtract number of 2 and 3
                int pos = Arrays.binarySearch(y, v+1);
                pairs += (pos > 0 ? (y.length - pos ) : (y.length - Math.abs(pos) + 1)) ;
                pairs-= (counts[3] + counts[4]);
                pairs += counts[0] + counts[1];

            } else if (v == 3) {
                pairs += counts[2];
                int pos = Arrays.binarySearch(y, v+1);
                pairs += (pos > 0 ? (y.length - pos ) : (y.length - Math.abs(pos) + 1));
                pairs += counts[0] + counts[1];

            } else {
                int pos = Arrays.binarySearch(y, v+1);
                pairs += (pos > 0 ? (y.length - pos ) : (y.length - Math.abs(pos) + 1));
                pairs += counts[0] + counts[1];
            }


        }
        return pairs;


    }

    // merging two sorted arrays
    public static void merge(long arr1[], long arr2[], int n, int m) {
        int totalLength = arr1.length + arr2.length;
        int gapCount = (totalLength & 1) == 1 ? (totalLength + 1) / 2 : totalLength / 2;
        while (gapCount > 0) {
            int start = 0;
            int end = gapCount;
            for (; end < totalLength; end++, start++) {
                if (start >= arr1.length) {
                    if (arr2[start - arr1.length] > arr2[end - arr1.length]) {
                        swapElementsIn2Arrays(arr2, arr2, start - arr1.length, end - arr1.length);
                    }
                } else if (end < arr1.length) {

                    if (arr1[start] > arr1[end]) {
                        swapElementsIn2Arrays(arr1, arr1, start, end);
                    }
                } else {
                    if (arr1[start] > arr2[end - arr1.length]) {
                        swapElementsIn2Arrays(arr1, arr2, start, end - arr1.length);
                    }

                }
            }
            gapCount = getGap(gapCount);
        }

    }

    private static int getGap(int lastGap) {
        if (lastGap == 1) return 0;
        return (lastGap & 1) == 1 ? (lastGap + 1) / 2 : lastGap / 2;

    }


    private static void swapElementsIn2Arrays(long[] arr1, long[] arr2, int start, int end) {
        arr1[start] = arr2[end] ^ arr1[start] ^ (arr2[end] = arr1[start]);


    }
}
