import java.util.HashMap;

public class Sol_숫자_문자열과_영단어 {
	static HashMap<String, String> hm = new HashMap<>();
	public static void main(String[] args) {
		String s = "one4seveneight";
		int ans = solution(s);
		System.out.println(ans);
	}
	public static int solution(String s) {
		int answer = 0;
		hm.put("zero", "0");
		hm.put("one", "1");
		hm.put("two", "2");
		hm.put("three", "3");
		hm.put("four", "4");
		hm.put("five", "5");
		hm.put("six", "6");
		hm.put("seven", "7");
		hm.put("eight", "8");
		hm.put("nine", "9");

		String res = "";
		String word = "";
		for (int i = 0; i < s.length(); i++) {
			if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				res += s.charAt(i);
			} else {
				word += s.charAt(i);
				if (hm.containsKey(word)) {
					res += hm.get(word);
					word = "";
				}
			}
		}
		answer = Integer.parseInt(res);
		return answer;
	}
}
