import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2343 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int [] lesson = new int[N];
		st = new StringTokenizer(br.readLine());
		int left = Integer.MIN_VALUE;
		int right = 0;
		for(int i=0;i<N;i++) {
			lesson[i] = Integer.parseInt(st.nextToken());
			left = Math.max(lesson[i], left);
			right += lesson[i];
		}
		int answer =0;
		while(left<=right) {
			int mid = (left+right)/2;
			int cnt =0;
			int idx=0;
			int blueSize =0;
			for(int i=0;i<N;i++) {
				blueSize +=lesson[i];
				if(blueSize>mid) {
					cnt++;
					blueSize = lesson[i];
					idx=i;
				}
			}
			if(idx<N-1)cnt++;
			if(cnt<=M) {
				answer =mid;
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		System.out.println(answer);
	}
}
