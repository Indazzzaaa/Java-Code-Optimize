package Strings;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Problems {
    // left most first repeating character
    public static int repeatedCharacter(String S) {
        boolean[] chars = new boolean[256];
        int res = -1;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (chars[S.charAt(i)]) {
                res = i;
            }
            chars[S.charAt(i)] = true;

        }

        return res;


    }

    // Non repeating character : given lower case lating letters if no lowercase then return $
    public static char nonrepeatingCharacter(String S) {
        int[] chars = new int[27];
        Arrays.fill(chars, -1);
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (chars[c & 31] > -1) chars[c & 31] = -2;
            else if (chars[c & 31] == -1) chars[c & 31] = i;
        }
        int res = S.length();
//            System.out.println(Arrays.toString(chars));
        for (int val : chars) {
            if (val > -1) {
                res = Math.min(res, val);
            }
        }
        if (res < S.length()) return S.charAt(res);
        else return '$';


    }

    // keypad typing
    public static String printNumber(String s, int n) {
        String map = "022233344455566677778889999";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            buffer.append(map.charAt(s.charAt(i) & 31));
//                str= str + (char)map.charAt(s.charAt(i)&31);
        }

        return buffer.toString();

    }

    // Remove common characters and concatenate
    public static String concatenatedString(String s1, String s2) {
        boolean[] charFreq = new boolean[27];
        boolean[] cf2 = new boolean[27];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s2.length(); i++) {
            charFreq[s2.charAt(i) & 31] = true;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (!charFreq[s1.charAt(i) & 31]) buffer.append(s1.charAt(i));
            cf2[s1.charAt(i) & 31] = true;

        }

        for (int i = 0; i < s2.length(); i++) {
            if (!cf2[s2.charAt(i) & 31]) buffer.append(s2.charAt(i));

        }

        return buffer.length() > 0 ? buffer.toString() : "-1";

    }

    // reverse word without reversing string
    public static void reverseWords(String s) {
        Stack<String> stringStack = new Stack<>();
        StringBuffer word = new StringBuffer();
        StringBuffer result = new StringBuffer(s.length());

        // extracting the word out
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                if (word.length() > 0) stringStack.push(word.toString());
                word.delete(0, word.length());
            } else {
                word.append(s.charAt(i));
            }
        }
        if (word.length() > 0) stringStack.push(word.toString());
        word.delete(0, word.length());


        while (stringStack.size() > 1) {
            result.append(stringStack.pop());
            result.append(".");
        }
        if (stringStack.size() > 0) result.append(stringStack.pop());

        System.out.println(result.toString());


    }

    // sum of numbers in string
    public static long findSum(String str) {
        long temp = 0;
        long result = 0;
        int i = 0;
        while (i < str.length()) {

            if (!isDigit(str.charAt(i))) {
                result += temp;
                temp = 0;
                i++;
            }
            while (i < str.length() && isDigit(str.charAt(i))) {
                temp = temp * 10 + (str.charAt(i) - 48);
                i++;
            }

        }

        if (i == str.length()) {
            if (temp > 0) result += temp;
            temp = 0;
        }

        return result;

    }

    private static boolean isDigit(char c) {

        if (c > 46 && c < 58) return true;

        return false;

    }

    // min index of char commom in both
    public static int minIndexChar(String str, String patt) {
        int isThere = 0;
        for (int i = 0; i < patt.length(); i++) {
            isThere = isThere | (1 << (patt.charAt(i) & 31));
        }

        for (int i = 0; i < str.length(); i++) {
            if ((isThere & (1 << (str.charAt(i) & 31))) > 0) return i;
        }


        return -1;
    }

    // !smallest window of string containing all the character of another string
    // * there is interesting concept creating window which can grow and shring in O(n) time and compare it's inner element
    // s= "timetopractice"  , p=:P = "toc" , output : "toprac"
    public static String smallestWindow(String s, String p) {
        int[] arr = new int[128];
        // counting the frequency
        for (char c : p.toCharArray()) {
            arr[c]++;
        }
        int start = 0, end = 0, minStart = 0;
        int minLen = Integer.MAX_VALUE, counter = p.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (arr[c1] > 0) {
                counter--;
            }
            arr[c1]--;
            end++;

            // this is interesting  part to resizing the window
            while (counter == 0) {
                // caching start of window and window start index
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }


                char c2 = s.charAt(start);
                arr[c2]++;
                if (arr[c2] > 0) {
                    counter++;
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "-1" : s.substring(minStart, minStart + minLen);
    }

    public static String mySmallestWindowSolution(String s, String p) {
        int[] feq = new int[27];

        // count the frequency of character in pattern
        for (int i = 0; i < p.length(); i++) {
            feq[p.charAt(i) & 31]++;
        }

        // initializing variables to keep track of the window start , end and length
        int start = 0, minStart = 0;
        int minLen = Integer.MAX_VALUE, end = 0;
        int charCount = p.length();
        while (end < s.length()) {
            char c = s.charAt(end);
            // this is present in the pattern
            if (feq[c & 31] > 0) charCount--;

            feq[c & 31]--;
            end++;
            // means we have found a window in which all character from pattern exist
            while (charCount == 0) {
                // this is for storing the min length of the window found and it's start.
                // also updating it as start moves forward
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                char atStart = s.charAt(start);

                // this is very tricky you need copy and pen to know why this works
                feq[atStart & 31]++;
                if (feq[atStart & 31] > 0) charCount++;

                start++;

            }



        }

        return minLen == Integer.MAX_VALUE ? "-1" : s.substring(minStart, minStart + minLen);


    }

    // ?  _____________________END OF FANTASTIC SOLUTION__________________________________________
    //Function to find nth number made of only prime digits.
    public static int primeDigits(int n) {

        int counter = 2;
        while (n > 0) {
            if (isAllDigitPrime(counter)) n--;

            counter++;

        }
        return --counter;

    }

    private static boolean isAllDigitPrime(int number) {
        boolean[] primeOnes = {false, false, true, true, false, true, false, true, false, false};
        while (number > 0) {
            if (!primeOnes[number % 10]) return false;
            number /= 10;
        }
        return true;

    }


    //Function to find minimum number of characters which Ishaan must insert
    //such that string doesn't have three consecutive same characters.
    public static long modified(String a) {
        if (a.length() < 3) return 0;
        int[] freq = new int[27];
        for (int i = 0; i < 3; i++) freq[a.charAt(i) & 31]++;

        long count = 0;
        int i = 0;
        while (i < a.length() - 2) {

            int c = a.charAt(i) & 31;
            if (freq[c] == 3) {
                count++;
                freq[c]--;
                freq[a.charAt(i + 1) & 31]--;

                i += 2;
                if (i < a.length() - 2) {

                    freq[a.charAt(i + 1) & 31]++;
                    freq[a.charAt(i + 2) & 31]++;
                }
                continue;

            }
            if (i < a.length() - 3) {

                freq[c]--;
                freq[a.charAt(i + 3) & 31]++;
            }
            i++;


        }


        return count;

    }

    // case specific shorting
    public static String caseSort(String str) {
        int[] small = new int[27];
        int[] bigger = new int[27];
        // sorting freq and gettting position.
        for (int i = 0; i < str.length(); i++) {
            // true for bigger case and flase for smaller one.
            if (str.charAt(i) < 96) {
                bigger[str.charAt(i) & 31]++;
            } else {
                small[str.charAt(i) & 31]++;
            }
        }

        // now working on finding sorted string
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (isSmall(str.charAt(i))) {
                result.append(giveMeCharInSortedSmall(small));
            } else {
                result.append(giveMeCharInSortedBigger(bigger));
            }
        }
        return result.toString();


    }

    private static boolean isSmall(char c) {
        if (c > 96) return true;

        return false;
    }

    private static char giveMeCharInSortedSmall(int[] freq) {
        for (int i = 1; i < freq.length; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                return (char) (i | 96);
            }
        }

        // dummy return not going to reach there
        return ' ';
    }

    private static char giveMeCharInSortedBigger(int[] freq) {
        for (int i = 1; i < freq.length; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                return (char) (i | 64);
            }
        }

        // dummy return not going to reach there
        return ' ';
    }

    // * Lexicographic Rank Of A String
    public static int findRank(String s) {
        if (isRepeated(s)) return 0;
        final int mod = 1000000007;
        int[] letterFreq = new int[27];
        // calculating n!
        ArrayList<Long> list  = new ArrayList<>();
        fillFact(list,s.length());

        letterFreq[s.charAt(0) & 31]++;
        for (int i = 1; i < s.length(); i++) {
//            fact= ((fact%mod)*i)%mod;
            letterFreq[s.charAt(i) & 31]++;
        }
//        fact= (fact*s.length())%mod;
        // count cumulative frequency
        for (int i = 1; i < letterFreq.length; i++) {
            letterFreq[i] += letterFreq[i - 1];
        }
        // finding rank
        long rank = 0;
        for (int i = 0; i < s.length(); i++) {
            int count = letterFreq[s.charAt(i) & 31] - 1;
            rank = (rank + (count * list.get(s.length()-i-1))%mod ) % mod;
            decrementByOneFurther(letterFreq, s.charAt(i) & 31);
        }

        return (int)rank + 1;
    }

    static void fillFact(ArrayList<Long> list, int n){

        long fact =1;
        int q = (int)Math.pow(10,9)+7;
        list.add(fact);
        for(int i=1;i<n;i++){
            fact = (fact*i)%q;
            list.add(fact);
        }
    }


    private static boolean isRepeated(String s) {
        int charBool = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((charBool & (1 << (s.charAt(i) & 31))) > 0) return true;
            else {
                charBool = charBool | (1 << (s.charAt(i) & 31));
            }
        }
        return false;

    }

    private static void decrementByOneFurther(int[] arr, int from) {
        for (int i = from; i < arr.length; i++) {
            arr[i]--;
        }
    }

    // Rabhin karb
    static int d = 256;

    //Function to check if the pattern is present in string or not.
    public static boolean search(String pat, String txt, int q) {
        int dm1 = 1;
        for (int i = 1; i < pat.length(); i++) {
            dm1 = (dm1 * d) % q;
        }

        // calculating t0
        int txtHash = 0, patHash = 0;
        for (int i = 0; i < pat.length(); i++) {
            patHash = ((patHash * d) % q + pat.charAt(i)) % q;
            txtHash = ((txtHash * d) % q + txt.charAt(i)) % q;
        }

        for (int i = 0; i <= (txt.length() - pat.length()); i++) {

            if (txtHash == patHash) {
                // check for spurious hit
                boolean flag = true;
                for (int j = 0; j < pat.length(); j++) {
                    if (pat.charAt(j) != txt.charAt(i + j)) flag = false;
                }

                if (flag) return true;
            }

            // recalculate hash for next interation
            if (i < (txt.length() - pat.length())) {
                txtHash = (d * (txtHash - (txt.charAt(i) * dm1) % q) % q + txt.charAt(i + pat.length())) % q;
                if (txtHash < 0) txtHash = txtHash + q;

            }

        }
        return false;

    }

    // * [pattern search KMP]
    //Function to fill lps[] for given patttern pat[0..M-1].
    public static void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;
        for (int i = 1; i < pat.length(); i++) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i] = ++len;
            } else {
                if (pat.charAt(0) != pat.charAt(i)) {
                    len = 0;
                    lps[i] = 0;
                } else {
                    lps[i] = 1;
                    len = 1;
                }
            }
        }


        // Your code here

    }

    //Function to check if the pattern exists in the string or not.
    public static boolean KMPSearch(String pat, String txt) {
        if (pat.length() > txt.length()) return false;
        int[] lpsArray = new int[pat.length()];
        computeLPSArray(pat, pat.length(), lpsArray);
        int i = 0, j = 0;

        while (i < txt.length()) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            } else {
                if (j == 0) {
                    i++;
                    continue;
                }
                j = lpsArray[j - 1];
            }

            if (j == pat.length()) return true;


        }

        return false;
    }

    //Function to check if a string is subsequence of other string.
    public static boolean isSubSequence(String a, String b) {
        int n = b.length();
        int m = a.length();
        if (m > n) return false;

        for (int i = n - 1; i >= 0; i--) {
            if (b.charAt(i) == a.charAt(m - 1)) m--;
            if (m == 0) return true;
        }

        return false;


    }

    // longest common prefix
    public static String longestCommonPrefix(String arr[], int n) {

        int sIndex = 0;// store the index of smallest string
        for (int i = 0; i < n; i++) {
            if (arr[sIndex].length() > arr[i].length()) sIndex = i;
        }

        StringBuffer result = new StringBuffer();
        String s = arr[sIndex];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j != sIndex) {
                    if (arr[j].charAt(i) != s.charAt(i)) {
                        return result.length() > 0 ? result.toString() : "-1";
                    }
                }
            }
            result.append(s.charAt(i));
        }

        return result.length() > 0 ? result.toString() : "-1";


    }


}
