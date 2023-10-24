public class Multiply_Matrix {

    public Multiply_Matrix(){

    }

    static double[][] multiY (double[][] yieldMatrix, double[][] transposed, int fields, int rows, int yieldRows){

        double result[][] = new double[yieldRows][1];
        int countYield = 0;

        for (int rowTransposed = 0; rowTransposed < rows; rowTransposed++) {
            for (int field = 0; field  < fields; field ++) {
                result[countYield][0] += yieldMatrix[field][0] * transposed[rowTransposed][field];
            }
            countYield++;
        }

        System.out.println("    ");
        System.out.println("    ");
        for(int row = 0;row < yieldRows;row++){
            System.out.print(result[row][0]);
            System.out.print("    ");
        }
        return result;
    }
}
