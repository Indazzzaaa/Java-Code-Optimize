package Mathssss;

import java.util.ArrayList;

public class MathProblems {

    // roots of quadratic equation
    public ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        // code here
        ArrayList<Integer> x= new ArrayList<>();
        double det=(b*b-4*a*c);
        if(det<0)
        {
            x.add(-1);//pranav's
        }
        else{
            det=Math.sqrt(det);
            double n=(-b+det)/(2*a);
            double m=(-b-det)/(2*a);
            x.add((int)Math.floor(Math.max(n,m)));
            x.add((int)Math.floor(Math.min(n,m)));}
        return x;
    }

    // Exactly 3 divisors
    public int exactly3Divisors(int N)
    {
        int count = 0;
        for(int i = 2; i*i <= N; i++ ){

            if(isPrime(i))
            {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int n){

        if(n==1) return false;
        if(n==2 || n==3 ) return true;
        if(n%2==0 || n%3 ==0) return false;

        for(int i=5;i*i<=n;i+=6){
            if(n%i==0) return false;
            if(n%(i+2)==0) return false;
        }

        return true;


    }
}
