import java.util.Stack;

public class Sol_괄호_변환 {
	public static void main(String[] args) {
		String p = "()))((()";
		String answer = solution(p);
		System.out.println(answer);
	}
	static StringBuilder sb = new StringBuilder();
	public static String solution(String p) {
		String answer = "";
		if (rightStringCheck(p)) answer = p;
		else{
			makeRightString(p);
			answer = sb.toString();
		}
		return answer;
	}

	public static void makeRightString(String w) {

		if (w.equals("")) return;
		int left = 0;
		int right = 0;
		int splitIdx = 0;
		String u = "";
		String v = "";
		for (int i = 0; i < w.length(); i++) {
			if (w.charAt(i) == '(') left++;
			else right++;
			if (right == left) {
				splitIdx = i;
				u = w.substring(0, i + 1);
				break;
			}
		}
		if (splitIdx != w.length() - 1) v = w.substring(splitIdx + 1, w.length());
		if (rightStringCheck(u)) {
			sb.append(u);
			makeRightString(v);
		} else {
			sb.append('(');
			makeRightString(v);
			sb.append(')');
			String delFrontEnd = u.substring(1, u.length() - 1);
			for (int k = 0; k < delFrontEnd.length(); k++) {
				if (delFrontEnd.charAt(k) == '(') sb.append(')');
				else sb.append('(');
			}
		}
	}

	public static boolean rightStringCheck(String str) {
		boolean right = true;
		Stack<Character> left = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') left.push('(');
			else {
				if (left.isEmpty()) return false;
				else left.pop();
			}
		}
		return right;
	}
}
