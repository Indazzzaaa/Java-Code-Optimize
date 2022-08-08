package Strings;

import java.util.Arrays;

// ! must see the solution in the section of problem marked red.

public class WorkOnStrings {
    // * few string operations
    public static void stringOperations(){
        String str = "abcdefdef";
        System.out.println(str.compareTo("ghi"));
        System.out.println("First occurance :" + str.indexOf("def"));
        System.out.println( "Last occurance" + str.lastIndexOf("def"));
        System.out.println("Concate : " + str.concat(" you are awesome"));
    }

    public static String convertUpper(String str) {
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = (char) (str.charAt(i) & '_');
        }
        return Arrays.toString(arr);
    }

    public static String convertLower(String str) {
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = (char) (str.charAt(i) | ' ');
        }
        return Arrays.toString(arr);
    }

    //* counting frequency of lowercase letter very optimized
    public static void lowerCaseFreqCount(String str) {
        int[] freq = new int[27]; // i=0 , will not use just for guest.
        // inserting in frequency table;
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) & 31]++;
        }

        for (int i = 1; i < freq.length; i++) {
            if (freq[i] > 0) {
                // i|96 is to get the character
                System.out.printf("Char : %c , Freq : %d\n", i | 96, freq[i]);
            }
        }


    }

    // letter present in string , only works for one case at a time
    public static void letterPresent(String str) {
        int letterObserver = 0; // 0 th bit is of no use for us
        for (int i = 0; i < str.length(); i++) {
            letterObserver |= (1 << (str.charAt(i) & 31));
        }

        for (int i = 1; i < 27; i++) {
            System.out.printf("Char : %c , isThere : %b\n", i | 96, (letterObserver & (1 << i)) > 0);
        }


    }

    // *[Anagram] keep in mind we have only made this for 26 alphabet character , which is all small alphabets
    // but our test might contain special characters so use 256
    public static boolean isAnagram(String str1, String str2, boolean isDistinct){

        if(isDistinct) return anagram_for_distinct(str1,str2);
        else return anagram_for_duplicates(str1,str2);


    }

    // TC : n+27,Aux = 27
    private static boolean anagram_for_duplicates(String str1, String str2){
        if(str2.length()!= str1.length()) return false;
        int[] freq = new int[27]; // first index of no use for us
        for(int i=0;i<str1.length();i++){
            freq[str1.charAt(i)&31]++;
            freq[str2.charAt(i)&31]--;
        }
        for(int val : freq){
            if(val != 0) return false;
        }
        return true;
    }

    // TC : N and aux : 1
    private static boolean anagram_for_distinct(String str1, String str2){
        if(str1.length()!=str2.length()) return false;
        int str1elements=0;
        int str2elements=0;
        for(int i=0;i<str1.length();i++){
            // for str1
            // first check is str2 bit is on
            if( (str2elements & (1<< (str1.charAt(i)&31)))>0){
                // switch off that bit
                str2elements ^=(1<<(str1.charAt(i)&31));
            }else{
                // activate the ith bit of str1Elements
                str1elements|=(1<<(str1.charAt(i)&31));
            }

            // for str 2
            // first check the str1 bit is on
            if( (str1elements & (1<< (str2.charAt(i)&31)))>0){
                // switch off that bit
                str1elements ^=(1<<(str2.charAt(i)&31));
            }else{
                // active the ith bit of str2Elements
                str2elements|=(1<<(str2.charAt(i)&31));
            }

        }

        return  str1elements==0 && str2elements==0;

    }

    // * longest distinct substring [n,1]
    public static int longest_distinct_substring(String str){
        int[] prev = new int[256];
        Arrays.fill(prev,-1);
        int res=0;int i=0;
        for(int j=0;j<str.length();j++){
            i = Math.max(i,prev[str.charAt(j)]+1);
            res= Math.max(res,j-i+1);
            prev[str.charAt(i)]=j;
        }
        return res;
    }
}
