import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	static int N, M;
	static ArrayList<ArrayList<Node>> adjList;
	static int[] mincost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>();
		mincost = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<>());
			mincost[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adjList.get(from).add(new Node(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkStra(start);

		System.out.println(mincost[end]);
	}

	static void dijkStra(int start) {
        boolean[] visited = new boolean[N + 1];
		mincost[start] = 0;
		visited[start] = true;

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});

		ArrayList<Node> startList = adjList.get(start);
		for (Node n : startList) {
			int to = n.to;
			int cost = n.cost;
			mincost[to] = cost;
			pq.add(new Node(to, cost));
		}

		while (!pq.isEmpty()) {

			Node n = pq.poll();
			int to = n.to;
			int cost = n.cost;

			if (cost <= mincost[to] && !visited[to]) {
                mincost[to]=cost;
				visited[to]=true;	
				ArrayList<Node> tempList = adjList.get(to);
				for (Node nextN : tempList) {
					int nto = nextN.to;
					int ncost = nextN.cost;
					if (mincost[nto] > cost + ncost) {
						mincost[nto] = cost + ncost;
						pq.add(new Node(nto, (cost + ncost)));

					}

				}

			}

		}

	}

}

