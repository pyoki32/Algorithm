import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Sol_1208 {

    static int N, S, leftSum, rightSum;
    static int[] arr;
    static ArrayList<Integer> leftArr;
    static ArrayList<Integer> rightArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        rightSum = 0;
        leftSum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            rightSum += arr[i];
            if (i < N / 2) {
                leftSum += arr[i];
            }
        }
        rightSum -= leftSum;
        leftArr = new ArrayList<>();
        rightArr = new ArrayList<>();

        calSum(N / 2, (N / 2) / 2, 0, 0, N / 2, 0);
        calSum( N - N / 2, (N - (N / 2)) / 2, 0, N / 2, N, 0);
        Collections.sort(leftArr);
        Collections.sort(rightArr);
        long answer = calCnt();
        System.out.println(answer);
    }

    static void calSum(int range, int pickCnt, int depth, int start, int end, int sum) {

        if (depth > pickCnt) {
            return;
        }
        if (end == N / 2) {
            leftArr.add(sum);
            if (range % 2 != 0 || depth != range / 2) {
                leftArr.add(leftSum - sum);
            }
        } else {
            rightArr.add(sum);
            if (range % 2 != 0 || depth != range / 2) {
                rightArr.add(rightSum - sum);
            }
        }
        for (int i = start; i < end; i++) {
            calSum(range, pickCnt, depth + 1, i + 1, end, sum + arr[i]);
        }
    }

    static long calCnt() {
        long cnt = 0;
        int leftIdx = 0;
        int rightIdx = rightArr.size() - 1;

        while (leftIdx < leftArr.size() && rightIdx >= 0) {

            int sum = leftArr.get(leftIdx) + rightArr.get(rightIdx);

            if (sum == S) {
                long leftCnt = 0;
                long rightCnt = 0;
                int leftSum = leftArr.get(leftIdx);
                int rightSum = rightArr.get(rightIdx);
                for (int i = leftIdx; i < leftArr.size(); i++) {
                    if (leftSum == leftArr.get(i)) {
                        leftCnt++;
                        leftIdx++;
                    } else break;
                }
                for (int i = rightIdx; i >= 0; i--) {
                    if (rightSum == rightArr.get(i)) {
                        rightCnt++;
                        rightIdx--;
                    } else break;
                }
                cnt += leftCnt * rightCnt;
            } else if (sum < S) {
                leftIdx++;
            } else if (sum > S) {
                rightIdx--;
            }
        }
        if (S == 0) cnt--;
        return cnt;
    }
}
