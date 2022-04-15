import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_1007 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[] visited;
    static ArrayList<Point> points;
    static double answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            points = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));

            }

            answer = Double.MAX_VALUE;
            visited = new boolean[N];
            pickPoints(0, 0);
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void pickPoints(int depth, int start) {
        if (depth > N / 2 - 1) {
            int vectorX = 0;
            int vectorY = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    vectorX += points.get(i).x;
                    vectorY += points.get(i).y;
                } else {
                    vectorX -= points.get(i).x;
                    vectorY -= points.get(i).y;
                }
            }
            double vectorSize = Math.pow(vectorX, 2) + Math.pow(vectorY, 2);
            vectorSize = Math.sqrt(vectorSize);
            answer = Math.min(answer, vectorSize);
            return;
        }
        for (int k = start; k < N; k++) {
            visited[k] = true;
            pickPoints(depth + 1, k + 1);
            visited[k] = false;
        }
    }
}
