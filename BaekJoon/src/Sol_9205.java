import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_9205 {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;

        }
    }

    static int n;
    static int homeR, homeC, storeR, storeC, rockR, rockC;
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<Point> points;
    static boolean[] visited;
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            adjList = new ArrayList<>();
            for (int i = 0; i <= n + 1; i++) {
                adjList.add(new ArrayList<>());
            }
            points = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            homeR = Integer.parseInt(st.nextToken());
            homeC = Integer.parseInt(st.nextToken());
            points.add(new Point(homeR, homeC));

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                storeR = Integer.parseInt(st.nextToken());
                storeC = Integer.parseInt(st.nextToken());
                points.add(new Point(storeR, storeC));
            }

            st = new StringTokenizer(br.readLine());
            rockR = Integer.parseInt(st.nextToken());
            rockC = Integer.parseInt(st.nextToken());
            points.add(new Point(rockR, rockC));
            connectPoint();
            visited = new boolean[n + 2];
            visited[0] = true;
            answer = false;
            dfs(0);
            if (answer) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.println(sb);
    }

    static void connectPoint() {

        for (int i = 0; i <= n + 1; i++) {
            for (int j = 0; j <= n + 1; j++) {
                if (i != j) {
                    int dist = Math.abs(points.get(i).row - points.get(j).row) + Math.abs(points.get(i).col - points.get(j).col);
                    if (dist <= 1000) {
                        adjList.get(j).add(i);
                    }
                }
            }
        }
    }

    static void dfs(int start) {

        if (start == n + 1) {
            answer = true;
            return;
        }
        ArrayList<Integer> nextPoint = adjList.get(start);
        for (int np : nextPoint) {
            if (!visited[np]) {
                visited[np] = true;
                dfs(np);
            }
        }
    }
}
