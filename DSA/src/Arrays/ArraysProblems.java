package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArraysProblems {
    // maximum of two adjacent pairs
    public static void maximumAdjacent(int sizeOfArray, int arr[]) {

        for (int i = 1; i < arr.length; i++) {
            System.out.println(Math.max(arr[i], arr[i - 1]));
        }


    }

    // reverse array in groups
    public static void reverseInGroups(ArrayList<Integer> arr, int n, int k) {

        int start = 0;
        while (n > 0) {
            if ((n / k) > 0) {
                reverse(arr, start, start + k - 1);
                start += k;
                n -= k;
            } else {
                reverse(arr, start, start + n - 1);
                n = 0;
            }
        }


    }

    private static void reverse(ArrayList<Integer> arr, int start, int end) {
        while (start < end) {
            int temp = arr.get(end);
            arr.set(end, arr.get(start));
            arr.set(start, temp);
            end--;
            start++;
        }
    }

    // rotating array anti clockwise by d
    public static void rotateArr(int arr[], int d, int n) {

        d %= n;

        rotate(arr, 0, d - 1);
        rotate(arr, d, n - 1);
        rotate(arr, 0, n - 1);
    }

    private static void rotate(int arr[], int start, int end) {
        while (start < end) {
            arr[start] = arr[end] ^ arr[start] ^ (arr[end] = arr[start]);
            start++;
            end--;
        }
    }

    // ! Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P) {
        int i = 0;
        while (i < N) {
            // this element has been processed
            if (arr[i] <= 0) {
                i++;
                continue;
            }

            // bcs 4th index will have value 5; and all a[i] >0;
            int elementIndex = arr[i] - 1;
            if (elementIndex >= N) {
                arr[i] = 0;
                i++;

            } else if (arr[elementIndex] > 0) {
                arr[i] = arr[elementIndex];
                arr[elementIndex] = -1;
            } else {
                arr[elementIndex]--;
                arr[i] = 0;
                i++;
            }
        }

        for (int j = 0; j < N; j++) {
            arr[j] = Math.abs(arr[j]);
        }


    }

    // first equilibrium point
    public static int equilibriumPoint(long arr[], int n) {
        if (n == 1) return 1;
        long total = 0;
        for (var val : arr) {
            total += val;
        }

        long leftSum = 0, rightSum = 0;
        for (int i = 1; i < n; i++) {
            leftSum += arr[i - 1];
            rightSum = total - leftSum - arr[i];

            if (leftSum == rightSum) return i + 1;

        }

        return -1;

    }


    //Function to find the smallest positive number missing from the array.
    public static int missingNumber(int arr[], int size) {
        int i = 0;
        while (i < size) {


            if (arr[i] <= 0 || (arr[i] - 1) > size) {
                arr[i] = 0;
                i++;
                continue;
            }

            int elementIndex = arr[i] - 1;

            if (elementIndex >= size) {
                arr[i] = 0;
                i++;
                continue;
            }

            if (elementIndex == i) {
                i++;
                continue;
            }

            if (arr[elementIndex] == arr[i]) {
                arr[i] = 0;
                i++;
                continue;
            }

            if (arr[elementIndex] <= 0) {
                arr[elementIndex] = arr[i];
                arr[i] = 0;
                i++;

            } else if (arr[elementIndex] > 0) {
                int temp = arr[i];
                arr[i] = arr[elementIndex];
                arr[elementIndex] = temp;

            }


        }

        for (int j = 0; j < size; j++) {
            if (arr[j] != (j + 1)) return j + 1;
        }

        return size + 1;

    }

    // rearrange in max1-min1, max2-min2 in sorted array

    public static void rearrange(long arr[], int n) {

        long maxElement = arr[n-1] + 1;
        int max = n-1;
        int min = 0;

        for(int i = 0; i < n; i++){
            if(i%2 == 0){
                arr[i] = arr[i] + (arr[max] % maxElement) * maxElement;
                max--;
            }
            else{
                arr[i] = arr[i] + (arr[min] % maxElement) * maxElement;
                min++;
            }
        }

        for(int i = 0; i < n; i++){
            arr[i] = arr[i] / maxElement;
        }





    }

    //17Q
    //How this can be achieved?
    //Let's assume an element is a and another element is b,
    // both the elements are less than n. So if an element a is incremented by b*n.
    // So the element becomes a + b*n so when a + b*n is divided by n then the value is b and a + b*n % n is a.

    static void arrange(long arr[], int n) {
        for(int i=0;i<n;i++)
        {
            arr[i]+=(arr[(int)arr[i]]%n)*n;
        }
        for(int i=0;i<n;i++)
        {
            arr[i]=arr[i]/n;
        }
    }

    // print pattern

  public  static List<Integer> pattern(int N){
        List<Integer> result = new ArrayList<>();


        result.add(N);
        result.add(N-5);
        int last = N-5;
        while(last>0){
            last-=5;
            result.add(last);



        }
        while (last!=N){
            last+=5;
            result.add(last);
        }

        return result;

    }

    // number game
   public  static Long numGame(Long n) {
        if(n<2) return n;

        long mod = (long)Math.pow(10,9)+7;
        long result =2;
        for(int i=3;i<=n;i++){
            result = lcm(result,i);
            result%=mod;
        }

        return result;
    }

    static long lcm(long a,long b){
        return (a*b)/gcd(a,b);
    }

    static long gcd(long a, long b){
        if(b==0) return a;

        else return gcd(b,a%b);
    }

    // max number of time occured elemnt in an range
    public static int maxOccured(int L[], int R[], int n, int maxx){
        int[] arr = new int[maxx+1];
        for(int i=0;i<n;i++){
            increaseFeq(arr,L[i],R[i]);
        }

        int result=0;
        int maxIndex=0;
        for(int i=0;i<=maxx;i++){
             result = arr[i]>arr[result] ? i : result;
        }

        return result;



    }

    private static void increaseFeq(int[] arr , int start, int end){
        for(int i=start;i<=end;i++){
            arr[i]++;
        }
    }

    // ! max index difference problem Q18
    // think from the window prospect, where we have to find longest window
    // who satisfies this condition.

   public static int maxIndexDiff(int arr[], int N) {
        int maxDiff=0;
        int i=0;
        while(i<N){


            maxDiff = Math.max(maxDiff,findJ(arr,arr[i] ,i+maxDiff+1));

            i++;
        }

        return maxDiff;


    }

    private  static int findJ(int[] arr ,  int val,int upto){

        if(upto>=arr.length) return 0;
        for(int i=arr.length-1;i>=upto;i--){
            if(arr[i]>=val) return i;
        }

        return 0;
    }




}
