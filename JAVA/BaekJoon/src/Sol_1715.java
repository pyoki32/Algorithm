import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(Long.parseLong(st.nextToken()));
        }
        long answer = 0;
        while (pq.size() > 1) {
            Long cardBundle1 = pq.poll();
            Long cardBundle2 = pq.poll();
            answer += cardBundle1 + cardBundle2;
            pq.add(cardBundle1 + cardBundle2);
        }
        System.out.println(answer);
    }
}
