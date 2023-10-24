
public class Minverse
{

    public Minverse(){

    }

    // Function to get determinant of matrix
    static double determinantOfMatrix(double mat[][])
    {
        int n = 4, index;
        double num1, num2, det = 1, total = 1; // Initialize result

        // temporary array for storing row
        double[] temp = new double[n + 1];

        // loop for traversing the diagonal elements
        for (int i = 0; i < n; i++)
        {
            index = i; // initialize the index

            // finding the index which has non zero value
            while (index < n && mat[index][i] == 0)
            {
                index++;
            }
            if (index == n) // if there is non zero element
            {
                // the determinant of matrix as zero
                continue;
            }
            if (index != i)
            {
                // loop for swapping the diagonal element row
                // and index row
                for (int j = 0; j < n; j++)
                {
                    swap(mat, index, j, i, j);
                }
                // determinant sign changes when we shift
                // rows go through determinant properties
                det = (det * Math.pow(-1, index - i));
            }

            // storing the values of diagonal row elements
            for (int j = 0; j < n; j++)
            {
                temp[j] = mat[i][j];
            }

            // traversing every row below the diagonal
            // element
            for (int j = i + 1; j < n; j++)
            {
                num1 = temp[i]; // value of diagonal element
                num2 = mat[j]
                        [i]; // value of next row element

                // traversing every column of row
                // and multiplying to every row
                for (int k = 0; k < n; k++)
                {
                    // multiplying to make the diagonal
                    // element and next row element equal
                    mat[j][k] = (num1 * mat[j][k])
                            - (num2 * temp[k]);
                }
                total = total * num1; // Det(kA)=kDet(A);
            }
        }

        // multiplying the diagonal elements to get
        // determinant
        for (int i = 0; i < n; i++)
        {
            det = det * mat[i][i];
        }
        return (det / total); // Det(kA)/k=Det(A);
    }



    static double[][] swap(double[][] arr, int i1, int j1, int i2, int j2)
    {
        double temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
        return arr;
    }

    // Function to get adjoint of M[N][N] in adj[N][N].
    static double[][] adjoint(double[][] M, int N)
    {
        // temp is used to store cofactors of M[][]
        int sign = 1;
        double[][] temp = new double[][]{};
        double[][] adj = new double[N][N];

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                // Get cofactor of A[i][j]
                temp = getCofactor(M, i, j, N);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0)? 1: -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinant(temp, N-1));
            }
        }
        return  adj;
    }


    /* Recursive function for finding determinant of matrix.
    n is current dimension of M[][]. */
    static double determinant(double[][] M, int n)
    {
        double D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (n == 1)
            return M[0][0];

        double [][]temp = new double[][]{}; // To store cofactors

        int sign = 1; // To store sign multiplier

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of A[0][f]
            temp = getCofactor(M, 0, f, n);
            D += sign * M[0][f] * determinant(temp, n - 1);

            // terms are to be added with alternate sign
            sign = -sign;
        }

        return D;
    }

    // Function to get cofactor of A[p][q] in temp[][]. n is current
// dimension of A[][]
    static double[][] getCofactor(double[][] M, int p, int q, int n)
    {
        int i = 0, j = 0;
        double[][] temp = new double[n][n];

        // Looping for each element of the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = M[row][col];

                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return  temp;
    }


    // Function to calculate and store inverse, returns false if
    // matrix is singular
    static double[][] inverse(double[][] m,  double[][] adj,  int N)
    {
        double[][] inverse = new double[N][N];
        double det = determinant(m, N);
        if (det == 0)
        {
            return new double[][]{};
        }

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adj[i][j]/(float)det;

        return inverse;
    }


    public static double[][] crame(double[][] m, int rows, int cols) {
        double[][] adjoint = adjoint(m, rows); // To store adjoint of A[][]
        double[][] inverse = inverse(m, adjoint, rows); // To store inverse of A[][]

        System.out.println("    ");
        for(int row = 0;row < rows;row++){
            for(int col = 0; col < cols;col++) {
                System.out.print(inverse[row][col]);
                System.out.print("    ");
            }
            System.out.println("    ");
        }

        return inverse;
    }


}

