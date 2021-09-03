import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_1593 {
	static int[] w_uppercase;
	static int[] w_lowercase;
	static ArrayList<Letter> wAl;

	static class Letter {
		char uLcase;
		int idx;
		int cnt;

		public Letter(char uLcase, int idx, int cnt) {
			this.uLcase = uLcase;
			this.idx = idx;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		String W = br.readLine();
		String S = br.readLine();

		w_uppercase = new int[26];
		w_lowercase = new int[26];
		for (int i = 0; i < g; i++) {
			char w = W.charAt(i);

			if ('A' <= w && w <= 'Z') {
				int w_idx = w - 'A';
				w_uppercase[w_idx]++;
			} else {
				int w_idx = w - 'a';
				w_lowercase[w_idx]++;
			}
		}

		wAl = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (w_uppercase[i] != 0) {
				wAl.add(new Letter('U', i, w_uppercase[i]));
			}
			if (w_lowercase[i] != 0) {
				wAl.add(new Letter('L', i, w_lowercase[i]));
			}
		}

		int[] s_uppercase = new int[26];
		int[] s_lowercase = new int[26];
		int answer = 0;
		// init
		for (int i = 0; i < g; i++) {
			char s_temp = S.charAt(i);
			if ('A' <= s_temp && s_temp <= 'Z') {
				int s_idx = s_temp - 'A';
				s_uppercase[s_idx]++;
			} else {
				int s_idx = s_temp - 'a';
				s_lowercase[s_idx]++;
			}
		}
		if (findMayaLetters(s_uppercase, s_lowercase))
			answer++;

		int start = 0;
		int end = g;
		for (int i = start; i < s - g; i++) {
			// 앞쪽 뺴기
			char s_front = S.charAt(i);
			if ('A' <= s_front && s_front <= 'Z') {
				int s_idx = s_front - 'A';

				s_uppercase[s_idx]--;
			} else {
				int s_idx = s_front - 'a';
				s_lowercase[s_idx]--;
			}
			// 뒤쪽 더하기
			char s_back = S.charAt(end);
			if ('A' <= s_back && s_back <= 'Z') {
				int s_idx = s_back - 'A';
				s_uppercase[s_idx]++;
			} else {
				int s_idx = s_back - 'a';
				s_lowercase[s_idx]++;
			}
			if (findMayaLetters(s_uppercase, s_lowercase))
				answer++;

			end++;
		}

		System.out.println(answer);

	}

	static boolean findMayaLetters(int[] s_uppercase, int[] s_lowercase) {
		boolean answerCheck = true;
		for (Letter l : wAl) {
			char uLcase = l.uLcase;
			int idx = l.idx;
			int cnt = l.cnt;
			if (uLcase == 'U') {
				if (s_uppercase[idx] != cnt) {
					return false;
				}
			} else {
				if (s_lowercase[idx] != cnt) {
					return false;
				}

			}
		}
		return answerCheck;
	}
}
