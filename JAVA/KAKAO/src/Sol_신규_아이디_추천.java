
public class Sol_신규_아이디_추천 {

	public static void main(String[] args) {
		String answer = solution("...!@BaT#*..y.abcdefghijklm");
		System.out.println(answer);
	}

	public static String solution(String new_id) {
		String answer = "";

		String step1 = "";
		int gap = 'a' - 'A';
		for (int i = 0; i < new_id.length(); i++) {
			if ('A' <= new_id.charAt(i) && new_id.charAt(i) <= 'Z') {
				char lowerCase = (char) (new_id.charAt(i) + gap);

				step1 += lowerCase;
			} else {
				step1 += new_id.charAt(i);
			}
		}
		// System.out.println(step1);

		String step2 = "";
		for (int i = 0; i < step1.length(); i++) {
			if (('a' <= step1.charAt(i) && step1.charAt(i) <= 'z') || ('0' <= step1.charAt(i) && step1.charAt(i) <= '9')
					|| step1.charAt(i) == '-' || step1.charAt(i) == '_' || step1.charAt(i) == '.') {
				step2 += step1.charAt(i);
			}
		}
		// System.out.println(step2);

		String step3 = "";
		boolean pointCheck = false;
		for (int i = 0; i < step2.length(); i++) {
			if (step2.charAt(i) == '.') {
				if (!pointCheck) {
					pointCheck = true;
					step3 += step2.charAt(i);
				}
			} else {
				pointCheck = false;
				step3 += step2.charAt(i);
			}
		}
		// System.out.println(step3);

		String step4 = "";
		int frontIdx = 0;
		int backIdx = step3.length() - 1;
		for (int i = 0; i < step3.length(); i++) {
			if (step3.charAt(i) != '.') {
				frontIdx = i;
				break;
			}
		}
		String tempStr = step3.substring(frontIdx);
		for (int i = tempStr.length() - 1; i >= 0; i--) {
			if (tempStr.charAt(i) != '.') {
				backIdx = i + 1;
				break;
			}
		}
		step4 = tempStr.substring(0, backIdx);
		// System.out.println(step4);

		String step5 = "";
		if (step4.equals("")) {
			step5 = "a";
		} else {
			step5 = step4;
		}
		// System.out.println(step5);

		String step6 = "";
		tempStr = "";
		if (step5.length() >= 16) {
			tempStr = step5.substring(0, 15);
			backIdx = 14;
			for (int i = 14; i >= 0; i--) {
				if (tempStr.charAt(i) != '.') {
					backIdx = i + 1;
					break;
				}
			}
			step6 = tempStr.substring(0, backIdx);
		} else {
			step6 = step5;
		}
		// System.out.println(step6);

		String step7 = "";
		if (step6.length() <= 2) {
			while (step6.length() <= 2) {
				step6 += step6.charAt(step6.length() - 1);
			}
		}
		step7 = step6;
		// System.out.println(step7);

		answer = step7;
		return answer;
	}
}
