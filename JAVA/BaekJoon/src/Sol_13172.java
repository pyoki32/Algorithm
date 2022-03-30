import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_13172 {
    static int X = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        Long answer = 0L;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            long res = 0L;
            int gcd = calGCD(N, S);
            N /= gcd;
            S /= gcd;
            //b^X - 2 ≡ b^-1 (mod X) , a × b^-1 mod X
            res = S * pow(N, X - 2) % X;
            answer += res;
        }
        System.out.println(answer % X);
    }

    static long pow(int b, int exp) {

        if (exp == 1) return b;
        long res = pow(b, exp / 2);
        if (exp % 2 != 0) res = ((res * res) % X) * b % X;
        else res = res * res % X;
        return res;
    }

    public static int calGCD(int A, int B) {
        if (B == 0) return A;
        return calGCD(B, A % B);
    }
}
