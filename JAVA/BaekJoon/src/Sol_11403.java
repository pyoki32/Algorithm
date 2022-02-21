import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11403 {
    static int N;
    static int[][] adjMatrix;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                initVisited();
                visitNode(i);
                if (visited[j]) sb.append(1).append(' ');
                else sb.append(0).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void visitNode(int start) {
        for (int i = 0; i < N; i++) {
            if (adjMatrix[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                visitNode(i);
            }
        }
    }

    static void initVisited() {
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }
    }
}
