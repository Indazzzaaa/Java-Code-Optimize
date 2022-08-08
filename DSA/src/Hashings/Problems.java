package Hashings;

import java.util.*;

public class Problems {

    // chaining
    public static ArrayList<ArrayList<Integer>> separateChaining(int arr[], int n, int hashSize) {
        ArrayList<ArrayList<Integer>> hash = new ArrayList<>();
        for (int i = 0; i < hashSize; i++) {
            hash.add(new ArrayList<Integer>());
        }

        for (int val : arr) {
            hash.get(val % hashSize).add(val);
        }
        return hash;


    }

    // linear probing
    public static int[] linearProbing(int hash_size, int arr[], int sizeOfArray) {
        int[] hash = new int[hash_size];
        Arrays.fill(hash, -1);
        for (int i = 0; i < sizeOfArray; i++) {
            int k = arr[i];

            int iter = 1;
            int p = k % hash_size;
            int start = p;
            while (hash[p] != -1 && hash[p] != arr[i]) {

                p = (k + iter) % hash_size;
                iter++;
                if (p == start) break;

            }

            if (hash[p] == -1) hash[p] = arr[i];

        }

        return hash;

        //Your code here
    }

    // quadratic probing
    static void quadraticProbing(int[] hash, int hash_size, int arr[], int N) {

        Arrays.fill(hash, -1);
        for (int i = 0; i < arr.length; i++) {
            int k = arr[i];

            int iter = 1;
            int p = k % hash_size;
            int start = p;
            while (hash[p] != -1 && hash[p] != arr[i]) {

                p = (k + iter * iter) % hash_size;
                iter++;
                if (p == start) break;

            }

            if (hash[p] == -1) hash[p] = arr[i];

        }

    }

