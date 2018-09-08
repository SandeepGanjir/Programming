/* Special class of Program with O(exp(n)) naive solution but has O(n) soultion using 
    dynamic programming and O(log(n)) optimized solution using matrix multiplication.
   Required Property : T(n) = Σ±Ci*T(n-i), where Ci could be 0 and Range of i << n.
   Example : A frog can leap 2,3 or 5 step in one jump. Given a stairs of n steps, 
    determine the number of different possible routes the frog can take.

   Use as below:
    G:\Temp\Programming> javac frogRoutes.java
    G:\Temp\Programming> java frogRoutes
*/

class xtremeOptimisation {
    private int[] baseCase = null;
    private int[][] Matrix = null;

    xtremeOptimisation(int[] baseCase, int[] nthElementVector) {
        this.baseCase = baseCase.clone();
        this.Matrix = new int[nthElementVector.length][nthElementVector.length];
        for (int i = 0; i < Matrix.length; i++) {
            Matrix[i][0] = nthElementVector[i];
            if (i < Matrix.length - 1)
                Matrix[i][i + 1] = 1;
        }
    }

    public static int compute(int[] baseCase, int[] nthElementVector, int n) {
        xtremeOptimisation Ins = new xtremeOptimisation(baseCase, nthElementVector);
        int[][] resultMatrix = Ins.computeNthPowerMatrix(Ins.Matrix, n);
        int[] resultVector = Ins.multiplyVectorWithMatrix(Ins.baseCase, resultMatrix);
        return resultVector[0];
    }

    private int[][] computeNthPowerMatrix(int[][] M, int n) {
        if (n < 0 || M.length == 0 || M.length != M[0].length)
            return null;

        int[][] resultMatrix = new int[M.length][M.length];
        for (int i = 0; i < resultMatrix.length; i++)
            resultMatrix[i][i] = 1;

        int[][] Mx = M.clone();
        for (int i = n; i > 0; i /= 2) {
            if (i % 2 == 1)
                resultMatrix = multiply2Matrices(resultMatrix, Mx);
            Mx = multiply2Matrices(Mx, Mx);
        }
        return resultMatrix;
    }

    private int[][] multiply2Matrices(int[][] A, int[][] B) {
        int[][] M = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            M[i] = multiplyVectorWithMatrix(A[i], B);
        }
        return M;
    }

    private int[] multiplyVectorWithMatrix(int[] V, int[][] M) {
        int[] resVector = null;
        if (V.length != M.length)
            return resVector;
        else
            resVector = new int[M[0].length];

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                resVector[j] += V[i] * M[i][j];
            }
        }
        return resVector;
    }
}

public class frogRoutes {
    public static void main(String[] args) {
        int[] baseCase = { 1, 0, 0, 0, 0 };
        int[] vec = { 0, 1, 1, 0, 1 };
        int result = xtremeOptimisation.compute(baseCase, vec, 50);

        System.out.println(result);
    }
}