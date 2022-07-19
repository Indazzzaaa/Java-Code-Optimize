package Matrix;

public class Matrix {
    enum MIRROR {
        LEFT, RIGHT, TOP, BOTTOM
    }

    //---------------------------------------------------------------------------------
    public static void printSnakePattern(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if ((i & 1) == 1) {
                for (int j = arr[i].length - 1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
            } else {
                for (int value : arr[i]) System.out.print(value + " ");
            }
            System.out.println();

        }


    }


    public static void printMatrixBoundary(int[][] arr) {
        int rows = arr.length, cols = arr[0].length;
        // working with corner cases row =1,col=1
        if (rows == 1) {
            for (var value : arr[0]) System.out.print(value + "\t");
            return;
        }
        if (cols == 1) {
            for (int[] ints : arr) {
                System.out.print(ints[0] + "\t");
            }
            return;
        }
        // print first row
        for (var val : arr[0]) System.out.print(val + "\t");
        // print second row to n-1 row
        for (int i = 1; i < rows - 1; i++) System.out.print(arr[i][cols - 1] + "\t");
        // print last row  in reverse
        for (int i = cols - 1; i >= 0; i--) System.out.print(arr[rows - 1][i] + "\t");
        // print col : 1 , from second last row to second row
        for (int i = rows - 2; i > 0; i--) System.out.print(arr[i][0] + "\t");
    }


    // * [Printing Spiral]

    public static void spiralPrinting(int[][] arr) {
        int top = 0, bottom = arr.length - 1, left = 0, right = arr[0].length - 1;
        while (top <= bottom && left <= right) {
            printMatrixBoundaryCustomized(arr, top, bottom, left, right);
            top++;
            bottom--;
            left++;
            right--;
        }

    }

    public static void printMatrixBoundaryCustomized(int[][] arr, int top, int bottom, int left, int right) {
        int rows = bottom - top + 1, cols = right - left + 1;
        // working with corner cases row =1,col=1
        if (rows == 1) {
            for (int i = left; i <= right; i++) System.out.print(arr[top][i] + "\t");
            return;
        }
        if (cols == 1) {
            for (int j = top; j <= bottom; j++) {
                System.out.print(arr[j][left] + "\t");
            }
            return;
        }
        // print first row
        for (int i = left; i <= right; i++) {
            System.out.print(arr[top][i] + "\t");
        }
        // print second row to n-1 row only last column
        for (int i = top + 1; i < bottom; i++) System.out.print(arr[i][right] + "\t");
        // print last row  in reverse
        for (int i = right; i >= left; i--) System.out.print(arr[bottom][i] + "\t");
        // print col : 1 , from second last row to second row
        for (int i = bottom - 1; i > top; i--) System.out.print(arr[i][left] + "\t");
    }

    // * [Transpose of matrix]
    public static void transpose(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                arr[i][j] = arr[i][j] ^ arr[j][i] ^ (arr[j][i] = arr[i][j]);
            }
        }
    }

    // * [Rotating array]

    public static void rotate90anticlockwise(int[][] arr) {
        transpose(arr);
        mirrorArray(arr, MIRROR.TOP);
    }

    public static void rotate90clockwise(int[][] arr) {
        transpose(arr);
        mirrorArray(arr, MIRROR.LEFT);
    }

    private static void mirrorArray(int[][] arr, MIRROR side) {

        switch (side) {
            case LEFT -> {
                mirrorLeft(arr);
            }
            case TOP -> {
                mirrorTop(arr);
            }

        }


    }

    // no use of bottom mirror and left mirror does not changes array so no need
    private static void mirrorTop(int[][] arr) {
        int top = 0;
        int bottom = arr.length - 1;
        while (bottom > top) {
            for (int i = 0; i < arr[0].length; i++) {
                arr[top][i] = arr[top][i] ^ arr[bottom][i] ^ (arr[bottom][i] = arr[top][i]);
            }
            top++;
            bottom--;
        }
    }

    private static void mirrorLeft(int[][] arr) {
        int left = 0, right = arr[0].length - 1;
        while (right > left) {
            for (int i = 0; i < arr.length; i++) {
                arr[i][right] = arr[i][right] ^ arr[i][left] ^ (arr[i][left] = arr[i][right]);
            }
            left++;
            right--;
        }
    }


}


