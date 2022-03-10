import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sol_17219 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, String> hm = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] splitStr = str.split(" ");
            if (!hm.containsKey(splitStr[0])) {
                hm.put(splitStr[0], splitStr[1]);
            }
        }
        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            sb.append(hm.get(site)).append('\n');
        }
        System.out.println(sb);
    }
}
