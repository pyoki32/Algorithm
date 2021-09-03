import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_2263 {
	static int n;
	static int[] inOrder;
	static int[] postOrder;
	static int[] inOrder_idx;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		inOrder = new int[n + 1];
		postOrder = new int[n + 1];
		inOrder_idx = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			inOrder[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			postOrder[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++)
			inOrder_idx[inOrder[i]] = i;
		solve(1,n,1,n);
		
		System.out.println(sb);

	}

	static void solve(int inOrder_start_idx, int inOrder_end_idx, int postOrder_start_idx, int postOrder_end_idx) {
		
		if(inOrder_start_idx>inOrder_end_idx || postOrder_start_idx> postOrder_end_idx) return;
		int rootNode = postOrder[postOrder_end_idx];
		sb.append(rootNode).append(' ');
		int root_idx = inOrder_idx[rootNode];
		int left_cnt = root_idx - inOrder_start_idx;
		
		//left
		solve(inOrder_start_idx,root_idx-1,postOrder_start_idx, postOrder_start_idx+left_cnt-1);
		//right
		solve(root_idx+1,inOrder_end_idx,postOrder_start_idx+left_cnt, postOrder_end_idx-1);
		
	}

}
