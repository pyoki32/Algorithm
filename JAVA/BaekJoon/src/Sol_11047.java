import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11047 {
        static int N,K;
        static int [] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            coins[i]= Integer.parseInt(st.nextToken());
        }
        int minCnt = 0;
        for(int i=N-1;i>=0;i--){
            if(K>=coins[i]){
                minCnt += K/coins[i];
                K %= coins[i];
            }
        }
        System.out.println(minCnt);
    }
}
