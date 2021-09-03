import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_1916 {
    static class City {
        int num;
        int value;
        public City(int num, int value) {
            this.num = num;
            this.value = value;
        }
    }
    static int N, M;
    static ArrayList<ArrayList<City>> adjList;
    static int[] ans_values;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();
        ans_values = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
            ans_values[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adjList.get(start).add(new City(end, value));
        }
        st = new StringTokenizer(br.readLine());
        int answerStart = Integer.parseInt(st.nextToken());
        int answerEnd = Integer.parseInt(st.nextToken());
        dijkstra(answerStart);

        System.out.println(ans_values[answerEnd]);

    }

    static void dijkstra(int start) {

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        ans_values[start] = 0;

        PriorityQueue<City> pq = new PriorityQueue<>(new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return c1.value - c2.value;
            }
        });

        ArrayList<City> initValue = adjList.get(start);
        for (City c : initValue) {
            int num = c.num;
            int value = c.value;
            ans_values[num] = value;
            pq.add(new City(num, value));
        }

        while (!pq.isEmpty()) {
            City city = pq.poll();
            int num = city.num;
            int value = city.value;
            if (value<=ans_values[num]&& !visited[num]) {
                ans_values[num]=value;
                visited[num] = true;
                ArrayList<City> temp = adjList.get(num);
                for (City nextCity : temp) {
                    int next_num = nextCity.num;
                    int next_value = nextCity.value;
                    if (ans_values[next_num] > value + next_value) {
                        pq.add(new City(next_num, (value + next_value)));
                    }
                }
            }
        }
    }
}
