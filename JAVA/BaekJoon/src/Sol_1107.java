import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1107 {
    static int N, M;
    static boolean[] brokenBtn;
    static int minCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        brokenBtn = new boolean[10];
        minCnt = Math.abs(N - 100);
        String nStr = String.valueOf(N);

        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                brokenBtn[num] = true;
            }
        }
        for (int i = 0; i <= 999999; i++) {
            String channel = String.valueOf(i);
            boolean moveCheck = true;
            for (int j = 0; j < channel.length(); j++) {
                if (brokenBtn[channel.charAt(j)-'0']) {
                    moveCheck = false;
                    break;
                }
            }
            if (moveCheck) {
                int pressDownCnt = Math.abs(i - N) + channel.length();
                minCnt = Math.min(minCnt, pressDownCnt);
            }
        }
        System.out.println(minCnt);
    }
}
