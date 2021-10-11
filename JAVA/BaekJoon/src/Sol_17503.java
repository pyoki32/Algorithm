import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_17503 {
    static class Beer {
        int v;
        int c;

        public Beer(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    static int N, M, K;
    static int minCLevel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Beer> pqc = new PriorityQueue<>(new Comparator<Beer>() {
            @Override
            public int compare(Beer b1, Beer b2) {
                return b1.c - b2.c;
            }
        });
        PriorityQueue<Beer> pqv = new PriorityQueue<>(new Comparator<Beer>() {
            @Override
            public int compare(Beer b1, Beer b2) {
                return b1.v - b2.v;
            }
        });
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pqc.add(new Beer(v, c));
        }
        int sumM = 0;
        int cLevel = 0;
        minCLevel = -1;
        while (!pqc.isEmpty()) {
            Beer inBeer = pqc.poll();
            pqv.add(inBeer);
            sumM += inBeer.v;
            cLevel = inBeer.c;
            if (pqv.size() > N) {
                Beer outBeer = pqv.poll();
                sumM -= outBeer.v;
            }
            if (sumM >= M && pqv.size() == N) {
                minCLevel = cLevel;
                break;
            }
        }

        System.out.println(minCLevel);

    }
}
