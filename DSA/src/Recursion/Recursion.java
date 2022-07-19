package Recursion;

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
}
