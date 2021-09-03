import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N =Integer.parseInt(st.nextToken());
        int C =Integer.parseInt(st.nextToken());
        int [] home = new int[N];
        for(int i =0 ;i< N;i++) {
            st = new StringTokenizer(br.readLine());
            home[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(home);
        int left = 1;
        int right = home[N-1]-home[0];
        int dist = 0;
        int answer =0;
        while(left<=right){
            int mid = (left+right)/2;
            int cnt =1;
            int start = home[0];
            for(int i=1;i<N;i++){
                dist = home[i]-start;
                if(mid <= dist){
                    cnt++;
                    start = home[i];
                }
            }
            if(cnt>=C){
                answer = mid;
                left = mid +1;
            }else{
                right = mid-1;
            }
        }
        System.out.println(answer);
    }
}
