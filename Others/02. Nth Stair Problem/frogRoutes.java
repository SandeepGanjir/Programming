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
    private long[] baseCase = null;
    private long[][] Matrix = null;

    /**
     * Computes T(n) with log(n) time complexity.
     * @param baseCase         := value of T(i) for n = 0 case
     * @param nthElementVector := value of T(n) in terms of T(n-i)s. So for
     *                          T(n) = T(n-2) + T(n-3), it would be { 0, 1, 1 }
     * @param n                := Value of n
     * @return long value of T(n)
     */
    public static long compute(int[] baseCase, int[] nthElementVector, int n) {
        if (baseCase.length > nthElementVector.length)
            return 0;

        xtremeOptimisation Ins = new xtremeOptimisation(baseCase, nthElementVector);
        long[][] resultMatrix = Ins.computeNthPowerMatrix(Ins.Matrix, n);
        long[] resultVector = Ins.multiplyVectorWithMatrix(Ins.baseCase, resultMatrix);

        if (resultVector == null || resultVector.length < 1)
            return 0;
        return resultVector[0];
    }

    private xtremeOptimisation(int[] baseCase, int[] nthElementVector) {
        this.baseCase = new long[nthElementVector.length];
        for (int i = 0; i < baseCase.length; i++)
            this.baseCase[i] = baseCase[i];

        this.Matrix = new long[nthElementVector.length][nthElementVector.length];
        for (int i = 0; i < Matrix.length; i++) {
            Matrix[i][0] = nthElementVector[i];
            if (i < Matrix.length - 1)
                Matrix[i][i + 1] = 1;
        }
    }

    private long[][] computeNthPowerMatrix(long[][] M, int n) {
        if (n < 0 || M==null || M.length == 0 || M.length != M[0].length)
            return null;

        long[][] resultMatrix = new long[M.length][M.length];
        for (int i = 0; i < resultMatrix.length; i++)
            resultMatrix[i][i] = 1;

        long[][] Mx = M.clone();
        for (int i = n; i > 0; i /= 2) {
            if (i % 2 == 1)
                resultMatrix = multiply2Matrices(resultMatrix, Mx);
            Mx = multiply2Matrices(Mx, Mx);
        }
        return resultMatrix;
    }

    private long[][] multiply2Matrices(long[][] A, long[][] B) {
        long[][] M = new long[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            M[i] = multiplyVectorWithMatrix(A[i], B);
        }
        return M;
    }

    private long[] multiplyVectorWithMatrix(long[] V, long[][] M) {
        long[] resVector = null;
        if (M == null || V.length != M.length)
            return resVector;
        else
            resVector = new long[M[0].length];

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
        long result = xtremeOptimisation.compute(baseCase, vec, 100);

        System.out.println(result);
    }
}