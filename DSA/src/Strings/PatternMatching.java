package Strings;

public class PatternMatching {

    // * [Naive]

    // for duplicates((n-m+1)*m , 1)
    public static void naive_patternMatching_Duplicates(String txt, String pattern) {
        for (int i = 0; i <= txt.length() - pattern.length(); i++) {
            int j = 0;
            for (j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) != txt.charAt(i + j)) break;
            }
            if (j == pattern.length()) System.out.println(i + "\t");
        }
    }

    // for distinct ( n,1)
    public static void naiveDistinct(String txt, String pattern) {
        int i = 0;
        int n = txt.length();
        int m = pattern.length();
        for (i = 0; i <= n - m; ) {
            int j = 0;
            while (j < m && txt.charAt(i + j) == pattern.charAt(j)) j++;
            if (j == m) System.out.println(i + "\t");
            i = j > 0 ? (i + j) : ++i;
        }

    }

    // *[Rabin karb = ((n-m+1)*m,1) ]
    // rolling hash property makes it more efficient than naive method despite same time complexity
    public static void rabinKarb(String txt, String pattern) {
        int n = txt.length();
        int m = pattern.length();
        int h = 1, d = 256, q = 101;

        // generating value d^(n-1)
        {
            int i = 0;
            while (++i < m) h = (h * d) % q;
        }
        // computing t0 and patternHash
        int patternHash = 0, t = 0;
        for (int i = 0; i < m; i++) {
            t = (t * d + txt.charAt(i)) % q;
            patternHash = (patternHash * d + pattern.charAt(i)) % q;
        }

        // checking spurious hits
        for (int i = 0; i <= n - m; i++) {
            // hash matches does pattern also matches
            if (patternHash == t) {
                {
                    int j = 0;
                    while (j < m && pattern.charAt(j) == txt.charAt(i + j)) j++;

                    if (j == m) System.out.println(i + "\t");
                }


            }
            // recalculate hash [hash of last window only can be calculated from second last window]
            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0) t += q; // think why t can be zero
            }
        }


    }

    // * [KPM]
    // naive lps algo [n^2,1]
    public static void naiveLps(String txt, int[] lps) {

        for (int i = 1; i < txt.length(); i++) {
            lps[i] = naiveLpsSolve(txt, i);
        }


    }

    private static int naiveLpsSolve(String txt, int pos) {
        int i = 0, j = 1;
        int res = 0;
        while (j <= pos) {
            if (txt.charAt(i) != txt.charAt(j)) {
                res = 0;
                i = 0;

            } else {
                res++;
                i++;

            }
            j++;
        }
        return res;

    }

    // * [Optimized lps] [N,1]
    public static void optimizedLps(String txt, int[] lps) {
        int len = 0;
        int i = 1;
        while (i < txt.length()) {
            if (txt.charAt(i) == txt.charAt(len)) {
                len++;
                lps[i++] = len;
            } else {
                if (len == 0) {
                    lps[i++] = 0;
                } else {
                    len = lps[len - 1];
                }
            }


        }


    }

    public static void KMP(String txt, String pattern) {
        int n = txt.length(),m=pattern.length();
        int[] lps = new int[m];
        optimizedLps(pattern,lps);
        int i=0,j=0;
        // this is to think why not (i = [0,n-m]) ,
        while(i<n){
            if(pattern.charAt(j)==txt.charAt(i)) {i++;j++;}
            if(j==m){
                System.out.println(i-j+"\t");
                j=lps[j-1];
            }
            else if(i<n && pattern.charAt(j)!=txt.charAt(i)){
                if(j==0) i++;
                else j = lps[j-1];
            }
        }
    }

}
