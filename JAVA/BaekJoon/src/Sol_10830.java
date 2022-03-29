import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_10830 {
    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }
        int[][] answer = pow(matrix, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    static int[][] pow(int[][] m, long exponent) {

        if (exponent == 1L) return m;

        int[][] res = pow(m, exponent / 2);
        res = multiplyMatrix(res, res);

        if (exponent % 2 != 0L) res = multiplyMatrix(res, matrix);

        return res;
    }

    static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] resMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    resMatrix[i][j] += m1[i][k] * m2[k][j];
                    resMatrix[i][j] %= 1000;

                }
            }
        }
        return resMatrix;
    }

}
