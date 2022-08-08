package General;

import java.util.Arrays;
import java.util.Stack;

public class OnStrings {
    // paranthesis matching
    //Function to check if brackets are balanced or not.
   public static boolean ispar(String x)
    {
        Stack<Character> prnthsis = new Stack<>();
        for(int i=0;i<x.length();i++){
            char c = x.charAt(i);
            if( c  =='{' || c == '('  || c == '['){
                prnthsis.push(c);
            }
            else {
                char opp = giveCorrspondingPrnthsis(c);
                if(opp =='[' || opp == '{' || opp=='(' ){

                    if(prnthsis.isEmpty()) return  false;

                     if(opp != prnthsis.pop()) return false;
                }
            }
        }


        return  prnthsis.empty();


    }

    private static char giveCorrspondingPrnthsis(char c){
       if(c=='}') return '{';
       if(c==']') return '[';
       if(c==')') return '(';
       else{

           return c;
       }


    }

    // checking for valid ip address
    public static boolean isValid(String s) {

       int start =0;
       int dotCount =0, lastDot=-1;
       for(int i=0;i<s.length();i++){
           if(s.charAt(i)=='.'){
                if((i-1)==lastDot) return false;
                else{
                    lastDot = i;
                }
               dotCount++;
               if(!is_in_IP_range(s,start,i-1)) return false;
               // checking is size is greater then 1 and is first letter is 0;
               if(((i-1 -start)>0) &&  (s.charAt(start)&15)==0)   return false;
               else{
                   start = i+1;
               }
           }
       }
       if(dotCount==3 && is_in_IP_range(s, start, s.length() - 1)){
           if(((s.length()-1 -start)>0) &&  (s.charAt(start)&15)==0)   return false;
            else{
               return true;
           }

       }
        return false ;
    }

    private static boolean is_in_IP_range(String s,int start ,int end){
       int value=0;
       for(int i = start;i<=end;i++){
           char c = s.charAt(i);
           if(c<48 || c> 57) return false;
           else{
               value = value*10 + (c&15);
           }
       }


       return   value<256;


    }
      //  only call when size is atleast two
    private static boolean is_zerosInStart(String s, int start ,int end){
       // if length is 1 and that is zero then it's ok
       return ((s.charAt(start)&15)!=0);



    }

    // String multiplication
    public static String multiplyStrings(String s1,String s2)
    {
        int sign = s1.charAt(0)=='-' ? (s2.charAt(0)=='-' ? 1 : -1) : (s2.charAt(0)=='-' ? -1:1);

        int i1 = s1.length() + s2.length() + 1;
        char[] result = new char[i1];
        Arrays.fill(result,'0');
        for(int i=s1.length()-1;i>=0;i--){
            if(s1.charAt(i)<48 || s1.charAt(i)>57) continue;
            for(int j= s2.length()-1;j>=0;j--){
                if(s2.charAt(j)<48 || s2.charAt(j)>57) continue;
                int p = (s1.charAt(i)-'0')*(s2.charAt(j)-'0') + (result[i+j+2]-'0');
                result[i+j+2] = (char)(p%10 + '0');
                result[i+j+1] += (char)(p/10);
            }
        }

        // answers should not prefixed zeros
        for(int i = 0; i< i1; i++){
            if(result[i]!='0') {
                if(sign==-1){
                    result[i-1]= '-';
                    return new String(result).substring(i-1);
                }
                return new String(result).substring(i);

            }
        }

        return "0";

    }





}
