import java.util.ArrayList;
import java.util.Stack;

public class Sol_표_편집_효율성 {
	static class Node {
		int front;
		int back;

		public Node(int front, int back) {
			this.front = front;
			this.back = back;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		int k = 2;
		String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };
		System.out.print(solution(n, k, cmd));
	}

	public static String solution(int n, int k, String[] cmd) {

		boolean[] delCheck = new boolean[n];
		int selectedIdx = k;
		Stack<Integer> delIdx = new Stack<>();
		ArrayList<Node> node = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			node.add(new Node(i - 1, i + 1));
		}

		for (int i = 0; i < cmd.length; i++) {
			char cmdKey = cmd[i].charAt(0);
			String[] cmdStr = cmd[i].split(" ");
			switch (cmdKey) {
			case 'U':
				int up = Integer.parseInt(cmdStr[1]);
				while (up > 0) {
					int upIdx = node.get(selectedIdx).front;
					if (upIdx != -1) {
						selectedIdx = upIdx;
					}
					up--;
				}
				break;

			case 'D':
				int down = Integer.parseInt(cmdStr[1]);
				while (down > 0) {
					int downIdx = node.get(selectedIdx).back;
					if (downIdx != n) {
						selectedIdx = downIdx;
					}
					down--;
				}
				break;
			case 'C':
				delCheck[selectedIdx] = true;
				delIdx.push(selectedIdx);
				boolean downCheck = false;
				int upIdx = node.get(selectedIdx).front;
				int downIdx = node.get(selectedIdx).back;
				if (upIdx != -1)
					node.set(upIdx, new Node(node.get(upIdx).front, downIdx));
				if (downIdx != n) {
					node.set(downIdx, new Node(upIdx, node.get(downIdx).back));
					selectedIdx = downIdx;
					downCheck = true;
				}
				if (!downCheck) {
					if (upIdx != -1) {
						selectedIdx = upIdx;
					}
				}
				break;

			case 'Z':
				int zIdx = delIdx.pop();
				int frontIdx = node.get(zIdx).front;
				int backIdx = node.get(zIdx).back;
				delCheck[zIdx] = false;
				if (frontIdx != -1)
					node.set(frontIdx, new Node(node.get(frontIdx).front, zIdx));
				if (backIdx != n)
					node.set(backIdx, new Node(zIdx, node.get(backIdx).back));
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (!delCheck[i]) {
				sb.append('O');
			} else {
				sb.append('X');
			}
		}
		String answer = "";
		answer += sb;
		return answer;
	}
}
