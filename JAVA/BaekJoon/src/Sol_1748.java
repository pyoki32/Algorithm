import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol_1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int digit = 1;
        while (digit <= N) {
            cnt += N - digit + 1;
            digit *= 10;
        }
        System.out.println(cnt);
    }
}