    // count non-repetitive element in array
    public static long countNonRepeated(int arr[], int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int j : arr) {
            hashMap.put(j, hashMap.getOrDefault(j, 0) + 1);
        }
        long res = 0;
        for (var entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) res++;
        }

        return res;


    }

    // printing non_repetivie element in array
    //Function to return non-repeated elements in the array.
    static ArrayList<Integer> printNonRepeated(int arr[], int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : arr) {
            hashMap.put(j, hashMap.getOrDefault(j, 0) + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey((Integer) arr[i]) && hashMap.get((Integer) arr[i]) == 1) result.add(arr[i]);
        }

        return result;
    }

    // first repeating element
    public static int firstRepeated(int[] arr, int n) {

        int res = Integer.MAX_VALUE;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!hashMap.containsKey(arr[i])) hashMap.put(arr[i], i);
            else {
                if (hashMap.containsKey(arr[i]) && hashMap.get(arr[i]) != i) {
                    res = Math.min(res, hashMap.get(arr[i]));

                }
            }
        }


        return res != Integer.MAX_VALUE ? res + 1 : -1;

    }

    // Intersection of two arrays
    public static int NumberofElementsInIntersection(int a[], int b[], int n, int m) {
        int res = 0;
        // HashSet<Integer> hash = new HashSet(Arrays.asList(a));
        HashSet<Integer> hash = new HashSet<>();
        for (int val : a) hash.add(val);
        for (int val : b) {
            if (hash.contains(val)) res++;
            hash.remove(val);
        }

        return res;
    }

    //pair with given sum
    public static int sumExists(int arr[], int N, int sum) {
        HashSet<Integer> hash = new HashSet<>();
        for (var val : arr) {

            if (hash.contains(sum - val)) return 1;

            hash.add(val);


        }
        return 0;
    }

    // check if two array are equal or not
    public static boolean check(long A[], long B[], int N) {
        if (A.length != B.length) return false;
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            long a = A[i];
            long b = B[i];
            if (map.containsKey(a)) {
                int freq = map.get(a);
                if (freq == -1) {
                    map.remove(a);

                } else {

                    map.put(a, freq + 1);
                }


            } else {
                map.put(a, 1);
            }


            if (map.containsKey(b)) {
                // get frequency if 1 remove that key other wise decrease by  1
                int freq = map.get(b);
                if (freq == 1) {
                    map.remove(b);
                    continue;
                }

                map.put(b, freq - 1);

            } else {
                map.put(b, -1);
            }


        }

        return map.size() == 0;


    }

    // prefix sum
    public static boolean findsum(int arr[], int n) {
        HashSet<Integer> hash = new HashSet<Integer>();
        int pSum = 0;
        for (var val : arr) {
            pSum += val;
            if (pSum == 0) return true;
            if (hash.contains(pSum)) return true;


            hash.add(pSum);


        }
        return false;
    }

    // casting votes
    public static String[] winner(String arr[], int n) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] result = new String[2];
        for (var val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        result[0] = arr[0];
        int voteCount = 1;
        for (var entry : map.entrySet()) {
            if (entry.getValue() > voteCount) {
                voteCount = entry.getValue();
                result[0] = entry.getKey();
            } else if (entry.getValue() == voteCount) {
                result[0] = result[0].compareTo(entry.getKey()) < 0 ? result[0] : entry.getKey();
            }
        }
        result[1] = Integer.toString(voteCount);
        return result;

    }

    // ! number of subarray leads to given sum
    //Function to count the number of subarrays which adds to the given sum.
    public static int subArraySum(int arr[], int n, int sum) {
        HashMap<Integer, Integer> hashSet = new HashMap<>();
        hashSet.put(0, 1);
        int ps = 0, result = 0;
        for (var val : arr) {
            ps += val;
            /*if(ps==sum){
                result++;
                continue;
            }*/
            if (hashSet.containsKey(ps - sum)) {
                int freq = hashSet.get(ps - sum);
                result += freq;
            }

            hashSet.put(ps, hashSet.getOrDefault(ps, 0) + 1);


        }

        return result;


    }

    // count zeros sum subarrays
    public static long findSubarray(long[] arr, int n) {
        HashMap<Integer, Integer> hashSet = new HashMap<>();
        hashSet.put(0, 1);
        int ps = 0;
        long result = 0;
        for (var val : arr) {
            ps += val;
            if (hashSet.containsKey(ps)) {
                int freq = hashSet.get(ps);
                result += freq;
            }
            hashSet.put(ps, hashSet.getOrDefault(ps, 0) + 1);


        }

        return result;
    }

    // count number of equal zeros and 1 subarray
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {

        HashMap<Integer, Integer> hashSet = new HashMap<>(2);
        hashSet.put(0, 0);
        hashSet.put(1, 0);
        int ps = 0;
        int result = 0;
        for (var val : arr) {
            ps += val;
            if (hashSet.containsKey(ps)) {
                int freq = hashSet.get(ps);
                result += freq;
            }
            hashSet.put(ps, hashSet.getOrDefault(ps, 0) + 1);


        }

        return result;
    }


    //Function to sort an array according to the other array.
    public static int[] sortA1ByA2(int A1[], int N, int A2[], int M) {
        int[] result = new int[N];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var val : A1) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        int index = 0;
        for (var val : A2) {
            if (map.containsKey(val)) {
                int freq = map.get(val);
                for (int i = 0; i < freq; i++, index++) {
                    result[index] = val;

                }
                map.remove(val);
            }
        }
        for (var val : map.keySet()) {
            int freq = map.get(val);
            for (int i = 0; i < freq; i++, index++) {
                result[index] = val;

            }

        }

        return result;


    }

    // sort array according to higher frequencies
   public static ArrayList<Integer> sortByFreq(int arr[], int n) {


        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (var val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            result.add(val);
        }
        Comparator<Integer> myComparator = (o1, o2) -> {
            if (map.get(o1) > map.get(o2)) return -1;
            if (map.get(o1) < map.get(o2)) return 1;
            else{
                return o1.compareTo(o2);
            }
        };

        result.sort(myComparator);

        return result;

    }



}
