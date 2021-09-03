
public class Sol_문자열_압축 {

	public static void main(String[] args) {
		String s = "aabbaccc";
		int ans = solution(s);
		System.out.println(ans);
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= s.length() / 2; i++) {
			StringBuilder sb = new StringBuilder();
			String strSplit = s.substring(0, i);
			int len = strSplit.length();
			int cnt = 1;
			int endIdx = 0;
			String temp = strSplit;

			for (int j = i; j <= s.length() - len; j += len) {
				String nextStr = s.substring(j, j + len);
				if (temp.equals(nextStr)) {
					cnt++;
				} else {
					if (cnt == 1) sb.append(temp);
					else sb.append(cnt).append(temp);

					cnt = 1;
					temp = nextStr;

				}
				endIdx = j;
			}
			if (cnt != 1) {
				sb.append(cnt).append(temp);
				endIdx += len;
				if (endIdx <= s.length() - 1) {
					sb.append(s.substring(endIdx, s.length()));
				}
			} else sb.append(s.substring(endIdx, s.length()));

			answer = Math.min(answer, sb.length());

		}

		answer = Math.min(answer, s.length());
		return answer;
	}
}
