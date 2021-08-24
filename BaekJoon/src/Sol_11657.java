import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_11657 {
    static class Node {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static int N, M;
    static ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
    static int[] times;
    static boolean[] visited;
    static boolean cycleCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adjList.get(A).add(new Node(B, C));
        }
        times = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            times[i] = Integer.MAX_VALUE;
        }
        times[1] = 0;
        cycleCheck = false;
        visited[1] = true;
        solve(1, 0, 0);
        if (cycleCheck) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (times[i] == Integer.MAX_VALUE) sb.append(-1).append('\n');
                else sb.append(times[i]).append('\n');
            }
            System.out.println(sb);
        }
    }

    static void solve(int startNode, int totalTime, int moveCnt) {
        if (moveCnt > N) {
            return;
        }
        ArrayList<Node> nextNode = adjList.get(startNode);
        for (Node n : nextNode) {
            int to = n.to;
            int time = n.time;
            if (times[to] > time + totalTime) {
                times[to] = time + totalTime;
                if (visited[to]) {
                    cycleCheck = true;
                    return;
                }
                visited[to] = true;
                solve(to, totalTime + time, moveCnt + 1);
                visited[to] = false;
            }
        }
    }
}
