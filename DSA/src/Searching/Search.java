package Searching;

public class Search {

    // binary search
    public static int binSearch(int[] sortedArr,int key){
        int low=0,end=sortedArr.length-1;

        while(low<=end){
        int mid= (low+end)/2;
        if(key==sortedArr[mid]) return mid;
        else if(key>sortedArr[mid]) low = mid+1;
        else{
            end=mid-1;
        }


        }

        return -1;



    }

    //  find first occurrence in sorted array of duplicates
    public static int firstOccr(int[] sortedArr,int key){
        int start =0,end=sortedArr.length-1;
        while(start<=end){
            int mid = (start+end)/2 ;
            if(key>sortedArr[mid]) start = mid+1;
            else if(key<sortedArr[mid])end = mid-1;
            else if(mid==0 || sortedArr[mid]!=sortedArr[mid-1]) return mid;
            else{
                end= mid-1;
            }


        }

        return -1;



    }
    // last occurrence in sorted array of duplicates
    public static int lastOccr(int[] sortedArr, int key){
        int start =0,end= sortedArr.length-1;
        while(start<=end){
             int mid= (start+end)/2;
             if(key>sortedArr[mid]) start = mid+1;
             else if(key<sortedArr[mid]) end= mid-1;
             else if(mid==sortedArr.length-1 || sortedArr[mid]!=sortedArr[mid+1]) return mid;
             else{
                 start = mid+1;
             }

        }

        return -1;


    }
    //  number of times key element present in sorted array

    public static int countOccr(int[] sortedArr,int key){
        int firstOccr = firstOccr(sortedArr,key);
        int lastOccr = lastOccr(sortedArr,key);
        return lastOccr-firstOccr+1;
    }

    // find floor square root
    public static int floorSqrt(int n){
        int start = 1, end=n;
        int ans=-1;
        while (start<=end){
            int mid = (start+end)/2;
            int sqr = mid*mid;
            if(sqr == n) return sqr;
            else if(sqr>n)  end = mid-1;
            else {
                start = mid+1;
                ans=mid;

            }

        }

        return ans;


    }

    // number greater then given value
    public static int ceilOfKey(int[] sortedArr, int key){
        int start =0,end = sortedArr.length-1;
        int ans=-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(sortedArr[mid]< key) start = mid+1;
            else{
                ans = mid;
                end = mid-1;

            }
        }

        return ans;
    }

    // number smaller then given key value
    public static int floorOfKey(int[] sotedArr,int key){
        int start =0,end = sotedArr.length-1;
        int ans = -1;
        while(start<=end){
            int mid = (start+end)/2;
            if(sotedArr[mid]>key) end = mid-1;
            else{
                ans = mid;
                start= mid+1;
            }
        }

        return ans;
    }
    // finding repeating element
    public static int findRepeating(int[] arr){
        int slow =arr[0],fast = arr[0];
        do{
            // fast is going twice the speed of slow
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow != fast);

        slow = arr[0];
        do{
            slow = arr[slow];
            fast = arr[fast];
        }while(slow!=fast);
            return  slow;



    }

    // median in two sorted array
    public static int median2SortedArr(int[] arr1, int[] arr2){
        // to make sure that arr1 is always of smaller length
        if(arr1.length>arr2.length) {
            var tmp = arr2;
            arr2=arr1;
            arr1=tmp;
        }
        int n1= arr1.length;
        int n2 = arr2.length;
        int left = 0,right = n1;
        while(left<=right){
            int i1 = (left+right)/2;
            int i2 = (n1+n2+1)/2 - i1;
            int max1 = (i1==0) ? Integer.MIN_VALUE : arr1[i1-1];
            int min1 = (i1==n1) ? Integer.MAX_VALUE : arr1[i1];
            int max2 = (i2==0) ? Integer.MIN_VALUE : arr2[i2-1];
            int min2 = (i2==n2) ? Integer.MAX_VALUE : arr2[i2];
            if(min1>=max2 && min2>=max1){
                if((n1+n2)%2 ==0) {
                    return  (Math.max(max1,max2) +Math.min(min1,min2))/2;
                }else{
                    return Math.max(min1,min2);
                }
            }else{
                if(min1< max2) left = i1+1;
                else{
                    right = i1-1;
                }

            }



        }

        return -1;


    }



}
