package Recursion;

import java.util.ArrayList;
import java.util.Set;

public class Problems {

    // power set of the string
   public static ArrayList<String> powerSet(String s)
    {
        ArrayList<String>  results = new ArrayList<>();
        allPossibleSubset(results,s,"",0);
        return  results;

    }

    private static void allPossibleSubset(ArrayList<String> results,String s, String result,int count){
       if(count == s.length() ) {
           results.add(result);
           return;
       }
       allPossibleSubset(results,s,result,count+1);
       allPossibleSubset(results,s,result+s.charAt(count),count+1);

    }

    //power of numbers

   public static long power(int N,int R){
        long result = powerHelper(N,R);
        return result %((long)Math.pow(10,9) +7);
    }

    public static long powerHelper(int N,int R)
    {
        if(R==0) return 1;
        long y;
        if(R%2==0) {
            y = powerHelper(N, R/2);
            y = (y * y) % 1000000007;
            return  y;
        }
        else{
            y = N % 1000000007;
            // reduce the power by 1 to make it even. The reducing power by one can be done if we take one n out. Like 2^3 can be written as 2*(2^2)
            y = (y * powerHelper(N, R - 1) % 1000000007) % 1000000007;
            return ((y + 1000000007) % 1000000007);
        }



    }
    //possible words from phone digit
    public static void possibleWords(int[] a,int N)
    {
        ArrayList<String> words = new ArrayList<>();
        String[] arr = construct(a);
        words(words,arr,"",0);
        words.forEach(System.out::println);
        System.out.println("Total Words : " + words.size());




    }

    private static String[] construct(int[] arr){
       String[] str = new String[arr.length];
       for(int i=0;i<arr.length;i++){
           str[i] = number2Word(arr[i]);
       }
    return str;

    }

    private static String number2Word(int num){
       switch (num){
           case 2:
               return "abc";
           case 3:
               return "def";
           case 4:
               return "ghi";
           case 5:
               return "jkl";
           case 6:
               return "mno";
           case 7:
               return "pqrs";
           case 8:
               return "tuv";
           case 9:
               return "wxyz";


       }
       return "";
    }



    public static void words(ArrayList<String> words,String[] str , String result , int count){
       if(count==str.length) {
           words.add(result);
           return;
       }
       for(int i=0;i<str[count].length();i++){
            words(words,str,result+str[count].charAt(i),count+1);
       }

    }

    // [Josephus problem]
    public static int josephus(int n, int k)
    {

        int res =0;
        if(n==1) return 0;
        res= (josephus(n-1,k) +k)%n;
        return  res+1;
    }

    // stair problem one step or two
    public static int reachUp(int n,int curr){
       if(curr== n) return 1;
       if(curr>n) return 0;
       int totalPath = reachUp(n,curr+1) + reachUp(n,curr+2);

       return totalPath;
    }


}
