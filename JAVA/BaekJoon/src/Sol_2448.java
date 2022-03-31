import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2448 {

    static String[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        map = new String[N + 1];
        map[1] = "  *  ";
        map[2] = " * * ";
        map[3] = "*****";
        StringBuilder strB = new StringBuilder();
        if (N != 3) {
            makeStart(3, 6, N);
        }
        for (int i = 1; i <= N; i++) {
            strB.append(map[i]).append("\n");
        }
        System.out.print(strB);
    }

    static void makeStart(int start, int end, int N) {
        if (end > N) return;
        for (int i = start + 1; i <= end; i++) {
            map[i] = map[i - start] + " " + map[i - start];
        }
        for (int i = 1; i <= start; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < start; j++) {
                sb.append(' ');
            }
            sb.append(map[i]);
            for (int j = 0; j < start; j++) {
                sb.append(' ');
            }
            map[i] = sb.toString();
        }
        makeStart(end, end * 2, N);
    }
}
