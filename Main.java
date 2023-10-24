

public class Main {

    public static void main(String[] args){

    double [][] matrixBeta;
    double [][] matrixA;
    double [][] matrixB;
    double [][] inverseMatrixA;


    Minverse inverseMatrix = new Minverse();
    Multiply_Matrix multMatrix = new Multiply_Matrix();
    Matrix arrays = new Matrix();

    matrixA = arrays.matrixA;
    inverseMatrixA = inverseMatrix.crame(matrixA,3,3);

    matrixB = arrays.matrixB;
    matrixBeta = multMatrix.multiY(matrixB,inverseMatrixA,3,3,3);


    }
}
