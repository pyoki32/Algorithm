import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2839 {
    static int MaxQuotient5Cnt, MaxQuotient3Cnt;
    static int answerQuotient5Cnt, answerQuotient3Cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        MaxQuotient5Cnt = N / 5;
        MaxQuotient3Cnt = N / 3;
        answerQuotient5Cnt = -1;
        answerQuotient3Cnt = -1;

        if (N % 5 == 0) {
            answerQuotient5Cnt = MaxQuotient5Cnt;
            answerQuotient3Cnt = 0;
        } else {
            calCntPlasticBag(N, MaxQuotient5Cnt);
        }

        if (answerQuotient5Cnt == -1 || answerQuotient3Cnt == -1) {
            System.out.println(-1);
        } else {
            System.out.println(answerQuotient5Cnt + answerQuotient3Cnt);
        }
    }

    static void calCntPlasticBag(int N, int quotient5Cnt) {

        if (quotient5Cnt < 0) return;
        if (quotient5Cnt == 0) {
            if (N % 3 == 0) {
                answerQuotient5Cnt = quotient5Cnt;
                answerQuotient3Cnt = N / 3;
            }
            return;
        }

        int reminder = N - (quotient5Cnt * 5);
        if (reminder % 3 == 0) {
            answerQuotient5Cnt = quotient5Cnt;
            answerQuotient3Cnt = reminder / 3;
            return;
        }
        calCntPlasticBag(N, quotient5Cnt - 1);

    }
}
