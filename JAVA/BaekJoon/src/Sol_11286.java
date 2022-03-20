import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>((x1, x2) -> {
            if (Math.abs(x1) == Math.abs(x2)) return x1 - x2;
            return Math.abs(x1) - Math.abs(x2);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            if (x == 0) {
                if (!pq.isEmpty()) sb.append(pq.poll()).append('\n');
                else sb.append(0).append('\n');
            } else {
                pq.add(x);
            }
        }
        System.out.println(sb);
    }
}
