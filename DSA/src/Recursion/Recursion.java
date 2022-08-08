package Recursion;

import java.util.Arrays;

public class Recursion {
    public static void toBinary(long n){
        if(n<=0) return ;
        toBinary(n>>1);
        System.out.print(n%2);

    }

    public static void toOctal(long n){
        if(n<=0) return;
        toOctal(n/8);
        System.out.print(n%8);
    }

    public static void toHexal(long n){
        if(n<=0) return;
        toHexal(n/16);
        getHexaCharacter((short) (n%16));


    }

    private  static void getHexaCharacter(short n){
        char c ='a';
         if(n<10) System.out.print(n);
         else {
             c+=(n-10);
             System.out.print(c);
         }
    }

    public static long digitSum(long n){
        if(n<=0) return 0;
        return n%10 + digitSum(n/10);
    }

    // Rope cut problem
    public static int findMaxCut(int n,int a,int b,int c){

        if(n<0) return -1;
        if(n==0) return 0;
       int cuts = maxOf3(findMaxCut(n-a,a,b,c),findMaxCut(n-b,a,b,c),findMaxCut(n-c,a,b,c));
       if(cuts>=0) {

           return  cuts + 1;
       }
       else return -1;


    }

    private static int maxOf3(int n1,int n2 , int n3){
        return  n1>n2 ?  (Math.max(n1, n3)) : (Math.max(n2, n3));
    }
    //printing subsets
    public static void printSubsets(String original,String result,int index){
        if(index==original.length()) {
            System.out.println(result);
            return;
        }

        printSubsets(original,result,index+1);
        printSubsets(original,result+original.charAt(index),index+1);

    }
    // printing permutation of string
    public static void permute(char[] str,int i){

        if(i== str.length-1) {
            toString(str);
            return;
        }


        for(int j=i;j<str.length;j++){
            swap(str,i,j);
            permute(str,i+1);
            swap(str,i,j);

        }
    }

    private  static  void swap(char[] str, int first,int second){
        str[first] = (char) (str[second]^str[first]^(str[second]=str[first]));
    }

    private static void toString(char[] str){
        String result = new String(str);
        System.out.println(result);
    }
}
