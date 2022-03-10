import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_16928 {
    static  int N,M;
    static int [] board ;
    static int [] minCnt;
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int [101];
        minCnt = new int [101];
        for(int i =1;i<=100;i++){
            minCnt[i]=Integer.MAX_VALUE;
        }
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x]=y;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u]=v;
        }
        move(1,0);
        System.out.println(minCnt[100]);
    }
    static void move(int start, int cnt){

        if(start > 100) {
            return;
        }
        if(board[start] > 0 && minCnt[start] > cnt ){
            minCnt[start] = cnt;
            move(board[start],cnt);
        }else if(board[start] == 0 && minCnt[start] > cnt ){
            minCnt[start] = cnt;
            for(int i=1;i<=6;i++){
                if(start+i <= 100 ){
                    move(start+i,cnt+1);
                }
            }
        }

    }
}
