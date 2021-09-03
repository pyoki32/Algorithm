import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Sol_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer x1, Integer x2) {
                return -(x1 - x2);
            }
        });
        PriorityQueue<Integer> right = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer x1, Integer x2) {
                return x1 - x2;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (i == 0) {
                sb.append(input).append('\n');
                left.add(input);
                continue;
            }

            if (input > left.peek()) {
                right.add(input);
            } else {
                left.add(input);
            }
            int interval = Math.abs(left.size()-right.size());
            if(interval>1) {
                if (left.size() > right.size()) {
                    right.add(left.poll());
                } else {
                    left.add(right.poll());
                }
            }

            int inputCnt = left.size() + right.size();
            if (inputCnt % 2 == 0) {
                int even = Math.min(left.peek(), right.peek());
                sb.append(even).append('\n');
            } else {
                if (left.size() > right.size()) sb.append(left.peek()).append('\n');
                else sb.append(right.peek()).append('\n');
            }
        }
        System.out.println(sb);
    }
}
