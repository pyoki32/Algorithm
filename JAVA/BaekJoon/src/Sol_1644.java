import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_1644 {
    static int N;
    static ArrayList<Integer> primeNumberList;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        primeNumberList = new ArrayList<>();
        boolean[] primeNumberCheck = new boolean[N + 1];

        answer = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = i + i; j <= N; j += i) {
                primeNumberCheck[j] = true;
            }
        }
        for (int i = 2; i <= N; i++) {
            if (!primeNumberCheck[i]) {
                primeNumberList.add(i);
            }
        }

        for (int i = 0; i < primeNumberList.size(); i++) {
            dfs(i, primeNumberList.get(i));
        }

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {

        if (sum == N) {
            answer++;
            return;
        } else if (sum > N) {
            return;
        }
        if (idx + 1 < primeNumberList.size())
            dfs(idx + 1, sum + primeNumberList.get(idx + 1));

    }
}
