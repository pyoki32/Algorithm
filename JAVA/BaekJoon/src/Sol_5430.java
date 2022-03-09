import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrStr = br.readLine();
            boolean rCheck = false;
            boolean errorCheck = false;
            Deque<Integer> dq = new LinkedList<>();
            if (n != 0) {
                arrStr = arrStr.substring(1, arrStr.length() - 1);
                String[] arr = arrStr.split(",");
                for (int i = 0; i < arr.length; i++) {
                    dq.add(Integer.parseInt(arr[i]));
                }
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) == 'R') {
                        if (rCheck) rCheck = false;
                        else rCheck = true;
                    } else {
                        if (!dq.isEmpty()) {
                            if (rCheck) {
                                dq.pollLast();
                            } else {
                                dq.pollFirst();
                            }
                        } else {
                            errorCheck = true;
                            break;
                        }
                    }
                }
                if (!errorCheck) {
                    if (!dq.isEmpty()) {
                        sb.append('[');
                        if (rCheck) {
                            while (!dq.isEmpty()) {
                                sb.append(dq.pollLast()).append(',');
                            }
                        } else {
                            while (!dq.isEmpty()) {
                                sb.append(dq.pollFirst()).append(',');
                            }
                        }
                        sb.deleteCharAt(sb.lastIndexOf(","));
                        sb.append(']').append('\n');
                    } else {
                        sb.append("[]\n");
                    }
                } else {
                    sb.append("error\n");
                }
            } else {
                for (int i = 0; i < p.length(); i++) {
                    if (p.charAt(i) == 'D') {
                        errorCheck = true;
                        break;
                    }
                }
                if (errorCheck) sb.append("error\n");
                else sb.append(arrStr).append('\n');
            }
        }
        System.out.println(sb);
    }
}
