import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_10972 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		nextPerm(arr, N);
		System.out.println(sb);
	}

	public static void nextPerm(int[] arr, int N) {
		int idx = -1;
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				idx = i;
			}
		}
		if (idx == -1) {
			sb.append(-1);
			return;
		}
		for (int i = N - 1; i > idx; i--) {
			if (arr[idx] < arr[i]) {
				swap(arr, idx, i);
				break;
			}
		}
		for (int i = idx + 1; i < (N + idx + 1) / 2; i++) {
			System.out.println(i);
			swap(arr, i, N - (i - idx));
		}
		for (int k : arr) {	
			sb.append(k).append(' ');
		}
	}

	public static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}
