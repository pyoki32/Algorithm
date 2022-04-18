import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N + 1][10][1024];
        int MOD = 1000000000;
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (j == 0) {
                        dp[i][j][k | 1 << j] += dp[i - 1][j + 1][k];

                    } else if (j == 9) {
                        dp[i][j][k | 1 << j] += dp[i - 1][j - 1][k];
                    } else {
                        dp[i][j][k | 1 << j] += dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k];
                    }
                    dp[i][j][k | 1 << j] %= MOD;
                }
            }
        }
        int answer = 0;
        for (int j = 0; j < 10; j++) {
            answer += dp[N][j][1023];
            answer %= MOD;
        }
        System.out.println(answer);
    }

}
