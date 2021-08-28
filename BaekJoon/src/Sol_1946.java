import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int [] newRecruits = new int [N+1];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int docPoint = Integer.parseInt(st.nextToken());
                int interviewPoint = Integer.parseInt(st.nextToken());
                newRecruits[docPoint]=interviewPoint;
            }
            int min = newRecruits[1];
            int count =0;
            for(int k=2;k<=N;k++) {
                if (min < newRecruits[k]) count++;
                else min = newRecruits[k];
            }
            sb.append(N-count).append('\n');
        }
        System.out.println(sb);
    }
}
