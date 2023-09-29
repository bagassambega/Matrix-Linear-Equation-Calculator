package matrix;
import java.util.Scanner;
 
public class Inverse 
{
    static double[][] getCofactor(double A[][], int given_row, int given_column)
    {
        int temp_row = 0, temp_column;
        int n = A.length;

        double [][]temp = new double[n-1][n-1];

        for (int row = 0; row < n; row++)
        {
            if (row != given_row)
            {
                temp_column = 0;
                for (int col = 0; col < n; col++)
                {
                    if (col != given_column)
                    {
                        temp[temp_row][temp_column] = A[row][col];
                        temp_column++;
                    }
                }
                temp_row++;
            }
        }

        return temp;
    }
    
    static double determinant(double A[][])
    {
        int D = 0;
    
        //Basis
        int N = A.length;
        if (N == 1)
            return A[0][0];
    
        int sign = 1;
    
        // Rekursi
        for (int f = 0; f < N; f++)
        {
            D += sign * A[0][f] * determinant(getCofactor(A, 0, f));
            sign = -sign;
        }
    
        return D;
    }
    
    static void adjoint(double A[][], double [][]adj)
    {
        int N = A.length;

        if (N == 1)
        {
            adj[0][0] = 1;
            return;
        }
    
        int sign = 1;
    
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                sign = ((i + j) % 2 == 0)? 1: -1;
                adj[j][i] = (sign)*(determinant(getCofactor(A, i, j)));
            }
        }
    }

    static boolean inverse(double A[][], double [][]inverse)
    {
        int N = A.length;

        double det = determinant(A);
        if (det == 0)
        {
            System.out.print("Matrix tidak memiliki inverse/balikan");
            return false;
        }

        double [][]adj = new double[N][N];
        adjoint(A, adj);
    
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adj[i][j]/(double)det;
    
        return true;
    }
    
    static void display(double A[][])
    {
        int N = A.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.printf("%.6f ",A[i][j]);
            System.out.println();
        }
    }
}