package Matrix;

import java.util.ArrayList;

public class Problems {

   public static ArrayList<Integer> sumTriangles(int[][] matrix, int n)
    {
        ArrayList<Integer> results = new ArrayList<Integer>(2);
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if(col>row){
                    int total = results.get(0)+matrix[row][col];
                    results.add(0,total);
                }
                else if(col==row) {
                    int total = results.get(0)+matrix[row][col];
                    results.add(0,total);
                    results.add(1,total);


                }
                else {
                    int total = results.get(1)+matrix[row][col];
                    results.add(1,total);

                }
            }
        }


        return  results;
    }

    // Boolean matrix encounter 1 convert row and col to 1
   public static void booleanMatrix(int[][] matrix)
    {
        for(int row =0;row<matrix.length;row++){
            for(int col=0;col<matrix[0].length;col++){
                if(matrix[row][col]==1){
                     convert(matrix,row,col);
                }
            }
        }

         // fill the array with 1 again
        for(int[] arr : matrix){
            for(int i=0;i<arr.length;i++){
                if(arr[i] <0) arr[i] = 1;
            }
        }

        // code here
    }

  public static  void convert(int[][] matrix, int row, int col){
       // fill entire row with -1 except one having 1
            for(int i=0;i<matrix[0].length;i++){
                if(matrix[row][i] ==1) continue;
                matrix[row][i] =-1;
            }

            // fill the column
         for(int j = 0;j<matrix.length;j++){
             if(matrix[j][col] ==1) continue;
             matrix[j][col] = -1;
         }
    }
}
