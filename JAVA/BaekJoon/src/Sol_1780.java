import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1780 {
    static int[][] maxtrix;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        answer = new int[3];
        maxtrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maxtrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(checkPaper(maxtrix[1][1],1,1,N,N)){
            int num =maxtrix[1][1];
            answer[num + 1]++;
        }else{
            countPaper(N, 1, 1);
        }
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    static void countPaper(int N, int startR, int startC) {
        if(N==1){
            int num = maxtrix[startR][startC];
            answer[num + 1]++;
            return;
        }
        int size = N / 3;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int nStartR = startR + size * i - size;
                int nStartC = startC + size * j - size;
                int nEndR = nStartR + size - 1;
                int nEndC = nStartC + size - 1;
                int num = maxtrix[nStartR][nStartC];
                if (checkPaper(num, nStartR, nStartC, nEndR, nEndC)) {
                    answer[num + 1]++;

                } else {
                    countPaper(size, nStartR, nStartC);
                }
            }
        }
    }

    static boolean checkPaper(int num, int startR, int startC, int endR, int endC) {
        if(startR  == endR && startC == endC) return false;
        for (int i = startR; i <= endR; i++) {
            for (int j = startC; j <= endC; j++) {
                if (maxtrix[i][j] != num) return false;
            }
        }
        return true;
    }

}
