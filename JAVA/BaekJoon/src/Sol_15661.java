import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_15661 {
    static int[][] teamPoint;
    static int N;
    static int answer;
    static boolean []pickNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        teamPoint = new int[N][N];
        pickNum = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                teamPoint[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        pickTeam(0,0);
        System.out.println(answer);
    }

    static void pickTeam( int start, int depth){
        answer = Math.min(answer,getPoint());
        if(depth > N/2 -1){
            return;
        }
        for(int i = start; i < N;i++){
            pickNum[i] = true;
            pickTeam(i+1,depth+1);
            pickNum[i]= false;

        }
    }
    static int getPoint(){
        int A=0;
        int B=0;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
               if(pickNum[i]&&pickNum[j]){
                   A += teamPoint[i][j]+teamPoint[j][i];
               }else if(!pickNum[i] && !pickNum[j]){
                   B += teamPoint[i][j]+teamPoint[j][i];
                }
            }
        }

        return Math.abs(A-B);
    }
}
