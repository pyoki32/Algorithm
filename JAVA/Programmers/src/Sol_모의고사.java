import java.util.ArrayList;

public class Sol_모의고사 {
	static int[][] person = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };

	public int[] solution(int[] answers) {

		int[] right = new int[3];
		for (int i = 0; i < answers.length; i++) {
			if (person[0][i % 5] == answers[i])
				right[0]++;
			if (person[1][i % 8] == answers[i])
				right[1]++;
			if (person[2][i % 10] == answers[i])
				right[2]++;
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++)
			max = Math.max(max, right[i]);
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < 3; i++)
			if (right[i] == max)
				res.add(i + 1);

		int[] answer = new int[res.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = res.get(i);

		return answer;
	}
}
