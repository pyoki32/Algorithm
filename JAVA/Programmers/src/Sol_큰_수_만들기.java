
public class Sol_큰_수_만들기 {

	public static void main(String[] args) {
		String number = "4177252841";
		int k = 4;
		String res = solution(number, k);
		System.out.println(res);
	}

	public static String solution(String number, int k) {
		String answer = "";

		int max = Integer.MIN_VALUE;
		int pick = number.length() - k;
		int start = 0;
		StringBuilder sb = new StringBuilder();
		while (pick > 0) {
			for (int i = start; i < number.length() - (pick - 1); i++) {
				if (max < number.charAt(i) - '0') {
					max = number.charAt(i) - '0';
					start = i;
				}
			}
			sb.append(number.charAt(start));
			max = Integer.MIN_VALUE;
			start++;
			pick--;

		}
		answer = sb.toString();
		return answer;
	}
}
