import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        String KimDong_pattern = "(100+1+|01)+";
        for(int tc =0; tc<testCase;tc++){
            String str = br.readLine();
            if(str.matches(KimDong_pattern)) sb.append("YES\n");
            else  sb.append("NO\n");;
        }
        System.out.println(sb);
    }
}
