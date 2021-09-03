import java.util.Arrays;

class Sol_입국심사 {
  public static long solution(int n, int[] times) {
		long answer = 0;
		long []times_long = new long[times.length];
		for(int i=0;i<times.length;i++) {
			times_long[i]=times[i];
		}
		Arrays.sort(times_long);
		long MAX_TIME = times[times.length - 1];
		long value =n;
		long low = 0;
		long high = MAX_TIME * n;
		long mid = 0;

		while (low <= high) {
			mid = (low + high) / 2;
			long cnt = calCnt(mid,times_long);
			if (value <= cnt) {
				high = mid -1;
			} else {
				low = mid + 1;
			}
		}
		answer = low;
		return answer;
	}

	static long calCnt(long mid, long[] times_long) {
		long cnt = 0;
		for (int i = 0; i < times_long.length; i++) {
			cnt += mid / times_long[i];
		}
		return cnt;
	}
}
