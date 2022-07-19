package Sorting;


import java.util.*;

public class Sorting {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        boolean isShuffled = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    arr[j + 1] = arr[j + 1] ^ arr[j] ^ (arr[j] = arr[j + 1]); // one line statement to swap the values
                    isShuffled = true;
                }
            }
            if (!isShuffled)
                return; // optimization to know that array is sorted in middle or not
        }
    }

    public static void bubbleSortOptimized(int[] arr) {
        if (arr.length <= 1) return;

        for (int i = 0; i <= arr.length / 2; i++) {
            for (int start = i, high = arr.length - i - 1; start < (arr.length - 1 - i) && high > i; start++, high--) {
                if (arr[start] > arr[start + 1]) swapElements(arr, start, (start + 1));
                if (arr[high] < arr[high - 1]) swapElements(arr, high, (high - 1));
            }

        }

    }

    // ! not fixed yet
    public static void optimizedBubbleSort(int[] arr) {
        if (arr.length <= 1)
            return;

        for (int i = 0; i < arr.length - 1; i++) {
            int start = 0, end = arr.length - 1 - i;
            while ((end - start) > 1) {
                if (arr[start] > arr[start + 1])
                    swapElements(arr, start, start + 1);
                if (arr[end - 1] < arr[end])
                    swapElements(arr, end - 1, end);

                start++;
                end--;
            }

            // swapping the greatest of two half
            if (arr[start] >= arr[end])
                swapElements(arr, arr.length - 1 - i, start);
            if (arr[start] <= arr[end])
                swapElements(arr, arr.length - 1 - i, end);
        }
    }

    private static void swapElements(int[] arr, int first, int second) {
        arr[first] = arr[first] ^ arr[second] ^ (arr[second] = arr[first]);
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = select(arr, i);
            swapElements(arr, i, index);

        }

    }

    private static int select(int[] arr, int i) {
        int minIndex = i;
        int l = i + 1;
        int r = arr.length - 1;
        // select from both the side
        while (l <= r) {
            if (arr[l] > arr[r]) {
                if (arr[minIndex] > arr[r]) {
                    minIndex = r;
                }
            } else {
                if (arr[minIndex] > arr[l]) {
                    minIndex = l;
                }
            }
            l++;
            r--;
        }

        return minIndex;

    }

    // Insertion sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insert(arr, i);
        }

    }

    private static void insert(int[] arr, int i) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;

    }

    // Merge sort
    public static void mergeSort(int[] arr, int l, int r) {

        // base case : at-least two elements should be there
        if (r > l) {
            int mid = l + (r - l) / 2; // to avoid overflowing (l+r)/2
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
//            merge(arr,l,mid,r);
            optimizedMerge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;
        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];
        // copying the elements
        for (int i = 0; i < leftLength; i++) leftArray[i] = arr[l + i];
        for (int i = 0; i < rightLength; i++) rightArray[i] = arr[m + 1 + i];
        // copying the element
        int mainArrayIndex = l;
        int left = 0, right = 0;
        while (left < leftLength && right < rightLength) {
            if (leftArray[left] >= rightArray[right]) {
                arr[mainArrayIndex] = rightArray[right];
                right++;
                mainArrayIndex++;
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


    public static void optimizedMerge(int[] arr, int l, int m, int r) {
        //gap algorithm  : nlog(n) for time , O(1) space
        // based on the fact that if element is smaller in sorted array it will smaller than all next ones also
        int lastAllowedIndex = l + (r - l + 1);
        // every time gap will divide to 2 : 8 ,4 , 2,1
        int gapCount = ((r - l + 1) & 1) == 1 ? (r - l + 2) / 2 : (r - l + 1) / 2;
        while (gapCount > 0) {
            int left = l;
            int right = l + gapCount;
            while (right < lastAllowedIndex) {
                if (arr[left] > arr[right]) swapElements(arr, left, right);
                right++;
                left++;
            }
            gapCount = getGap(gapCount);
        }


    }

    private static int getGap(int current) {
        if (current == 1) return 0;
        else return (current & 1) == 0 ? current / 2 : (current + 1) / 2;
    }

    // Intersection of two sorted arrays
    public static void intersection(int[] a, int[] b) {
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if ((i - 1) > 0 && a[i] == a[i - 1]) {
                i++;
                continue;

            } else if (a[i] > b[j]) {
                j++;
            } else if (b[j] > a[i]) {
                i++;
            } else {
                System.out.println(a[i]);
                i++;
                j++;
            }


        }
    }

    // Union of two sorted arrays
    public static void union(int[] a, int[] b) {
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (i > 0 && a[i] == a[i - 1]) {
                i++;
            } else if (j > 0 && b[j] == b[j - 1]) {
                j++;
            } else if (a[i] < b[j]) {
                System.out.println(a[i]);
                i++;
            } else if (b[j] < a[i]) {
                System.out.println(b[j]);
                j++;
            } else {
                System.out.println(a[i]);
                i++;
                j++;
            }
        }

        while (i < a.length) {
            if (i > 0 && a[i] == a[i - 1]) {
                i++;
            } else {
                System.out.println(a[i++]);
            }
        }

        while (j < b.length) {
            if (j > 0 && b[j] == b[j - 1]) {
                j++;
            } else {
                System.out.println(b[j++]);
            }
        }
    }

    //[QUICK SORT]
    public static int lomatoPartition(int[] a, int l, int h) {
        Random rd = new Random();
        int randomIndex = rd.nextInt(l, h);
        swapElements(a, randomIndex, h);
        int sIndex = l - 1;
        for (int j = l; j < h; j++) {
            if (a[j] < a[h]) {
                sIndex++;
                swapElements(a, sIndex, j);
            }
        }
        swapElements(a, ++sIndex, h);
        return sIndex;

    }

    // in hoars partition elements equal for greater goes right of final pivot unlike lomato
    public static int hoarsPartition(int[] a, int l, int h) {
        int lIndex = l - 1;
        int rIndex = h + 1;
        Random rd = new Random();
        int randomIndex = rd.nextInt(l, h);
        swapElements(a, randomIndex, l);
        int pivot = a[l];


        while (true) {
            do {
                lIndex++;
            } while (a[lIndex] < pivot);

            do {
                rIndex--;
            } while (a[rIndex] > pivot);
            if (rIndex <= lIndex) return rIndex;
            swapElements(a, lIndex, rIndex);

        }

    }

    public static void quickSort(int[] a, int l, int r) {
        while (r > l) {
            int pivot = hoarsPartition(a, l, r);
            quickSort(a, l, pivot);

            // tail call elimination
            l = pivot + 1;


//            quickSort(a,pivot+1,r);
        }
    }

    //[Cycle Sort]
    public static void cycleSort(int[] arr) {
        for (int cs = 0; cs < arr.length - 1; cs++) {
            int temp = arr[cs];
            int pos = cs;
            // find the correct pos of correct element;
            for (int j = cs + 1; j < arr.length; j++) {
                if (arr[j] < temp) pos++;
            }

            // swap the elements
            arr[pos] = temp ^ arr[pos] ^ (temp = arr[pos]);
            // working in cycles
            while (pos != cs) {
                pos = cs;
                for (int i = cs + 1; i < arr.length; i++) {
                    if (arr[i] < temp) pos++;
                }
                // swap the elements
                arr[pos] = temp ^ arr[pos] ^ (temp = arr[pos]);
            }


        }


    }

    //[Counting sort]
    // in this since we know that there may be max k elements in this so optimized it
    // but count sort we used in radix sort is different for this.
    public static void countSort(int[] arr, int k) {
        int[] elementsCount = new int[k + 1];
        for (int j : arr) {
            elementsCount[j]++;
        }

        int currentPos = 0;
        for (int i = 0; i < elementsCount.length; i++) {
            int frequency = elementsCount[i];
            while (frequency > 0) {
                arr[currentPos++] = i;
                frequency--;
            }
        }


    }

    //[Heap Sort]

    public static void heapSort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swapElements(arr, 0, i);
            // reduce size and maxhepify
            maxHeap(arr, i, 0);
        }
    }

    private static void maxHeap(int[] arr, int currentSize, int rootToCheck) {
        int largest = rootToCheck;
        int left = 2 * rootToCheck + 1;
        int right = 2 * rootToCheck + 2;
        if (left < currentSize && arr[left] > arr[largest]) largest = left;
        if (right < currentSize && arr[right] > arr[largest]) largest = right;

        if (largest != rootToCheck) {
            swapElements(arr, largest, rootToCheck);
            maxHeap(arr, currentSize, largest);
        }
    }

    private static void buildHeap(int[] arr) {

        //  from parent to last node to all
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            {
                maxHeap(arr, arr.length, i);
            }
        }
    }

    //[Radix sort]
    public static void radixSort(int[] arr) {
        int maxElement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxElement) maxElement = arr[i];
        }

        for (int exp = 1; maxElement / exp > 0; exp *= 10) {
            radixCountSort(arr, exp);
        }


    }

    private static void radixCountSort(int[] arr, int exp) {
        int[] count = new int[10];
        int[] output = new int[arr.length];
        for (int val : arr) {
            count[(val / exp) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int j = arr.length - 1; j >= 0; j--) {
            output[count[(arr[j] / exp) % 10] - 1] = arr[j];
            count[(arr[j] / exp) % 10]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }

    }

    //[Bucket Sort] Good when data is uniformly distributed
    public static void bucketSort(int[] arr) {
        int max = arr[0];
        for (int val : arr) {
            if (max < val) max = val;
        }

        int bktSize = 0;
        // we can think more about the bkt size
        if (max <= Math.pow(10, 2)) bktSize = (int) Math.pow(2, 2);
        else if (max <= Math.pow(10, 3)) bktSize = (int) Math.pow(2, 3);
        else if (max <= Math.pow(10, 4)) bktSize = (int) Math.pow(2, 4);
        else if (max <= Math.pow(10, 5)) bktSize = (int) Math.pow(2, 5);
        else {
            bktSize = (int) Math.pow(2, 6);
        }

        max += 1; // !important
        // creating bkts
        ArrayList<ArrayList<Integer>> bkts = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < bktSize; i++) {
            bkts.add(new ArrayList<Integer>());
        }

        // fill the bkts
        for (int val : arr) {
            int decidedBkt = bktSize * val / max;
//            System.out.println("Bkt : " + decidedBkt + " value : " + val);

            bkts.get(decidedBkt).add(val);
        }

        // sort the bkts
        for (var bktt : bkts) {
            Collections.sort(bktt);
        }
        // join the bkts
        int index = 0;
        for (var bktt : bkts) {
            for (Integer integer : bktt) {
                arr[index++] = integer;
            }
        }

    }


}