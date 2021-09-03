
public class Sol_조이스틱_greedy {
	static int minMoveCnt;

	public static void main(String[] args) {

		String name = "JEROEN";
		char k = 'Z';
		System.out.println(solution(name));
	}

	public static int solution(String name) {
		int answer = 0;
		minMoveCnt = Integer.MAX_VALUE;
		boolean[] visited = new boolean[name.length()];
		StringBuilder initStr = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			initStr.append('A');
			if (initStr.charAt(i) == name.charAt(i))
				visited[i] = true;
		}

		for (int i = 0; i < name.length(); i++) {
			if (initStr.charAt(i) == name.charAt(i))
				visited[i] = true;
		}

		int alphaIdx_leftGap = 0;
		int alphaIdx_rightGap = 0;
		int cnt = 0;
		int visitCnt = 0;
		for (int i = 0; i < name.length(); i++) {
			if (!visited[i]) {
				visitCnt++;
				char target = name.charAt(i);
				alphaIdx_rightGap = target - 'A';
				alphaIdx_leftGap = 26 - (target - 'A');
				int alphaMin = Math.min(alphaIdx_rightGap, alphaIdx_leftGap);
				cnt += alphaMin;
			}
		}
		
		if (visited[0] == false)
			visitCnt--;
		visited[0] = true;
		calCnt(0, visited, 0, 0, visitCnt);
		answer = cnt + minMoveCnt;
		return answer;
	}

	static void calCnt(int startIdx, boolean[] visited, int cnt, int depth, int visitCnt) {

		if (visitCnt == depth) {
			minMoveCnt = Math.min(minMoveCnt, cnt);
			return;
		}

		int rightIdx = moveRight(startIdx, visited.length - 1, visited);
		if (rightIdx != -1) {
			int rightCnt = rightIdx - startIdx;
			visited[rightIdx] = true;
			calCnt(rightIdx, visited, cnt + rightCnt, depth + 1, visitCnt);
			visited[rightIdx] = false;
		}

		int leftIdx1 = moveLeft(startIdx, 0, visited);
		int leftIdx2 = moveLeft(visited.length - 1, startIdx + 1, visited);
		if (leftIdx1 != -1) {
			int leftCnt1 = startIdx - leftIdx1;
			visited[leftIdx1] = true;
			calCnt(leftIdx1, visited, cnt + leftCnt1, depth + 1, visitCnt);
			visited[leftIdx1] = false;
		}

		if (leftIdx1 == -1 && leftIdx2 != -1) {
			int leftCnt2 = startIdx + ((visited.length) - leftIdx2);
			visited[leftIdx2] = true;
			calCnt(leftIdx2, visited, cnt + leftCnt2, depth + 1, visitCnt);
			visited[leftIdx2] = false;
		}

	}

	static int moveLeft(int start, int end, boolean[] visited) {
		for (int i = start; i >= end; i--) {
			if (!visited[i])
				return i;
		}
		return -1;
	}

	static int moveRight(int start, int end, boolean[] visited) {

		for (int i = start; i <= end; i++) {
			if (!visited[i])
				return i;
		}
		return -1;
	}
}
