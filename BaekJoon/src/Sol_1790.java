import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1790 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int left = 1;
        int right = N;
        int answer = 0;
        int order =0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = cal_cnt(mid);
            if (cnt <= k) {
                answer = mid;
                order = cnt;
                left = mid+1;
            }
            else{
                 right = mid-1;
            }
        }
        if(cal_cnt(N)<k){
            System.out.println(-1);
        }
        else {
            if (order != k) {
                int gap = k - order;
                String ans_str = Integer.toString((answer + 1));
                System.out.println(ans_str.charAt(gap-1));
            } else {
                String ans_str = Integer.toString(answer);
                System.out.println(ans_str.charAt(ans_str.length() - 1));
            }
        }
    }
    static int cal_cnt(int mid){
        int cnt =0;
        String str_mid = Integer.toString(mid);
        for (int i = 0; i < str_mid.length()-1; i++) {
            cnt += 9 * Math.pow(10, i) * (i + 1);
        }
        cnt += str_mid.length() * (mid - (Math.pow(10, str_mid.length() - 1) - 1));
        return cnt;
    }
}
