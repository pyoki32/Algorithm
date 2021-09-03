import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_3584 {
	static ArrayList<ArrayList<Integer>> tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			tree = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				tree.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				tree.get(B).add(A);
			}
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> aParents = new ArrayList<>();
			ArrayList<Integer> bParents = new ArrayList<>();
			aParents.add(a);
			bParents.add(b);
			findParentsNode(a, aParents);
			findParentsNode(b, bParents);
			System.out.println(aParents+"-------a");
			System.out.println(bParents+"-------b");
			int answer = solve(aParents, bParents);
			sb.append(answer).append('\n');

		}
		System.out.println(sb);
	}

	public static void findParentsNode(int start, ArrayList<Integer> roots) {

		ArrayList<Integer> tempList = tree.get(start);
		if (!tempList.isEmpty()) {
			int root = tempList.get(0);
			roots.add(root);
			findParentsNode(root, roots);
		}
	}

	public static int solve(ArrayList<Integer> aRoots, ArrayList<Integer> bRoots) {
		int answer = 0;
		for (int i = 0; i < aRoots.size(); i++) {
			for (int j = 0; j < bRoots.size(); j++) {
				if (aRoots.get(i) == bRoots.get(j)) {
					answer = aRoots.get(i);
					return answer;
				}
			}
		}
		return -1;
	}
}
