import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17281 {

    static int N;
    static int[][] player_record;
    static boolean[] visited;
    static boolean[] base, run;
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        player_record = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player_record[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[9];
        MAX = Integer.MIN_VALUE;
        int[] pick = new int[9];
        perm(pick, 0);
        System.out.println(MAX);
    }

    static void perm(int[] pick, int depth) {
        if (depth > 7) {
            int getPoint = playGame(pick);
            MAX = Math.max(MAX, getPoint);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (depth > 2) pick[depth + 1] = i;
                else pick[depth] = i;
                perm(pick, depth + 1);
                visited[i] = false;
            }
        }
    }

    static int playGame(int[] pick) {
        int score = 0;
        int outCnt = 0;
        int innings = 0;
        int batting_order = 0;
        while (innings < N) {
            base = new boolean[4];
            outCnt = 0;
            while (outCnt < 3) {
                run = new boolean[4];
                int point = player_record[innings][pick[batting_order]];
                batting_order++;
                if (batting_order == 9) batting_order = 0;
                if (point == 0) {
                    outCnt++;
                    continue;
                }
                base[0] = true;
                for (int i = 0; i < 4; i++) {
                    if (base[i]) {
                        int move = i + point;
                        if (move > 3) score++;
                        else {
                            run[move] = true;
                        }
                        base[i] = false;
                    }
                    if (run[i]) {
                        base[i] = true;
                    }
                }
            }
            innings++;
        }
        return score;
    }
}
