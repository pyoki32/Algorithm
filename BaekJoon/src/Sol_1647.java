import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_1647 {

    static class Home {
        int num;
        int weight;

        public Home(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static int N, M;
    static ArrayList<ArrayList<Home>> adjList;
    static boolean[] visited;
    static int[] pickEdge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pickEdge = new int[N + 1];
        visited = new boolean[N + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
            pickEdge[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adjList.get(A).add(new Home(B, C));
        }
        prim(1);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(pickEdge[i], max);
            sum += pickEdge[i];
            System.out.println(pickEdge[i]);
        }
        sum -= max;
        System.out.println(sum);

    }

    static void prim(int startNode) {
        pickEdge[startNode] = 0;
        visited[startNode] = true;
        PriorityQueue<Home> pq = new PriorityQueue<>(new Comparator<Home>() {
            @Override
            public int compare(Home h1, Home h2) {
                return h1.weight - h2.weight;
            }
        });
        ArrayList<Home> tempList = adjList.get(startNode);
        for (Home h : tempList) {
            int num = h.num;
            int weight = h.weight;
            pickEdge[num] = weight;
            pq.add(new Home(num, weight));
        }
        while (!pq.isEmpty()) {
            Home h = pq.poll();
            int num = h.num;
            int weight = h.weight;
            if (!visited[num] && pickEdge[num] >= weight) {
                visited[num] = true;
                pickEdge[num] = weight;
                ArrayList<Home> nextList = adjList.get(num);
                for (Home nextH : nextList) {
                    int nextNum = nextH.num;
                    int nextWeight = nextH.weight;
                    pq.add(new Home(nextNum, nextWeight));
                }
            }
        }
    }
}
