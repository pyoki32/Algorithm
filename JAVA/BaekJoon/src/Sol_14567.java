import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_14567 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] inDegree;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		inDegree = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList.get(A).add(B);
			inDegree[B]++;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer x1, Integer x2) {
				return inDegree[x1] - inDegree[x2];
			}
		});
		
		int[] answer = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				pq.add(i);
				answer[i] = 1;
			}
		}

		while (!pq.isEmpty()) {
			int node = pq.poll();
			ArrayList<Integer> temp = adjList.get(node);
			for (int k : temp) {
				inDegree[k]--;
				answer[k] = Math.max(answer[k], answer[node] + 1);
				if(inDegree[k]==0)pq.add(k);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
	}
}
