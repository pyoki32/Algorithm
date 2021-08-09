import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Sol_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[] primeNumberCheck = new boolean[1000001];

        for (int i = 2; i <= N; i++) {
            for (int j = i + i; j <= N; j += i) {
                primeNumberCheck[j] = true;
            }
        }
        for (int i = M; i <= N; i++) {
            if (primeNumberCheck[i] == false && i != 1) {
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);
    }
}
