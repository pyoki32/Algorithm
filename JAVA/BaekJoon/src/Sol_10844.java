import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [][] dp = new int[N+1][10];
        int MOD = 1000000000;
        for(int i=1;i<=9;i++){
            dp[1][i]=1;
        }

        for(int i=2;i<=N;i++){
                dp[i][0] += dp[i-1][1];
                dp[i][9] += dp[i-1][8];
                dp[i][0] %=MOD;
                dp[i][1] %=MOD;
                for(int j=1;j<9;j++){
                dp[i][j] += dp[i-1][j-1]+dp[i-1][j+1];
                dp[i][j] %=MOD;
            }
        }
        int answer = 0;
            for(int j= 0; j<10;j++){
               answer += dp[N][j];
               answer %= MOD;
        }
            System.out.println(answer);
    }
}
